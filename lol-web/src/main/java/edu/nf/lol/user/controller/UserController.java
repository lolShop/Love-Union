package edu.nf.lol.user.controller;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import edu.nf.lol.BaseController;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserService;
import edu.nf.lol.vo.ResponseVO;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @author zhangch
 * @date 2020/3/12
 */
@RestController
@RequestMapping("/user/info")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController extends BaseController {

    @Autowired
    private UserService service;

    /**
     * 账号密码登录
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/login_password")
    public ResponseVO loginPassword(User user, HttpSession session){
        User u = service.userLogin(user);
        if(u == null){
            return fail(HttpStatus.SC_NOT_FOUND, "账号或密码错误，登录失败");
        }
        session.setAttribute("user", u);
        return success(u);
    }

    /**
     * 验证码登录
     * @param userPhone
     * @param code
     * @param session
     * @return
     */
    @PostMapping("/login_code")
    public ResponseVO loginCode(String userPhone, String code, HttpSession session){
        User user = service.checkUser(userPhone);
        String sessionCode = (String)session.getAttribute(userPhone);
        if(sessionCode == null || !sessionCode.equals(code)){
            session.removeAttribute(userPhone);
            return fail(HttpStatus.SC_NOT_FOUND, "验证码错误，请重新获得");
        }
        session.setAttribute("user", user);
        return success("登录成功");
    }

    /**
     * 获取验证码
     * @param userPhone
     * @param session
     * @return
     */
    @GetMapping("/get_code")
    public ResponseVO getCode(String userPhone, Boolean register, HttpSession session){
        User user = service.checkUser(userPhone);
        //如果为注册功能
        if(register){
            //验证账号是否存在
            if( user != null){
                return fail(HttpStatus.SC_NOT_FOUND, "该账号已经存在，获取验证码失败");
            }
        }else{
            //否则为登录
            //如果账号不存在
            if(user == null){
                return fail(HttpStatus.SC_NOT_FOUND, "账号不存在");
            }
        }
        int appid = AlinoteConfig.appid;
        String appkey = AlinoteConfig.appkey;
        int ran = (int) (Math.random()*(899999)+100000);
        String ranStr = Integer.toString(ran);
        //将手机号码和验证码放到session中
        session.setAttribute(userPhone, ranStr);
        String[] params = {ranStr,"10"};
        SmsSingleSender sender = new SmsSingleSender(appid, appkey);
        try {
            SmsSingleSenderResult result = sender.sendWithParam("86", userPhone,
                     AlinoteConfig.templateId, params, AlinoteConfig.smsSign, "", "");
            if(result.result == 0){
                return success("发送成功");
            }
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        }
        return fail(HttpStatus.SC_NOT_FOUND, "发送失败");
    }

    /**
     * 60秒后删除验证码
     * @return
     */
    @PostMapping("/remove_code")
    public void removeCode(String userPhone, HttpSession session){
        session.removeAttribute(userPhone);
    }

    /**
     * 用户注册
     * @param code
     * @param userPhone
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/user_register")
    public ResponseVO userRegister(String code, String userPhone, String password, HttpSession session){
        String pvCode = (String)session.getAttribute(userPhone);
        //输入的验证码错误，删除验证码，需要重新获取
        if(pvCode == null || !code.equals(pvCode)){
            session.removeAttribute(userPhone);
            return fail(HttpStatus.SC_NOT_FOUND, "验证码错误，请重新获取验证码");
        }
        User user = new User();
        user.setUserPhone(userPhone);
        user.setPassword(password);
        service.userRegister(user);
        return success("注册成功");
    }

    /**
     * 查询用户详细资料
     * @param session
     * @return
     */
    @GetMapping("/query_info")
    public ResponseVO queryInfo(HttpSession session){
        User user = (User)session.getAttribute("user");
        return success(user);
    }

    /**
     * 修改密码
     * @param oldPwd
     * @param newPwd
     * @param session
     * @return
     */
    @PostMapping("/update_password")
    public ResponseVO updatePassword(String oldPwd, String newPwd, HttpSession session){
        User user = (User)session.getAttribute("user");
        System.out.println(user);
        if(!oldPwd.equals(user.getPassword())){
            return success("原始密码错误，请重试！");
        }
        user.setPassword(newPwd);
        service.updateUserPwd(user);
        session.setAttribute("user", user);
        return success("修改成功！");
    }

    /**
     * 修改用户信息
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/update_info")
    public ResponseVO updateInfo(User user, HttpSession session){
        User u = (User)session.getAttribute("user");
        user.setUserId(u.getUserId());
        user.setBirthday(u.getBirthday());
        service.updateInfo(user);
        session.setAttribute("user", user);
        return success("修改成功");
    }
}

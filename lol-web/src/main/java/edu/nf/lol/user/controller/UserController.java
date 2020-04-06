package edu.nf.lol.user.controller;

import edu.nf.lol.BaseController;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserService;
import edu.nf.lol.vo.ResponseVO;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

    @PostMapping("/update_info")
    public ResponseVO updateInfo(User user, HttpSession session){
        User u = (User)session.getAttribute("user");
        user.setUserId(u.getUserId());
        user.setBirthday(u.getBirthday());
        service.updateInfo(user);
        session.setAttribute("user", user);
        return success("修改成功");
    }
    /**
     * zachery登陆
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/user_login")
    public ResponseVO userLogin( User user, HttpSession session){
        User u = service.findUser(user);
        if(u != null){
            session.setAttribute("user", u);
            return success("登录成功");
        }
        return fail(500,"登录失败,账号或密码错误");
    }

    /**
     * zachery注册
     * @param user
     * @return
     */
    @PostMapping("/user_register")
    public ResponseVO addUser( User user){
        User u = service.userRegisterCheck(user);
        if(u != null){
            return fail(HttpStatus.SC_INTERNAL_SERVER_ERROR,"账号已存在,请重新注册");
        }
            service.userRegister(user);
        return success ("注册成功!");
    }

    @GetMapping("/get_OnLine_user")
    public ResponseVO getOnLineUser(HttpSession session){
        User u = (User) session.getAttribute("user");
        return success(u);
    }

    @GetMapping("/user_Logout")
    public ResponseVO logOutUser(HttpSession session){
        session.removeAttribute("user");
        return success("login.html");
    }
}

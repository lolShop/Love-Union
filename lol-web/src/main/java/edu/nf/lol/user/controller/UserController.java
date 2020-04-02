package edu.nf.lol.user.controller;

import edu.nf.lol.BaseController;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserService;
import edu.nf.lol.vo.ResponseVO;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    @PostMapping("/user_login")
    public ResponseVO userLogin(User user, HttpSession session){
        User u = service.userLogin(user);
        if(u == null){
            return fail(HttpStatus.SC_NOT_FOUND, "账号或密码错误，登录失败");
        }
        session.setAttribute("user", u);
        return success(u);
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

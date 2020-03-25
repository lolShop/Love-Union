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
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController extends BaseController {

    @Autowired
    private UserService service;

    @PostMapping("/user_login/{user_phone}/{password}")
    public ResponseVO userLogin(@PathVariable("user_phone") String userPhone, @PathVariable("password") String password, HttpSession session){
        User user = new User();
        user.setUserPhone(userPhone);
        user.setPassword(password);
        User u = service.userLogin(user);
        if(u == null){
            return fail(HttpStatus.SC_NOT_FOUND, "账号或密码错误，登录失败");
        }
        session.setAttribute("userId", u.getUserId());
        return success(u);
    }
}

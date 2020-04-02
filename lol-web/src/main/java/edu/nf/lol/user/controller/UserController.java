package edu.nf.lol.user.controller;

import edu.nf.lol.BaseController;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserService;
import edu.nf.lol.vo.ResponseVO;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

    @PostMapping("/user_login")
    public ResponseVO userLogin(@Valid User user, HttpSession session){
        User u = service.findUser(user);
        session.setAttribute("OnLineUser", u);
        System.out.println("用户:"+u.getUserName()+"验证成功!并加入session作用域");
        return success("index.html");
    }

    @PostMapping("/user_register")
    public ResponseVO addUser(@Valid User user){
        User u = service.userRegisterCheck(user);
        if(u.getUserPhone().equals(user.getUserPhone())){
            return fail(HttpStatus.SC_INTERNAL_SERVER_ERROR,"账号已存在");
        }else{
            service.userRegister(user);
            return success ("login.html");
        }
    }

    @GetMapping("/get_OnLine_user")
    public ResponseVO getOnLineUser(HttpSession session){
        User u = (User) session.getAttribute("OnLineUser");
        return success(u);
    }

    @GetMapping("/user_Logout")
    public ResponseVO logOutUser(HttpSession session){
        session.removeAttribute("OnLineUser");
        return success("login.html");
    }
}

package edu.nf.lol.service.test.user;

import edu.nf.lol.user.entity.Address;
import edu.nf.lol.user.entity.Collect;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserAddressService;
import edu.nf.lol.user.service.UserCollectService;
import edu.nf.lol.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhangch
 * @date 2020/3/12
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testUserLogin(){
        User user = new User();
        user.setUserPhone("123456");
        user.setPassword("12345");
        User u = userService.userLogin(user);
        System.out.println(u);
    }

    @Test
    void testCheckUser(){
        User user = userService.checkUser("1");
        System.out.println(user);
    }

    @Test
    void testMath(){
        //生成6位数随机数
        int ran2 = (int) (Math.random()*(899999)+100000);
        String ran = Integer.toString(ran2);
        System.out.println(ran);
    }


}

package edu.nf.lol.service.test.user;

import edu.nf.lol.product.entity.Product;
import edu.nf.lol.user.entity.Address;
import edu.nf.lol.user.entity.Collect;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserAddressService;
import edu.nf.lol.user.service.UserCollectService;
import edu.nf.lol.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        user.setUserPhone("17688174618");
        user.setPassword("123456");
        User u = userService.findUser(user);
        System.out.println(u.getUserName());
    }
}

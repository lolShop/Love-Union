package edu.nf.lol.user.dao;

import edu.nf.lol.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zachery
 * @title: UserDaoTest
 * @projectName lol
 * @description: TODO
 * @date 2020/4/213:08
 */
class UserDaoTest {

    @Autowired
    private UserDao dao;
    @Test
    void getUserByPhone() {
        User user = new User();
        user.setUserPhone("17688174618");
        user.setPassword("123456");
        User u = dao.getUserByPhone(user);
        System.out.println(u.getUserName());
    }
}
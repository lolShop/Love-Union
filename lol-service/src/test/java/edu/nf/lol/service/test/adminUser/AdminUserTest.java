package edu.nf.lol.service.test.adminUser;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.user.service.AdminUserListService;
import edu.nf.lol.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class AdminUserTest {
    @Autowired
    private AdminUserListService adminUserListService;
    @Test
    public void testAdminUser(){
        PageInfo<User> pageInfo = adminUserListService.adminUserList(1, 5);
        for (User user : pageInfo.getList()){
            System.out.println(user.getUserName());

      }
        }
    }



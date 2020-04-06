package edu.nf.lol.service.test.user;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductSpecs;
import edu.nf.lol.user.entity.Collect;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserCollectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author zhangch
 * @date 2020/3/17
 */
@SpringBootTest
public class UserCollectTest {

    @Autowired
    private UserCollectService userCollectService;

    @Test
    void testAddCollect(){
        User user = new User();
        Product product = new Product();
        user.setUserId(1001);
        product.setProductId(1000);
        userCollectService.addCollect(user, product);
    }

    @Test
    void testQueryCollect(){
        User user = new User();
        user.setUserId(1000);
        PageInfo<Collect> list = userCollectService.queryCollect(user, 1, 8);
//        list.forEach(collect -> System.out.println(collect.getProduct().getProductMainImage()));
        System.out.println(list.getList());
    }

    @Test
    void testDeleteCollect(){
        Collect collect = new Collect();
        collect.setCollectId(1002);
        userCollectService.deleteCollect(collect);
    }
}

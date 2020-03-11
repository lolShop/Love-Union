package edu.nf.lol.service.test.product;

import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.service.AdminProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Crazy
 * @date 2020/3/10
 */
@SpringBootTest
public class AdminProductTest {
    @Autowired
    private AdminProductService adminProductService;
    @Test
    public void testProductDetail(){
        List<Product> list = adminProductService.adminProductAll();
        list.forEach(product -> {
            System.out.println(product.getProductName());
        });
    }
}
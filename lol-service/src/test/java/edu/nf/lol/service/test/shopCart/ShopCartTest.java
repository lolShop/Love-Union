package edu.nf.lol.service.test.shopCart;

import edu.nf.lol.shopCart.entity.ShopCart;
import edu.nf.lol.shopCart.service.ShopCartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

/**
 * @author Administrator
 * @date 2020/3/18
 */
@SpringBootTest
public class ShopCartTest {
    @Autowired
    private ShopCartService shopCartService;

    @Test
    public void testListShopCart() {
        List<ShopCart> list = shopCartService.listShopCart(1000);
        for (ShopCart sc : list) {
            System.out.println(
                    sc.getUser().getUserName() + "  " + sc.getProductSpecs().getProduct().getProductName() + "    " + sc.getProductSpecs().getProductSpecs() +
                            "  " + sc.getProductSpecs().getPromotionPrice() + "   " + sc.getProductSpecs().getSpecsPrice() + "    " + sc.getShopCount() + "   " +
                            sc.getProductSpecs().getSpecsPrice() + sc.getProductSpecs().getProduct().getProductMainImage()
            );
        }
    }

    @Test
    public void testListNum() {
        ShopCart sc = shopCartService.listNum(1000);
        System.out.println(sc.getCount() + "  " + sc.getMoney());
    }
}
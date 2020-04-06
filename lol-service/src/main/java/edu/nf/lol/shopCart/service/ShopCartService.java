package edu.nf.lol.shopCart.service;

import edu.nf.lol.shopCart.entity.ShopCart;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/3/18
 */
public interface ShopCartService {
    List<ShopCart> listShopCart(Integer uid);

    ShopCart listNum(Integer uid);

    void addCount(Integer shopId);

    void decreaseCount(Integer shopId);

    void delShopCart(Integer shopId);

    void select(Integer state,Integer shopId);

    void checkAll(Integer state, Integer uid);

    void settlement(Integer uid);

    ShopCart findShopCart(Integer specId);

    void addShopCart(ShopCart shopCart);
}

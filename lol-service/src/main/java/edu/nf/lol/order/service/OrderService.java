package edu.nf.lol.order.service;

import edu.nf.lol.order.entity.OrderInfo;
import edu.nf.lol.shopCart.entity.ShopCart;
import edu.nf.lol.user.entity.Address;

import java.util.List;

/**
 * @author yangTao
 * @date 2020/3/19
 */
public interface OrderService {

    /**
     * 获取用户地址列表
     * @param userId
     * @return
     */
    List<Address> getAddressByUserId(Integer userId);

    /**
     * 获取用户购物车
     * @param userId
     * @return
     */
    List<ShopCart> getUserShopCar(Integer userId);

    void placeOrder(OrderInfo orderInfo);

}
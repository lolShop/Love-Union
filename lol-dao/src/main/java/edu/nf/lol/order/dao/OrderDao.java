package edu.nf.lol.order.dao;

import edu.nf.lol.order.entity.OrderInfo;
import edu.nf.lol.shopCart.entity.ShopCart;
import edu.nf.lol.user.entity.Address;

import java.util.List;

/**
 * @author yangTao
 * @date 2020/3/19
 * 订单
 */
public interface OrderDao {

    /**
     *  根据用户id获取收货地址
     * @param userId 用户id
     * @return
     */
    List<Address> getAddressByUserId(Integer userId);

    /**
     * 获取用户的购物车
     * @param userId
     * @return
     */
    List<ShopCart> getUserShopCar(Integer userId);

    /**
     * 下单
     * @param orderInfo
     */
    void placeOrder(OrderInfo orderInfo);

}
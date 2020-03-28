package edu.nf.lol.order.service.impl;


import edu.nf.lol.exception.LolException;
import edu.nf.lol.order.dao.OrderDao;
import edu.nf.lol.order.entity.OrderInfo;
import edu.nf.lol.order.service.OrderService;
import edu.nf.lol.shopCart.entity.ShopCart;
import edu.nf.lol.user.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yangTao
 * @date 2020/3/19
 */
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Address> getAddressByUserId(Integer userId) {
        try {
            List<Address> addressByUserId = orderDao.getAddressByUserId(userId);
            return addressByUserId;
        }catch(RuntimeException e){
            throw new LolException("收货地址获取失败");
        }
    }

    @Override
    public List<ShopCart> getUserShopCar(Integer userId) {

        try {
            List<ShopCart> shopCarts = orderDao.getUserShopCar(userId);
            return shopCarts;
        }catch(RuntimeException e){
            throw new LolException("获取用户购物车失败");
        }

    }

    @Override
    public void placeOrder(OrderInfo orderInfo) {
        orderDao.placeOrder(orderInfo);
    }


}
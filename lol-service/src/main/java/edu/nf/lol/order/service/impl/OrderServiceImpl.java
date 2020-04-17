package edu.nf.lol.order.service.impl;


import edu.nf.lol.exception.LolException;
import edu.nf.lol.order.dao.OrderDao;
import edu.nf.lol.order.entity.OrderDetails;
import edu.nf.lol.order.entity.OrderInfo;
import edu.nf.lol.order.service.OrderService;
import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductSpecs;
import edu.nf.lol.shopCart.entity.ShopCart;
import edu.nf.lol.user.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
        OrderInfo orderInfo1 = new OrderInfo();
        orderInfo1.setOrderId(orderInfo.getOrderId());
        System.out.println(orderInfo.getOrderId());
        for (ShopCart shopCart : orderDao.getUserShopCar(orderInfo.getUser().getUserId())) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrderInfo(orderInfo1);
            orderDetails.setProductCount(shopCart.getShopCount());
            ProductSpecs productSpecs = new ProductSpecs();
            productSpecs.setSpecsId(shopCart.getProductSpecs().getSpecsId());
            orderDetails.setProductSpecs(productSpecs);
            Product product = new Product();
            product.setProductId(shopCart.getProductSpecs().getProduct().getProductId());
//            orderDetails.setProduct(product);
            orderDao.addOrderDetails(orderDetails);
        }
        orderDao.emptyCart();
    }

    @Override
    public void updateOrderStaticOne(OrderInfo orderInfo) {
        orderDao.updateOrderStaticOne(orderInfo);
    }

    @Override
    public BigDecimal getOrderPrice(String orderId) {
        return orderDao.getOrderPrice(orderId);
    }


}
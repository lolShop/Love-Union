package edu.nf.lol.service.test.order;

import edu.nf.lol.order.dao.OrderDao;
import edu.nf.lol.order.entity.OrderDetails;
import edu.nf.lol.order.entity.OrderInfo;
import edu.nf.lol.order.entity.OrderStatic;
import edu.nf.lol.order.service.OrderService;
import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductSpecs;
import edu.nf.lol.shopCart.entity.ShopCart;
import edu.nf.lol.user.entity.Address;
import edu.nf.lol.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * @author yangTao
 * @date 2020/3/19
 */
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService ;

    @Autowired
    private OrderDao orderDao;

    @Test
    public void testGetAddress(){
        orderService.getUserShopCar(1001).forEach(Address ->{
            System.out.println(Address.toString());
        });
    }

    @Test
    public void testPlaceOrder(){
        OrderInfo orderInfo = new OrderInfo();
        OrderStatic orderStatic = new OrderStatic();
        orderStatic.setStaticId(1000);
        User user = new User();
        user.setUserId(1001);
        Address address = new Address();
        address.setAddressId(1000);
        orderInfo.setOrderId("555");
        orderInfo.setOrderStatic(orderStatic);
        orderInfo.setUser(user);
        orderInfo.setAddress(address);
        orderInfo.setPreferentialAmount(new BigDecimal(10));
        orderInfo.setPaymentMoney(new BigDecimal(100));
        orderInfo.setOrderRemarks("罗大头是个憨憨");
        orderService.placeOrder(orderInfo);
    }

    @Test
    public void getPrice(){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId("555");
        User user = new User();
        user.setUserId(1001);
        orderInfo.setUser(user);
        for (ShopCart shopCart : orderDao.getUserShopCar(orderInfo.getUser().getUserId())) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrderInfo(orderInfo);
            orderDetails.setGoodNumber(shopCart.getShopCount());
            ProductSpecs productSpecs = new ProductSpecs();
            productSpecs.setSpecsId(shopCart.getProductSpecs().getSpecsId());
            orderDetails.setProductSpecs(productSpecs);
            Product product = new Product();
            product.setProductId(shopCart.getProductSpecs().getProduct().getProductId());
            orderDetails.setProduct(product);
            orderDao.addOrderDetails(orderDetails);
        }
    }
}
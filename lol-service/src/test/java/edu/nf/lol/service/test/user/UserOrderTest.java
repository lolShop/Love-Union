package edu.nf.lol.service.test.user;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.order.entity.OrderDetails;
import edu.nf.lol.order.entity.OrderInfo;
import edu.nf.lol.order.entity.OrderStatic;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserOrderService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author zhangch
 * @date 2020/3/18
 */
@SpringBootTest
public class UserOrderTest {

    @Autowired
    private UserOrderService userOrderService;

    @Test
    void testQueryAllOrder(){
        User user = new User();
        user.setUserId(1000);
        PageInfo<OrderInfo> pageInfo = userOrderService.queryUsersOrder(user, 1, 2);
        List<OrderInfo> orders = pageInfo.getList();
        System.out.println(orders.get(0).getOrderId());
        System.out.println(orders.get(0).getDetails().size());
    }

    @Test
    void testOrderStatus(){
        OrderInfo info = new OrderInfo();
        OrderStatic orderStatic = new OrderStatic();
        orderStatic.setStaticId(1000);
        info.setOrderStatic(orderStatic);
        info.setOrderId("LOL-20200317171025847-841");
        userOrderService.updateOrderStatus(info);
    }

    @Test
    void testDeleteOrder(){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId("CF-20200304224555352-801");
        userOrderService.deleteOrder(orderInfo);
    }

}

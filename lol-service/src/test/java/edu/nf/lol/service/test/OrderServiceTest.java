package edu.nf.lol.service.test;

import edu.nf.lol.order.AdminService.AdminOrderService;
import edu.nf.lol.order.entity.OrderInfo;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
//@SpringBootApplication(scanBasePackages =  "edu.nf.lol.order.AdminService")
//@MapperScan("edu.nf.lol.dao")
public class OrderServiceTest {
    @Autowired
    private AdminOrderService service;
//    @Test
////    public void orderServiceTest() {
////        PageInfo<OrderInfo>pageInfo=service.pageOrder(1,2);
////        pageInfo.getList().forEach(orderInfo -> {
////            System.out.println(orderInfo.getOrderId());
////        });
////    }
    @Test
    public void orderServiceTest2() {
        List<OrderInfo> list=service.pageOrder2();
        list.forEach(orderInfo -> {
            System.out.println(orderInfo.getOrderId());
            System.out.println(orderInfo.getDeliveryTime());
            System.out.println(orderInfo.getPaymentMoney());
        });

    }

    @Test
    public void testCheck(){
        for (OrderInfo info : service.chanxun(5, 5, "待发货").getList()) {
            System.out.println(info.toString());
        }
    }

}

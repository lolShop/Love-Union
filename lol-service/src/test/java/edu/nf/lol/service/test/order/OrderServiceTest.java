package edu.nf.lol.service.test.order;


import edu.nf.lol.order.service.OrderService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yangTao
 * @date 2020/3/19
 */
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService ;

    @Test
    public void testGetAddress(){
        orderService.getUserShopCar(1001).forEach(Address ->{
            System.out.println(Address.toString());
        });
    }

}
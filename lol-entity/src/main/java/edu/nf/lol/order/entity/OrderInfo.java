package edu.nf.lol.order.entity;

import edu.nf.lol.user.entity.Address;
import edu.nf.lol.user.entity.User;
import lombok.Data;

import java.lang.ref.PhantomReference;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author yangTao
 * @date 2020/3/9
 * 订单信息表
 */
@Data
public class OrderInfo {
    private String orderId;
    private BigDecimal preferentialAmount;
    private BigDecimal paymentMoney;
    private Date orderTime;
    private Date paymentTime;
    private Date deliveryTime;
    private Date receivingTime;
     private  OrderStatic orderStatic;
     private User user;
     private Address address;
}
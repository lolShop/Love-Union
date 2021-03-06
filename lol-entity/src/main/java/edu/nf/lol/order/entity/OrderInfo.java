package edu.nf.lol.order.entity;

import edu.nf.lol.user.entity.Address;
import edu.nf.lol.user.entity.User;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


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
     private Integer orderStatic;
     private User user;
     private Address address;
     private String orderRemarks;
    private List<OrderDetails> details;
}
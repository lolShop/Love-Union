package edu.nf.lol.order.entity;

import edu.nf.lol.user.entity.User;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yangTao
 * @date 2020/3/9
 * 退款信息表
 */
@Data
public class RefundApply {
    private Integer refundId;
    private String refundExplain;
    private String voucherImage;
    private String orderId;
    private Integer reasonSum;
    private User user;
    private RefundApply refundApply;
    private BigDecimal reasonPrice;
    private RefundStatic refundStatic;
    private OrderDetails orderDetails;
    private  RefundReason refundReason;
    private  OrderInfo orderInfo;
}
package edu.nf.lol.order.entity;

import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductSpecs;
import lombok.Data;

/**
 * @author yangTao
 * @date 2020/3/9
 * 订单详情
 */
@Data
public class OrderDetails {
    private Integer detailsId;
    private Integer goodNumber;
    private  OrderInfo orderInfo;
    private ProductSpecs productSpecs;
    private Product product;
}
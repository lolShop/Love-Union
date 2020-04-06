package edu.nf.lol.shopCart.entity;

import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductSpecs;
import edu.nf.lol.user.entity.User;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Administrator
 * @date 2020/3/9
 */
@Data
public class ShopCart {
    private Integer shopId;
    private Integer shopUid;
    private Integer specsProductId;
    private Integer shopCount;
    private Integer shopState;
    private ProductSpecs productSpecs;
    private User user;
    private Integer count;
    private BigDecimal money;
}
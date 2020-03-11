package edu.nf.lol.shopCart.entity;

import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductSpecs;
import lombok.Data;

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
    private Boolean shopState;
    private Product product;
    private ProductSpecs productSpecs;
}
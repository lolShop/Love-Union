package edu.nf.lol.product.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Crazy 这是商品类的实体
 * @date 2020/3/5
 */

@Data
public class Product {
    private  Integer   productId;
    private String    productName;
    private Date productCreateTime;
    private Date  productUpdateTime;
    private String  productMainImage;
    private String  productParticular;
    private  Integer   productWeight;
    private String productAttribute;
    private  Integer  productState	;
    private  ProductType productType;
    private List<ProductSpecs> productSpecsList = new ArrayList<>();
}
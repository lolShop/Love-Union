package edu.nf.lol.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


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
    @JsonFormat( pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date productCreateTime;
    @JsonFormat( pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date  productUpdateTime;
    private String  productMainImage;
    private String  productParticular;
    private  Integer   productWeight;
    private String productAttribute;
    private  Integer  productState;
    private  Integer productTypeId;
    private  ProductType productType;
    private List<ProductSpecs> productSpecsList;
    private  List<ProductImage> productImageList;

}
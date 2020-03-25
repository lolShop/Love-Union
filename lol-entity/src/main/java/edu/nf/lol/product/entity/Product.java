package edu.nf.lol.product.entity;

import lombok.Data;

import java.util.Date;

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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getProductCreateTime() {
        return productCreateTime;
    }

    public void setProductCreateTime(Date productCreateTime) {
        this.productCreateTime = productCreateTime;
    }

    public Date getProductUpdateTime() {
        return productUpdateTime;
    }

    public void setProductUpdateTime(Date productUpdateTime) {
        this.productUpdateTime = productUpdateTime;
    }

    public String getProductMainImage() {
        return productMainImage;
    }

    public void setProductMainImage(String productMainImage) {
        this.productMainImage = productMainImage;
    }

    public String getProductParticular() {
        return productParticular;
    }

    public void setProductParticular(String productParticular) {
        this.productParticular = productParticular;
    }

    public Integer getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(Integer productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductAttribute() {
        return productAttribute;
    }

    public void setProductAttribute(String productAttribute) {
        this.productAttribute = productAttribute;
    }

    public Integer getProductState() {
        return productState;
    }

    public void setProductState(Integer productState) {
        this.productState = productState;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
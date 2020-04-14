package edu.nf.lol.product.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 这是商品型表
 * @author Crazy
 * @date 2020/3/9
 */
@Data
public class ProductType implements Serializable {
    private   Integer     productTypeId;
    private   Integer     parentId ;
    private   String      productTypeName;
    private   Date        createTime;
    private   Date        updateTime;
    private   Integer      productState;
    private List<ProductType> productTypeList;
}
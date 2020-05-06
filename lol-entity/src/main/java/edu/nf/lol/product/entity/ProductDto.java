package edu.nf.lol.product.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author Crazy 这是商品类的实体
 * @date 2020/3/5
 */

@Data
@Document(indexName ="product")
public class ProductDto {
    @Id
    private  Integer   productId;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String    productName;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String    productTypeName;
    private String  productMainImage;
    private BigDecimal specsPrice;
    private BigDecimal promotionPrice;
    private  Date productCreateTime;
}
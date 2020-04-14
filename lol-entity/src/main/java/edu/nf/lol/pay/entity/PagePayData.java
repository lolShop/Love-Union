package edu.nf.lol.pay.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yangTao
 * @date 2020/4/2
 */
@Data
public class PagePayData {
    private String orderId;
    private String orderName;
    private BigDecimal price;
    private String body;
}
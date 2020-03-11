package edu.nf.lol.user.entity;

import edu.nf.lol.product.entity.Product;
import lombok.Data;

import java.util.Date;

/**
 * @author zhangch
 * @date 2020/3/9
 * 浏览足迹表
 */
@Data
public class Browse {
    private Integer footId;
    private Date footDate;
    private User user;
    private Product product;
}

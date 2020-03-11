package edu.nf.lol.user.entity;

import edu.nf.lol.product.entity.Product;
import lombok.Data;

/**
 * @author zhangch
 * @date 2020/3/8
 */
@Data
public class Collect {

    private Integer collectId;
    private User user;
    private Product product;
}

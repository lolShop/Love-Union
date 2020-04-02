package edu.nf.lol.product.service;

import edu.nf.lol.product.entity.Product;


import java.util.List;

/**
 * @author Crazy
 * @date 2020/4/1
 */
public interface ProductIndexService {
    List<Product> productRecommend(Integer state);
    List<Product> productAll();
}
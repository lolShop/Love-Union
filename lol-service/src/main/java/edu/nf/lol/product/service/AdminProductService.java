package edu.nf.lol.product.service;

import edu.nf.lol.product.entity.Product;

import java.util.List;

/**
 * @author Crazy
 * @date 2020/3/10
 */
public interface AdminProductService {
    List<Product> adminProductAll();
}
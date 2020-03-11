package edu.nf.lol.product.dao;

import edu.nf.lol.product.entity.Product;

import java.util.List;

/**
 * @author Crazy
 * @date 2020/3/10
 */
public interface AdminProductDao {
    /**
     * 在后端关于商品管理的模块dao
     * @param
     * @return
     */
   List<Product>  adminProductAll();
}
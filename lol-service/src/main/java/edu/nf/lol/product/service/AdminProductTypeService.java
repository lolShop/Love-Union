package edu.nf.lol.product.service;

import edu.nf.lol.product.entity.ProductType;

import java.util.List;

/**
 * @author zachery
 * @title: AdminProductTypeService
 * @projectName lol
 * @description: TODO
 * @date 2020/3/2418:43
 */
public interface AdminProductTypeService {

    /**
     * 商品分类查询
     * @param parentId
     * @return
     */
    List<ProductType> adminProductTypeAll(Integer parentId);
}

package edu.nf.lol.product.service;

import edu.nf.lol.product.entity.ProductType;

import java.util.List;

/**
 * @author Crazy
 * @date 2020/3/27
 */
public interface AdminProductTypeService {
    /**
     * 这是后台的商品分级别显示 用递归方法实现
     */
       List<ProductType>   listProductTypeParentId(Integer parentId);

    /**
     * 商品分类查询
     * @param parentId
     * @return
     */
    List<ProductType> adminProductTypeAll(Integer parentId);

    List<ProductType>   listProductTypeId(Integer parentId);
}
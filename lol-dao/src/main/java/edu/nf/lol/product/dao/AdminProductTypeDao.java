package edu.nf.lol.product.dao;

import edu.nf.lol.product.entity.ProductType;

import java.util.List;
import java.util.Map;

/**
 * @author Crazy
 * @date 2020/3/27
 */
public interface AdminProductTypeDao {
    /**
     * 这是后台的商品分级别显示 用递归方法实现
     */
       List<ProductType>   listProductTypeParentId(Integer parentId );
       List<ProductType>   listProductTypeAll();
    /**
     * 商品分类查询
     * @param parentId
     * @return
     */
    List<ProductType> adminProductTypeAll(Integer parentId);
    /**
     * 根据根据商品类型查父类类型id也是 递归方法实现
     */
    List<ProductType>   listProductTypeId(Integer Id );
}
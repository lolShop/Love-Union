package edu.nf.lol.product.dao;

import edu.nf.lol.product.entity.ProductType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zachery
 * @title: AdminProductTypeDao
 * @projectName lol
 * @description: TODO
 * @date 2020/3/2417:32
 * 后台商品分类
 */
@Repository
public interface AdminProductTypeDao {
    /**
     * 商品分类查询
     * @param parentId
     * @return
     */
    List<ProductType> adminProductTypeAll(Integer parentId);

}

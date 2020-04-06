package edu.nf.lol.product.dao;

import edu.nf.lol.product.entity.Product;
import org.apache.ibatis.annotations.Param;

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
    /**
     * 这是根据商品Id查询,单个商品的信息,在后台模态框中显示
     * @param product
     * @return
     */
    Product adminProductDetail(Product product);

    /**
     * 商品列表分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Product> adminProductAll(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    /**
     *增
     * @param product
     */
    void adminAddProduct(Product product);

    /**
     * 删
     * @param product
     */
    void adminDelProduct(Product product);

    /**
     * 改
     * @param product
     */
    void adminUpdateProduct(Product product);
}
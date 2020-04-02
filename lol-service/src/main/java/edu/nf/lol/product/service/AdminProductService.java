package edu.nf.lol.product.service;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.product.entity.Product;

/**
 * @author Crazy
 * @date 2020/3/10
 */
public interface AdminProductService {

    /**
     * 根据id查询商品详细信息
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
    PageInfo<Product> adminProductAll(Integer pageNum, Integer pageSize);

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
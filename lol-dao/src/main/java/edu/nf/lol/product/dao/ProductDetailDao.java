package edu.nf.lol.product.dao;

import edu.nf.lol.product.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Crazy  这是遍历商品详细页面的Dao
 * @date 2020/3/5
 */

public interface ProductDetailDao {
    /**
     * 这是根据商品Id查询,单个商品的信息,在商品详细页显示
     * @param product
     * @return
     */
      Product productDetail(Product product);

}
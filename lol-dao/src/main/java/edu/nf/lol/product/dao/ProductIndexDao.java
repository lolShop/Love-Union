package edu.nf.lol.product.dao;

import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Crazy
 * @date 2020/4/1
 */
public interface ProductIndexDao {
    /**
     * 根据商品的状态来查询商品 为推荐商品
     *
     */
    List<Product> productAll();
    List<ProductType>  listProductType(Integer parentId );
}
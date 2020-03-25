package edu.nf.lol.product.dao;

import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductSpecs;

import java.util.List;

/**
 * @author Crazy
 * @date 2020/3/18
 */
public interface ProductSpecsDao {
    /**
     * 这是查询商品规格表信息的字段
     */
    List<ProductSpecs>  listProductSpecs(Integer productId);

    /**
     * 这是根据商品ID 和SKU 详细规格来寻找
     * @return
     */
    ProductSpecs productSpecsProductId(ProductSpecs productSpecs);
}
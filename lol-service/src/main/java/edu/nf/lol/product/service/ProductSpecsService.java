package edu.nf.lol.product.service;

import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductSpecs;

import java.util.List;

/**
 * @author Crazy
 * @date 2020/3/18
 */
public interface ProductSpecsService {
    List<ProductSpecs> listProductSpecs(Integer productId);
    ProductSpecs productSpecsProductId(Product product, String specs);
}
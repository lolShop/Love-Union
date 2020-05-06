package edu.nf.lol.product.service.impl;

import edu.nf.lol.product.entity.ProductType;
import edu.nf.lol.product.service.ProductDetailService;
import edu.nf.lol.product.dao.ProductDetailDao;
import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.service.ProductIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Crazy
 * @date 2020/3/5
 */
@Service("productDetailService")
@Transactional
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductDetailDao productDetailDao;
    @Autowired
    private ProductIndexService productIndexService;
    @Override
    public Product productDetail(Product product) {
        Product product1=productDetailDao.productDetail(product);
        ProductType type= new ProductType();
        type.setProductTypeName(productIndexService.getTypeName(product1.getProductTypeId()));
        product1.setProductType(type);
        return product1;
    }
}
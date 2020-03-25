package edu.nf.lol.product.service.impl;

import edu.nf.lol.exception.LolException;
import edu.nf.lol.product.dao.ProductSpecsDao;
import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductSpecs;

import edu.nf.lol.product.service.ProductSpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @author Crazy
 * @date 2020/3/18
 */
@Service("productSpecsService")
@Transactional
public class ProductSpecsServiceImpl implements ProductSpecsService {
    @Autowired
    private ProductSpecsDao productSpecsDao;
    @Override
    public List<ProductSpecs> listProductSpecs(Integer productId) {
        try {
            List<ProductSpecs> list=productSpecsDao.listProductSpecs(productId);
            return  list;
        }catch (RuntimeException e){
            throw  new LolException("查询失败,请稍后重试");
        }
    }

    @Override
    public ProductSpecs productSpecsProductId(Product product,String specs) {
        ProductSpecs productSpecs= new ProductSpecs();
        productSpecs.setProduct(product);
        String specsValue="{"+specs.substring(0,specs.lastIndexOf(","))+"}";
        productSpecs.setProductSpecs(specsValue);
        try {
            ProductSpecs productSpecs1=productSpecsDao.productSpecsProductId(productSpecs);
            return  productSpecs1;
        }catch (RuntimeException e){
            throw  new LolException("查询失败,请稍后重试");
        }
    }

}
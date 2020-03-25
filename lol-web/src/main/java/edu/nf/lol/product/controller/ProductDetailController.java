package edu.nf.lol.product.controller;

import edu.nf.lol.BaseController;
import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductSpecs;
import edu.nf.lol.product.service.ProductDetailService;
import edu.nf.lol.product.service.ProductSpecsService;
import edu.nf.lol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Crazy
 * @date 2020/3/5
 */
@RestController
@RequestMapping("product")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ProductDetailController extends BaseController {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductSpecsService productSpecsService;

    /**
     * 在商品详细页面显示商品信息
     * @param product
     * @return
     */
    @GetMapping("/detail")
    public ResponseVO productDetail(Product product){
        Product product1 = productDetailService.productDetail(product);
        List<ProductSpecs> list=productSpecsService.listProductSpecs(product.getProductId());
        product1.setProductSpecsList(list);
        ResponseVO vo=success(product1);
        return  vo;
    }
    @GetMapping("/specs")
    public ResponseVO productDetail(Integer productId,String specs){
        Product product= new Product();
        product.setProductId(productId);
        ProductSpecs productSpecs=productSpecsService.productSpecsProductId(product,specs);
        ResponseVO vo=success(productSpecs);
        return  vo;
    }
}
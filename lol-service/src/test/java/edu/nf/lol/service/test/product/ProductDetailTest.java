package edu.nf.lol.service.test.product;





import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductImage;
import edu.nf.lol.product.entity.ProductSpecs;
import edu.nf.lol.product.entity.ProductType;
import edu.nf.lol.product.service.ProductDetailService;


import edu.nf.lol.product.service.ProductSpecsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Crazy
 * @date 2020/3/5
 */

//@SpringBootTest
public class ProductDetailTest {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductSpecsService productSpecsService;
    @Test
    public void testProductDetail(){
        Product product= new Product();
        product.setProductId(1);
        Product product1=productDetailService.productDetail(product);
        List<ProductImage> productImages=product1.getProductImageList();
        productImages.forEach(
                productImage -> {
                    System.out.println(productImage.getImageName());
                }
        );
        System.out.println(product1.getProductWeight());
    }
    @Test
    public void testListProduct(){
        ProductType productType= new ProductType();
        Product product= new Product();
        product.setProductId(1);
        List<ProductSpecs> list=productSpecsService.listProductSpecs(product.getProductId());
    }
    @Test
    public  void  testProductSpecsProductId(){
        ProductSpecs specs= new ProductSpecs();
        specs.setProductSpecs("{'颜色':'彩色','尺码':'XXL'}");
        System.out.println(specs.getProductSpecs());
//        ProductSpecs specs1=productSpecsService.productSpecsProductId();
//        System.out.println(specs1);
    }
}
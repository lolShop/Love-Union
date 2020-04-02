package edu.nf.lol.service.test.product;



import edu.nf.lol.repository.ProductRepository;
import edu.nf.lol.product.entity.*;
import edu.nf.lol.product.service.ProductDetailService;


import edu.nf.lol.product.service.ProductIndexService;

import edu.nf.lol.product.service.ProductSpecsService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/**
 * @author Crazy
 * @date 2020/3/5
 */

@SpringBootTest
public class ProductDetailTest {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductSpecsService productSpecsService;
    @Autowired
    private ProductIndexService productIndexService;
    @Autowired
    private ProductRepository productRepository;
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
//        ProductSpecs specs= new ProductSpecs();
////        specs.setProductSpecs("{'颜色':'彩色','尺码':'XXL'}");
////        System.out.println(specs.getProductSpecs());
//////        ProductSpecs specs1=productSpecsService.productSpecsProductId();
//////        System.out.println(specs1);
//        List<Product> products=productIndexService.productAll();
//        products.forEach(product -> {
//            ProductDto productDto= new ProductDto();
//            productDto.setProductId(product.getProductId());
//            productDto.setProductName(product.getProductName());
//            productDto.setProductMainImage(product.getProductMainImage());
////            productDto.setSpecsPrice(product.getProductSpecsList().get(0).getSpecsPrice());
////            productDto.setPromotionPrice(product.getProductSpecsList().get(0).getPromotionPrice());
//            productRepository.index(productDto);
//        });
        ProductDto product=new ProductDto();
        product.setProductId(1);
        product.setProductName("悠米卫衣");
        product.setProductMainImage("20200102113518_85881.jpg");
        productRepository.index(product);
    }
}
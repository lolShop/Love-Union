package edu.nf.lol.service.test.product;



import edu.nf.lol.product.entity.*;
import edu.nf.lol.product.entity.PageBean;
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
       productIndexService.productAll();
    }
    @Test
    public  void  testProductSpecsProductId(){
        PageBean<ProductDto> pageBean =productIndexService.productSearch(1,3,"服饰");
        pageBean.getList().forEach(productDto -> {
                System.out.println(productDto.getProductName());
                System.out.println(productDto.getProductTypeName());
            });
    }
}
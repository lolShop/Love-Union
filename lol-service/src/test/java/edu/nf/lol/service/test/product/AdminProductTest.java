package edu.nf.lol.service.test.product;

import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductType;
import edu.nf.lol.product.service.AdminProductService;
import edu.nf.lol.product.service.AdminProductTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Crazy
 * @date 2020/3/10
 */
@SpringBootTest
public class AdminProductTest {
    @Autowired
    private AdminProductService adminProductService;
    @Autowired
    private AdminProductTypeService adminProductTypeService;
    @Test
    public void testProductDetail(){
        List<Product> list = adminProductService.adminProductAll();
        System.out.println(list);
        list.forEach(product -> {
            System.out.println(product.getProductName());
        });
    }
    @Test
    public  void  testListProductTypeParentId(){
        List<ProductType> list= adminProductTypeService.listProductTypeParentId(0);
        testProductTypeList(list);
    }
    public  void testProductTypeList(List<ProductType> productList){
        productList.forEach(ProductType->{
            System.out.println(ProductType.getProductTypeName());
            testProductTypeList(ProductType.getProductTypeList());
        });
    }

}
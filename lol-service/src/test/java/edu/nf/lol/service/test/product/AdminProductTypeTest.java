package edu.nf.lol.service.test.product;

import edu.nf.lol.product.entity.ProductType;
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
public class AdminProductTypeTest {
    @Autowired
    private AdminProductTypeService adminProductTypeService;

    @Test
    public void testAdminProductTypeDetail(){
        ProductType type = new ProductType();
        type.setParentId(0);
        List<ProductType> list = adminProductTypeService.adminProductTypeAll(0);
        for (ProductType productType : list) {
            System.out.println(productType.getProductTypeName());
        }
    }


    @Test
    public  void  testListProductTypeParentId(){
        List<ProductType> list= adminProductTypeService.adminProductTypeAll(0);
        testProductTypeList(list);
    }
    public  void testProductTypeList(List<ProductType> productList){
        productList.forEach(ProductType->{
            System.out.println(ProductType.getProductTypeName());
            testProductTypeList(ProductType.getProductTypeList());
        });
    }

}
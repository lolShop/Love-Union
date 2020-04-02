package edu.nf.lol.service.test.product;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductType;
import edu.nf.lol.product.service.AdminProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Crazy
 * @date 2020/3/10
 */
@SpringBootTest
public class AdminProductTest {
    @Autowired
    private AdminProductService adminProductService;

    @Test
    public void testAdminProductDetail(){
        Product p = new Product();
        p.setProductId(12);
        Product product = adminProductService.adminProductDetail(p);
        System.out.println(product.getProductName());
    }

    @Test
    public void testAdminProductAll(){
        PageInfo<Product> list = adminProductService.adminProductAll(1,5);
        System.out.println(list.getList().size());
    }
    @Test
    public void testAdminAddProduct(){
        Product p = new Product();
        ProductType type = new ProductType();
        type.setProductTypeId(1);
        p.setProductType(type);
        p.setProductName("悠米卫衣1.1");
        p.setProductMainImage("123scfvedrft.jpg");
        p.setProductParticular("12sdgfewrdt.jpg");
        p.setProductWeight(200);
        p.setProductAttribute("{\"颜色\":[\"彩色\"],\"尺码\":[\"XS\",\"S\",\"M\",\"L\",\"XL\",\"XXL\"]}");
        p.setProductState(1);
        adminProductService.adminAddProduct(p);
    }

    @Test
    public void testAdminDelProduct(){
        Product p = new Product();
        p.setProductId(6);
        adminProductService.adminDelProduct(p);
    }

    @Test
    public void testAdminUpdateProduct(){
        Product p = new Product();
        ProductType type = new ProductType();
        type.setProductTypeId(2);
        p.setProductId(6);
        p.setProductType(type);
        p.setProductName("悠米卫衣1.2");
        p.setProductMainImage("123scfvedrft.jpg");
        p.setProductParticular("12sdgfewrdt.jpg");
        p.setProductWeight(200);
        p.setProductAttribute("{\"颜色\":[\"彩色\"],\"尺码\":[\"XS\",\"S\",\"M\",\"L\",\"XL\",\"XXL\"]}");
        p.setProductState(2);
        adminProductService.adminUpdateProduct(p);
    }

}
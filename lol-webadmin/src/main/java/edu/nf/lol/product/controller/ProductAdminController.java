package edu.nf.lol.product.controller;




import com.github.pagehelper.PageInfo;
import edu.nf.lol.BaseController;
import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.service.AdminProductService;


import edu.nf.lol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author Crazy
 * @date 2020/3/5
 */
@RestController
@RequestMapping("admin/product")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ProductAdminController extends BaseController {
    @Autowired
    private AdminProductService service;
    /**
     * 在商品详细页面显示商品信息
     * @param
     * @return
     */
    @GetMapping("/all")
    public ResponseVO productDetail(){
        List<Product> list = service.adminProductAll();
        ResponseVO vo=success(list);
        return  vo;
    }


    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list_adminProductAll")
    public ResponseVO<PageInfo> adminProductAll(Integer pageNum, Integer pageSize){
        PageInfo<Product> pageInfo = service.adminProductAll(pageNum, pageSize);
        return success(pageInfo);
    }

    /**
     * 增
     * @param product
     * @return
     */
    @PostMapping("/adminProduct_add")
    public ResponseVO adminAddProduct(@Valid Product product){
        service.adminAddProduct(product);
        return  success("添加成功");
    }

    /**
     * 删
     * @param product
     * @return
     */
    @PostMapping("/adminProduct_del")
    public ResponseVO adminDelProduct(@Valid Product product){
        service.adminDelProduct(product);
        return  success("product_list.html");
    }

    @GetMapping("/getProductById")
    public ResponseVO adminGetProductById(@Valid Product product){
        Product backProduct=service.adminProductDetail(product);
        return success (backProduct);
    }

    /**
     * 改
     * @param product
     * @return
     */
    @PostMapping("/adminProduct_update")
    public ResponseVO<List<String>> adminUpdateProduct(@Valid Product product,
                                                       MultipartFile[] files) throws IOException {

        service.adminUpdateProduct(product);
        return  success("修改成功");
    }

}
package edu.nf.lol.product.controller;




import edu.nf.lol.BaseController;
import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.service.AdminProductService;


import edu.nf.lol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
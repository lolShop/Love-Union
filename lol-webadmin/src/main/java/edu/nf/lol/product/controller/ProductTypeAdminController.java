package edu.nf.lol.product.controller;

import edu.nf.lol.BaseController;
import edu.nf.lol.product.entity.ProductType;
import edu.nf.lol.product.service.AdminProductTypeService;
import edu.nf.lol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author zachery
 * @title: ProductTypeAdminCpntroller
 * @projectName lol
 * @description: TODO
 * @date 2020/3/2515:09
 */
@RestController
@RequestMapping("admin/productType")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ProductTypeAdminController extends BaseController {

    @Autowired
    private AdminProductTypeService service;

    /**
     * 商品类型查找
     * @param type
     * @return
     */
    @PostMapping("/find_type")
    public ResponseVO<List> adminProductAll(HttpServletRequest request, @Valid ProductType type){
        Integer t = Integer.valueOf(request.getParameter("parentId"));
        type.setParentId(t);
        List<ProductType> list = service.adminProductTypeAll(0);
        return success(list);
    }

    @GetMapping("/product_type_all")
    public ResponseVO productDetail(){
        List<ProductType> list = service.adminProductTypeAll(0);
        ResponseVO vo=success(list);
        return  vo;
    }

}

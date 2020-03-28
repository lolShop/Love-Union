package edu.nf.lol.product.controller;



import edu.nf.lol.BaseController;
import edu.nf.lol.product.entity.ProductType;
import edu.nf.lol.product.service.AdminProductTypeService;
import edu.nf.lol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author Crazy
 * @date 2020/3/28
 */
@RestController
@RequestMapping("admin/productType")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ProductTypeAdminController extends BaseController {
    @Autowired
    private AdminProductTypeService adminProductTypeService;

    @GetMapping("/all")
    public ResponseVO productDetail(){
        List<ProductType> list = adminProductTypeService.listProductTypeParentId(0);
        ResponseVO vo=success(list);
        return  vo;
    }
}
package edu.nf.lol.product.controller;

import edu.nf.lol.BaseController;

import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.service.ProductIndexService;
import edu.nf.lol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Crazy
 * @date 2020/4/1
 */
@RestController
@RequestMapping("product")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ProductIndexController extends BaseController {
    @Autowired
    private   ProductIndexService productIndexService;
    @GetMapping("/recommend")
    public  ResponseVO productRecommend(){
        List<Product> list=productIndexService.productRecommend(4);
        return  success(list);
    }
    @GetMapping("/esAll")
    public  ResponseVO productEsAll(){
        List<Product> list=productIndexService.productAll();
        return  success(list);
    }
}
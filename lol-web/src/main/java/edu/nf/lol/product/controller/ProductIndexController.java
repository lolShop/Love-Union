package edu.nf.lol.product.controller;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.BaseController;

import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductDto;
import edu.nf.lol.product.entity.PageBean;
import edu.nf.lol.product.entity.ProductType;
import edu.nf.lol.product.service.ProductIndexService;
import edu.nf.lol.vo.ResponseVO;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
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
    @GetMapping("/search")
    public  ResponseVO productSearch(Integer pageNum,Integer pageSize,String productName){
        PageBean<ProductDto> pageBean=productIndexService.productSearch(pageNum,pageSize,productName);
        return  success(pageBean);
    }
    @GetMapping("/parentId")
    public ResponseVO listProductTypeParent(Integer parentId){
        List<ProductType> list=productIndexService.listProductType(parentId);
        return  success(list);
    }
    @GetMapping("/sort")
    public  ResponseVO listProductPrice(Integer pageNum,Integer pageSize,String descName,String sort,String productName){
        SortOrder sortOrder=SortOrder.ASC;
        if (sort.equals("desc")){
            sortOrder=SortOrder.DESC;
        }
        PageBean<ProductDto> pageBean=productIndexService.listProductPrice(pageNum, pageSize,descName,sortOrder,productName);
        return  success(pageBean);
    }
}
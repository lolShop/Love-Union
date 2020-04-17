package edu.nf.lol.product.service;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.product.entity.Product;


import java.util.List;

/**
 * @author Crazy
 * @date 2020/4/1
 */
public interface ProductIndexService {
    PageInfo<Product> productRecommend(Integer pageNum,Integer pageSize,Integer state);
    List<Product> productAll();
}
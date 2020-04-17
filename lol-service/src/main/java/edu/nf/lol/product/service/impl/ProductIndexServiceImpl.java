package edu.nf.lol.product.service.impl;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.product.dao.ProductIndexDao;
import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductDto;
import edu.nf.lol.product.service.ProductIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Crazy
 * @date 2020/4/1
 */
@Service("productIndexService")
@Transactional
public class ProductIndexServiceImpl implements ProductIndexService {
    @Autowired
    private ProductIndexDao productIndexDao;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public PageInfo<Product> productRecommend(Integer pageNum,Integer pageSize,Integer state) {
           List<Product> list=productIndexDao.productRecommend(pageNum,pageSize,state);
           PageInfo pageInfo= new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<Product> productAll() {
        List<Product> list=productIndexDao.productAll();
        list.forEach(product -> {
            ProductDto productDto= new ProductDto();
            productDto.setProductId(product.getProductId());
            productDto.setProductName(product.getProductName());
            productDto.setProductMainImage(product.getProductMainImage());
            productDto.setSpecsPrice(product.getProductSpecsList().get(0).getSpecsPrice());
            productDto.setPromotionPrice(product.getProductSpecsList().get(0).getPromotionPrice());
        });
        return list;
    }
}
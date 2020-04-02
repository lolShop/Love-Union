package edu.nf.lol.product.service.impl;

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
    public List<Product> productRecommend(Integer state) {
        List<Product> list=(List<Product>)redisTemplate.opsForValue().get("productRecommend");
        if (list==null){
            list=productIndexDao.productRecommend(state);
            redisTemplate.opsForValue().set("productRecommend",list);
        }
        return list;
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
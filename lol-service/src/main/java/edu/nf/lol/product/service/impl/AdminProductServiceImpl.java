package edu.nf.lol.product.service.impl;

import edu.nf.lol.exception.LolException;
import edu.nf.lol.product.service.AdminProductService;
import edu.nf.lol.product.dao.AdminProductDao;
import edu.nf.lol.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Crazy
 * @date 2020/3/10
 */
@Service("adminProductService")
@Transactional
public class AdminProductServiceImpl implements AdminProductService {
    @Autowired
    private  AdminProductDao adminProductDao;
    @Override
    public List<Product> adminProductAll() {
        try {
            List<Product> products=adminProductDao.adminProductAll();
            return  products;
        }catch (RuntimeException e){
              throw  new LolException("查询失败,请稍后重试");
        }
    }
}
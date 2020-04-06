package edu.nf.lol.product.service.impl;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.exception.LolException;
import edu.nf.lol.product.service.AdminProductService;
import edu.nf.lol.product.dao.AdminProductDao;
import edu.nf.lol.product.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(AdminProductServiceImpl.class);
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


    @Override
    public Product adminProductDetail(Product product) {
        try {
            log.info("后台消息:查询编号为"+product.getProductId()+"的商品详细信息");
            return  adminProductDao.adminProductDetail(product);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LolException("查询失败，请稍后尝试");
        }
    }

    @Override
    public PageInfo<Product> adminProductAll(Integer pageNum, Integer pageSize) {
        try {
            List<Product> list = adminProductDao.adminProductAll(pageNum,pageSize);
            PageInfo<Product> pageInfo = new PageInfo<>(list);
            log.info("后台消息:查询条数" + pageInfo.getList().size());
            return pageInfo;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LolException("服务器内部错误，暂时无法访问");
        }
    }

    @Override
    public void adminAddProduct(Product product) {
        try {
            adminProductDao.adminAddProduct(product);
            log.info("后台消息:已成功添加编号为"+product.getProductId()+"的商品信息");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LolException("服务器繁忙，请稍后尝试");
        }
    }

    @Override
    public void adminDelProduct(Product product) {
        try {
            adminProductDao.adminDelProduct(product);
            log.info("后台消息:已成功删除编号为"+product.getProductId()+"的商品信息");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LolException("服务器错误，删除失败");
        }
    }

    @Override
    public void adminUpdateProduct(Product product) {
        try {
            adminProductDao.adminUpdateProduct(product);
            log.info("后台消息:已成功修改编号为"+product.getProductId()+"的商品信息");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LolException("服务器内部错误，更新失败");
        }
    }
}
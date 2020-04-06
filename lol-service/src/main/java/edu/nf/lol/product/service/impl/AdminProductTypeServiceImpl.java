package edu.nf.lol.product.service.impl;


import edu.nf.lol.exception.LolException;
import edu.nf.lol.product.dao.AdminProductTypeDao;

import edu.nf.lol.product.entity.ProductType;

import edu.nf.lol.product.service.AdminProductTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author Crazy
 * @date 2020/3/27
 */
@Service("adminProductTypeService")
@Transactional
public class AdminProductTypeServiceImpl implements AdminProductTypeService {
    private static final Logger log = LoggerFactory.getLogger(AdminProductTypeServiceImpl.class);
    @Autowired
    private AdminProductTypeDao adminProductTypeDao;
    @Override
    public List<ProductType> listProductTypeParentId(Integer parentId) {
        List<ProductType> list=adminProductTypeDao.listProductTypeParentId(parentId);
       list.forEach(productType -> {
           productType.setProductTypeList(listProductTypeParentId(productType.getProductTypeId()));
       });
        return list;
    }


    @Override
    public List<ProductType> adminProductTypeAll(Integer parentId) {

        try {
            List<ProductType> list=adminProductTypeDao.adminProductTypeAll(parentId);
            list.forEach(productType -> {
                productType.setProductTypeList(adminProductTypeAll(productType.getProductTypeId()));
            });
            log.info("后台消息,查询商品分类:"+parentId);
            return list;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LolException("查询失败，请稍后尝试");
        }
    }
}
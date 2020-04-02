package edu.nf.lol.product.service.impl;


import edu.nf.lol.product.dao.AdminProductTypeDao;

import edu.nf.lol.product.entity.ProductType;

import edu.nf.lol.product.service.AdminProductTypeService;
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
}
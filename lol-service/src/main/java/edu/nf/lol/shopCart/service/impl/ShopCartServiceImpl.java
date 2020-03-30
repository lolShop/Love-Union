package edu.nf.lol.shopCart.service.impl;

import edu.nf.lol.shopCart.service.ShopCartService;
import edu.nf.lol.shopCart.dao.ShopCartDao;
import edu.nf.lol.shopCart.entity.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/3/18
 */
@Service("shopCartService")
@Transactional
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private ShopCartDao shopCartDao;

    @Override
    public List<ShopCart> listShopCart(Integer uid) {
        List<ShopCart> list = shopCartDao.listShopCart(uid);
        return list;
    }

    @Override
    public ShopCart listNum(Integer uid) {
        ShopCart sc = shopCartDao.listNum(uid);
        return sc;
    }

    @Override
    public void addCount(Integer shopId) {
        shopCartDao.addCount(shopId);
    }

    @Override
    public void decreaseCount(Integer shopId) {
        shopCartDao.decreaseCount(shopId);
    }

    @Override
    public void delShopCart(Integer shopId) {
        shopCartDao.delShopCart(shopId);
    }

    @Override
    public void select(Integer state,Integer shopId) {
        shopCartDao.select(state,shopId);
    }

    @Override
    public void checkAll(Integer state, Integer uid) {
        shopCartDao.checkAll(state,uid);
    }

    @Override
    public void settlement(Integer uid) {
        shopCartDao.settlement(uid);
    }

    @Override
    public ShopCart findShopCart(Integer specId) {
        return shopCartDao.findShopCart(specId);
    }


}
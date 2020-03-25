package edu.nf.lol.shopCart.dao;

import edu.nf.lol.shopCart.entity.ShopCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cf
 * @date 2020/3/17
 */

public interface ShopCartDao {

    /**
     * 根据用户id查询购物车
     * @param uid 用户id
     * @return
     */
    List<ShopCart> listShopCart(@Param("uid") Integer uid);

    /**
     * 查询选中商品数量
     * @param uid
     * @return
     */
    ShopCart listNum(@Param("uid") Integer uid);

    /**
     * 根据id增加商品数量
     * @param shopId
     */
    void addCount(Integer shopId);

    /**
     * 根据id减少商品数量
     * @param shopId
     */
    void decreaseCount(Integer shopId);

    /**
     * 根据id删除商品
     * @param shopId
     */
    void delShopCart(Integer shopId);

    /**
     * 选中 / 取消商品
     * @param shopId
     */
    void select(Integer state,Integer shopId);

    /**
     * 根据用户id全选/取消所有购物项
     * @param state
     * @param uid
     */
    void checkAll(Integer state,Integer uid);

    /**
     * 结算
     * @param uid
     */
    void settlement(Integer uid);
}

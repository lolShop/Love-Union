package edu.nf.lol.user.dao;

import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductSpecs;
import edu.nf.lol.user.entity.Collect;
import edu.nf.lol.user.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangch
 * @date 2020/3/12
 * 跟用户收藏相关的操作
 */
public interface UserCollectDao {

    /**
     * 添加商品收藏
     * @param user
     * @param product
     */
    void addCollect(User user, Product product);

    /**
     * 查询收藏商品信息
     * @param user
     * @return
     */
    List<Collect> queryCollect(@Param("user") User user, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    /**
     * 取消收藏商品
     * @param collect
     */
    void deleteCollect(Collect collect);
}

package edu.nf.lol.user.service;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductSpecs;
import edu.nf.lol.user.entity.Collect;
import edu.nf.lol.user.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangch
 * @date 2020/3/12
 */
public interface UserCollectService {

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
    PageInfo<Collect> queryCollect(User user, Integer pageNum, Integer pageSize);

    /**
     * 取消收藏商品
     * @param collect
     */
    void deleteCollect(Collect collect);
}

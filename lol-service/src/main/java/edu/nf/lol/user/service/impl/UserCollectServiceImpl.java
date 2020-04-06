package edu.nf.lol.user.service.impl;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductSpecs;
import edu.nf.lol.user.dao.UserCollectDao;
import edu.nf.lol.user.entity.Collect;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserCollectService;
import edu.nf.lol.user.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangch
 * @date 2020/3/12
 */
@Service("UserCollectService")
@Transactional(rollbackFor = RuntimeException.class)
public class UserCollectServiceImpl implements UserCollectService {

    @Autowired
    private UserCollectDao dao;

    @Override
    public void addCollect(User user, Product product) {
        try {
            dao.addCollect(user, product);
        } catch (Exception e) {
            throw new RuntimeException("收藏失败");
        }
    }

    @Override
    public PageInfo<Collect> queryCollect(User user, Integer pageNum, Integer pageSize) {
        try {
            List<Collect> list = dao.queryCollect(user, pageNum, pageSize);
            if(list.size() == 0){
                return null;
            }
            PageInfo<Collect> pageCollect = new PageInfo<>(list);
            return pageCollect;
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
    }

    @Override
    public void deleteCollect(Collect collect) {
        try {
            dao.deleteCollect(collect);
        } catch (Exception e) {
            throw new RuntimeException("取消收藏失败");
        }
    }
}

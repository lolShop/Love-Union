package edu.nf.lol.user.service.impl;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.order.entity.OrderDetails;
import edu.nf.lol.order.entity.OrderInfo;
import edu.nf.lol.user.dao.UserOrderDao;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserOrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangch
 * @date 2020/3/18
 */
@Service("userOrderService")
@Transactional
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private UserOrderDao userOrderDao;

    /**
     * 查询出用户下的所有订单，并且查询订单下的购物项
     * @param user
     * @return 返回值是一个分页对象，包含每个订单的购物项集合
     */
    @Override
    public PageInfo<OrderInfo> queryUsersOrder(User user, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize) {
        List<OrderInfo> orderInfos =  userOrderDao.queryUsersOrder(user, pageNum, pageSize);
        if(orderInfos.size() == 0){
            return null;
        }
        for (OrderInfo orderInfo : orderInfos) {
            String orderId = orderInfo.getOrderId();
            // 根据订单id查询出该订单下的所有购物项
            List<OrderDetails> details = userOrderDao.queryOrderItem(orderId);
            orderInfo.setDetails(details);
        }
        PageInfo<OrderInfo> pageInfo = new PageInfo<>(orderInfos);
        return pageInfo;
    }

    /**
     * 修改订单状态
     * 注意：如果是订单过期或者取消了订单，那么库存需要增加
     * @param orderInfo
     */
    @Override
    public void updateOrderStatus(OrderInfo orderInfo) {
        try {
            userOrderDao.updateOrderStatus(orderInfo);
        } catch (Exception e) {
            throw new RuntimeException("修改失败");
        }
    }

    /**
     * 删除一个订单，及所属这个订单的购物项
     * @param orderInfo 包含订单id
     */
    @Override
    public void deleteOrder(OrderInfo orderInfo) {
        try {
            userOrderDao.deleteOrderDetails(orderInfo);
            userOrderDao.deleteOrderInfo(orderInfo);
        } catch (Exception e) {
            throw new RuntimeException("删除失败");
        }
    }
}

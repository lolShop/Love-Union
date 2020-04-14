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
        try {
            List<OrderInfo> orderInfos =  userOrderDao.queryUsersOrder(user, pageNum, pageSize);
            return getDetails(orderInfos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败");
        }
    }

    /**
     * 根据状态查询订单
     * @param orderInfo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<OrderInfo> queryOrderByStatus(OrderInfo orderInfo, Integer pageNum, Integer pageSize) {
        try {
            List<OrderInfo> orderInfos =  userOrderDao.queryOrderByStatus(orderInfo, pageNum, pageSize);
            return getDetails(orderInfos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败");
        }
    }

    /**
     * 查询订单详情
     * @param orderInfo
     * @return
     */
    @Override
    public OrderInfo queryOrderInfo(OrderInfo orderInfo) {
        try {
            OrderInfo order =  userOrderDao.queryOrderInfo(orderInfo);
            List<OrderDetails> details = userOrderDao.queryOrderItem(order.getOrderId());
            order.setDetails(details);
            System.out.println(details);
            System.out.println(order);
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败");
        }
    }

    /**
     * 根据提供的订单集合，查询每个订单的购物项，并返回分页对象
     * @param orderInfos
     * @return
     */
    private PageInfo<OrderInfo> getDetails(List<OrderInfo> orderInfos){
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
            e.printStackTrace();
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
            e.printStackTrace();
            throw new RuntimeException("删除失败");
        }
    }
}

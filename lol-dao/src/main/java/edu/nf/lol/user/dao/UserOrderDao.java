package edu.nf.lol.user.dao;

import edu.nf.lol.order.entity.OrderDetails;
import edu.nf.lol.order.entity.OrderInfo;
import edu.nf.lol.user.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangch
 * @date 2020/3/18
 */
public interface UserOrderDao {

    /**
     * 查询用户所有的订单
     * @param user
     * @return
     */
    List<OrderInfo> queryUsersOrder(User user, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    /**
     * 查询用户所有的订单
     * @param orderInfo
     * @return
     */
    List<OrderInfo> queryOrderByStatus(OrderInfo orderInfo, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    /**
     * 根据提供的订单id查询出所有的购物项
     * @param orderId
     * @return
     */
    List<OrderDetails> queryOrderItem(String orderId);

    /**
     * 修改订单状态
     * @param orderInfo
     */
    void updateOrderStatus(OrderInfo orderInfo);

    /**
     * 删除订单信息
     * @param orderInfo
     */
    void deleteOrderInfo(OrderInfo orderInfo);

    /**
     * 删除购物项信息
     * @param orderInfo
     */
    void deleteOrderDetails(OrderInfo orderInfo);
}

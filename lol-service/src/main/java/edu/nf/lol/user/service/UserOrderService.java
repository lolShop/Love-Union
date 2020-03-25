package edu.nf.lol.user.service;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.order.entity.OrderDetails;
import edu.nf.lol.order.entity.OrderInfo;
import edu.nf.lol.user.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author zhangch
 * @date 2020/3/18
 */
public interface UserOrderService {

    PageInfo<OrderInfo> queryUsersOrder(User user, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    void updateOrderStatus(OrderInfo orderInfo);

    void deleteOrder(OrderInfo orderInfo);
}

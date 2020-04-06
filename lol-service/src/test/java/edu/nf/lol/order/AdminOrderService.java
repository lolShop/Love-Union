package edu.nf.lol.order;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.order.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.PlatformTransactionManager;

import javax.activation.DataSource;

public interface AdminOrderService {
    /*
     *根据id查询一条信息
     *
     */
     OrderInfo getOrder(Integer id);
     /*
      *查询所有订单信息
      */
     PageInfo<OrderInfo> listOrder(@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize,Integer state);
     /*
      *修改订单信息
      */
     void updateOrder(OrderInfo orderInfo);
     /*
      *删除订单信息
      */
     void deleteOrder(Integer id);
}

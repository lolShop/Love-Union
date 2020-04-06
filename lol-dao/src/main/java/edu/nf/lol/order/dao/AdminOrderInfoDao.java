package edu.nf.lol.order.dao;

import edu.nf.lol.order.entity.OrderInfo;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface AdminOrderInfoDao {
    //显示
    List<OrderInfo> pageOrder(@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize);
    //查询
    List<OrderInfo> chanxun (@Param("pageNum")Integer pageNum,@Param("pageSize")Integer size,String staticName);

   List<OrderInfo> pageOrder2();

}

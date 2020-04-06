package edu.nf.lol.order.AdminService;


import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.github.pagehelper.PageInfo;
import edu.nf.lol.order.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminOrderService {
    //显示
    PageInfo<OrderInfo> pageOrder (@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize);
    //    List<OrderInfo> pageOrder2();
    //查询
    PageInfo<OrderInfo> chanxun (@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize,@Param("staticName")String staticName);


    List<OrderInfo> pageOrder2();
}

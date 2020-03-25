package edu.nf.lol.user.controller;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.BaseController;
import edu.nf.lol.order.entity.OrderDetails;
import edu.nf.lol.order.entity.OrderInfo;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserOrderService;
import edu.nf.lol.vo.ResponseVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhangch
 * @date 2020/3/23
 */
@RestController
@RequestMapping("/user/order")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserOrderController extends BaseController {

    @Autowired
    private UserOrderService service;

    /**
     * 查询用户所有的订单
     * @return
     */
    @GetMapping("/query_my_order")
    public ResponseVO queryMyOrder(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize){
        User user = new User();
        user.setUserId(1000);
        PageInfo<OrderInfo> orderInfo = service.queryUsersOrder(user, pageNum, pageSize);
        return success(orderInfo);
    }

    /**
     * 删除订单及订单中的购物项
     * @param orderInfo 里面包含订单id
     * @return
     */
    @PostMapping("/del_order")
    public ResponseVO delOrder(OrderInfo orderInfo){
        service.deleteOrder(orderInfo);
        return success("删除成功");
    }
}

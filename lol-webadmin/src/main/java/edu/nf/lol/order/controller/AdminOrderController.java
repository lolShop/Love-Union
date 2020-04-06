package edu.nf.lol.order.controller;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.BaseController;
import edu.nf.lol.order.AdminService.AdminOrderService;
import edu.nf.lol.order.AdminService.AdminOrderStatuService;
import edu.nf.lol.order.entity.OrderInfo;
import edu.nf.lol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminOrderController extends BaseController {

    @Autowired
    private AdminOrderService orderService;

    @Autowired
    private AdminOrderStatuService orderStatuService;

    @GetMapping("/orderPage")
    public ResponseVO orderPage(Integer pageNum, Integer pageSize){
        PageInfo<OrderInfo> pageInfo = orderService.pageOrder(pageNum,pageSize);
        return success(pageInfo);
    }
    @GetMapping("/chanxun")
    public ResponseVO chanxun(Integer pageNum,Integer pageSize,String staticName){
        System.out.println(staticName);
        PageInfo<OrderInfo> pageInfo= orderService.chanxun(pageNum,pageSize,staticName);
        return success(pageInfo);
    }

    @PostMapping("/updateStatic")
    public ResponseVO updateStatic(String orderId){
        System.out.println(orderId);
        orderStatuService.update(orderId);

        return success();
    }

}

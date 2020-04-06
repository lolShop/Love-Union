package edu.nf.lol.order.AdminService.impl;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.order.AdminService.AdminOrderService;
import edu.nf.lol.order.dao.AdminOrderInfoDao;
import edu.nf.lol.order.entity.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {

    @Autowired
    private AdminOrderInfoDao orderInfoMapper;
    //显示
    @Override
    public PageInfo<OrderInfo> pageOrder(Integer pageNum, Integer pageSize) {
        List<OrderInfo> list = orderInfoMapper.pageOrder(pageNum,pageSize);
        PageInfo pageInfo=new PageInfo(list);
        return pageInfo;
    }


    //查询
    @Override
    public PageInfo<OrderInfo> chanxun(Integer pageNum, Integer pageSize, String staticName) {
        List<OrderInfo>list=orderInfoMapper.chanxun(pageNum,pageSize,staticName);
        PageInfo pageInfo=new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<OrderInfo> pageOrder2() {
        return null;
    }


}

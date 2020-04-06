package edu.nf.lol.order.AdminService.impl;

import edu.nf.lol.order.AdminService.AdminOrderStatuService;
import edu.nf.lol.order.dao.AdminOrderInfoStatuDao;
import org.springframework.stereotype.Service;

@Service
public class AdminOrderStatuServiceImpl implements AdminOrderStatuService {
    private AdminOrderInfoStatuDao adminOrderInfoStatuDao;
    @Override
    public void update(String id) {
        adminOrderInfoStatuDao.updateStatic(id);
    }
}

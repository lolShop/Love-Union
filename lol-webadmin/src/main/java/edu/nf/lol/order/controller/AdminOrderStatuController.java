package edu.nf.lol.order.controller;

import edu.nf.lol.BaseController;
import edu.nf.lol.order.AdminService.AdminOrderService;
import edu.nf.lol.order.AdminService.AdminOrderStatuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminOrderStatuController extends BaseController {
    @Autowired
    private AdminOrderStatuService adminOrderStatuService;


}

package edu.nf.lol.user.controller;

import edu.nf.lol.BaseController;
import edu.nf.lol.user.entity.Address;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserAddressService;
import edu.nf.lol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zhangch
 * @date 2020/3/19
 */
@RestController
@RequestMapping("/user/address")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserAddressController extends BaseController {

    @Autowired
    private UserAddressService service;

    @GetMapping("/all/{userId}")
    public ResponseVO getAllAddress(@PathVariable("userId") Integer userId){
        User user = new User();
        user.setUserId(userId);
        List<Address> address = service.queryAddress(user);
        return success(address);
    }

    @PostMapping("/update_status")
    public ResponseVO updateStatus(Address address){
        service.setDefaultAddress(address);
        return success("修改成功");
    }

    @PostMapping("/del_address")
    public ResponseVO delAddress(Address address){
        service.delAddress(address);
        return success("删除成功");
    }

    @PostMapping("/add_address")
    public ResponseVO addAddress(Address address){
        System.out.println(address);
        service.addAddress(address);
        return success("添加成功");
    }

    @PostMapping("/update_info")
    public ResponseVO updateAddress(Address address){
        service.updateAddress(address);
        return success("修改成功");
    }
}

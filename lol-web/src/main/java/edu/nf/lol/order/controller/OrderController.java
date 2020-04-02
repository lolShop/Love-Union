package edu.nf.lol.order.controller;

import edu.nf.lol.BaseController;
import edu.nf.lol.order.service.OrderService;
import edu.nf.lol.shopCart.entity.ShopCart;
import edu.nf.lol.user.entity.Address;
import edu.nf.lol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

/**
 * @author yangTao
 * @date 2020/3/19
 */
@RestController("orderController")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/getAddress")
    public ResponseVO<List<Address>> getAddressByUserId(HttpSession session){
        return success(orderService.getAddressByUserId(1001));
    }

    @GetMapping("/order/getUserShopCar")
    public ResponseVO<List<ShopCart>> getUserShopCar(HttpSession session){
        return success(orderService.getUserShopCar(1001));
    }

    @PostMapping("/order/addAddress")
    public ResponseVO addAddress(Address address){
        System.out.println(address.toString());
        return null;
    }

    @PostMapping("/order/updateAddressStatic")
    public ResponseVO updateAddressStatic(Integer addressId){
        System.out.println(addressId);
        return null;
    }


}
package edu.nf.lol.shopCart.controller;

import edu.nf.lol.BaseController;
import edu.nf.lol.shopCart.entity.ShopCart;
import edu.nf.lol.shopCart.service.ShopCartService;
import edu.nf.lol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/3/19
 */
@RestController
@RequestMapping("shopCart")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ShopCartController extends BaseController {
    @Autowired
    private ShopCartService shopCartService;

    @GetMapping("/listShopCart")
    public ResponseVO listShopCart(Integer uid){
        List<ShopCart> list = shopCartService.listShopCart(uid);
        return success(list);
    }
    @GetMapping("/listNum")
    public ResponseVO listNum(Integer uid){
        ShopCart sc = shopCartService.listNum(uid);
        return success(sc);
    }
    @GetMapping("/addCount")
    public void addCount(Integer shopId){
        shopCartService.addCount(shopId);
    }
    @GetMapping("/decreaseCount")
    public void decreaseCount(Integer shopId){
        shopCartService.decreaseCount(shopId);
    }
    @GetMapping("/delShopCart")
    public ResponseVO delShopCart(Integer shopId){
        shopCartService.delShopCart(shopId);
        return success("删除成功");
    }
    @GetMapping("/selectShopCart")
    public void selectShopCart(Integer state,Integer shopId){
        shopCartService.select(state,shopId);
    }

    @GetMapping("/checkAll")
    public void checkAll(Integer state,Integer uid){
        shopCartService.checkAll(state,uid);
    }

    @GetMapping("/settlement")
    public void settlement(Integer uid){
        shopCartService.settlement(uid);
    }


}
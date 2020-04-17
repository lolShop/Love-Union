package edu.nf.lol.shopCart.controller;

import edu.nf.lol.BaseController;
import edu.nf.lol.product.entity.Product;
import edu.nf.lol.product.entity.ProductSpecs;
import edu.nf.lol.product.service.ProductSpecsService;
import edu.nf.lol.shopCart.entity.ShopCart;
import edu.nf.lol.shopCart.service.ShopCartService;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    @Autowired
    private ProductSpecsService productSpecsService;
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

    @RequestMapping("/addShopCart")
    public ResponseVO addShopCart(Integer productId, Integer count, String specs,HttpSession session){
        Product product= new Product();
        product.setProductId(productId);
        User user = (User)session.getAttribute("user");
        ProductSpecs productSpecs=productSpecsService.productSpecsProductId(product,specs);
        ShopCart shopCart=new ShopCart();
        shopCart.setShopCount(count);
        shopCart.setShopUid(user.getUserId());
        shopCart.setSpecsProductId(productSpecs.getSpecsId());
        shopCartService.addShopCart(shopCart);
        return success("添加成功！");
    }
}
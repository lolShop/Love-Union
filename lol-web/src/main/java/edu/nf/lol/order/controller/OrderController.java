package edu.nf.lol.order.controller;

import com.alipay.api.AlipayApiException;
import edu.nf.lol.BaseController;
import edu.nf.lol.order.entity.OrderInfo;
import edu.nf.lol.order.entity.OrderStatic;
import edu.nf.lol.order.service.OrderService;
import edu.nf.lol.pay.entity.PagePayData;
import edu.nf.lol.pay.service.PagePayService;
import edu.nf.lol.shopCart.entity.ShopCart;
import edu.nf.lol.user.entity.Address;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangTao
 * @date 2020/3/19
 */
//@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("order")
@RestController("orderController")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PagePayService pagePayService;

    @PostMapping("/placeOrder")
    public void placeOrder(Integer addressId, String orderRemarks, HttpServletResponse response, HttpSession session, HttpServletRequest request) throws IOException, AlipayApiException {
        System.out.println(addressId + orderRemarks);
        User sessionUser =(User)session.getAttribute("user");
        Integer userId = sessionUser.getUserId() ;//session.getA....  //从会话获取用户id
        List<ShopCart> userShopCar = orderService.getUserShopCar(userId);

        OrderInfo orderInfo = new OrderInfo();
        PagePayData pagePayData = new PagePayData();

        String orderId = "lol-"+System.currentTimeMillis();
        session.getServletContext().setAttribute("orderId",orderId);
        BigDecimal price = new BigDecimal("0") ;

        Address address = new Address();
        address.setAddressId(addressId);

        User user = new User();
        user.setUserId(userId);

        OrderStatic orderStatic = new OrderStatic();
        orderStatic.setStaticId(1000);

        orderInfo.setAddress(address);
        orderInfo.setUser(user);
        orderInfo.setOrderId(orderId);
        orderInfo.setOrderStatic(orderStatic);

        for (ShopCart shopCart : userShopCar) {
            BigDecimal promotion = shopCart.getProductSpecs().getPromotionPrice();
            BigDecimal count = new BigDecimal(shopCart.getShopCount());
            BigDecimal multiply = promotion.multiply(count);
            price = multiply.add(price);
        }
        orderInfo.setPaymentMoney(price);
        orderInfo.setOrderRemarks(orderRemarks);

        orderService.placeOrder(orderInfo);

        pagePayData.setOrderId(orderId);
        pagePayData.setPrice(price);
        pagePayData.setOrderName("爱联盟订单支付");
        pagePayData.setBody("");
        pagePayService.pagePay(pagePayData,response);
    }

    @GetMapping("/updateOrderStaticOne")
    public void updateOrderStaticOne(HttpSession session,HttpServletResponse response) throws IOException {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(session.getServletContext().getAttribute("orderId").toString());
        OrderStatic orderStatic = new OrderStatic();
        orderStatic.setStaticId(1001);
        orderInfo.setOrderStatic(orderStatic);
        orderService.updateOrderStaticOne(orderInfo);
        response.sendRedirect("http://localhost:8080/lol/myorder.html");
    }

    @GetMapping("/getAddress")
    public ResponseVO<List<Address>> getAddressByUserId(HttpSession session){
        return success(orderService.getAddressByUserId(1001));
    }

    @GetMapping("/getUserShopCar")
    public ResponseVO<List<ShopCart>> getUserShopCar(HttpSession session){
        return success(orderService.getUserShopCar(1001));
    }

    @PostMapping("/addAddress")
    public ResponseVO addAddress(Address address){
        System.out.println(address.toString());
        return null;
    }

    @PostMapping("/updateAddressStatic")
    public ResponseVO updateAddressStatic(Integer addressId){
        System.out.println(addressId);
        return null;
    }




}
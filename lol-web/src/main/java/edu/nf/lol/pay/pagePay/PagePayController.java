package edu.nf.lol.pay.pagePay;

import com.alipay.api.AlipayApiException;
import edu.nf.lol.order.service.OrderService;
import edu.nf.lol.pay.entity.PagePayData;
import edu.nf.lol.pay.service.PagePayService;
import edu.nf.lol.shopCart.entity.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author yangTao
 * @date 2020/4/2
 */
@Controller("pagePayController")
public class PagePayController {

    @Autowired
    private PagePayService pagePayService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/pay/pagePay")
    public void pagePay(String orderId, HttpServletResponse response) throws IOException, AlipayApiException {
        PagePayData pagePayData = new PagePayData();
        BigDecimal price = orderService.getOrderPrice(orderId);
        pagePayData.setOrderId(orderId);
        pagePayData.setPrice(price);
        pagePayData.setOrderName("爱联盟订单支付");
        pagePayData.setBody("");
        pagePayService.pagePay(pagePayData,response);
    }

}
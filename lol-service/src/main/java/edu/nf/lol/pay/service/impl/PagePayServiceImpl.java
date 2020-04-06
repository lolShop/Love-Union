package edu.nf.lol.pay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import edu.nf.lol.pay.entity.PagePayData;
import edu.nf.lol.pay.service.PagePayService;
import edu.nf.lol.pay.service.config.PayConfig;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangTao
 * @date 2020/4/1
 */
@Service("pagePayService")
public class PagePayServiceImpl implements PagePayService {

    @Override
    public void pagePay(PagePayData pagePayData, HttpServletResponse response) throws AlipayApiException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient(PayConfig.gatewayUrl,PayConfig.app_id,
                PayConfig.merchant_private_key,PayConfig.format,PayConfig.charset,PayConfig.alipay_public_key,PayConfig.sign_type);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setReturnUrl(PayConfig.return_url);
        request.setNotifyUrl(PayConfig.notify_url);

        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        request.setBizModel(model);
        model.setOutTradeNo(pagePayData.getOrderId());
        model.setSubject(pagePayData.getOrderName());
        model.setTotalAmount(pagePayData.getPrice().toString());
        model.setBody(pagePayData.getBody());
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        AlipayTradePagePayResponse payResponse = null;
        payResponse = alipayClient.pageExecute(request,"post");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(payResponse.getBody());

    }
}
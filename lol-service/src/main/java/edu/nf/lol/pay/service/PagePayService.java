package edu.nf.lol.pay.service;

import com.alipay.api.AlipayApiException;
import edu.nf.lol.pay.entity.PagePayData;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangTao
 * @date 2020/4/1
 * 用于支付业务
 */

public interface PagePayService {

    void pagePay(PagePayData pagePayData, HttpServletResponse response) throws AlipayApiException, IOException;

}
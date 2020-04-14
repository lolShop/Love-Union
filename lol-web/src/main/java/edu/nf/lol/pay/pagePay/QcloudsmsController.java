package edu.nf.lol.pay.pagePay;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import edu.nf.lol.BaseController;
import edu.nf.lol.vo.ResponseVO;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * @author Crazy
 * @date 2020/4/6
 */
@RestController
public class QcloudsmsController extends BaseController {
    int appid = 1400236726;
    String appkey = "35d153facc3bc019831ca25e386c0e45";
    int templateId = 392072;
    String smsSign = "李冲海的成长经历";
    @GetMapping("/note")
    public ResponseVO getNote(String  phoneNumber, HttpSession session) throws HTTPException, IOException {
            int random=(int)((Math.random()*9+1)*100000);
            session.setAttribute("random",random);
            session.setAttribute("phoneNumber",phoneNumber);
            String[] params = {String.valueOf(random),"10"};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,templateId, params, smsSign, "", "");
            ResponseVO vo=success("发送成功");
            if ( result.result!=0){
                vo=fail(500,"发送失败");
            }
        return vo;
    }
}
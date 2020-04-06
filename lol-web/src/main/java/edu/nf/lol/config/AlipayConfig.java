package edu.nf.lol.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016102400750475";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCG/BwtsBdPluBbtjPSBeYIWznbkfDIX2+dkKy3shpxh0K6FUQk7SsHfDERC1mzDjXBakHCGssq+T8sZoiwAXqXsKcvztkGUENKZxXFGpYIDwjTfYaqW5a6wfcjZCfYwn37CS/A5HhUzwvtTl1a9sYz24e3SOrJvOx0+KMFSNgP4oLa6zdYRD9grXLsUXSJfWF10/MfLkicIWfMc4I+uNbLaWohXws6p7gyaZGzt6HQYUStephecH4LlQ9zr67/khrb2pqDbQA4rBWybUZIYbH7jKoUWO9U/YomIUM1JPRY7NBAcpC/6ewkpeS+2nB6PRPJSI56H9QoxB+j/kTHk8WnAgMBAAECggEAI5Md+gyGmDszNfh2/8r+LQWd4fg8dRJx4BzY6DDQN0tmkpQ4OWMoC/j3FX7gTQhZsnfGjNly4gh+Vb1QGNLK9F9iMH9bQwa0NU+ltSSklcsgkFxsuUkaGG42K+WhescHXLm3FCcib3eBtTEJ+YdABTq+MdfNyQ05ZDqC/EmOZ+Dwfu5KLGH5eqdYBcT3DO49yefbYLPE0gVWhIRlZI6hRqsh1htvuVa+cFoRDvcahjwtw5z6FCxFom8OBBWhC2VC0+B6cNkiggrgp/STW4aoxOmlCr2G4Sr7bh96+b0uDxzlDNmSs7NJiWImm1ytFG8ll0yiYojRANUkaznLBLWBAQKBgQDA1bwixzmhPadHHhsB+8bNMQjEIEgfJ55T1LROmIKm43W9c+AqvAls5886oP6TEuQoQbTe0LFcw+ooBKWItBoJB387bZsXDO7EMP1Yd7CVg5w/yeg9luV+1V3Loyz+zD3I81bFn4wc85QePKkb2+Lm+8EgoPrtqAeBKxw75pmf0QKBgQCzM1O0AhlaPoVcWEbblN4kc6pCq6xVtzyjLUyDUQQuZ5cPqwvGOBcBUKSEO/vpMR30s7SvOIRKcbEQLS1oj+k3C3HOhksLCSejhDSX2RSmMqgOczD/El89GZVxvfT/cjKzrlvyxTaaPRv5DsWoygsOeJjz95HvfXG4q+VhebEj9wKBgH0ImUp7n1R7Y4FrddudY1Qc3TA+NkVtLO3yV082+FA54FgNUGalMf9ZdzbuuOKoNyQbJqnq63XY2nVEPgkbrl34bHkeIpeZnR+9HChGw0rO4XtC8cEtnHTW6FBn537284Det37AattC3OGWN5iGxOM8jgcYGlwKvYL1bwMwDKBhAoGBAJgZtrARIAezDWrGGs6B2tNBwX/5SrsuXi96YXkMKOpiTpuj6MiEDzK0ig8bAsR5Rh3O8kvPQqoVOd6rGeGoVKC9/Aj5f3SMfrKRojpIrPgsAuzQ5QN7So8HygrS2sJ+4X4VPkIfFitwNu/8k9SiOSQ9REW/GoUWCpJehMFpkusdAoGAGiCr5sccaPD77+DR9MnfuyhRQ3sHJZuKHXBMqammpytpO32LCyhHDaLya2XfRkAAyq0zV7Y1qG+wIcCV8upgLJ/5dAKidxl/h3BNMLZ6PTYQ5Vy1J85GUnqXdQxEfWbZj3YeoUu1mXmK+mlugdU/T08DRV8UT/Ag6TKxRLcqCOE=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhvwcLbAXT5bgW7Yz0gXmCFs525HwyF9vnZCst7IacYdCuhVEJO0rB3wxEQtZsw41wWpBwhrLKvk/LGaIsAF6l7CnL87ZBlBDSmcVxRqWCA8I032GqluWusH3I2Qn2MJ9+wkvwOR4VM8L7U5dWvbGM9uHt0jqybzsdPijBUjYD+KC2us3WEQ/YK1y7FF0iX1hddPzHy5InCFnzHOCPrjWy2lqIV8LOqe4MmmRs7eh0GFErXqYXnB+C5UPc6+u/5Ia29qag20AOKwVsm1GSGGx+4yqFFjvVP2KJiFDNST0WOzQQHKQv+nsJKXkvtpwej0TyUiOeh/UKMQfo/5Ex5PFpwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/lol/return.html";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/lol/return.html";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


package edu.nf.lol.user.controller;

/**
 * @author zhangch
 * @date 2020/4/10
 */
public class AlinoteConfig {

    // 短信应用 SDK AppID
    public  static int appid = 1400236726; // SDK AppID 以1400开头
    // 短信应用 SDK AppKey
    public  static String appkey = "35d153facc3bc019831ca25e386c0e45";
    // 短信模板 ID，需要在短信应用中申请
    public  static int templateId = 392072; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
    // 签名
    public  static String smsSign = "李冲海的成长经历"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
}

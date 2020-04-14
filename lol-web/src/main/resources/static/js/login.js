$(function(){

})

//点击**验证登陆
$('.c-login-switch').on('click', function(){
    var pwdStyle = $('#password').css('display');
    var smsStyle = $("#sms").css('display');
    changeStyle(pwdStyle, $('#password'));
    changeStyle(smsStyle, $('#sms'));
    if($('#password').css('display') == "block"){
        $(this).text("短信验证登陆");
    }else{
        $(this).text("密码验证登陆");
    }
})
function changeStyle(style, obj){
    if(style == "block"){
        obj.css("display", "none");
    }else{
        obj.css("display", "block");
    }
}

//获取验证码
$("#J_mobile_verifycode_btn").on("click", function(){
    //如果在倒计时，获取验证码按钮不能点
    if($(this).text() != "获取验证码"){
        return;
    }
    //如果 获取验证码 为无效状态，则回退事件
    if($(this).css('cursor') == "not-allowed"){
        return;
    }
    //服务器发送验证码
    var userPhone = $("input[name='phone']").val();
    $.ajax({
        url:'user/info/get_code',
        type:'get',
        data:{'userPhone':userPhone, "register":false},
        success:function(result){
            if(result.code == 404){
                $("input[name='pvcode']").parent().next().children().text(result.message);
                return;
            }
            //开始倒计时
            loseCode(userPhone);
        }
    })
})
//60秒后验证码失效，需要重新获得验证码
var i = 60;
var timer;
function loseCode(userPhone){
    //每隔1秒钟执行一次down()方法
    timer = window.setInterval("down("+userPhone+")",1000);
}
function down(userPhone){
    if(i == 0){
        $("#J_mobile_verifycode_btn").text("获取验证码");
        //清除timer
        window.clearInterval(timer);
        i = 60;
        //将服务器保存的验证码删除，当前验证码失效
        $.ajax({
            url:'user/info/remove_code',
            type:'post',
            data:{"userPhone":userPhone},
            success:function(result){
                if(result.code != 200){
                    $("input[name='pvcode']").parent().next().children().text(result.message);
                }
            }
        })
    }else{
        $("#J_mobile_verifycode_btn").text(i--+"秒后重试");
    }
}

//点击登录
$("#J_mobile_reg_button").on("click", function(){
    var continue_phone = phoneBlur($("input[name='phone']"));
    var pwd_sms = false;
    var userPhone = $("input[name='phone']").val();
    var formData;
    var url;
    var data;
    if($('#password').css('display') == "block"){
        pwd_sms = passwordBlur($("input[name='password']"));
        formData = $("input[name='password']").val();
        url = "user/info/login_password";
        data = {"userPhone": userPhone, "password": formData};
    }else if($('#sms').css('display') == "block"){
        pwd_sms = passwordBlur($("input[name='pvcode']"));
        formData = $("input[name='pvcode']").val();
        url = "user/info/login_code";
        data = {"userPhone": userPhone, "code": formData};
    }
    //账号等信息不完整
    if(!(continue_phone && pwd_sms)){
        return;
    }
    $.ajax({
        url: url,
        type: 'post',
        data: data,
        success: function(result){
            console.log(result);
            if(result.code == 404){
                $("input[name='phone']").parent().next().children().text(result.message);
            }else{
                alert("登录成功，跳转到首页");
            }
        }
    })
})

//输入手机号码得到焦点
function phoneFocus(obj){
    $(obj).css('border-color', 'black');
    $(obj).parent().next().children().css('color', '#ec5042');
    //获取验证码失效
    $('#J_mobile_verifycode_btn').addClass('ui-btn-disable');
    $('#J_mobile_verifycode_btn').removeClass('ui-btn-secondary');
    if($(obj).val().trim() != ""){
        $(obj).parent().next().children().text("");
        $('#J_mobile_verifycode_btn').addClass('ui-btn-secondary');
        $('#J_mobile_verifycode_btn').removeClass('ui-btn-disable');
    }
}
//输入手机号码失去焦点
function phoneBlur(obj){
    var continueOn = true;
    $(obj).parent().next().children().text("");
    $(obj).css('border-color', '#d2c2d2');
    //验证码为空,不符合号码标准,匹配标准
    if($(obj).val().trim() == ""){
        $(obj).parent().next().children().text("请输入账号");
        $(obj).css('border-color', '#ec5042');
        $('#J_mobile_verifycode_btn').addClass('ui-btn-disable');
        $('#J_mobile_verifycode_btn').removeClass('ui-btn-secondary');
        continueOn = false;
    }else{
        $('#J_mobile_verifycode_btn').addClass('ui-btn-secondary');
        $('#J_mobile_verifycode_btn').removeClass('ui-btn-disable');
        continueOn = true;
    }
    return continueOn;
}

//输入密码得到焦点
function passwordFocus(obj){
    $(obj).css('border-color', 'black');
    $(obj).parent().next().children().css('color', '#ec5042');
}
//输入密码失去焦点
function passwordBlur(obj){
    var continueOn = true;
    $(obj).css('border-color', '#d2c2d2');
    $(obj).parent().next().children().text("");
    var reg = new RegExp("^[A-Za-z0-9]{6,12}$");
    if($(obj).val().trim() == ""){
        $(obj).css('border-color', '#ec5042');
        $(obj).parent().next().children().text("请输入密码");
        continueOn = false;
    }
    return continueOn;
}

//验证码得到焦点
function codeFocus(obj){
    $(obj).css('border-color', 'black');
    $(obj).parent().next().children().text("");
}
//验证码失去焦点
function codeBlur(obj){
    var continueOn = true;
    $(obj).css('border-color', '#d2c2d2');
    //验证码不匹配6位数字
    if($(obj).val().trim() == ""){
        $(obj).css('border-color', '#ec5042');
        $(obj).parent().next().children().text("请输入手机验证码");
        continueOn = false;
    }
    return continueOn;
}

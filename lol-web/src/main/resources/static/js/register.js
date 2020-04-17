$(function(){
	clickRegister();
	getVcode();
})

//获取验证码
var deleteCodeTimer;
function getVcode(){
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
			data:{'userPhone':userPhone, "register":true},
			success:function(result){
				if(result.code == 404){
					$("input[name='phone']").parent().next().children().text(result.message);
					$("input[name='phone']").css("border-color", "rgb(236, 80, 66)");
					return;
				}
				//开始倒计时
				showTime();
				//设定十分钟后从session删除验证码
				deleteCodeTimer = window.setInterval("removeSessionCode("+userPhone+")",1000*60*10);
			}
		})
	})
}

//60秒倒计时
var i = 60;
var timer;
function showTime(){
	//每隔1秒钟执行一次down()方法，这个倒计时的作用只是用于显示秒数，
	timer = window.setInterval("down()",1000);
}

function down(userPhone){
	if(i == 0){
		$("#J_mobile_verifycode_btn").text("获取验证码");
		//获取验证码样式改成可以点击
		$('#J_mobile_verifycode_btn').addClass('ui-btn-secondary');
		$('#J_mobile_verifycode_btn').removeClass('ui-btn-disable');
		//清除timer
		window.clearInterval(timer);
		i = 60;
	}else{
		$("#J_mobile_verifycode_btn").text(i--+"秒后重试");
		//获取验证码样式改成禁止点击
		$('#J_mobile_verifycode_btn').addClass('ui-btn-disable');
		$('#J_mobile_verifycode_btn').removeClass('ui-btn-secondary');
	}
}


//这个方法作用是删除后台session中key为userPhone的属性
function removeSessionCode(userPhone){
	//将服务器保存的验证码删除，当前验证码失效
	$.ajax({
		url:'user/info/remove_code',
		type:'post',
		data:{"userPhone":userPhone},
		success:function(result){
			if(result.code != 200){
				$("input[name='pvcode']").parent().next().children().text(result.message);
			}
			window.clearInterval(deleteCodeTimer);
		}
	})
}

//点击注册
function clickRegister(){
	$('#J_mobile_reg_button').on('click', function(){
		//如果输入的数据都符合规范，则返回true
		var continue_phone = phoneBlur($("input[name='phone']"));
		var continue_code = codeBlur($("input[name = 'pvcode']"));
		var continue_pwd = passwordBlur($("input[name='password']"));
		var continue_confirm = confirmBlur($("input[name='confirm']"));
		//有一个不符合规范，return事件，不往下执行
		if(!(continue_phone && continue_code && continue_pwd && continue_confirm)){
			return;
		}
		var code = $("input[name='pvcode']").val();
		var userPhone = $("input[name = 'phone']").val();
		var password = $("input[name='password']").val();
		$.ajax({
			url:'user/info/user_register',
			type:'post',
			data:{'code':code, 'userPhone':userPhone, 'password':password},
			success:function(result){
				console.log(result);
				//如果验证码错误
				if(result.code == 404){
					$("input[name='pvcode']").parent().next().children().text(result.message);
					return;
				}
				//注册成功，进行下一步操作
				window.location.href = "login.html";
			}
		})
	})
}

//输入手机号码得到焦点
function phoneFocus(obj){
	$(obj).css('border-color', 'black');
	$(obj).parent().next().children().text("请输入手机号码");
	$(obj).parent().next().children().css('color', '#ec5042');
	$('#J_mobile_verifycode_btn').addClass('ui-btn-disable');
	$('#J_mobile_verifycode_btn').removeClass('ui-btn-secondary');
	var reg = new RegExp("^1[3-9]\\d{9}$") ;
	if(reg.test($(obj).val())){
		$(obj).parent().next().children().text("");
		$('#J_mobile_verifycode_btn').addClass('ui-btn-secondary');
		$('#J_mobile_verifycode_btn').removeClass('ui-btn-disable');
	}
}
//输入手机号码失去焦点
function phoneBlur(obj){
	var continueOn = true;
	var reg = new RegExp("^1[3-9]\\d{9}$") ;
	$(obj).parent().next().children().text("");
	$(obj).css('border-color', '#d2c2d2');
	//验证码为空,不符合号码标准,匹配标准
	if($(obj).val().trim() == ""){
		$(obj).parent().next().children().text("手机号码不能为空");
		$(obj).css('border-color', '#ec5042');
		$('#J_mobile_verifycode_btn').addClass('ui-btn-disable');
		$('#J_mobile_verifycode_btn').removeClass('ui-btn-secondary');
		continueOn = false;
	}else if(!reg.test($(obj).val())){
		$(obj).parent().next().children().text("请输入正确的手机号码");
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

//验证码得到焦点
function codeFocus(obj){
	$(obj).css('border-color', 'black');
	$(obj).parent().next().children().text("");
}
//验证码失去焦点
function codeBlur(obj){
	var continueOn = true;
	$(obj).css('border-color', '#d2c2d2');
	var reg = new RegExp("^\\d{6}$") ;
	//验证码不匹配6位数字
	if(!reg.test($(obj).val())){
		$(obj).css('border-color', '#ec5042');
		$(obj).parent().next().children().text("请输入6位手机验证码");
		continueOn = false;
	}
	return continueOn;
}

//输入密码得到焦点
function passwordFocus(obj){
	$(obj).css('border-color', 'black');
	$(obj).parent().next().children().css('color', '#ec5042');
	$(obj).parent().next().children().text("请输入6-20位由数字和字母组成的密码");
	var reg = new RegExp("^[A-Za-z0-9]{6,12}$") ;
	if(reg.test($(obj).val())){
		$(obj).parent().next().children().text("");
	}
}
//输入密码失去焦点
function passwordBlur(obj){
	var continueOn = true;
	$(obj).css('border-color', '#d2c2d2');
	$(obj).parent().next().children().text("");
	var reg = new RegExp("^[A-Za-z0-9]{6,12}$");
	if(!reg.test($(obj).val())){
		$(obj).css('border-color', '#ec5042');
		$(obj).parent().next().children().text("请输入6-20位由数字和字母组成的密码");
		continueOn = false;
	}
	return continueOn;
}

//确认密码得到焦点
function confirmFocus(obj){
	$(obj).css('border-color', 'black');
	$(obj).parent().next().children().css('color', '#ec5042');
	$(obj).parent().next().children().text("");
}
//确认密码失去焦点
function confirmBlur(obj){
	var continueOn = true;
	$(obj).css('border-color', '#d2c2d2');
	$(obj).parent().next().children().text("");
	if($(obj).val() == ""){
		$(obj).css('border-color', '#ec5042');
		$(obj).parent().next().children().text("请输入确认密码");
		continueOn = false;
	}else if($(obj).val() != $("input[name='password']").val()){
		$(obj).css('border-color', '#ec5042');
		$(obj).parent().next().children().text("两次输入的密码不一致，请重试");
		continueOn = false;
	}
	return continueOn;
}













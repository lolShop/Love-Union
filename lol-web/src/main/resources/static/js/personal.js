//初始化,显示用户信息和头像
function init(){
	$.ajax({
		url:'user/info/query_info',
		type:'get',
		success:function(result){
			console.info(result);
			//部分浏览器(IE)不支持日期格式“yyyy-MM-dd hh:mm:ss”,必须将“-”转化为“/”
			var date = new Date(Date.parse(result.data.birthday.replace(/-/g, "/")));
			$(".user-access").text(result.data.userPhone); // 账号
			$("input[name='username']").val(result.data.userName); // 姓名
			var sex = result.data.sex ? 1 : 0;
			$(".uitem-radiobox i[val='"+sex+"']").addClass('ico-checked'); // 性别
			$("#year").text(date.getFullYear()); //生日
			$("#month").text(date.getMonth()+1);
			$("#day").text(date.getDate());
			$("input[name='email']").val(result.data.email); // 邮箱
		}
	})
}

function showWindow(){
	//周围阴影和弹窗显示
	$('#confirm_panel').show();
	$('#_overlay_').show();
	$('.dj_bt_close, .dj_bt_cancel').on('click', function(){
		$('#confirm_panel').hide();
		$('#_overlay_').hide();
	})
}

function updateInfo(){
	$('#userInfo-save').on('click', function(){
		var userPhone = $(".user-access").text(); // 账号
		var userName = $("input[name='username']").val(); // 姓名
		var sex = $(".uitem-radiobox i[class='ico-center ico-checked']").attr('val'); // 性别
		var year = $("#year").text(); //生日
		var month = $("#month").text();
		var day = $("#day").text();
		var birthday = year +"-"+ month +"-"+ day;
		var email = $("input[name='email']").val(); // 邮箱

		$.ajax({
			url:'user/info/update_info',
			type:'post',
			data:{'userPhone':userPhone, 'userName':userName, 'sex':sex, 'email':email},
			success:function(result){
				console.info(result);
			}
		})
	})
}

//点击上传file的input
function upload_img(){
	$("#upload_file").change(function () {
		var file = $("#upload_file")[0].files[0];
		var reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = function (e) {
			var img = document.getElementById("show_upload");
			img.src = e.target.result;
		};
	})
}
//点击上传头像的图片，触发input的点击事件
function click_img(){
	$('#upload_file').click();
}
//执行修改图片的方法
function updatePhoto(){
	click_img();
}

//修改密码
function updatePassword(){
	$('#password-save').on('click', function(){
		var oldPwd = $('#old-password').val();
		var newPwd = $('#new-password').val();
		var newAgain = $('#new-again').val();
		var rag = '[0-9a-zA-Z_]{6,20}';
		if(oldPwd == "" || newPwd == "" || newAgain == ""){
			showWindow();
			$('#alert_cont').text('密码不能为空');
			$('.dj_bt_ok').on('click', function(){
				$('#confirm_panel').hide();
				$('#_overlay_').hide();
			})
		}else if(!newPwd.match(rag)){
			showWindow();
			$('#alert_cont').text('新密码要为6到20位的数字字母和下划线组成!');
			$('.dj_bt_ok').on('click', function(){
				$('#confirm_panel').hide();
				$('#_overlay_').hide();
			})
		}
		else if(newPwd != newAgain){
			showWindow();
			$('#alert_cont').text('新密码与重复密码不一致');
			$('.dj_bt_ok').on('click', function(){
				$('#confirm_panel').hide();
				$('#_overlay_').hide();
			})
		}else{
			$.ajax({
				url:'http://localhost:8080/lol/user/info/update_password',
				type:'post',
				data:{'oldPwd':oldPwd, 'newPwd':newPwd},
				success:function(result){
					showWindow();
					$('#alert_cont').text(result.data);
					$('.dj_bt_ok').on('click', function(){
						$('#confirm_panel').hide();
						$('#_overlay_').hide();
					});
					$('#old-password').val('');
					$('#new-password').val('');
					$('#new-again').val('');
				}
			})
		}
	})
}

$(function(){
	init();
	updateInfo();
	updatePassword();
	updatePhoto();
	$.get("top.html", function (data) {
		$(".head_top").html(data);
	});
	$.get("bottom.html", function (data) {
		$(".bottom").html(data);
	});
})
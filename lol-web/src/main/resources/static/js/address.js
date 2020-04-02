/**
 * 潜在的bug:
 * 除了需要显示的数据,其他的地址信息都用hidden放在页面上,
 * 取地址id等信息是通过li标签下的子标签索引取的
 */

var userId = 1000;
//初始化页面,打印所有的收货地址信息
function init(){
	$.ajax({
		url:'user/address/all/' + userId,
		type:'get',
		success:function(result){
			$("#dj1 ul li:not(:first)").remove();
			$.each(result.data, function(i, address){
				//修饰手机号码
				var phone = address.takePhone.substring(0, 3) + "****" + address.takePhone.substring(7, 11);
				//判断状态是否为默认地址
				var status = address.status == 0 ? 
					"<a href='javascript:;' class='addrlist-link' title='设为默认地址'>设为默认地址</a>" : 
					"<a class='btn-default' >默认地址</a>";
				//打印数据
				$("#dj1 ul").append("<li class='clearfix'>"+
					"<span style='display: none;' class='hidden_address_id'>" + address.addressId + "</span>"+
					"<span style='display: none;' class='hidden_postcode'>" + address.postcode + "</span>"+
					"<span class='addrlist-name'>" + address.takeName + "</span>"+
					"<span class='addrlist-addr'>" +address.takeAddress+ "</span>"+
					"<span style='display: none;'> "+address.takePhone+"</span>"+
					"<span class='addrlist-tel'>" +phone+"</span>"+
					"<span class='addrlist-action'>"+
						"<a href='javascript:;' class='btn-change' title='修改'>修改</a>"+
						"<a href='javascript:;' class='btn-del' title='删除'>删除</a>"+
					"</span>"+
					"<span class='addrlist-default'>"+ status + "</span>"+
				"</li>");
			})
		},
		error:function(){
		}
	});
}
//给 设为默认地址 绑定点击事件,实现修改状态的功能 
function setDefaultStatus(){
	$("#dj1 ul").on('click', '.addrlist-link', function(){
		//获取地址id
		var addressId = $(this).parent().parent().children()[0].innerHTML;
		$.ajax({
			url:'user/address/update_status',
			type:'post',
			data:{"addressId": addressId, "user.userId": userId},
			success:function(result){
				if(result.code == 200){
					init();
				}else{
					alert(result.message);
				}
				
			},
			error:function(){
			}	
		})
	})
}

//删除收货地址
function delAddress(){
	$("#dj1 ul").on('click', '.btn-del', function(){
		//获取地址id
		var addressId = $(this).parent().parent().children()[0].innerHTML;
		$.ajax({
			url:'user/address/del_address',
			type:'post',
			data:{"addressId": addressId, "user.userId": userId},
			success:function(result){
				if(result.code == 200){
					init();
				}else{
					alert(result.message);
				}
			},
			error:function(){
			}	
		})
	})
}

//点击 新增收货地址
function clickInsert(){
	$(".btn-addaddr").on("click",function(){
		recover();
	});
}

//点击保存收货地址
function clickSaveAddress(){
	$('.btn-uitem-save').on('click', function(){
		//取值
		var addressId = $("#address_id").val();
		var takeName = $("input[name=take_name]").val();
		var takePhone = $("input[name='take_phone']").val();
		var city_1 = $("span[title=省]").text();
		var city_2 = $("span[title=市]").text();
		var city_3 = $("span[title=区]").text();
		var city_info = $("textarea[name='detail']").val();
		var takeAddress = city_1+"|"+city_2+"|"+city_3+" "+city_info
		var postcode = $("input[name=zip]").val();
		//判断当前是进行修改还是新增
		var type = $(".addr-tit").text().split("收货地址")[0];
		var url = type == "新增" ? "user/address/add_address" : "user/address/update_info";
		var data = type == "新增" ? 
			{'takeName': takeName, 'takePhone':takePhone, 'takeAddress':takeAddress, 'postcode':postcode, 'user.userId':userId} : 
			{'takeName': takeName, 'takePhone':takePhone, 'takeAddress':takeAddress, 'postcode':postcode, 'addressId':addressId};
		console.info(data);
		$.ajax({
			url:url,
			type:'post',
			data:data,
			success:function(result){
				console.info(result);
				if(result.code == 200){
					init();
					recover();
				}else{
					alert(result.message);
				}
			}
		})
	})
}

//保存地址和修改地址后需要恢复下面控件原来的样式
function recover(){
	$("input[name=take_name]").val("");
	$("input[name='take_phone']").val("");
	$("span[title=省]").text("请选择");
	$("span[title=市]").text("请选择");
	$("span[title=区]").text("请选择");
	$("textarea[name='detail']").val("");
	$("input[name=zip]").val("");
	$(".addr-tit").text("");
	$(".addr-tit").append("新增收货地址( <span style='color:red;'>*</span> 为必填项)")
}

//点击修改,将该条地址详情赋值到修改框
function clickUpdate(){
	$("#dj1 ul").on('click', '.btn-change', function(){
		//从上面的地址列表中取值
		var li = $(this).parent().parent()
		var addressId = li.children()[0].innerHTML;
		var postcode = li.children()[1].innerHTML;
		var takeName = li.children()[2].innerHTML;
		var takeAddress = li.children()[3].innerHTML;
		var city_1 = splitAddress(takeAddress, 0).split("|")[0];
		var city_2 = splitAddress(takeAddress, 0).split("|")[1];
		var city_3 = splitAddress(takeAddress, 0).split("|")[2];
		var detail = splitAddress(takeAddress, 1);
		var takePhone = li.children()[4].innerHTML;
		//给输入框赋值
		$("#address_id").val(addressId);
		$("input[name=take_name]").val(takeName);
		$("input[name='take_phone']").val(takePhone);
		$("span[title=省]").text(city_1);
		$("span[title=市]").text(city_2);
		$("span[title=区]").text(city_3);
		$("textarea[name='detail']").val(detail);
		$("input[name=zip]").val(postcode);
		//将 新增收货地址 改成 修改收货地址
		$(".addr-tit").text("");
		$(".addr-tit").append("修改收货地址( <span style='color:red;'>*</span> 为必填项)");
	})
}

//根据提供的收货地址和索引,拿到城市或者详细地址
function splitAddress(takeAddress, index){
	//将收货地址按空格切割,数组第一个就是城市信息,而后的所有都为详细信息
	var c = takeAddress.split(" ");
	var city = new Array(2);
	city[0] = c[0];
	city[1] = "";
	//将除了第一个为城市信息以后的所有信息都包含为详细信息
	for (var i = 1; i < c.length; i++) {
		city[1] += c[i];
	}
	return city[index];
}


//程序入口
$(function(){
	init();
	setDefaultStatus();
	delAddress();
	clickSaveAddress();
	clickUpdate();
	clickInsert();
	$.get("top.html", function (data) {
		$(".head_top").html(data);
	});
	$.get("bottom.html", function (data) {
		$(".bottom").html(data);
	});
})

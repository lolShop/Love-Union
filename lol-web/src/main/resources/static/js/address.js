/**
 * 潜在的bug:
 * 除了需要显示的数据,其他的地址信息都用hidden放在页面上,
 * 取地址id等信息是通过li标签下的子标签索引取的
 */

var userId = 1000;
//初始化页面,打印所有的收货地址信息
function init(){
	$.ajax({
		url:'user/address/all',
		type:'get',
		success:function(result){
			$("#dj1 ul li:not(:first)").remove();
			$(".center-none").hide();
			if(result.data == null || result.data.length == 0){
				$("#dj1").hide();
				$(".center-none").show();
				$(".conter-cont h2").after(
					"<div class='center-none' >"+
						"<i class='ico-none'></i>"+
						"<p class='none-tip'>您当前暂无收货地址哦！</p>"+
					"</div>"
				);
				return;
			}
			$("#dj1").show();
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
			data:{"addressId": addressId},
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
			data:{"addressId": addressId},
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
			{'takeName': takeName, 'takePhone':takePhone, 'takeAddress':takeAddress, 'postcode':postcode} :
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
	showProv();
})

//选中一个省份
$('.mrselect').on('click', '.prov-li', function(){
	//将选中的省份赋值给span
	$($(this).parents().parents().parents().children()[0]).html($(this).text());
	//给span设置value属性
	var value = $(this).attr('value');
	$($(this).parents().parents().parents().children()[0]).attr('value', value);
	//将省份value写入current字符串中
	current.prov = value;
	//将城市和县区刷新
	$('#city').attr('value','');
	$('#country').attr('value','');
	$('#city').text('请选择');
	$('#country').text('请选择');
});
//选中一个城市
$('.mrselect').on('click', '.city-li', function(){
	$($(this).parents().parents().parents().children()[0]).html($(this).text());
	var value = $(this).attr('value');
	$($(this).parents().parents().parents().children()[0]).attr('value', value);
	current.city = value;
	$('#country').attr('value','');
	$('#country').text('请选择');
});
//选中一个地区
$('.mrselect').on('click', '.country-li', function(){
	$($(this).parents().parents().parents().children()[0]).html($(this).text());
	var value = $(this).attr('value');
	$($(this).parents().parents().parents().children()[0]).attr('value', value);
	current.country = value;
})

//用于保存当前所选的省市区
//点击省份li后,添加省份value,点击城市时,判断prov是否存在,
var current = {
	prov: '',
	city: '',
	country: ''
};

function showProv(){
	//btn.disabled = true;
	//城市数组的长度
	var len = provice.length;
	//遍历数组,在省份select中加入option,option中显示该省份的名称,option的value属性为省份的索引
	$($($($('#prov').parent().children()[2]).children()[0]).children()).remove();
	for (var i = 0; i < len; i++) {
		var provLi = "<li value='"+i+"' class='prov-li'>"+provice[i]['name']+"</li>";
		//在省份中append该li
		$($($('#prov').parent().children()[2]).children()[0]).append(provLi);
	}
}

/*根据所选的省份来显示城市列表*/
function showCity(obj) {
	//获取选中的省份的value
	var prov = $('#prov').attr('value');
	//如果省份还没有选中
	if(prov == ""){
		return;
	}
	var cityLen = provice[prov]["city"].length;
	$($($($('#city').parent().children()[2]).children()[0]).children()).remove();
	for (var j = 0; j < cityLen; j++) {
		var cityLi = "<li value='"+j+"' class='city-li'>"+provice[prov]["city"][j].name+"</li>";
		//在省份中append该li
		$($($(obj).parent().children()[2]).children()[0]).append(cityLi);
	}
}

/*根据所选的城市来显示县区列表*/
function showCountry(obj) {
	//获取选中的城市的value
	var city = $('#city').attr('value');
	//如果城市还没有选中
	if(city == ""){
		return;
	}
	var countryLen = provice[current.prov]["city"][city].districtAndCounty.length;
	$($($($('#country').parent().children()[2]).children()[0]).children()).remove();
	for (var n = 0; n < countryLen; n++) {
		var countryLi = "<li value='"+n+"' class='country-li'>"+provice[current.prov]["city"][city].districtAndCounty[n]+"</li>";
		//在省份中append该li
		$($($(obj).parent().children()[2]).children()[0]).append(countryLi);
	}
}
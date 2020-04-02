
//点击经过效果
var id = null;
$("#addressList").on('click',".in",function(){
	$(".on").removeClass("on");
	$(this).addClass("on");
	id = $(".on").attr("id");
	$("#ckaddress").prop("value",$(this).get(0).getAttribute("data-id"))
	$(".t3").text("配送至: "+$(this).get(0).getAttribute("data-address"))
	$(".t2").text("收货人: "+$(this).get(0).getAttribute("data-name"))
});
$(".clearfix").on("mouseenter",".on",function(){
	var res = $(this).children().hasClass("m-r-1");
	if(res){
		$(this).append(
			'<a href="javascript:;" class="tx js_pay_address_edit"></a>'+
			'<a href="javascript:;" class="gb js_pay_address_del"></a>'
		)
	}else{
		$(this).append(
				'<a class="m-r">设为默认</a>'+
			'<a href="javascript:;" class="tx js_pay_address_edit"></a>'+
			'<a href="javascript:;" class="gb js_pay_address_del"></a>'
		)
	}
	
})
$(".clearfix").on("mouseleave",".on",function(){
	var res = $(this).children().hasClass("m-r-1");
	if(res){
		$(".js_pay_address_edit").remove();
		$(".js_pay_address_del").remove();
	}else{
		$("#"+id+" a").remove();
	}
})




//默认地址
$("#addressList").on("click",".m-r",function(){
	$(".m-r").remove();
	$("#"+id).append(
		'<a title="默认地址" class="m-r m-r-1" style="background: #ccc;color: #fff;display: block;">&nbsp;&nbsp;默认地址&nbsp;&nbsp;</a>'
	)
		$.post('http://localhost:8080/lol/order/updateAddressStatic',{addressId:$("#ckaddress").prop("value")})
})

//删除地址
$("#addressList").on("click",".js_pay_address_del",function(){
	$("#"+id).remove();
	
})

//添加地址
$("#addressList").on('click','.tj',function(){
	$("#popup_pay_address").attr("style","width: 580px;display:block;visibility: visible;position: fixed;z-index: 9999;left: 50%;top: 50%;margin-top: -225px;margin-left: -291px;");
})

//添加窗口关闭
$(".popup-close").click(function(){
	$("#popup_pay_address").attr("style","display: none;");
})

//编辑窗口
$(".in").on("click",".js_pay_address_edit",function(){
	$("#popup_pay_address").attr("style","width: 580px;display:block;visibility: visible;position: fixed;z-index: 9999;left: 50%;top: 50%;margin-top: -225px;margin-left: -291px;");
})

//初始化收货地址信息
var initAddress = function(){
	$.ajax({
		url:'http://localhost:8080/lol/order/getAddress',
		method:'get',
		success:function (result) {
			$.each(result.data,function (index,address) {
				if(address.status==1){
					$('#addressList').append('<li data-name="'+address.takeName+" "+address.takePhone+'" data-address="'+address.takeAddress+'" data-id="'+address.addressId+'" class="in">' +
						'<span>'+address.takeName+'</span>' +
						'<p class="sg">'+address.takeAddress+'</p>' +
						'<p class="fl mr30">'+address.postcode+'</p>' +
						'<p class="fl mr30">'+address.takePhone+'</p>' +
						'<a title="默认地址" class="m-r m-r-1" style="background: #ccc;color: #fff;display: block;">&nbsp;&nbsp;默认地址&nbsp;&nbsp;</a>'+
						'</li>')
					$(".t3").text("配送至:"+address.takeAddress)
					$(".t2").text("收货人:"+address.takeName+" "+address.takePhone)
				}else {
					$('#addressList').append('<li data-name="'+address.takeName+" "+address.takePhone+'" data-address="'+address.takeAddress+'" data-id="'+address.addressId+'" class="in">' +
						'<span>'+address.takeName+'</span>' +
						'<p class="sg">'+address.takeAddress+'</p>' +
						'<p class="fl mr30">'+address.postcode+'</p>' +
						'<p class="fl mr30">'+address.takePhone+'</p>' +
						'</li>')
				}
			})
			$('#addressList').append('<li class="tj">' +
				'<a href="#"><em></em><b>新增收货地址</b></a>' +
				'</li>')
		}
	})
};

//初始化商品项
var initShopCar = function(){
 	$.ajax({
		url:'http://localhost:8080/lol/order/getUserShopCar',
		method:'get',
		success:function (result) {
			var shopCar = result.data;
			var count = 0;
			var priceall = 0;
			$.each(shopCar, function (index, data) {
				count=count+data.shopCount;
				priceall=priceall+data.shopCount*data.productSpecs.specsPrice;
				$("#shop-list").append('<tr class="shopcar-item">' +
					'<td>' +
					'<div class="img">' +
					'<div>' +
					'<a href="#">' +
					'<img src="http://49.234.120.206:8080/image/'+data.productSpecs.product.productMainImage+'" width="113" height="108" />' +
					'</a>' +
					'</div>' +
					'</div>' +
					'</td>' +
					'<td class="td-1">' +
					'<span>' +
					'<a href="" target="_blank">'+data.productSpecs.product.productName+'</a>' +
					'</span>' +
					'</td>' +
					'<td class="td-2" id="sku'+data.shopId+'">' +
					'                            </td>' +
					'                            <td>' +
					'                            <span>'+data.productSpecs.specsPrice.toFixed(2)+'元</span>' +
					'                            </td>' +
					'                            <td>' +
					'                                <span>' +
					'                                    '+data.shopCount+'' +
					'                                </span>' +
					'                            </td>' +
					'                            <td>' +
					'                                <b>                                   ' +
					'                                    '+(data.productSpecs.specsPrice*data.shopCount).toFixed(2)+'元' +
					'                                   ' +
					'                                </b>' +
					'                            </td>' +
					'</tr>')
				var skuvalue=data.productSpecs.productSpecs
				console.log(skuvalue)
				var jsonObj = JSON.parse(skuvalue)


				for (var key in jsonObj) {

					$("#sku"+data.shopId).append(
						'<span class="col">'+key+': '+jsonObj[key]+'</span>'
					)
				}


			})



			$("#sp-sum").text(count+"件商品，商品总价")
			$("#sp-price").text(priceall.toFixed(2)+" 元")
			$("#sp-sfk").text(priceall.toFixed(2)+" 元")
		}
	})
};

//页面加载事件
$(function () {
	//调用初始化收货地址函数
	initAddress();
	//调用初始化商品项函数
	initShopCar();
})

//添加收货地址
$("#tjbd").click(function(){
	$.post('http://localhost:8080/lol/order/addAddress',$("#addressform").serialize());
});

//提交订单
$("#add-order-pay").click(function(){
	var form = $('<form>\n' +
		'<input name="addressId" value="'+$("#ckaddress").val()+'"/>' +
		'<input name="orderRemarks" value="'+$("#order-remarks").val()+'"/>' +
		'</form>')
	form.attr('method','post');
	form.attr('action','http://localhost:8080/lol/order/placeOrder');
	$('body').append(form);  //将表单放置在web中
	form.submit();  //表单提交
	//$.get('http://localhost:8080/lol/order/placeOrder',{addressId:$("#ckaddress").val(),orderRemarks:$("#order-remarks").val()})
	//window.location.replace("http://localhost:8080/lol/order/placeOrder?addressId="+$("#ckaddress").val()+"&orderRemarks="+$("#order-remarks").val()+"");
})


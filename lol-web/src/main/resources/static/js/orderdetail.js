//程序入口
$(function(){
	init();
	cancelOrder();
	$.get("top.html", function (data) {
		$(".head_top").html(data);
	});
	$.get("bottom.html", function (data) {
		$(".bottom").html(data);
	});
})

function init(){
	var orderId = localStorage["orderId"];
	orderId = orderId;
	$.ajax({
		url:'user/order/query_info',
		type:'get',
		data:{'orderId': orderId},
		success:function(result){
			if(result.code != 200){
				alert(result.message);
				return;
			}
			//渲染状态模块
			rangeStatus(getStatusName(result.data.orderStatic));
			//给地址模块赋值
			$($($('.order-infobox').children()[0]).children()[1]).text(result.data.address.takeName);
			$($($('.order-infobox').children()[1]).children()[1]).text(result.data.address.takeAddress);
			$($($('.order-infobox').children()[2]).children()[1]).text(result.data.address.takePhone);
			//打印购物项信息
			$('.order-goodlist ul li').remove();
			var totalPrice = 0;
			var totalDiscounts = 0;
			$.each(result.data.details, function(index, details){
				console.log(details);
				//规格
				var jsonObj = JSON.parse(details.productSpecs.productSpecs);
				var specs = "";
				for (var name in jsonObj) {
					specs += name+"："+jsonObj[name]+"， "
				}
				specs = specs.substring(0,specs.length-2);
				//总价
				totalPrice += details.productSpecs.specsPrice * details.productCount;
				//总优惠价
				totalDiscounts += (details.productSpecs.specsPrice-details.productSpecs.promotionPrice) * details.productCount;
				$('.order-goodlist ul').append(
					"<li>"+
					"<div class='fl or-goodinfo'>"+
					"<a href='javascript:;'   style='display: block;' class='fl or-goodimg' target='_blank'>"+
					"<img src='https://game.gtimg.cn/images/zb/x5/uploadImg/goods/202001/20200102110657_67912.jpg' width='113' height='108' alt='商品图'>"+
					"</a>"+
					"<div class='fl or-gooddet'>"+
					"<p class='or-goodname'>"+details.productSpecs.product.productName+"</p>"+
					"<p class='or-goodcolor'>"+specs+"</p>"+
					"</div>"+
					"</div>"+
					"<div class='fl or-goodpri'>"+(details.productSpecs.specsPrice).toFixed(2)+"</div>"+
					"<div class='fl or-goodnum'>"+details.productCount+"</div>"+
					"</li>"
				);
				//打印总价和总优惠价
				$($($('#total-price').children()[0]).children()[1]).text('￥'+totalPrice);
				$($($('#total-price').children()[1]).children()[1]).text('-￥'+totalDiscounts);
				$($($('#total-price').children()[2]).children()[1]).text('￥'+0);
			})
		}
	})
}

//根据订单状态值返回状态名称
function getStatusName(orderStatus){
	if(orderStatus == 1){
		return "待付款";
	}else if(orderStatus == 2){
		return "待发货";
	}else if(orderStatus == 3){
		return "待收货";
	}else if(orderStatus == 4){
		return "待评价";
	}else if(orderStatus == 5){
		return "已完成";
	}else if(orderStatus == 6){
		return "已取消";
	}else if(orderStatus == 7){
		return "已过期";
	}
}

function rangeStatus(statusName){
	var allLine = $('.probar');
	var allStatus = $("div[class='order-process clearfix'] div i");
	var index = 0;
	if(statusName == "待发货"){
		index = 1;
		appendStatus("等待发货", "订单支付成功，等待商家发货");
	}else if(statusName == "待收货"){
		index = 2;
		appendStatus("等待收货", "商品已到达，等待接收");
	}else if(statusName == "待评价"){
		index = 3;
		appendStatus("等待评价", "商品已接收，期待用户评价");
	}else if(statusName == "待付款"){
		appendStatus("等待付款", "订单未支付，等待付款");
		$('.pro-infobox').append(
			'<a href="javascript:;" id="onlineService1" class="ce-btn btn-pay" target="_blank" title="联系客服">联系客服</a>'+
			'<a href="javascript:;" class="ce-btn btn-pay" title="立即付款">立即付款</a>'+
			'<a href="javascript:;" class="ce-btn btn-pay cancel-order" title="取消订单">取消订单</a>'
		);
	}else{
		appendStatus("订单关闭", "订单过期过取消，交易关闭");
	}
	if(index >= 1){
		$(allLine[0]).css('width', '100%');
		$(allLine[1]).css('width', '50%');
		$(allStatus[1]).attr('class', 'ico-center or-pay-yellow');
		if(index >= 2){
			$(allLine[1]).css('width', '100%');
			$(allLine[2]).css('width', '50%');
			$(allStatus[2]).attr('class', 'ico-center or-delivery-yellow');
			if(index >= 3){
				$(allLine[2]).css('width', '100%');
				$(allStatus[3]).attr('class', 'ico-center or-receipt-yellow');
			}
		}
	}
}

function appendStatus(statusName, content){
	$('.pro-infobox').html(
		"<div class='pro-info clearfix'>"+
			"<i class='ico-center ico-exmark'></i>"+
			"<span>当前状态："+statusName+"</span>"+
		"</div>"+
		"<p class='pro-tips'>"+content+"</p>"
	);
}

function cancelOrder(){
	var orderId = localStorage["orderId"];
	$('.pro-infobox').on('click', '.cancel-order', function(){
		//周围阴影和弹窗显示
		$('#confirm_panel').show();
		$('#_overlay_').show();
		$('#alert_cont').text('你确认要取消该订单吗?');
		//点击关闭或者取消,关闭弹窗和阴影
		$('.dj_bt_close, .dj_bt_cancel').on('click', function(){
			$('#confirm_panel').hide();
			$('#_overlay_').hide();
		});
		//点击确认
		$('.dj_bt_ok').on('click', function(){
			$.ajax({
				url:'user/order/update_status',
				type:'post',
				data:{'orderId': orderId, 'orderStatic.staticId': 1005},
				success:function(result){
					if(result.code == 200){
						init();
					}
				}
			})
			$('#confirm_panel').hide();
			$('#_overlay_').hide();
		})
	})
}
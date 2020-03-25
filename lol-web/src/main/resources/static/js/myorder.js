var userId = 1000;

function init(){
	$.ajax({
		url:'http://localhost:8080/lol/user/order/query_my_order',
		type:'get',
		data:{'pageNum' : 1, 'pageSize' : 5},
		success:function(result){
			console.info(result.data.list);
			load(result.data);
			page(result.data);
		}
	})
}
//加载页面
function load(pageInfo){
	$('#dj1 table tbody').remove();
	//如果没有订单,则显示
	if(pageInfo.list[0] == null){
		emptyOrder();
		return;
	}
	
	//遍历 PageInfo<List<OrderDetails>> 这个集合,listDetails就是该条订单下面的购物项集合
	$.each(pageInfo.list, function(indexs, listDetails){
		//获取该条订单的订单id
		var key = listDetails[0].orderInfo.orderId;
		//定义一个总价变量
		var totalPrice = 0;
		//遍历购物项,累加总价
		$.each(listDetails, function(index, getTotalPrice){
			//计算订单里面的所有购物项单价乘数量后的总价
			totalPrice += (getTotalPrice.productSpecs.specsPrice-getTotalPrice.productSpecs.promotionPrice) * getTotalPrice.goodNumber ;
		})
		//遍历购物项,打印数据
		$.each(listDetails, function(index, details){
			//如果当前遍历的是第一个购物项
			if(index == 0){
				//使用prepend()方法,让订单遍历在table的开头
				$('#dj1 table').append(
					"<tbody class='tbbr'>"+
						"<tr class='tbhead'>"+
							"<td colspan='5' class='tl'>"+
								"<span>供应商名称：爱联盟</span>"+
								"<span class='tb-padl'>订单号："+key+"</span>"+
								"<span class='tb-padl'>"+details.orderInfo.orderTime+"</span>"+
							"</td>"+
							"<td><a href='javascript:;'class='ico-center btn-del'>删除</a></td>"+
						"</tr>"+
						"<tr>"+
							"<td colspan='3' class='brl'>"+
								"<div>"+
									"<a href='#' style='display: block;' class='f1 relpic' target='_blank'>"+
										"<img src='https://game.gtimg.cn/images/zb/x5/uploadImg/goods/201911/20191112190040_40410.jpg' width='113' height='108' alt='商品图' />"+
									"</a>"+
									"<div class='f1 reinfo clearfix'>"+
										"<div class='relname'>"+details.product.productName+"</div>"+
										"<div class='reldesc'>"+details.productSpecs.productSpecs+"</div>"+
									"</div>"+
									"<div class='f1 reprice'>"+
										"<p class='re-newpri'>"+(details.productSpecs.specsPrice-details.productSpecs.promotionPrice)+"</p>"+
										"<p class='re-oldpri'>"+details.productSpecs.specsPrice+"</p>"+
									"</div>"+
									"<div class='f1 renum'>"+details.goodNumber+"</div>"+
								"</div>"+
							"</td>"+
							"<td rowspan='"+listDetails.length+"'>"+
								"<div class='re-total'>"+totalPrice+"</div>"+
								"<div class='re-freight'>（含运费：0.00）</div>"+
							"</td>"+
							"<td rowspan='"+listDetails.length+"'>"+
								"<div class='re-status'><font class='cff0 fb lh24'>"+details.orderInfo.orderStatic.staticName+"</font></div>"+
							"</td>"+
							"<td rowspan='"+listDetails.length+"'>"+
								"<a href='orderdetail.html' class='mt20 re-link' title='订单详情' target='_blank'>订单详情</a>"+
							"</td>"+
						"</tr>"+
					"</tbody>"
				);
			}//如果是第二个或之后的购物项目
			else if(index > 0){
				var idSpan = $("span[class='tb-padl']");
				$.each(idSpan, function(i, span){
					if(span.innerHTML.substring(4, span.innerHTML.length) == key){
						//如果这个span里的订单id是当前订单详情里的id,那么将之后的购物项打印到这个订单中
						$(span).parent().parent().parent().append(
							"<tr>"+
								"<td colspan='3' class='brl'>"+
									"<div>"+
										"<a href='#' style='display: block;' class='f1 relpic' target='_blank'>"+
											"<img src='https://game.gtimg.cn/images/zb/x5/uploadImg/goods/201911/20191112190040_40410.jpg' width='113' height='108' alt='商品图' />"+
										"</a>"+
										"<div class='f1 reinfo clearfix'>"+
											"<div class='relname'>"+details.product.productName+"</div>"+
											"<div class='reldesc'>"+details.productSpecs.productSpecs+"</div>"+
										"</div>"+
										"<div class='f1 reprice'>"+
											"<p class='re-newpri'>"+(details.productSpecs.specsPrice-details.productSpecs.promotionPrice)+"</p>"+
											"<p class='re-oldpri'>"+details.productSpecs.specsPrice+"</p>"+
										"</div>"+
										"<div class='f1 renum'>"+details.goodNumber+"</div>"+
									"</div>"+
								"</td>"+
							"</tr>"
						);
					}
				});
			}
		})
	})
}

//当订单为空或者查询出现错误时
function emptyOrder(){
	$('#dj1 table').remove();
	$('#dj2 div').remove();
	$('#dj3 div').remove();
	$('#dj4 div').remove();
	$('#dj5 div').remove();
	$('#dj1').append(
		"<div class='center-none'>"+
			"<i class='ico-none'></i>"+
			"<p class='none-tip'>您当前暂无 任何 订单哦！</p>"+
		"</div>"
	)
	$('#dj2').append(
		"<div class='center-none'>"+
			"<i class='ico-none'></i>"+
			"<p class='none-tip'>您当前暂无 任何 订单哦！</p>"+
		"</div>"
	)
	$('#dj3').append(
		"<div class='center-none'>"+
			"<i class='ico-none'></i>"+
			"<p class='none-tip'>您当前暂无 任何 订单哦！</p>"+
		"</div>"
	)
	$('#dj4').append(
		"<div class='center-none'>"+
			"<i class='ico-none'></i>"+
			"<p class='none-tip'>您当前暂无 任何 订单哦！</p>"+
		"</div>"
	)
	$('#dj5').append(
		"<div class='center-none'>"+
			"<i class='ico-none'></i>"+
			"<p class='none-tip'>您当前暂无 任何 订单哦！</p>"+
		"</div>"
	)
}

//分页
function page(pageInfo){
	$("#page").pagination(pageInfo.total, { //第一个参数指定共多少条记录
		items_per_page:pageInfo.pageSize, // 每页显示多少条记录
		next_text:">", //下一页按钮图标
		prev_text:"<", //上一页按钮图标
		num_display_entries:3,//主体页数
		num_edge_entries: 2, //边缘页数
		callback: function(index){//定义一个回调函数，用于每次点击页码发起分页查询请求
			console.info("到这");
			//index为当前页码，只不过下标是从0开始，因此需要+1操作
			var pageNum = ++index;
			$.ajax({
				url : 'http://localhost:8080/lol/user/order/query_my_order',
				type : 'get',
				data : {'pageNum' : pageNum, 'pageSize' : 5},
				success : function(result){
					console.info(result);
					load(result.data);
				}
			});
		}
	});
}

//删除订单
function delOrder(){
	$('#dj1 table').on('click', '.btn-del', function(){
		var order = $($(this).parent().parent().children()[0]).children()[1].innerHTML;
		var orderId = order.substring(4, order.length);
		$.ajax({
			url:'http://localhost:8080/lol/user/order/del_order',
			type:'post',
			data:{'orderId': orderId},
			success:function(result){
				console.info(result);
				if(result.code == 200){
					init();
				}
			}
		})
		
	})
}





//程序入口
$(function(){
	init();
	delOrder()
})
//注意:在通过状态id查询订单时,状态id是死的,如果数据库有修改,则这里也要改
//逻辑:1.查询所有订单: 查询数据库,将所有订单都查出,得到result,将result.data和dj1这个div传入load方法中,
//	先将dj1中的table中的数据删掉,然后判断是否存在订单,如果没有,则打印提示页面,否则打印出table和分页的div两个标签,
//	遍历result.data,遍历的数据是订单集合,得到订单id,同时定义一个总价变量,在遍历的同时累加,再遍历订单中的购物项,
//	如果是第一个购物项,则先打印,如果还有购物项,则再打印.然后调用page分页方法,查询数据后重新调用load加载方法.
//	2.查询不同状态订单: 


function init(){
	$.ajax({
		url:'user/order/query_my_order',
		type:'get',
		data:{'pageNum' : 1, 'pageSize' : 5},
		success:function(result){
			var pageId = '#dj1Page';
			load(result.data, $(pageId).parent().attr('id'));
			var url = 'user/order/query_my_order';
			page(result.data, $(pageId).parent().attr('id'), url, null);
		}
	})
}
//加载页面
function load(pageInfo, target){
	$('#'+target + ' table tbody').remove();
	//如果没有订单,则显示
	if(pageInfo == null || pageInfo.list[0] == null){
		emptyOrder(target);
		return;
	}else{
		printTable(target);
	}

	//遍历 PageInfo<OrderInfo> 这个集合,listDetails就是该条订单下面的购物项集合
	$.each(pageInfo.list, function(indexs, orderInfo){
		//获取该条订单的订单id
		var key = orderInfo.orderId;
		//定义一个总价变量
		var totalPrice = 0;
		//遍历购物项,累加总价
		$.each(orderInfo.details, function(index, detail){

			//计算订单里面的所有购物项单价乘数量后的总价
			totalPrice += (detail.productSpecs.specsPrice-detail.productSpecs.promotionPrice) * detail.goodNumber ;
		})
		//遍历购物项,打印数据
		$.each(orderInfo.details, function(index, details){

			var jsonObj = JSON.parse(details.productSpecs.productSpecs);
			var specs = "";
			for (var name in jsonObj) {
				specs += name+"："+jsonObj[name]+"， "
			}
			specs = specs.substring(0,specs.length-2);
			//如果当前遍历的是第一个购物项
			if(index == 0){
				//使用prepend()方法,让订单遍历在table的开头
				$('#'+ target + ' table').append(
					"<tbody class='tbbr'>"+
					"<tr class='tbhead'>"+
					"<td colspan='5' class='tl'>"+
					"<span>供应商名称：爱联盟</span>"+
					"<span class='tb-padl'>订单号："+key+"</span>"+
					"<span class='tb-padl'>"+orderInfo.orderTime+"</span>"+
					"</td>"+
					"<td><a href='javascript:;'class='ico-center btn-del'>删除</a></td>"+
					"</tr>"+
					"<tr>"+
					"<td colspan='3' class='brl'>"+
					"<div>"+
					"<a href='#' style='display: block;' class='f1 relpic' target='_blank'>"+
					"<img src='image/prodcutlist/"+details.product.productMainImage+"' width='113' height='108' alt='商品图' />"+
					"</a>"+
					"<div class='f1 reinfo clearfix'>"+
					"<div class='relname'>"+details.product.productName+"</div>"+
					"<div class='reldesc'>"+specs+"</div>"+
					"</div>"+
					"<div class='f1 reprice'>"+
					"<p class='re-newpri'>"+(details.productSpecs.specsPrice-details.productSpecs.promotionPrice)+"</p>"+
					"<p class='re-oldpri'>"+details.productSpecs.specsPrice+"</p>"+
					"</div>"+
					"<div class='f1 renum'>"+details.goodNumber+"</div>"+
					"</div>"+
					"</td>"+
					"<td rowspan='"+orderInfo.details.length+"'>"+
					"<div class='re-total'>"+totalPrice+"</div>"+
					"<div class='re-freight'>（含运费：0.00）</div>"+
					"</td>"+
					"<td rowspan='"+orderInfo.details.length+"'>"+
					"<div class='re-status'><font class='cff0 fb lh24'>"+orderInfo.orderStatic.staticName+"</font></div>"+
					"</td>"+
					"<td rowspan='"+orderInfo.details.length+"'>"+
					"<a href='orderdetail.html' class='mt20 re-link' title='订单详情' target='_blank'>订单详情</a>"+
					"</td>"+
					"</tr>"+
					"</tbody>"
				);
			}//如果是第二个或之后的购物项目
			else if(index > 0){
				var idSpan = $('#' + target + " span[class='tb-padl']");
				$.each(idSpan, function(i, span){
					if(span.innerHTML.substring(4) == key){
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
							"<div class='reldesc'>"+specs+"</div>"+
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
		});
		//通过不同状态,显示不同的标签.如待付款中添加 去支付 和 取消订单 两个标签
		var orderIndex = $('#'+target+' table tbody').length;
		var statusName = orderInfo.orderStatic.staticName;
		statusButton(statusName, orderIndex, target);
	})
}

//当订单为空或者查询出现错误时
function emptyOrder(target){
	$('#' + target+' div').remove();
	$('#' + target+' table').remove();
	$('#' + target).prepend(
		"<div class='center-none'>"+
		"<i class='ico-none'></i>"+
		"<p class='none-tip'>当前状态暂无 任何 订单哦！</p>"+
		"</div>"
	)
}

//再遍历订单集合时,如果存在订单,则需要执行下面操作
function printTable(target){
	$('#' + target+' table').remove();
	//$('#' + target+' div[id^=dj]').remove();
	$('#' + target).prepend(
		"<table width='100%'>"+
		"<thead>"+
		"<tr>"+
		"<th width='335' class='tl'>商品</th>"+
		"<th width='105'>单价（元）</th>"+
		"<th width='105'>数量</th>"+
		"<th width='150'>总计（元）</th>"+
		"<th width='150'>订单状态</th>"+
		"<th width='150'>操作</th>"+
		"</tr>"+
		"</thead>"+
		"</table>"
	);
}

//分页
function page(pageInfo, target, url, statusId){
	if(pageInfo == null){
		return;
	}
	var targetPageId = $('#'+target+" div[id^='dj']").attr('id');
	$('#'+targetPageId).pagination(pageInfo.total, { //第一个参数指定共多少条记录
		items_per_page:pageInfo.pageSize, // 每页显示多少条记录
		next_text:">", //下一页按钮图标
		prev_text:"<", //上一页按钮图标
		num_display_entries:3,//主体页数
		num_edge_entries: 2, //边缘页数
		callback: function(index){//定义一个回调函数，用于每次点击页码发起分页查询请求
			//index为当前页码，只不过下标是从0开始，因此需要+1操作
			var pageNum = ++index;
			var data = "";
			if(statusId == null){
				data = {'pageNum' : pageNum, 'pageSize' : 5};
			}else{
				data = {'statusId': statusId, 'pageNum': pageNum, 'pageSize': 5};
			}
			$.ajax({
				url : url,
				type : 'get',
				data : data,
				success : function(result){
					load(result.data, target);
				}
			});
		}
	});
}

//删除订单
function delOrder(){
	$('#con_order').on('click', '.btn-del', function(){
		var order = $($(this).parent().parent().children()[0]).children()[1].innerHTML;
		var orderId = order.substring(4);
		//周围阴影和弹窗显示
		$('#confirm_panel').show();
		$('#_overlay_').show();
		$('#alert_cont').text('你确认要删除该订单吗?');
		//点击关闭或者取消,关闭弹窗和阴影
		$('.dj_bt_close, .dj_bt_cancel').on('click', function(){
			$('#confirm_panel').hide();
			$('#_overlay_').hide();
		});
		//点击确认
		$('.dj_bt_ok').on('click', function(){
			$.ajax({
				url:'user/order/del_order',
				type:'post',
				data:{'orderId': orderId},
				success:function(result){
					if(result.code == 200){
						//$(this).parent().parent().parent().parent().parent().children()[1].remove();
						init();
						queryOrderByStatus(1000, 'dj2');
						queryOrderByStatus(1001, 'dj3');
						queryOrderByStatus(1002, 'dj4');
						queryOrderByStatus(1003, 'dj5');
					}
				}
			})
			$('#confirm_panel').hide();
			$('#_overlay_').hide();
		})
	})
}

//取消订单
function cancelOrder(){
	$('#con_order').on('click', '.btn-cancel', function(){
		var order = $($($(this).parent().parent().parent().children()[0]).children()[0]).children()[1].innerHTML;
		var orderId = order.substring(4);
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
						queryOrderByStatus(1000, 'dj2');
						queryOrderByStatus(1001, 'dj3');
						queryOrderByStatus(1002, 'dj4');
						queryOrderByStatus(1003, 'dj5');
					}
				}
			})
			$('#confirm_panel').hide();
			$('#_overlay_').hide();
		})
	})
}

/**
 * @param {Object} statusId 状态
 * @param {Object} target 需要打印的目标
 */
function queryOrderByStatus(statusId, target){
	$.ajax({
		url:'user/order/query_by_status',
		type:'get',
		data:{'statusId': statusId, 'pageNum': 1, 'pageSize': 5},
		success:function(result){
			if(result.data != null && result.data.total != null){
				var statusTarget = target.substring(2);
				$('#'+statusTarget+' span[class=topay]').remove();
				$('#'+statusTarget).append('<span class="ico-point topay" style="display: inline;">'+result.data.total+'</span>');
			}
			load(result.data, target);
			var url = 'user/order/query_by_status';
			page(result.data, target, url, statusId);
		}
	})
}

/**
 * @param {Object} statusName 状态名称
 * @param {Object} orderIndex 索引
 * @param {Object} target 目标
 */
function statusButton(statusName, orderIndex, target){
	var orderInfo = $($($('#'+target+' table').children()[orderIndex]).children()[1]).children()[3];
	if(statusName == '待付款'){
		$(orderInfo).append(
			"<a href='javascript:;' class='ce-btn btn-repay' title='去支付'>去支付</a>"+
			"<a href='javascript:;' class='ce-btn btn-repay btn-cancel' title='取消订单'>取消订单</a>"
		);
	}else if(statusName == '待收货'){
		$(orderInfo).append(
			"<a href='javascript:0;' class='ce-btn btn-repay' title='确定收货'>确定收货</a>"
		);
	}else if(statusName == '待评价'){
		$(orderInfo).append(
			"<a  href='javascript:;' class='ce-btn btn-repay' title='评价'>评价</a>"
			//如果已经评价,那么显示这条
			//"<a href='javascript:;' class='re-link' title='查看评价' >查看评价</a>"
		);
	}
}

//点击订单详情,
function clickOrderInfo(){
	$('#con_order').on('click', '.re-link', function(){
		var orderId = $($($($(this).parent().parent().parent().children()[0]).children()[0]).children()[1]).text();
		orderId = orderId.substring(4);
		console.info(orderId);
		//判断当前浏览器是否支持
		if (window.localStorage) {
			//存储变量的值
			//localStorage.setItem('orderId', orderId);
			localStorage.orderId = orderId;
		} else {
			alert("NOT SUPPORT");
		}
	})
}



//程序入口
$(function(){
	init();
	queryOrderByStatus(1000, 'dj2');
	queryOrderByStatus(1001, 'dj3');
	queryOrderByStatus(1002, 'dj4');
	queryOrderByStatus(1003, 'dj5');
	delOrder();
	clickOrderInfo()
	cancelOrder();
	$.get("top.html", function (data) {
		$(".head_top").html(data);
	});
	$.get("bottom.html", function (data) {
		$(".bottom").html(data);
	});
})
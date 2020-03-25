var userId = 1000;

//页面初始化,加载时需要查询所有的商品收藏
function init(){
	$.ajax({
		url:'http://127.0.0.1:8080/lol/user/collect/query_collect',
		type:'get',
		data:{'pageNum': 1, 'pageSize': 8},
		success:function(result){
			range(result.data);
			page(result.data);
		}
	})
}
//渲染页面
function range(pageInfo){
	$('#dj1 ul li').remove();
	//如果没有收藏
	if(pageInfo.list[0] == null){
		$('#dj1 ul').append(
			"<div class='collect-none' >"+
				"您还没有收藏任何周边呢，<a href='#' class='yellow'>快去逛逛吧></a>"+
			"</div>"
		);
	return;
	}
	$.each(pageInfo.list, function(index, collect){
		$('#dj1 ul').append(
			"<li>"+
				"<div class='top-img'>"+
					"<a class='colist-goodimg' target='_blank' href='javascript:0'>"+
						"<img src='https://game.gtimg.cn/images/zb/x5/uploadImg/goods/201812/20181219182251_97182.jpg' width='235' height='225' alt='商品图'/>"+
					"</a>"+
					"<div class='collect-action'>"+
						"<span style='display: none;' class='hidden_product_id'>"+ collect.product.productId +"</span>"+
						"<div class='collect-actzzc'></div>"+
						"<a href='javascript:0' class='btn-cancel'>取消收藏</a>"+
					"</div>"+
				"</div>"+
				"<p class='colist-name'>"+ collect.product.productName +"</p>"+
				"<div class='colist-pri clearfix'>"+
					"<span class='f1 colist-newpri'>￥"+(collect.product.productSpecsList[0].specsPrice-collect.product.productSpecsList[0].promotionPrice)+"</span>"+
					"<span class='f1 colist-oldpri'>￥"+collect.product.productSpecsList[0].specsPrice+"</span>"+
				"</div>"+
			"</li>");
	})
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
			//index为当前页码，只不过下标是从0开始，因此需要+1操作
			var pageNum = ++index;
			$.ajax({
				url : 'http://localhost:8080/lol/user/collect/query_collect',
				type : 'get',
				data : {'pageNum' : pageNum, 'pageSize' : 8},
				success : function(result){
					range(result.data);
				}
			});
		}
	});
}

//取消收藏
function cancelCollect(){
	<!-- 取消收藏的显示和隐藏 -->
	$("#dj1 ul").on("mouseover", ".top-img", function(){
		//var id = $(this).parent().parent().children()[0].innerHTML;
		$($(this).children()[1]).css("display", "block");
	});
	$("#dj1 ul").on("mouseleave", ".top-img", function(){
		$($(this).children()[1]).css("display", "none");
	})
	//<!-- 改变取消收藏的背景透明度 -->
	$("#dj1 ul").on("mouseover", ".collect-action", function(){
		$($(this).children()[0]).css("opacity", "0.7");
	});
	$("#dj1 ul").on("mouseleave", ".collect-action", function(){
		$($(this).children()[0]).css("opacity", "0.5");
	})
}


function implCancelCollect(){
	$('#dj1 ul').on('click', '.btn-cancel', function(){
		var productId = $(this).parent().children()[0].innerHTML;
		$.ajax({
			url:'http://127.0.0.1:8080/lol/user/collect/cancel_collect',
			type:'post',
			data:{'product.productId': productId, 'user.userId': userId},
			success: function(result){
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
	cancelCollect();
	implCancelCollect();
})


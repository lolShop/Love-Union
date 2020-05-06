
$(function(){
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if(r != null) return decodeURI(r[2]);
		return null;
	}
   $(".jqzoom").jqueryzoom({
		xzoom:230,
		yzoom:220,
	});
	$("#spec-list img:eq(0)").css({
		"border":"3px solid #f74a4a"
	})
	$("#spec-list img").bind("click",function(){
		var src=$(this).attr("src");
		$("#spec-n1 img").eq(0).attr({
			src:src.replace("\/n5\/","\/n1\/"),
			jqimg:src.replace("\/n5\/","\/n0\/")
		});
		$("#spec-list img").css({
			"border":""
		})
		$(this).css({
			"border":"3px solid #f74a4a"
		});
	})


	$(".pord-tab li").bind("click",function(){
		$(".pord-tab li").prop("class","")
		$(this).prop("class","curtab")
		$(".divNone").prop("style","display:none;")
		$(".divNone").eq($(this).index()).prop("style","")
	})
	//选择判断规格属性 找到SKU
	function skuColor(index){
		$("#blk_detail_main_spec").on("click","#"+index+" li",function () {
			var specs="";
			$("#"+index+" li").prop("class","hoverColor")
			$(this).addClass(" current")
			$("#blk_detail_main_spec li").each(function () {
				if ($(this).attr("class")!="hoverColor"){
					specs+='"'+$(this).parent().prev().text()+'"'+":"+'"'+$(this).text()+'"'+","
				}
			})
			$.ajax({
				url:"product/specs",
				method:"get",
				data:{"productId":$("#blk_detail_main_spec .product").prop("id"),"specs":specs},
				success: function (result) {
					$("#product strong").text(result.data.promotionPrice.toFixed(2));
					$("#product .pord-orpri").text("￥"+result.data.specsPrice.toFixed(2));
					$("#stock").text(result.data.specsStock)
				}
			})
		})

	}
	$(".btn_detail_main_buy_min").bind("click",function(){
		var num=$(".inpt_detail_main_buy_num").val();
		if(num>1){
			num--;
			$(".inpt_detail_main_buy_num").val(num);
		}
	})
	$(".btn_detail_main_buy_plus").bind("click",function(){
		var num=$(".inpt_detail_main_buy_num").val();
		$(".inpt_detail_main_buy_num").val(++num);
	})
	$.get("top.html", function (data) {
		$(".top").html(data);
	});
	$.get("bottom.html", function (data) {
		$(".bottom").html(data);
	});
	$.ajax({
		url:"product/detail",
		method:"get",
		data:{"productId":GetQueryString("productId")},
		success:function (result) {
			var typeArr=result.data.productType.productTypeName.split("\\");
			$.each(typeArr,function (i,type) {
					$("#blk_detail_crumbs").append("<a href='http://localhost:8080/lol/productlist.html?productName="+type+"' target=\"_blank\" class=\"index-link ml5\">"+type+"&gt;</a>")
			})
			$("#blk_detail_crumbs").append("<em>"+result.data.productName+"</em>");
			//动态显示商品信息
			$("#product").append("<span class='pord-name'>"+result.data.productName+"</span>" +
			"<div class='pord-price clearfix'>" +
			"<span class='fl pord-dispri'>￥<strong>"+result.data.productSpecsList[0].promotionPrice.toFixed(2)+"</strong></span>" +
			"<span class='fl pord-orpri'>￥"+result.data.productSpecsList[0].specsPrice.toFixed(2)+"</span>" +
			"</div>")
			//绑定商品ID
			$("#blk_detail_main_spec .product").prop("id",result.data.productId)
			//SKU的遍历
			var jsonObj=JSON.parse(result.data.productAttribute);
			for (var key in jsonObj)
			{
				var b=true;
				$("#blk_detail_main_spec").append("<div class='mt10 pord-color pord-sellist clearfix'>" +
					"<label class='fl'>"+key+"</label>" +
					"<ul class='fl ml28' id="+key+">" )
				skuColor(key)
				for(var value in jsonObj[key])
				{
					var keyClass="";
					if (b){
						keyClass="current";
						b=false;
					}
					$("#"+key+"").append(
						"<li  class='hoverColor "+keyClass+"'style='overflow:hidden;'><div class='pord-selbox'>"+jsonObj[key][value]+"</div></li>" +
						"</ul>" +
						"</div>")
				}
			}
			//图片组的显示
			$.each(result.data.productImageList,function (i,img) {
				if (i==0){
					$("#spec-n1 img").prop("src","image/detail/"+img.imageName);
					$("#spec-n1 img").attr("jqimg","image/detail/"+img.imageName)
				}
				$("#js-more-views img").eq(i).prop("src","image/detail/"+img.imageName);
			})

			//商品详细显示
			$("#blk_detail_tab_desc #par-name").text(result.data.productName);
			$("#blk_detail_tab_desc #time").text(result.data.productCreateTime);
			$("#blk_detail_tab_desc #weight").text(result.data.productWeight+"g");
			 $("#stock").text(result.data.productSpecsList[0].specsStock);
			 $("#productTypeName").text(result.data.productType.productTypeName)
		}
	})
	//收藏
	$(".pord-btn").on("click",".btn-collect",function () {
		//收藏表判断
	})
	//添加购物车
	$(".pord-btn").on("click","#btn_detail_cart_add",function () {
		$("#popup_detail_cart").show();
	})
	//继续购物
	$("#popup_detail_cart").on("click","#btn-continue",function () {
		$("#popup_detail_cart").hide();
	})
	//关闭
	$("#popup_detail_cart").on("click",".popup-close",function () {
		$("#popup_detail_cart").hide();
	})
})

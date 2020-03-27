//程序入口
$(function(){
	init();
})

function init(){
	var orderId = localStorage["orderId"];
	orderId = "'"+orderId+"'";
	$.ajax({
		url:'http://localhost:8080/lol/user/order/query_info',
		type:'get',
		data:{'orderId': orderId},
		success:function(result){
			console.info(result.data);
		}
	})
}















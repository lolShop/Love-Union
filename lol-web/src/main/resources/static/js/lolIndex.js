$(function () {
    $.ajax({
        url:"product/recommend",
        method:"get",
        data:{'pageNum':1, 'pageSize':4,"state":1002},
        success :function(result) {
        $.each(result.data.list,function(i,product) {
            $("#recommend").append("<li>" +
                "<a href='http://localhost:8080/lol/detail.html?productId="+product.productId+"' class='good-link' target='blank'>" +
                "<div class='ico-comm i-mark'>"+
                "<span class='mark-txt'>新品</span>" +
                "</div>" +
                "<img src='image/prodcutlist/"+product.productMainImage+"'width='280' height='268' alt='活动图'>" +
                "<div class='good-info'>" +
                "<p class='good-name'>"+product.productName+"</p>" +
                "<span class=\"new-pri\">￥"+product.productSpecsList[0].promotionPrice.toFixed(2)+"</span> \n" +
                "<span class=\"old-pri\">￥"+product.productSpecsList[0].specsPrice.toFixed(2)+"</span>\n" +
                "</div>" +
                "</a>" +
                "</li>"
        )
        })
    }
})
})
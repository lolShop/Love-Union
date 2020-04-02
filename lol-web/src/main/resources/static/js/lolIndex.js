$(function () {
    $.ajax({
        url:"product/recommend",
        method:"get",
        success :function(result) {
        $.each(result.data,function (i,product) {
            $("#recommend").append("<li>" +
                "<a href='http://localhost:8080/lol/detail.html?productId="+product.productId+"' class='good-link' target='blank'>" +
                "<div class='ico-comm i-mark'>"+
                "<span class='mark-txt'>新品</span>" +
                "</div>" +
                "<img src='image/prodcutlist/"+product.productMainImage+"'width='280' height='268' alt='活动图'>" +
                "<div class='good-info'>" +
                "<p class='good-name'>"+product.productName+"</p>" +
                "<span class='new-pri'>￥"+product.productSpecsList[0].promotionPrice.toFixed(2)+"</span>" +
                "<span class='old-pri'>￥"+product.productSpecsList[0].promotionPrice.toFixed(2)+"</span>" +
                "</div>" +
                "</a>" +
                "</li>"
        )
        })
    }
})
})
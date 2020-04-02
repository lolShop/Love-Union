$(function () {
    $("#search").on("click",function () {
        $.ajax({
            url:"product/search",
            method:"get",
            data:{"productName":$(".top-soso").val()},
            success:function (result) {

            }
        })
    })
})
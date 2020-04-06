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

    $.ajax({
        url:"user/info/query_info",
        method:"get",
        success:function (result) {
            if (result.data!=null){
                $(".unlogin").prop("style","display: none")
                $(".loginbox").prop("style","");
                $(".hdimg img").prop("src","image/user/default.jpg");
                $(".hdname").text(result.data.userName)
            }else {
                $(".unlogin").prop("style","")
                $(".loginbox").prop("style","display: none");
            }
        }
    })
    $("#login").on("click",function () {
        location.href="http://localhost:8080/lol/login.html";
    })

    $(".loginbox").hover(function(){
        $(".top-hover").prop("style","display: block")
    }, function() {
        $(".top-hover").prop("style","");
    });
    $(".top-hover").hover(function(){
        $(".top-hover").prop("style","display: block")
    }, function() {
        $(".top-hover").prop("style","");
    });
    $(".loginbox").on("click",function () {
        location.href="http://localhost:8080/lol/personal.html";
    })

})
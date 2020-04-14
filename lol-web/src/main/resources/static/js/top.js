$(function () {
    $("#search").on("click",function () {
        location.href="http://localhost:8080/lol/productlist.html?productName="+$(".top-soso").val()+"";
    })

    //用户头像
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
    //父类导航栏显示
    $.ajax({
        url:"product/parentId",
        method:"get",
        data:{"parentId":0},
        success:function (result) {
            $.each(result.data,function (i,type) {
                $("#blk_header_navbar").append(" <li class='cur'>" +
                    " <a href='http://localhost:8080/lol/productlist.html?productName="+type.productTypeName+"' class='ico-comm' title='"+type.productTypeName+"' target='_blank'>"+type.productTypeName+"</a>" +
                " </li>")
                $("#dj_class_ul").append(" <dd class='' type='cat' val='"+type.productTypeName+"'>"+type.productTypeName+"</dd>")
            })
        }
    })

    //登录页面
    $("#login").on("click",function () {
        location.href="http://localhost:8080/lol/login.html";
    })
    //登录经过
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
    //个人信息页面
    $(".loginbox").on("click",function () {
        location.href="http://localhost:8080/lol/personal.html";
    })

})
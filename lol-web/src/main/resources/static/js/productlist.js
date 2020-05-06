$(function () {
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r != null) return decodeURI(r[2]);
        return null;
    }
    var productName=GetQueryString("productName");
    $.get("top.html", function (data) {
        $(".top").html(data);
    });
    $.get("bottom.html", function (data) {
        $(".bottom").html(data);
    });
    function type(productName){
        $("#blk_header_navbar a").each(function () {
                $(this).attr("class","ico-comm");
            if ($(this).text()==productName){
                $(this).attr("class","ico-comm active");
            }
        })
    }
    //分页显示
    $.ajax({
        url:"product/search",
        method:"get",
        data:{'pageNum' : 1, 'pageSize' :2,"productName":productName},
        success :function(result) {
            page(result.data,productName);
            type(GetQueryString("productName"))
        }
    })
    //分页
    function page(data,sort){
        $('#box').paging({
            initPageNo: 1, // 初始页码
            totalPages: data.pageTotal, //总页数
            totalCount: '合计' + data.rows + '条数据', // 条目总数
            slideSpeed: 600, // 缓动速度。单位毫秒
            jump: true, //是否支持跳转
            callback: function(page) { // 回调函数
                $.ajax({
                    url:"product/search",
                    method:"get",
                    data:{'pageNum' : page, 'pageSize' :2,"productName":productName},
                    success :function(result) {
                        if (sort=="sort"){
                            addTable(data.list);
                        }else {
                            addTable(result.data.list);
                        }
                        type(GetQueryString("productName"))
                    }
                })
            }
        })
    };
    //渲染表格
    function addTable(list){
        $('#dj_goods_ul').empty();
        $.each(list,function (i,product) {
            $('#dj_goods_ul').append("<li>\n" +
                "<div class=\"ico-comm i-mark\">\n" +
                "<span class=\"mark-txt\">新品</span>\n" +
                "</div>\n" +
                "<a href=\"http://localhost:8080/lol/detail.html?productId="+product.productId+"\" target=\"_blank\" class=\"good-link\">\n" +
                "<div class=\"ico-comm i-mark\">\n" +
                "<span class=\"mark-txt\">新品</span>\n" +
                "</div>\n" +
                "<img src=\"image/prodcutlist/"+product.productMainImage+"\" width=\"280\" height=\"268\" alt=\"商品图\">\n" +
                "<div class=\"good-info\">\n" +
                "<p class=\"good-name\">"+product.productName+"</p>\n" +
                "<div class=\"good-pri\">\n" +
                "<span class=\"new-pri\">￥"+product.promotionPrice.toFixed(2)+"</span> \n" +
                "<span class=\"old-pri\">￥"+product.specsPrice.toFixed(2)+"</span>\n" +
                "</div>\n" +
                "</div>\n" +
                "</a>\n" +
                "</li>")
        })
    }
   ;
//类型选择不跳转页面
    $("#dj_class_ul").on("click","dd",function () {
        $("#dj_class_ul dd").prop("class","");
        $(this).prop("class","cur");
        productName=$(this).text();
        $.ajax({
            url:"product/search",
            method:"get",
            data:{'pageNum' : 1, 'pageSize' :2,"productName":productName},
            success :function(result) {
                page(result.data);
            }
        })
    })
    //价格、新品、销量 排序
    $("#sort").on("click","dd",function () {
        $("#sort dd").find("i").prop("class","ico-comm i-arrgray")
        $("#sort dd").prop("class","flt-arrow");
        $(this).prop("class","flt-arrow cur");
        if ($(this).attr("sort")==undefined||$(this).attr("sort")=="desc"){
            $(this).attr("sort","asc")
            $(this).find("i").prop("class","ico-comm i-arrgray i-arrbtop")
        }else if($(this).attr("sort")=="asc"){
            $(this).attr("sort","desc")
            $(this).find("i").prop("class","ico-comm i-arrgray i-arrbbot")
        }
        desc($(this).attr("id"),$(this).attr("sort"))
    })
    function desc(descName,sort) {
        //分页显示
        $.ajax({
            url:"product/sort",
            method:"get",
            data:{'pageNum' : 1, 'pageSize' :2,"descName":descName,"sort":sort,"productName":productName},
            success :function(result) {
                page(result.data,"sort");
            }
        })
    }
    //显示商品类型
    $("#typeName").text(productName);
})

$(function () {
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r != null) return decodeURI(r[2]);
        return null;
    }

    $.get("top.html", function (data) {
        $(".top").html(data);
    });
    $.get("bottom.html", function (data) {
        $(".bottom").html(data);
    });

    //分页显示
    $.ajax({
        url:"product/recommend",
        method:"get",
        data:{'pageNum' : 1, 'pageSize' : 16,"state":GetQueryString("state")},
        success :function(result) {
            page(result.data);
            addTable(result.data.list)
        }
    })

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
                "<span class=\"new-pri\">￥"+product.productSpecsList[0].promotionPrice.toFixed(2)+"</span> \n" +
                "<span class=\"old-pri\">￥"+product.productSpecsList[0].specsPrice.toFixed(2)+"</span>\n" +
                "</div>\n" +
                "</div>\n" +
                "</a>\n" +
                "</li>")
        })
    }
    //分页
    function page(data){
        $("#page").pagination(data.total, { //第一个参数指定共多少条记录
            items_per_page:data.pageSize, // 每页显示多少条记录
            next_text:"下一页", //下一页按钮图标
            prev_text:"上一页", //上一页按钮图标
            num_display_entries:3,//主体页数
            num_edge_entries: 2, //边缘页数
            callback: function(index){//定义一个回调函数，用于每次点击页码发起分页查询请求
                //index为当前页码，只不过下标是从0开始，因此需要+1操作
                var pageNum = ++index;
                $.ajax({
                    url:"product/recommend",
                    method:"get",
                    data:{'pageNum' : 1, 'pageSize' : 16,"state":GetQueryString("state")},
                    success :function(result) {
                        addTable(result.data.list)
                    }
                })
            }
        });
    };
})
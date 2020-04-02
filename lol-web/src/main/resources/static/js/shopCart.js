$(function () {
    $.ajax({
        url: "shopCart/listShopCart",
        method: "get",
        data: {"uid": "1000"},
        success: function (result) {
            //渲染购物车
            shopCart(result.data);
            //判断是否全选
            ifCheckAll();
        }
    })

    $.ajax({
        url: "shopCart/listNum",
        method: "get",
        data: {"uid": "1000"},
        success: function (result) {
            //渲染结算
            var count=0;
            var money=0;
            if(result.data != null){
                count = result.data.count;
                money = result.data.money;
            }
            $("#jscart").prepend(
                '<p>已选商品 <b class="total">'+count+'</b> 件&nbsp;&nbsp;总价：<b class="money">'+money.toFixed(2)+'元</b></p>'
            )

        }
    })

})


//结算
function js(){
    var xz =false;
    var count = 0;
    var money=0;
    $(".goodscheck").each(function (index, obj) {
        if($(this).is(".checked")){
            xz =true;
            var value = $(this).parent().parent().find(".mycart-item-price").text();
            var num =$(this).parent().parent().find(".inpt_cart_list_buy_num").attr("value");
            value=(value.substring(0,value.length-1))*1;
            money += value;
            num = num*1;
            count +=num;
        }
    })
    $("#jscart .total").empty();
    $("#jscart .money").empty();
    if(xz){
        $("#jscart .total").append(
            count
        )
        $("#jscart .money").append(
            money.toFixed(2)+'元'
        )
    }else{
        $("#jscart .total").append(
            '0'
        )
        $("#jscart .money").append(
            '0.00元'
        )
    }

}

//删除
$("#listShopCart").on("click",".btn-del",function () {
    var shopId =$(this).attr("value");
    var row = $(this).parent().parent().parent();
    delCart(shopId);
    $(row).remove();
    js();
})

//移入收藏夹
$("#listShopCart").on("click",".btn-fav",function () {
    $("#confirm_panel").attr("style","display: block; visibility: visible; position: fixed; z-index: 9999; left: 50%; top: 50%; margin-top: -75px; margin-left: -190px;");
    var productId = $(this).attr("value");
    var shopId = $(this).parent().find(".btn-del").attr("value");
    var row = $(this).parent().parent().parent();
    favCart(productId);
    $(".dj_bt_ok").click(function () {
        delCart(shopId);
        $(row).remove();
        $("#confirm_panel").attr("style","display: none;");

    })
    $(".dj_bt_cancel").click(function () {
        $("#confirm_panel").attr("style","display: none;");
    })
    js();
})
//增加数量
$("#listShopCart").on("click",".btn_cart_list_buy_plus",function () {

    var count = $(this).parent().find(".inpt_cart_list_buy_num").val()*1+1;
    if(count<=$(this).parent().find(".stock").val()*1){
        $(this).parent().find(".inpt_cart_list_buy_num").val($(this).parent().find(".inpt_cart_list_buy_num").val()*1+1);
        var text = $(this).parent().parent().parent().find(".price").text();
        var price = text.substring(0,text.length-1)*1;
        $(this).parent().parent().parent().find(".mycart-item-price").empty();
        $(this).parent().parent().parent().find(".mycart-item-price").append(
            (count * price).toFixed(2)+ '元'
        )
        addCount($(this).attr("value"));
        js();
    }else{
        alert("库存不足！")
    }

})

//减少数量
$("#listShopCart").on("click",".btn_cart_list_buy_min",function () {
    var shopId = $(this).attr("value");
    var count = $(this).parent().find(".inpt_cart_list_buy_num").val()*1-1;
    if(count<=0){

    }else{
        $(this).parent().find(".inpt_cart_list_buy_num").val($(this).parent().find(".inpt_cart_list_buy_num").val()*1-1);
        var text = $(this).parent().parent().parent().find(".price").text();
        var price = text.substring(0,text.length-1)*1;
        $(this).parent().parent().parent().find(".mycart-item-price").empty();
        $(this).parent().parent().parent().find(".mycart-item-price").append(
            (count * price).toFixed(2)+ '元'
        )
        decreaseCount(shopId);
        js();
    }

})



function shopCart(list) {
    $.each(list, function (index, obj) {
        $("#listShopCart").append(
            '<div class="mycart-item" value="1">' +
            '<div class="fli spi-1">' +
            '<span id="c'+obj.shopId+'" value="'+obj.shopId+'" class="goodscheck input-check "><i></i></span>' +
            '</div>' +
            '<div class="fli spi-2">' +
            '<div class="mycart-item-img">' +
            '<img src="image/shoppingCartImage/' + obj.productSpecs.product.productMainImage + '"></img>' +
            '</div>' +
            '</div>' +
            '<div class="fli spi-3">' +
            '<p class="mycart-item-name">' +
            '<a>' + obj.productSpecs.product.productName + '</a>' +
            '</p>' +
            '<p class="mycart-item-em"></p>' +
            '</div>' +
            '<div class="fli spi-4">' +
            '<div class="mycart-item-sel disabled" id="pro'+obj.shopId+'">' +

            '</div>' +
            '</div>' +
            '<div class="fli spi-5">' +
            '<p class="mycart-item-per">' +
            '<s>' + obj.productSpecs.specsPrice.toFixed(2) + '元</s><br/>' +
            '<font class="price">' + obj.productSpecs.promotionPrice.toFixed(2) + '元</font>' +
            '</p>' +
            '</div>' +
            '<div class="fli spi-6" id="s' + obj.shopId + '">' +
            '<div class="mycart-item-count">' +
            '<span class="num-minus btn_cart_list_buy_min" value="' + obj.shopId + '">-</span>' +
            '<input type="text" id="value' + obj.shopId + '" class="inpt_cart_list_buy_num" value="' + obj.shopCount + '" oninput="value=value.replace(/[^\d]/g)">' +
            '<input type="hidden" class="stock" value="'+obj.productSpecs.specsStock+'">'+
            '<span class="num-plus btn_cart_list_buy_plus" value="' + obj.shopId + '">+</span>' +
            '</div>' +
            '</div>' +
            '<div class="fli spi-7" id="xj'+obj.shopId+'">' +
            '<p class="mycart-item-price" >' +
            '' + (obj.productSpecs.promotionPrice * obj.shopCount).toFixed(2) + '元' +
            '</p>' +
            '</div>' +
            '<div class="fli spi-8" id="df' + obj.shopId + '">' +
            '<div class="mycart-item-tg">' +
            '<a class="btn-del" value="' + obj.shopId + '" href="javascript:;">删除</a>' +
            '<a class="btn-fav" value="'+obj.productSpecs.product.productId+'" href="javascript:;">移入收藏夹</a>' +
            '</div>' +
            '</div>' +
            '</div>'
        )
        if(obj.shopState==1){
            $("#c"+obj.shopId).addClass("checked");
        }

        var jsonObj = JSON.parse(obj.productSpecs.productSpecs);
        for (var key in jsonObj) {
            $("#pro"+obj.shopId).append(
                '<p style="margin-bottom: 0px">'+key+':'+jsonObj[key]+'</p>'
            )
        }
    })
}


function addCount(shopId) {
    $.ajax({
        url: 'shopCart/addCount',
        method: 'get',
        data: {'shopId': shopId},
        success: function () {
        }

    })
}

function decreaseCount(shopId) {
    $.ajax({
        url: 'shopCart/decreaseCount',
        method: 'get',
        data: {'shopId': shopId},
        success: function () {
        }

    })
}

function delCart(shopId) {
    $.ajax({
        url: 'shopCart/delShopCart',
        method: 'get',
        data: {'shopId': shopId},
        success: function (result) {
        }

    })
}
function favCart(pid) {
    $.ajax({
        url: 'user/favCollect',
        method: 'get',
        data: {'uid': 1000,'pid':pid},
        success: function (result) {
        }

    })
}

//收藏框关闭
$(".dj_bt_close").click(function(){
    $("#confirm_panel").attr("style","display: none;");
})

//结算
$(".mycart-tb-btn").click(function () {
    $.ajax({
        url:"shopCart/settlement",
        method:"get",
        data:{"uid":1000},
        success:function () {

        }
    })
})
//取消
$("#listShopCart").on("click",".goodscheck", function () {
    var shopId = $(this).attr("value");
    if($(this).hasClass("checked")){
        $(this).removeClass("checked");
        select(0,shopId)
    }else{
        $(this).addClass("checked");
        select(1,shopId)
    }
    ifCheckAll()
    js();
})
//全选/取消方法
function checkAll(state,uid){
    $.ajax({
        url:"shopCart/checkAll",
        method:"get",
        data:{"state":state,"uid":uid},
        success:function () {
        }
    })
}



//全选/取消
$("#ck-all").on("click", function () {
    $(".input-check").each(function (index, obj) {

        if(index == 0){
            if(!$(this).is(".checked")){
                $(this).addClass("checked");
                checkAll(1,1000);
            }else{
                $(this).removeClass("checked");
                checkAll(0,1000);
            }
        }else{
            if($("#ck-all").is(".checked")){
                $(this).addClass("checked");
            }else{
                $(this).removeClass("checked");
            }
        }
    })
    js();
})


//判断是否全选
function ifCheckAll  () {
    var ica = true;
    if($("#listShopCart").find(".mycart-item")[0] ==undefined){
        ica = false;
    }else{
        $(".goodscheck").each(function (index, obj) {
            if (!$(this).is(".checked")) {
                $("#ck-all").removeClass("checked")
                ica = false;
            }
        })
    }

    if(ica){
        $("#ck-all").addClass("checked");
    }else{
        $("#ck-all").removeClass("checked");
    }
};

//选中
function select(state,shopId){
    $.ajax({
        url:"shopCart/selectShopCart",
        method:"get",
        data:{"state":state,"shopId":shopId},
        success:function () {
        }
    })
}


//导入导航栏和底部样式
$(function () {

    $.get("top.html", function (data) {
        $(".top").html(data);
    });
    $.get("bottom.html", function (data) {
        $(".bottom").html(data);
    });
})


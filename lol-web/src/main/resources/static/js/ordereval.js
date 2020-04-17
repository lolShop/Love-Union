//评论弹出关闭
$(".dj_bt_close").click(function () {
    $("#alert_panel").attr("style","display: none;")
})

//评论成功
$(".dj_bt_buy").click(function () {
    location.href="http://localhost:8080/lol/myorder.html"
})


$(function(){
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r != null) return decodeURI(r[2]);
        return null;
    }
    $.ajax({
        url:"comment/ordereval",
        method:"get",
        data: {"comId":GetQueryString("comId")},
        success:function (result) {
            $("#pl1").attr("style","display: none;");
            $("#pl2").attr("style","display: block;");
            $(".eval-goods").append(
                '<div class="eval-goodimg">'+
                '<a href="/wuxia/detail.shtml?id=6588" style="display: block">'+
                '<img src="image/prodcutlist/'+result.data.product.productMainImage+'" width="113" height="108" alt="商品图">'+
                '</a>'+
                '</div>'+
                '<p class="eval-goodname">'+result.data.product.productName+'</p>'
            )
            xj(result.data.description);
            $(".eval-comt").append(
                '<p class="fl eval-txt">'+result.data.content+'</p>'
            )
            $.each(result.data.commentPhotos, function (index, obj) {
                $(".eval-imgul").append(
                    '<li>'+
                    '<div class="eval-imgdel">'+
                    '<div class="imgdel-zzc"></div>'+
                    '<a href="javascript:;" class="btn-imgdel" title="删除">删除</a>'+
                    '</div>'+
                    '<img src="https://cffile.oss-cn-beijing.aliyuncs.com/image/'+obj.photo+'" alt="商品图" width="64" height="64">'+
                    '</li>'
                )
            })
        }
    })

    //点击哪个菜单，就变色
    $('.ct-ordertab li').on('click', function(){
        $('.ct-ordertab li').removeClass('current');
        $(this).addClass('current');
    });

    //点击哪个菜单，就出现相应内容
    $('.ct-ordertab li').on('click', function(){
        //获取当前点击的li元素在ul中的索引
        var index = $('.ct-ordertab li').index(this);
        //点击的li对应的div的id
        var id = 'dj'+(index+1);
        //所有的div
        var all = $('#con_order').children();
        all.css('display', 'none');
        $('#'+id).css('display', 'block');
    });
})


var imgs = $('.eval-star img');
for(var i=0;i<imgs.length;i++){
    imgs[i].onmouseover = function(e){
        var id =parseInt(this.id);
        for(var j=1;j<6;j++){
            if(j<id){
                $("#"+j).attr('src', 'image/ordereval/star-on.png');
            }else if(j==id){
                if(e.offsetX < $('#'+id).width()/2){
                    $("#"+j).attr('src', 'image/ordereval/star-half.png');
                }else{
                    $("#"+j).attr('src', 'image/ordereval/star-on.png');
                }
            }else if(j>id){
                $("#"+j).attr('src', 'image/ordereval/star-off.png');
            }
        }

    }
    imgs[i].onmousemove = function(e){
        var id =parseInt(this.id);
        for(var j=1;j<6;j++){
            if(j<id){
                $("#"+j).attr('src', 'image/ordereval/star-on.png');
            }else if(j==id){
                if(e.offsetX < $('#'+id).width()/2){
                    $("#"+j).attr('src', 'image/ordereval/star-half.png');
                    $("#score").val(j-0.5);
                }else{
                    $("#"+j).attr('src', 'image/ordereval/star-on.png');
                    $("#score").val(j);
                }
            }else if(j>id){
                $("#"+j).attr('src', 'image/ordereval/star-off.png');
            }
        }
    }
}
//匿名评论
$(".eval-check").click(function () {
    if($(this).hasClass("eval-checked")){
        $(this).removeClass("eval-checked");
    }else{
        $(this).addClass("eval-checked");
    }
})
var formData = new FormData($("#f1")[0]);
var num =0;
var  index=0;
$(".ico-upload").on("change",".btn-upload",function () {
    var icon = $(".upload-icon");
    var formData1 = new FormData(),
        fs = $("#file")[0].files;
    var max_size = 1024 * 1024 * 2;
    var yz = true;
    for (var i = 0; i < fs.length; i++) {
        var d = fs[i]
        if(d.size <= max_size){  //文件必须小于100M
            if(/.(jpg|png)$/.test(d.name)){
                formData1.append("file", fs[i]);  //文件上传处理
            }else{
                alert('上传文件必须是jpg或png！')
                yz = false
            }
        }else{
            alert('上传文件过大！')
            yz = false
        }
    }
    if(yz){
        for(var i=0;i<$(".btn-upload")[0].files.length;i++){
            var name = "file"+num;
            formData.append(name,$(".btn-upload")[0].files[i]);
            num++;
            $(".eval-imgul").append(
                '<li>'+
                '<div class="eval-imgdel">'+
                '<div class="imgdel-zzc"></div>'+
                '<a href="javascript:;" class="btn-imgdel" title="删除">删除</a>'+
                '</div>'+
                '<img class="blogMainImg" width="64" height="64" alt="商品图">'+
                '</li>'
            )
            var file = $(".btn-upload")[0].files[i];
            var reader = new FileReader();
            reader.onload = function (e) {
                $('.blogMainImg:eq('+index+')').attr("src",e.target.result);
                index++;
            };
            reader.readAsDataURL(file);
        }
    }
})
//删除照片
$(".eval-imgul").on("click",".btn-imgdel",function () {
    var index1 = $(this).parent().parent().index();
    var key ="file"+index1;
    index--;
    formData.delete(key);
    $(this).parent().parent().remove();
})


//星级显示
function xj(xj){
    var xs;
    var y = String(xj).indexOf(".") + 1;//获取小数点的位置
    var count = String(xj).length - y;//获取小数点后的个数
    if(y > 0) {
        xs=true;
    } else {
        xs=false;
    }
    if(!xs){
        for(var i=0;i<5;i++){
            var j=i+6;
            if(xj>i){
                $("#"+j).attr('src','image/ordereval/star-on.png');
            }else{
                $("#"+j).attr('src','image/ordereval/star-off.png');
            }
        }
    }else{
        for(var i=1;i<6;i++){
            var j=i+5;
            if(xj>i){
                $("#"+j).attr('src', 'image/ordereval/star-on.png');
            }else if(xj+0.5==i){
                $("#"+j).attr('src', 'image/ordereval/star-half.png');
            }else{
                $("#"+j).attr('src', 'image/ordereval/star-off.png');
            }
        }
    }
}




//发表评论
$(".btn-publish").click(function () {
    var size = $(".eval-imgul li").size();
    var file  =new Array(size);
    for(var i=0;i<$(".eval-imgul li").size();i++){
        var name ="file"+i;
        file[i] = formData.get(name);
    }
    var data = new FormData();
    for(var i=0;i<file.length;i++){
        data.append("files",file[i])
    }
    if($(".eval-area").val()==""){
        $(".eval-area").val("默认好评")
    }
    if(!$(".blogMainImg").length>0){
        $.ajax({
            url:"comment/publishComment",
            type:"post",
            data:{"description":$("#score").val(),"content":$(".eval-area").val(),"userId":1000,"detailsId":1000,"comState":$(".eval-check").hasClass("eval-checked")},
            success:function (result) {
                $("#alert_panel").attr("style","display: block;");
            }
        })
    }else{
        $.ajax({
            url:"comment/ajax_upload",
            type:"post",
            data:data,
            processData : false,
            contentType : false,
            success:function () {
                $.ajax({
                    url:"comment/publishComment",
                    type:"post",
                    data:{"description":$("#score").val(),"content":$(".eval-area").val(),"userId":1000,"detailsId":1000,"photo":"1","comState":$(".eval-check").hasClass("eval-checked")},
                    success:function (result) {
                        $("#alert_panel").attr("style","display: block;");
                    }
                })
            }
        })


    }

})
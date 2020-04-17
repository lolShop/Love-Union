$(function () {
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r != null) return decodeURI(r[2]);
        return null;
    }
    $.ajax({
        url:"comment/listComment",
        type:"post",
        data:{"pid":1000,'pageNum' : 1, 'pageSize' :10},
        success:function (result) {
            addTable(result.data);
            page(result.data);
        }
    })
    $.ajax({
        url:"comment/fs",
        type:"post",
        data:{"pid":GetQueryString("productId")},
        success:function (result) {
            var xj=0;
            if(result.data.toFixed(1)*1==result.data.toFixed(0)*1){
                xj=result.data.toFixed(1)*1;
            }else if(result.data.toFixed(1)<parseInt(result.data)*1+0.5){
                xj =parseInt(result.data)*1+0.5;

            }else if(parseInt(result.data)+0.5<(result.data.toFixed(1))<parseInt(result.data)+1){
                xj =parseInt(result.data)*1+1;
            }
            $(".all-comment").append(
                '<span class="fl">玩家综合评分：</span>'+
                '<span class="pord-starall fl">'+
                '<i class="ico-detail i-star'+(xj)*2+'"></i>'+
                '</span>'+
                '<span class="fl all-point">'+xj.toFixed(1)+'</span>'
            )
        }
    })
})
function addTable(pageInfo){
    $(".cent-ul li").remove();
    $.each(pageInfo.list, function(index, obj){
        $(".cent-ul").append(
            '<li id="li'+obj.comId+'">'+
            '<div class="fl headimg" >'+
            '<div class="imgbox" id="img'+obj.comId+'">'+
            '</div>'+

            '</div>'+
            '<div class="fl comment-text">'+
            '<div class="comment-point clearfix">'+
            '<span class="fl">商品评分</span>'+
            '<div class="fl pord-starbox">'+
            '<i class="ico-detail i-star'+obj.description*2+'"></i>'+
            '</div>'+
            '<span class="fl all-point ml5">'+obj.description.toFixed(1)+'分</span>'+
            '<div class="fr comment-time">'+obj.comDate+'</div>'+
            '</div>'+
            '<p class="text-detail">'+obj.content+'</p>'+
            '<div class="roll-list-box clearfix">'+
            '<ul class="roll-list" id="s'+obj.comId+'">'+
            '</ul>'+
            '</div>'+
            '</li>'
        )
        //是否匿名
        if(obj.comState){
            $("#img"+obj.comId).append(
                '<img src="image/user/default.jpg" width="58" height="58" title="匿名用户">'
            )
            $("#img"+obj.comId).parent().append(
                '<p class="qqnick">匿名用户</p>'
            )
        }else{
            $("#img"+obj.comId).append(
                '<img src="image/user/'+obj.user.photo+'" width="58" height="58" title="'+obj.user.userName+'">'
            )
            $("#img"+obj.comId).parent().append(
                '<p class="qqnick">'+obj.user.userName+'</p>'
            )
        };
        var dt =false;
        $.each(obj.commentPhotos, function(index, cp){
            if(cp.photo!=null){
                dt=true;
                $("#s"+obj.comId).append(
                    '<li class="simg'+index+'" >'+
                    '<div class="simgbox">'+
                    '<img src="https://cffile.oss-cn-beijing.aliyuncs.com/image/'+cp.photo+'" width="100" height="100" alt="商品图">'+
                    '</div>'+
                    '</li>'
                )

            }
        })
        if(dt){
            if(obj.commentPhotos.length==1){
                $("#li"+obj.comId).find(".comment-text").append(
                    '<div class="lightbox" style="display: block;">'+
                    '<a class="lightbox-close" href="javascript:;"><i class="ico-detail"></i></a>'+
                    '<div class="cent-bimgbox clearfix">'+
                    '<ul class="pic-box">'+
                    '</ul>'+
                    '</div>'+
                    '</div>'
                )
            }else{
                $("#li"+obj.comId).find(".comment-text").append(
                    '<div class="lightbox" style="display: none;">'+
                    '<a class="lightbox-close" href="javascript:;"><i class="ico-detail"></i></a>'+
                    '<div class="cent-bimgbox clearfix">'+
                    '<ul class="pic-box">'+
                    '</ul>'+
                    '</div>'+
                    '<a href="javascript:;" class="ico-detail btn-arrow btn-prev" style="display: inline;"></a>'+
                    '<a href="javascript:;" class="ico-detail btn-arrow btn-next" style="display: inline;"></a>'+
                    '</div>'
                )
            }

            $.each(obj.commentPhotos, function(index, cp){
                $("#li"+obj.comId).find(".pic-box").append(
                    '<li class="limg'+index+'" style="display: none;">'+
                    '<img src="https://cffile.oss-cn-beijing.aliyuncs.com/image/'+cp.photo+'" width="400" height="400" alt="商品图">'+
                    '</li>'
                )
            })
        }
    });
}


//分页方法
function page(pageInfo){
    $("#page").pagination(pageInfo.total, { //第一个参数指定共多少条记录
        items_per_page:pageInfo.pageSize, // 每页显示多少条记录
        next_text:"下一页", //下一页按钮图标
        prev_text:"上一页", //上一页按钮图标
        num_display_entries:4,//主体页数
        num_edge_entries: 2, //边缘页数
        callback: function(index){//定义一个回调函数，用于每次点击页码发起分页查询请求
            //index为当前页码，只不过下标是从0开始，因此需要+1操作
            var pageNum = ++index;
            $.ajax({
                url : 'comment/listComment',
                type :'post',
                data : {'pageNum':pageNum,'pageSize':10,"pid":1000},
                success : function(result){
                    //渲染表格
                    addTable(result.data);
                }
            });
        }
    });
}
//点击图片显示大图
$(".cent-ul").on("click",".simgbox",function () {
    $(this).parent().parent().parent().parent().parent().parent().find(".on").removeClass("on");
    $(this).parent().parent().find(".on").removeClass("on");
    $(this).parent().parent().parent().parent().parent().parent().find(".lightbox").attr("style","display:none");
    $(this).parent().parent().parent().parent().parent().find(".lightbox").find(".pic-box li").attr("style","display:none");
    $(this).parent().parent().parent().parent().parent().find(".lightbox").attr("style","display:block");
    $(this).parent().addClass("on");
    $(this).parent().parent().parent().parent().parent().find(".lightbox").find(".limg"+$(this).parent().index()).attr("style","display:block");
    if($(this).parent().index()==0){
        $(this).parent().parent().parent().parent().parent().find(".btn-prev").attr("style","display:none");
        $(this).parent().parent().parent().parent().parent().find(".btn-next").attr("style","display:block");
    }else if($(this).parent().index()==$(this).parent().parent().parent().parent().parent().find(".lightbox").find(".pic-box li").size()-1){
        $(this).parent().parent().parent().parent().parent().find(".btn-prev").attr("style","display:block");
        $(this).parent().parent().parent().parent().parent().find(".btn-next").attr("style","display:none");
    }else if($(this).parent().parent().parent().parent().parent().find(".lightbox").find(".pic-box li").size()==1){
        $(this).parent().parent().parent().parent().parent().find(".btn-prev").attr("style","display:none");
        $(this).parent().parent().parent().parent().parent().find(".btn-next").attr("style","display:none");
    }else{
        $(this).parent().parent().parent().parent().parent().find(".btn-prev").attr("style","display:block");
        $(this).parent().parent().parent().parent().parent().find(".btn-next").attr("style","display:block");
    }

})

//下一张大图
$(".cent-ul").on("click",".btn-next",function () {
    var x = $(this).parent().parent().find(".on").index()+1;
    $(this).parent().parent().find(".on").removeClass("on");
    $(this).parent().parent().find(".simg"+x).addClass("on");
    $(this).parent().find(".pic-box li").attr("style","display:none");
    $(this).parent().find(".limg"+x).attr("style","display:block");
    $(this).parent().find(".btn-prev").attr("style","display:block");
    if(x>$(this).parent().find(".pic-box li").size()-2){
        $(this).attr("style","display:none");
    }
})

//上一张大图
$(".cent-ul").on("click",".btn-prev",function () {
    var x = $(this).parent().parent().find(".on").index()-1;
    $(this).parent().find(".btn-next").attr("style","display:block");
    $(this).parent().parent().find(".on").removeClass("on");
    $(this).parent().parent().find(".simg"+x).addClass("on");
    $(this).parent().find(".pic-box li").attr("style","display:none");
    $(this).parent().find(".limg"+x).attr("style","display:block");
    if(x<1){
        $(this).attr("style","display:none");
    }
})

//关闭大图
$(".cent-ul").on("click",".lightbox-close",function () {
    $(this).parent().attr("style","display:none");
})
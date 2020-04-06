$(function () {
    $.ajax({
        url : 'admin/product/list_adminProductAll',
        type : 'get',
        data : {'pageNum' : 1, 'pageSize' : 10},
        success : function(result){
            if(result.code == 500){
                alert(result.message);
            }else{
                addTable(result.data);
                page(result.data);
            }
        }
    });
});

function addTable(pageInfo){
    $('#tab tr:not(:first)').remove();
    $.each(pageInfo.list, function(index, obj){
        var updateTime;
        var pState;
        var jsonObj=JSON.parse(obj.productAttribute);
        var jsonProductAttribute="";
        for (var key in jsonObj)
        {
            jsonProductAttribute+=key+":";
            for(var value in jsonObj[key])
            {
                jsonProductAttribute+="　"+jsonObj[key][value];
            }
            jsonProductAttribute+="; ";
        }
        if(obj.productUpdateTime==undefined){
             updateTime = "无修改";
        }else{
             updateTime = obj.productUpdateTime;
        }
        if(obj.productState=1){
            pState = "正常";
        }
        else{
            pState = "缺货";
        }
        $('#tab').append(
            '<tr>'+
            '<td>'+
            '<label class="lyear-checkbox checkbox-primary">'+
            '<input type="checkbox" name="ids[]" value="1"><span></span>'+
            '</label>'+
            '</td>'+
            '<td>'+obj.productId+'</td>'+
            '<td>'+obj.productType.productTypeId+'</td>'+
            '<td>'+obj.productName+'</td>'+
            '<td>'+obj.productCreateTime+'</td>'+
            '<td>'+updateTime+'</td>'+
            '<td>'+obj.productMainImage+'</td>'+
            '<td>'+obj.productParticular+'</td>'+
            '<td>'+obj.productWeight+'g</td>'+
            '<td>'+jsonProductAttribute+'</td>'+
            '<td><font class="text-success">'+pState+'</font></td>'+
            '<td>'+
            '<div class="btn-group">'+
            '<a class="btn btn-xs btn-default" id="'+obj.productId+'" onclick="getProductById(this)" title="编辑" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>'+
            '<a class="btn btn-xs btn-default" id="'+obj.productId+'" onclick="delProductById(this)" title="删除" data-toggle="tooltip"><i class="mdi mdi-window-close"></i></a>'+
            '</div>'+
            '</td>'+
            '</tr>'
        );
    });
}


//模态框获取当前点击的商品
function getProductById(i) {
    $(function(){
        var id=$(i).prop('id');
        $.ajax({
            url:'admin/product/getProductById?productId='+id,
            method:'get',
            success:function (result) {
                if(result.code==200){
                    $('#mymodal').modal('show');
                    $('#inputProductId').val(result.data.productId);
                    $('#inputProductName').val(result.data.productName);
                    $('#inputProductParticular').val(result.data.productParticular);
                    $('#inputProductWeight').val(result.data.productWeight);
                    //$('#inputProductMainImage').val(result.data.productMainImage);
                }
            }
        })
    })
}

//删除
function delProductById(i) {
    $(function(){
        var id=$(i).prop('id');
        $.ajax({
            url:'admin/product/adminProduct_del?productId='+id,
            method:'post',
            success:function (result) {
                if(result.code==200){
                    location.href = result.message;
                }else{
                    alert("删除失败!");
                }
            }
        });
    });
}

//分页渲染
function page(pageInfo){
    $("#page").pagination(pageInfo.total, { //第一个参数指定共多少条记录
        items_per_page:pageInfo.pageSize, // 每页显示多少条记录
        next_text:">", //下一页按钮图标
        prev_text:"<", //上一页按钮图标
        num_display_entries:5,//主体页数
        num_edge_entries: 2, //边缘页数
        callback: function(index){//定义一个回调函数，用于每次点击页码发起分页查询请求
            //index为当前页码，只不过下标是从0开始，因此需要+1操作
            var pageNum = ++index;
            $.ajax({
                url : 'admin/product/list_adminProductAll',
                method : 'get',
                data : {'pageNum' : pageNum, 'pageSize' : 10},
                success : function(result){
                    addTable(result.data);
                }
            });
        }
    });
}
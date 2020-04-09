//查询数据
$(function(){
    $.ajax({
        url: 'admin/user/ListUser?pageNum=1&pageSize=10',
        method: 'GET',
        success: function(result){
            //添加数据到表格
            addTable(result.data.list);
            //回调分页
            page(result.data);
        }
    });
})

//添加表格数据
function addTable(list){
    //先移除原有的表格数据
    $("#tab tr:not(#head)").remove();
    //循环追加数据
    $.each(list, function(index,obj){
        $('#tab').append(
            '<tr>' +
            '<td class="table_class">' +
            '<label class="lyear-checkbox checkbox-primary">'+
            '<input type="checkbox" value="'+obj.userId+'"><span></span>'+
            '</label>'+
            '</td>' +
            '<td>'+obj.userId+'</td>' +
            '<td>'+obj.userPhone+'</td>' +
            '<td>'+obj.userName+'</td>' +
            '<td><img src="images/users/default.jpg" style="height: 40px;width: 40px"/></td>' +
            '<td id="'+obj.userId+'"></td>' +
            '<td>'+obj.birthday+'</td>' +
            '<td>'+obj.phone+'</td>' +
            '<td>'+obj.email+'</td>' +
            '<td>'+
            '<div class="btn-group">'+
            '<a class="btn btn-xs btn-default" href="#!" title="编辑" data-toggle="tooltip" id="'+obj.userId+'" onclick="updateUser(this)"><i class="mdi mdi-pencil"></i></a>'+
            '<a class="btn btn-xs btn-default" href="#!" title="删除" data-toggle="tooltip"  id="'+obj.userId+'" onclick="delUser('+obj.userId+')"><i class="mdi mdi-window-close"></i></a>'+
            '</div>'+
            '</td>'+
            '</tr>'
        );
        //判断男女
        if (obj.sex==true){
            $("#"+obj.userId).append(
                '男'
            )
        }else {
            $("#"+obj.userId).append(
                '女'
            )
        }
    });
}


//分页方法
function page(data){
    //选择器选中分页的div容器，并调用pagination方法来设置分页控件
    $("#page").pagination(data.total, { //第一个参数指定共多少条记录
        items_per_page : 10, // 每页显示多少条记录
        next_text : "下一页", //下一页按钮图标
        prev_text : "上一页", //上一页按钮图标
        num_display_entries : 5,//主体页数
        num_edge_entries : 2, //边缘页数
        callback : function(index){//定义一个回调函数，用于每次点击页码发起分页查询请求
            var pageNum = ++index;
            $.get("admin/user/ListUser?pageNum="+pageNum+"&pageSize=10", function(result){
                addTable(result.data.list);
            });
        }
    });
}

//删除
function delUser(uid){
    var msg = "真的要删除吗？"
    //alert(uid)
    if(confirm(msg) == true){
        $.ajax({
            url:'admin/user/delUser',
            type:'post',
            data:{'uid':uid},
            success:function (result) {
                // var row = $(this).parent().parent().parent().parent().parent().parent();
                // alert(row);
                // $(row).remove();
            }

        })
    }
}
//添加数据,选中按钮class
$('.btn-add').click(function () {
    $.ajax({
        url:'admin/user/addUser',
        type:'post',
        //选中from表单的id
        data:$("#addAdminUser").serialize(),
    })
})

//将数据放到模态框
function updateUser(i) {
    $(function(){
        var uid = $(i).prop('id');
        alert(uid)
        $.ajax({
            url: 'admin/user/getUserId?userId=' + uid,
            method: 'get',
            success: function (result) {
                if (result.code == 200) {
                    //显示模态框
                    $('#myModal').modal('show');
                    $('#updateUserId').val(result.data.userId)
                    $('#updateUserPhone').val(result.data.userPhone);
                    $('#updatePassword').val(result.data.password);
                    $('#updateUserName').val(result.data.userName);
                    $('#updatePhoto').val(result.data.photo);
                    $('#updateSex').val(result.data.sex);
                    $('#updateManSex').val(result.data.sex);
                    $('#updateBirthday').val(result.data.birthday)
                    $('#updatePhone').val(result.data.phone);
                    $('#updateEmail').val(result.data.email);
                }
            }
        })
    })
}

//修改数据
$('.btn-update').click(function () {
    $.ajax({
        url:'admin/user/updateUser',
        type:'get',
        //选中from表单的id
        data:$("#updateWorkModel").serialize(),
    })
})

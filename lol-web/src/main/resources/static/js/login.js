$(function(){
    $('#loginBut').on('click', function(){
        $.ajax({
            url : '../author_login',
            data : $('#f1').serialize(),
            type : 'post',
            success : function(result){
                if(result.code == 200){
                    alert("登陆成功!");
                    location.href = result.message;
                }else{
                    alert("账号或密码错误");
                }
            }
        });
    });
})
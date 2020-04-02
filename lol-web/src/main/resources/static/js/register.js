$(function(){
    $('#registerBut').on('click', function(){
        if($('#rePassword').val() != $('#password').val()){
            alert("两次密码不一致");
        }else{
            $.ajax({
                url : '../author_register',
                data : $('#f1').serialize(),
                type : 'post',
                success : function(result){
                    if(result.code == 200){
                        alert("注册成功!");
                        location.href = result.message;
                    }else{
                        alert("此账号已被注册,请重新注册");
                        location.href = "auth-register.html";
                    }
                }
            });
        }
    });
})
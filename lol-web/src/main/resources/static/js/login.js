$(function(){
    $('#loginBut').on('click', function(){
        $.ajax({
            url : 'user/info/user_login',
            data : $('#f1').serialize(),
            type : 'post',
            success : function(result){
                if(result.code == 200){
                    alert("登陆成功!");
                    location.href="http://localhost:8080/lol/index.html";
                }else if(result.code == 500){
                    alert("账号或密码错误");
                    location.href = result.message;
                }
            }
        });
    });
})
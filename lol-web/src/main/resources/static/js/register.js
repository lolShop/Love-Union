$(function(){
    $('#registerBut').on('click', function(){
        if($('#u_name').val() == "" || $('#password').val() == "" ||$('#phone').val() == ""){
            alert("请正确填写注册信息");
        }
        if($('#rePassword').val() != $('#password').val()){
            alert("两次密码不一致");
        }else{
            $.ajax({
                url : 'user/info/user_register',
                data : $('#f1').serialize(),
                type : 'post',
                success : function(result){
                    if(result.code == 200){
                        alert("注册成功!");
                        location.href="http://localhost:8080/lol/login.html";
                    }else if(result.code == 500){
                        alert(result.message);
                        $('#u_name').val("");
                        $('#password').val("");
                        $('#phone').val("");
                        $('#rePassword').val("");
                    }
                }
            });
        }
    });
})
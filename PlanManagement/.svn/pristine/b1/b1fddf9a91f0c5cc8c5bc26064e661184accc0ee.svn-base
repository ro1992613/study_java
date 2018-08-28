/**
 * Created by wangyanjun on 2016/9/24 0024.
 */
$(function() {
    $('body').on("keydown",function (event) {
        if(event.keyCode==13){
            $("#loginBtn").click();
        }
    });
    $('#log-form').validator({
        onValid: function(validity) {
            $(validity.field).closest('.am-input-group').find('.am-alert').hide();
        },

        onInValid: function(validity) {
            var $field = $(validity.field);
            var $group = $field.closest('.am-input-group');
            var $alert = $group.find('.am-alert');
            // 使用自定义的提示信息 或 插件内置的提示信息
            var msg = $field.data('validationMessage') || this.getValidationMessage(validity);

            if (!$alert.length) {
                $alert = $('<div class="log-alert am-alert am-alert-danger am-radius"></div>').hide().
                appendTo($group);
            }

            $alert.html(msg).show();
        }
    });
    $("#loginBtn").on("click", function() {
        console.log("123")
        var form = document.forms[0];
        var user = form.loginName.value;
        var pwd = form.loginPwd.value;
        user = user.trim();
        pwd = pwd.trim();
        if (!user || !pwd) {
            $(".errorAlert").fadeIn().html("用户名或密码不能为空");
            var errorAlert = $(".errorAlert");
            setTimeout(function() {
                errorAlert.fadeOut();
            }, 1000);
            return;
        } else {
            $.post(router+"/login/login",{"user_name":user,"user_pass":pwd},function (data) {
                if(data.code==1){
                    location.href="/page/index.html"
                }else{
                    alert(data.data);
                }
            });
        }
    });
    document.onkeyup = function(e) {
        if (e.keyCode === 13) {
            $(".lgBtn").click();
        }
    };
});

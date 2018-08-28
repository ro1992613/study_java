/**
 * Created by wangyanjun on 2016/9/24 0024.
 */
$(function() {
    $('body').on("keydown",function (event) {
        if(event.keyCode==13){
            $("#registerBtn").click();
        }
    });
    $('#re-form').validator({
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
    $("#registerBtn").on("click", function() {
        var form = document.forms[0];
        var user = form.registerName.value;
        var alias = form.registerAlias.value;
        var pwd = form.registerPwd.value;
        var pwd_ = form.registerPwd_.value;
        user = user.trim();
        pwd = pwd.trim();
        pwd_=pwd_.trim();
        if(pwd==pwd_){
            if (!user || !pwd || !alias) {
                $(".errorAlert").fadeIn().html("用户名或密码不能为空");
                var errorAlert = $(".errorAlert");
                setTimeout(function() {
                    errorAlert.fadeOut();
                }, 1000);
                return;
            } else {
                $.post(router+"/login/register",{"user_name":user,"user_pass":pwd,"user_alias":alias},function (data) {
                    if(data.code==1){
                        location.href="/login.html"
                    }else{
                        alert(data.data);
                    }
                });
            }
        }
    });
    document.onkeyup = function(e) {
        if (e.keyCode === 13) {
            $("#registerBtn").click();
        }
    };
});

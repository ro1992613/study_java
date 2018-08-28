$(function() {
    var path=location.pathname;
    $.post(router+"/login/check_login_status",{},function (data) {
        if(data.code!=1){
            location.href="/login.html";
        }else{

        }
    });
});

function logout() {
    $.post(router+"/login/logout",{},function (data) {
        location.href="/login.html";
    });
}
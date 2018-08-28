var selected_user_id;
var selected_user_name;

var app=new Vue({
    el: '#app',
    data: {
        user_name:undefined,
        user_id:undefined,
        vue_user_list:[],
        vue_role_list:[],
        page_index:1,
        page_count:1
    },
    methods:{
        delete_user:function (user_id) {
            var r=confirm("确认删除！");
            if(r==true){
                $.post(router+"/rightmanage/user/deleteUser",{"user_id":user_id},function (data) {
                    getAllUsers(1);
                });
            }
        },
        edit_roles:function (user_id) {
            selected_user_id=user_id;
            app.$data.user_id=user_id;
            $.post(router+"/rightmanage/user/getUserInfo",{"user_id":user_id},function (data) {
                app.$data.user_name=data.data.user_name;
                selected_user_name=data.data.user_name;
            });
            $.post(router+"/rightmanage/user/getRolesByUserId",{"user_id":user_id},function (data) {
                var node=$("#role_list_content input");
                [].map.call(node,function (v) {
                    v.checked=false;
                    for(var i in data.data){
                        if(data.data[i]==v.name){
                            v.checked=true;
                            continue;
                        }
                    }
                })
            });
        },
        show_update_user_modal:function(user_id){
            if(user_id){
                $.post(router+"/rightmanage/user/getUserInfo",{"user_id":user_id},function (data) {
                    if(data.code==1){
                        $("#update_user_id").val(data.data.user_id);
                        $("#update_user_name").val(data.data.user_name);
                        $("#update_user_alias").val(data.data.user_alias);
                        $("#update_remark").val(data.data.remark);
                        var $modal = $('#update_user_modal');
                        $modal.modal();
                    }else{
                        alert("获取用户信息失败！");
                    }
                });
            }else {
                alert("修改用户信息失败！");
            }
        },
        show_users_first:function () {
            getAllUsers(1);
        },
        show_users_last:function () {
            if(app.$data.page_index>1){
                app.$data.page_index=app.$data.page_index-1;
            }
            getAllUsers(app.$data.page_index);
        },
        show_users_skip:function () {
            var page=parseInt($("#input_page_no").val());
            if(isNaN(page)){
                page=1;
            }else if(page<1){
                page=1;
            }else if(page>=app.$data.page_count){
                page=app.$data.page_count;
            }
            app.$data.page_index=page;
            getAllUsers(app.$data.page_index);
        },
        show_users_next:function () {
            if(app.$data.page_count<app.$data.page_index){
                app.$data.page_index=app.$data.page_index+1;
            }
            getAllUsers(app.$data.page_index);
        },
        show_users_final:function () {
            getAllUsers(app.$data.page_count);
        }
    },
    created: function () {
        getAllUsers(1);
        getAllRoles();
    }
});

function getAllRoles() {
    $.post(router+"/rightmanage/role/getAllRoles",{},function (data) {
        if(data.code==1){
            app.$data.vue_role_list=data.data;
        }else{
            app.$data.vue_role_list=[];
        }
    });
}

function getAllUsers(page_no) {
    $.post(router+"/rightmanage/user/getAllUsers",{"page_no":page_no},function (data) {
        if(data.code==1){
            app.$data.vue_user_list=data.data.result;
            app.$data.page_count=data.data.totalPageCount;
            app.$data.page_index=data.data.pageNo;
        }else{
            app.$data.vue_user_list=[];
            app.$data.page_count=1;
            app.$data.page_index=1;
        }
    });
}

function show_add_user_modal() {
    var $modal = $('#add_user_modal');
    $modal.modal();
}

function add_user() {
    var add_user_name=$("#add_user_name").val();
    var add_user_alias=$("#add_user_alias").val();
    var add_user_pass=$("#add_user_pass").val();
    var add_user_pass_=$("#add_user_pass_").val();
    var add_remark=$("#add_remark").val();
    if(add_user_name && add_user_alias && add_user_pass && add_user_pass_ && add_user_pass==add_user_pass_){
        $.post(router+"/rightmanage/user/addUser",{"user_name":add_user_name,"user_alias":add_user_alias,"user_pass":add_user_pass,"remark":add_remark},function (data) {
            $("#add_user_name").val("");
            $("#add_user_alias").val("");
            $("#add_user_pass").val("");
            $("#add_user_pass_").val("");
            $("#add_remark").val("");
            getAllUsers(1);
        });
    }else{
        alert("输入信息不完整！");
    }
    var $modal = $('#add_user_modal');
    $modal.modal('close');
}

function update_user_role() {
    if(selected_user_id){
        var node=$("#role_list_content input");
        var role_id="";
        for(var i=0;i<node.length;i++){
            if(node[i].checked==true){
                role_id=role_id+","+node[i].name;
            }
        }
        $.post(router+"/rightmanage/user/updateUserRoles",{"user_id":selected_user_id,"role_id":role_id},function (data) {
            app.$data.user_id=undefined;
            app.$data.user_name=undefined;
            selected_user_name=undefined;
            if(data.code==1){
                alert("成功！");
            }else {
                alert("失败！");
            }
        });
    }
}

function update_user() {
    var user_id= $("#update_user_id").val();
    var user_name=$("#update_user_name").val();
    var user_alias= $("#update_user_alias").val();
    var user_pass= $("#update_user_pass").val();
    var remark= $("#update_remark").val();
    if(user_name && user_alias && user_pass && user_id){
        $.post(router+"/rightmanage/user/updateUser",{"user_id":user_id,"user_name":user_name,"user_alias":user_alias,"user_pass":user_pass,"remark":remark},function (data) {
            $("#update_user_id").val("");
            $("#update_user_name").val("");
            $("#update_user_alias").val("");
            $("#update_user_pass").val("");
            $("#update_remark").val("");
            getAllUsers(1);
        });
    }else{
        alert("输入信息不完整！");
    }
    var $modal = $('#update_user_modal');
    $modal.modal('close');
}

function test() {
    var node=$("#role_list_content input");
    console.log(node);
    [].map.call(node,function (v) {
        console.log(v.name);
        if(v.name==1){
            v.checked=false;
        }
    })
}


var permission_data;
var selected_role_id;
var app=new Vue({
    el: '#app',
    data: {
        vue_role_list:[],
        role_name:'点击角色添加权限'
    },
    methods:{
        delete_role:function (role_id) {
            if(role_id){
                var rs=confirm("确定删除！");
                if(rs==true){
                    $.post(router+"/rightmanage/role/deleteRole",{"role_id":role_id},function (data) {
                        getAllRoles();
                    });
                }
            }
        },
        show_update_role_modal:function (role_id) {
            if(role_id){
                $.post(router+"/rightmanage/role/getRoloInfo",{"role_id":role_id},function (data) {
                    if(data.code==1){
                        $("#update_role_id").val(data.data.role_id);
                        $("#update_role_name").val(data.data.role_name);
                        $("#update_remark").val(data.data.remark);
                        var $modal = $('#update_role_modal');
                        $modal.modal();
                    }else {
                        alert("获取角色信息失败！");
                    }

                });
            }
        },
        set_role_permission:function (role_id) {
            if(role_id){
                $.post(router+"/rightmanage/role/getRoloInfo",{"role_id":role_id},function (data) {
                    app.$data.role_name='为角色'+data.data.role_name+'添加权限';
                });
                $.post(router+"/rightmanage/permission/getPermissionsByRoleId",{"role_id":role_id},function (data) {
                    selected_role_id=role_id;
                    for(var i in permission_data){
                        permission_data[i].checked=false;
                        for(var j in data.data){
                            if(permission_data[i].id==data.data[j].permission_id){
                                permission_data[i].checked=true;
                                permission_data[i].open=true;
                                continue;
                            }
                        }
                    }
                    var setting = {
                        check: {
                            chkboxType : {"Y": "", "N": ""},
                            enable: true
                        },
                        data: {
                            simpleData: {
                                enable: true
                            }
                        },
                        callback: {
                            onCheck: function (event, treeId, treeNode) {
                                // var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
                                // console.log(treeObj)
                                // console.log(event);
                                // console.log(treeNode);
                                // console.log(treeObj.getCheckedNodes())
                            }
                        },
                        view: {
                            fontCss: setFontCss
                        }
                    };
                    $.fn.zTree.init($("#permissionTree"), setting, permission_data);
                    var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
                    treeObj.expandAll(true);
                });
            }
        },
        update_role_permission:function () {
            if(selected_role_id){
                var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
                var nodes=treeObj.getCheckedNodes(true);
                var permission_ids="";
                for(var i=0;i<nodes.length;i++){
                    permission_ids=permission_ids+","+nodes[i].id;
                }
                $.post(router+"/rightmanage/role/updateRolePermissions",{"role_id":selected_role_id,"permission_id":permission_ids},function (data) {
                    selected_role_id=undefined;
                    app.$data.role_name='点击角色添加权限';
                    alert("保存成功！");
                });
            }
        }
    },
    created: function () {
        getAllRoles();
    }
});
$(function () {
    $.post(router+"/rightmanage/permission/getAllPermissions",{},function (data) {
        permission_data=data.data;
        var setting = {
            check: {
                chkboxType : {"Y": "", "N": ""},
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onCheck: function (event, treeId, treeNode) {
                    var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
                    console.log(treeObj)
                    console.log(event);
                    console.log(treeNode);
                    console.log(treeObj.getCheckedNodes())
                }
            },
            view: {
                fontCss: setFontCss
            }
        };
        $.fn.zTree.init($("#permissionTree"), setting, permission_data);
        var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
        treeObj.expandAll(true);
        $('.tpl-skiner-content-bar').find('span').on('click', function() {
            $('body').attr('class', $(this).attr('data-color'))
            saveSelectColor.Color = $(this).attr('data-color');
            $.fn.zTree.init($("#permissionTree"), setting, permission_data);
            var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
            treeObj.expandAll(true);
        })
    })
});
function setFontCss(treeId, treeNode) {
    var selectColor=$('body').attr('class');
    var color="red";
    if(selectColor=='theme-black'){
        color="white";
    }
    if(selectColor=='theme-white'){
        color="black";
    }
    return {"color":color,"background-color":"transparent","border":"0"};
};

function getAllRoles() {
    $.post(router+"/rightmanage/role/getAllRoles",{},function (data) {
        if(data.code==1){
            app.$data.vue_role_list=data.data;
        }else{
            app.$data.vue_role_list=[];
        }
    });
}

function show_add_role_modal() {
    var $modal = $('#add_role_modal');
    $modal.modal();
}

function add_role() {
    var add_role_name=$("#add_role_name").val();
    var add_remark=$("#add_remark").val();
    if(add_role_name){
        $.post(router+"/rightmanage/role/addRole",{"role_name":add_role_name,"remark":add_remark},function (data) {
            getAllRoles();
        });
    }else{
        alert("输入信息不完整！");
    }
    var $modal = $('#add_role_modal');
    $modal.modal('close');
}

function update_role() {
    var role_id=$("#update_role_id").val();
    var role_name=$("#update_role_name").val();
    var remark=$("#update_remark").val();
    if(add_role_name){
        $.post(router+"/rightmanage/role/updateRole",{"role_id":role_id,"role_name":role_name,"remark":remark},function (data) {
            $("#update_role_id").val("");
            $("#update_role_name").val("");
            $("#update_remark").val("");
            getAllRoles();
        });
    }else{
        alert("输入信息不完整！");
    }
    var $modal = $('#update_role_modal');
    $modal.modal('close');
}
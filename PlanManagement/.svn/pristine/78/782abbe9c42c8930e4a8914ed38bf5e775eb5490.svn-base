var app=new Vue({
    el: '#app',
    data: {
        permission:{
            permission_father_id:undefined,
            permission_id:undefined,
            permission_name:undefined,
            permission_type:undefined,
            permission_url:undefined
        }
    },
    methods:{

    },
    created: function () {
    }
});

$(function () {
    getAllPermission();
});

function getAllPermission() {
    $.post(router+"/rightmanage/permission/getAllPermissions",{},function (data) {
        permission_data=data.data;
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onRemove: function (event, treeId, treeNode) {
                    var r=confirm("确定删除该权限");
                    if(r==true){
                        $.post(router+"/rightmanage/permission/deletePermission",{"permission_id":treeNode.id},function (data) {
                            app.$data.permission.permission_id=undefined;
                            app.$data.permission.permission_name=undefined;
                            app.$data.permission.permission_type=undefined;
                            app.$data.permission.permission_url=undefined;
                            app.$data.permission.permission_father_id=undefined;
                            getAllPermission();
                        });
                    }
                },
                onClick: function (event, treeId, treeNode) {
                    $.post(router+"/rightmanage/permission/getPermissionInfo",{"permission_id":treeNode.id},function (data) {
                        app.$data.permission.permission_id=data.data.permission_id;
                        app.$data.permission.permission_name=data.data.permission_name;
                        app.$data.permission.permission_type=data.data.permission_type;
                        app.$data.permission.permission_url=data.data.permission_url;
                        app.$data.permission.permission_father_id=data.data.permission_father_id;
                    });
                }
            },
            view: {
                fontCss: setFontCss,
                addHoverDom: addHoverDom,
                removeHoverDom: removeHoverDom
            },edit:{
                enable: true,
                showRemoveBtn:true,
                showRenameBtn:false,
                removeTitle:"删除",
                renameTitle:"编辑"
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
}

function addHoverDom(treeId, treeNode) {
    //nomal
    //var style='margin-right: 2px;background-position:-144px 0; vertical-align: top;';
    //metro
    var style='margin-right: 2px;background-position:-189px 0; vertical-align: top;';
    var aObj = $("#" + treeNode.tId + "_span");
    if ($("#addBtn_"+treeNode.id).length>0) return;
    var editStr = "<span id='addBtn_" +treeNode.id+ "' class='button' title='新增' style='"+style+"' > </span>";
    aObj.after(editStr);
    var btn = $("#addBtn_"+treeNode.id);
    if (btn) btn.bind("click", function(){
        var $modal = $('#add_permission_modal');
        $("#add_permission_father_id").val(treeNode.id);
        $modal.modal();
    });
};
function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.id).unbind().remove();
};
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

function add_permission() {
    var add_permission_name=$("#add_permission_name").val();
    var add_permission_father_id=$("#add_permission_father_id").val();
    var add_permission_type=$("#add_permission_type").val();
    var add_permission_url=$("#add_permission_url").val();
    var $modal = $('#add_permission_modal');
    $modal.modal('close');
    if(add_permission_name && add_permission_father_id && add_permission_type){
        $.post(router+"/rightmanage/permission/addPermission",{"permission_url":add_permission_url,"permission_type":add_permission_type,"permission_father_id":add_permission_father_id,"permission_name":add_permission_name},function (data) {
            getAllPermission();
        });
    }else{
        alert("输入信息不完整！");
    }
}

function update_permission() {
    var permission_father_id= app.$data.permission.permission_father_id;
    var permission_id= app.$data.permission.permission_id;
    var permission_name= app.$data.permission.permission_name;
    var permission_type= app.$data.permission.permission_type;
    var permission_url= app.$data.permission.permission_url;
    if(permission_father_id&&permission_id &&permission_name &&permission_type &&permission_url){
        $.post(router+"/rightmanage/permission/updatePermission",{"permission_id":permission_id,"permission_url":permission_url,"permission_type":permission_type,"permission_father_id":permission_father_id,"permission_name":permission_name},function (data) {
            getAllPermission();
            if(data.code==1){
                alert("更新权限信息成功！");
            }else {
                alert("更新权限信息失败！");
            }
            app.$data.permission.permission_id=undefined;
            app.$data.permission.permission_name=undefined;
            app.$data.permission.permission_type=undefined;
            app.$data.permission.permission_url=undefined;
            app.$data.permission.permission_father_id=undefined;
        });
    }else {
        alert("更新权限信息失败！");
    }
}
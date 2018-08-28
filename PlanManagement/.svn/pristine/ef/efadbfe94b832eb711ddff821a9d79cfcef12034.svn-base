var selected_list_id;
var selected_task_id;
var selected_list_name;
var selected_task_name;
var selected_task_state;
var app=new Vue({
    el: '#app',
    data: {
        vue_list_name:"",
        vue_list_id:undefined,
        vue_task_list:[],
        vue_list_list:[],
        task:{
            task_name:"点击任务查看标题详情",
            content:"描述",
            task_id:undefined
        },
        page_index:1,
        page_count:1
    },
    methods:{
        show_tasks:function (list_id) {
            selected_list_id=list_id;
            var page=1;
            getTasks(list_id,page,selected_task_state);
        },show_tasks_first:function(){
            var list_id=selected_list_id;
            var page=1;
            if(list_id && list_id!=""){
                getTasks(list_id,page,selected_task_state);
            }else{
                getAllTasks(page,selected_task_state);
            }
        },show_tasks_last:function(){
            var list_id=selected_list_id;
            var page=app.$data.page_index;
            if(page>1){
                page=page-1;
            }
            if(list_id && list_id!=""){
                getTasks(list_id,page,selected_task_state);
            }else{
                getAllTasks(page,selected_task_state);
            }
        },show_tasks_skip:function(){
            var list_id=selected_list_id;
            var page=parseInt($("#input_page_no").val());
            if(isNaN(page)){
                page=1;
            }else if(page<=1){
                page=1;
            }else if (page>=app.$data.page_count){
                page=app.$data.page_count;
            }

            if(list_id && list_id!=""){
                getTasks(list_id,page,selected_task_state);
            }else{
                getAllTasks(page,selected_task_state);
            }
        },show_tasks_next:function(){
            var list_id=selected_list_id;
            var page=app.$data.page_index;
            if (page<app.$data.page_count){
                page=page+1;
            }
            if(list_id && list_id!=""){
                getTasks(list_id,page,selected_task_state);
            }else{
                getAllTasks(page,selected_task_state);
            }
        },show_tasks_final:function(){
            var list_id=selected_list_id;
            var page=app.$data.page_count;
            if(list_id && list_id!=""){
                getTasks(list_id,page,selected_task_state);
            }else{
                getAllTasks(page,selected_task_state);
            }
        },show_content:function (task_id) {
            $('.am-dropdown').dropdown('close');
            selected_task_id=task_id;
            app.$data.task.task_id=task_id;
            app.$data.task.content="";
            $.post(router+"/task/getTaskInfo",{"task_id":task_id},function (data) {
                selected_task_name=data.data.task_name;
                app.$data.task.task_name=selected_task_name;
            });
            $.post(router+"/content/getContent",{"task_id":task_id},function (data) {
                app.$data.task.content=data.data.content;
            });
        },show_update_content:function (task_id) {
            $('.am-dropdown').dropdown('close');
            var $modal = $('#edit_content_modal');
            selected_task_id=task_id;
            $("#edit_content_modal_id").val(task_id);
            $.post(router+"/task/getTaskInfo",{"task_id":task_id},function (data) {
                selected_task_name=data.data.task_name;
                $("#edit_content_modal_title").val(selected_task_name);
                app.$data.task.task_name=selected_task_name;
                $.post(router+"/content/getContent",{"task_id":task_id},function (data) {
                    app.$data.task.content=data.data.content;
                    $("#edit_content_modal_content").val(data.data.content);
                    $modal.modal();
                });
            });
        },change_task_state:function(task_id){
            $.post(router+"/task/getTaskInfo",{"task_id":task_id},function (data) {
                var list_id=data.data.list_id;
                var task_name=data.data.task_name;
                var task_level=data.data.task_level;
                var task_state=data.data.task_state;
                var remark=data.data.remark;
                if(task_state==1){
                    task_state=0;
                }else{
                    task_state=1;
                }
                $.post(router+"/task/updateTask",{"task_id":task_id,"list_id":list_id,"task_name":task_name,"task_level":task_level,"task_state":task_state,"remark":remark},function (data) {
                    var list_id=selected_list_id;
                    var page=app.$data.page_index;
                    if(list_id && list_id!=""){
                        getTasks(list_id,page,"");
                    }else{
                        getAllTasks(page,"");
                    }
                })
            });
        },add_task:function () {
            var task_name=$("#add_task_name").val();
            var task_date=$("#add_task_date").val();
            var list_id=selected_list_id;
            $.post(router+"/task/addTask",{"list_id":list_id,"task_date":task_date,"task_name":task_name},function (data) {
                $("#add_task_name").val("");
                if(list_id && list_id!=""){
                    getTasks(list_id);
                }else{
                    getAllTasks(1,"");
                }
            });
        },delete_task:function (task_id) {
            $('.am-dropdown').dropdown('close');
            var list_id=selected_list_id;
            $.post(router+"/task/deleteTask",{"task_id":task_id},function (data) {
                if(list_id && list_id!=""){
                    getTasks(list_id);
                }else{
                    getAllTasks(1,"");
                }
            });
        },delete_list:function(list_id) {
            $('.am-dropdown').dropdown('close');
            var r=confirm("是否删除这个清单！");
            if (r==true){
                $.post(router+"/list/deleteList",{"list_id":list_id},function (data) {
                    getAllTasks(1,"");
                    getList();
                });
            }
        }
    },created: function () {
        getAllTasks(1,"");
        getList();
        laydate.render({
            elem: '#add_task_date' //指定元素
        });
    }
});

function getAllTasks(page_no,task_state) {
    selected_list_id="";
    $.post(router+"/task/getAllTasks",{"page_no":page_no,"task_state":task_state},function (data) {
        if(data.code==1){
            app.$data.vue_list_name="所有";
            app.$data.vue_list_id=undefined;
            app.$data.vue_task_list=data.data.result;
            app.$data.page_count=data.data.totalPageCount;
            app.$data.page_index=data.data.pageNo;
        }else{
            app.$data.vue_list_name="所有";
            app.$data.vue_list_id=undefined;
            app.$data.vue_task_list=[];
            app.$data.page_count=1;
            app.$data.page_index=1;
        }
        setTimeout(function () {
            $('.am-dropdown').dropdown();
        },200);
    });
}
function getTasks(list_id,page_no,task_state) {
    $.post(router+"/list/getListInfo",{"list_id":list_id},function (data) {
        app.$data.vue_list_name=data.data.list_name;
        app.$data.vue_list_id=list_id;
        selected_list_id=list_id;
        selected_list_name=data.data.list_name;
    });
    $.post(router+"/task/getTasks",{"list_id":list_id,"page_no":page_no,"task_state":task_state},function (data) {
        if(data.code==1) {
            app.$data.vue_task_list = data.data.result;
            app.$data.page_count = data.data.totalPageCount;
            app.$data.page_index = data.data.pageNo;
        }else{
            app.$data.vue_task_list=[];
            app.$data.page_count=1;
            app.$data.page_index=1;
        }
        setTimeout(function () {
            $('.am-dropdown').dropdown();
        },200);
    });
}

function getList() {
    $.post(router+"/list/getList",{},function (data) {
        app.$data.vue_list_list=data.data;
        setTimeout(function () {
            $('.sidebar-nav-link a').on('click', function () {
                $('.sidebar-nav-link a').removeClass('active');
                $(this).addClass('active');
            });
        },100);
    });
}

function update_task() {
    var $modal = $('#edit_content_modal');
    var task_id=$("#edit_content_modal_id").val();
    var task_name=$("#edit_content_modal_title").val();
    var content= $("#edit_content_modal_content").val();
    $.post(router+"/content/updateContent",{"task_id":task_id,"content":content},function (data) {
        app.$data.task.content=content;
    });
    $modal.modal('close');
}

function update_content() {
    var task_id=app.$data.task.task_id;
    var content=app.$data.task.content;
    if(task_id && task_id!=""){
        $.post(router+"/content/updateContent",{"task_id":task_id,"content":content},function (data) {
            app.$data.task.content=undefined;
            app.$data.task.task_id=undefined;
            alert("修改成功！");
        });
    }else {
        alert("请选择一个任务！");
    }
}

function show_add_list_modal() {
    var $modal = $('#add_list_modal');
    $modal.modal();
}

function show_update_list_modal() {
    $('.am-dropdown').dropdown('close');
    var list_id=selected_list_id;
    if(list_id && list_id!=""){
        $.post(router+"/list/getListInfo",{"list_id":list_id},function (data) {
            $("#update_list_id").val(list_id);
            $("#update_list_name").val(data.data.list_name);
            $("#update_list_remark").val(data.data.remark);
            var $modal = $('#update_list_modal');
            $modal.modal();
        });
    }else{
        aler("不能编辑清单！");
    }
}

function add_list() {
    var list_name=$("#add_list_name").val();
    $.post(router+"/list/addList",{"list_name":list_name},function (data) {
        getList();
    });
    var $modal = $('#add_list_modal');
    $modal.modal('close');
}
function update_list() {
    var list_id=$("#update_list_id").val();
    var list_name=$("#update_list_name").val();
    var remark=$("#update_list_remark").val();
    if(list_id && list_name){
        $.post(router+"/list/updateList",{"list_id":list_id,"list_name":list_name,"remark":remark},function (data) {
            getAllTasks(1,"");
            getList();
            alert("修改成功！");
        });
    }else{
        alert("修改失败！");
    }
    var $modal = $('#update_list_modal');
    $modal.modal('close');
}

function change_task_state(state) {
    selected_task_state=state;
    var list_id=selected_list_id;
    var page=1;
    if(list_id && list_id!=""){
        getTasks(list_id,page,selected_task_state);
    }else{
        getAllTasks(page,selected_task_state);
    }
}
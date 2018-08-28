var xhr;
var editor;
var update_editor;
$(function() {
    var E = window.wangEditor;
    editor= new E('#container');
    editor.customConfig.uploadImgShowBase64 = true;    // 或者 var editor = new E( document.getElementById('#editor') )
    editor.customConfig.pasteFilterStyle = false;
    editor.create();
    // var a = document.getElementById('file');
    //获取图片file，input的位置
    var b = $("#img_file")[0];
    //当input type="file" ，的那个值发生改变时
    b.onchange = function(evt) {
        // 如果浏览器不支持FileReader，则不处理
        if (!window.FileReader)
            return;
        //获取到file的值，当前
        // FileList object
        var files = evt.target.files;
        for (var i = 0, f; f = files[i]; i++) {
            //匹配图片
            if (!f.type.match('image.*')) {
                continue;
            }
            //FileReader对象
            var reader = new FileReader();
            //读取文件成功后触发
            reader.onload = (function(theFile) {
                return function(e) {
                    // img 元素
                    $("#imgs")[0].src = e.target.result;
                };
            })(f);
            //读取文件
            reader.readAsDataURL(f);
        }
    }
});

var app=new Vue({
    el: '#app',
    data: {
        update_article_info:{
            article_id:undefined,
            article_type:undefined,
            article_title:undefined,
            article_summary:undefined
        },
        add_article_info:{},
        article_id:undefined,
        vue_article_list:[],
        vue_article_type_list:[],
        page_index: 1,
        page_count: 1
    },
    methods: {
        edit_article:function (article_id) {
            $.AMUI.progress.start();
            app.$data.update_article_info.article_id=article_id;
            $.post(router+"/article/getArticle",{"article_id":article_id},function (data) {
                app.$data.update_article_info.article_type=data.data.article_type;
                app.$data.update_article_info.article_summary=data.data.article_summary;
                app.$data.update_article_info.article_title=data.data.article_title;
                setTimeout(function () {

                    var E = window.wangEditor;
                    update_editor= new E('#update_container');
                    update_editor.customConfig.uploadImgShowBase64 = true;    // 或者 var editor = new E( document.getElementById('#editor') )
                    update_editor.customConfig.pasteFilterStyle = false;
                    update_editor.create();
                    update_editor.txt.html(data.data.article_content);
                    var b = $("#update_img_file")[0];
                    $("#update_imgs")[0].src=router+"/"+data.data.article_logo_url;
                    //当input type="file" ，的那个值发生改变时
                    b.onchange = function(evt) {
                        // 如果浏览器不支持FileReader，则不处理
                        if (!window.FileReader)
                            return;
                        //获取到file的值，当前
                        // FileList object
                        var files = evt.target.files;
                        for (var i = 0, f; f = files[i]; i++) {
                            //匹配图片
                            if (!f.type.match('image.*')) {
                                continue;
                            }
                            //FileReader对象
                            var reader = new FileReader();
                            //读取文件成功后触发
                            reader.onload = (function(theFile) {
                                return function(e) {
                                    // img 元素
                                    $("#update_imgs")[0].src = e.target.result;
                                };
                            })(f);
                            //读取文件
                            reader.readAsDataURL(f);
                        }
                    }
                    $.AMUI.progress.done();
                },500);
            });
        },
        delete_article:function (article_id) {
            $.AMUI.progress.start();
            var rs=confirm("确定要删除这篇文章！");
            if(rs==true){
                $.post(router+"/article/deleteArticle",{"article_id":article_id},function (data) {
                    if(data.code==1){
                        if(app.$data.page_index=app.$data.page_count){
                            app.$data.page_index=app.$data.page_index-1;
                        }
                        getBlogList(app.$data.page_index);
                    }
                });
            }
        },show_article_first:function () {
            $.AMUI.progress.start();
            getBlogList(1);
        },show_article_last:function () {
            $.AMUI.progress.start();
            if(app.$data.page_index>1){
                app.$data.page_index=app.$data.page_index-1;
            }
            getBlogList(app.$data.page_index);
        },show_article_skip:function () {
            $.AMUI.progress.start();
            var page=parseInt($("#input_page_no").val());
            if(isNaN(page)){
                page=1;
            }else if(page<1){
                page=1;
            }else if(page>=app.$data.page_count){
                page=app.$data.page_count;
            }
            app.$data.page_index=page;
            getBlogList(app.$data.page_index);
        },show_article_next:function () {
            $.AMUI.progress.start();
            if(app.$data.page_count<app.$data.page_index){
                app.$data.page_index=app.$data.page_index+1;
            }
            getBlogList(app.$data.page_index);
        },show_article_final:function () {
            $.AMUI.progress.start();
            getBlogList(app.$data.page_count);
        }
    },
    created: function () {
        $.AMUI.progress.start();
        getArticle_type();
        getBlogList(1);
    }
});

function getBlogList(page) {
    if(page<1){page=1;}
    $.post(router+"/article/getMyArticleList",{"page":page},function (data) {
        if(data.code==1){
            app.$data.page_index=page;
            app.$data.vue_article_list=data.data.result;
            app.$data.page_count=data.data.totalPageCount;
        }else{
            app.$data.vue_article_list=[];
        }
        $.AMUI.progress.done();
    });
}

function getArticle_type() {
    $.post(router+"/articleType/getArticleType",{},function (data) {
        if(data.code==1){
            app.$data.vue_article_type_list=data.data;
        }else{
            app.$data.vue_article_type_list=[];
        }
    });
}

function add_article() {
    $.AMUI.progress.start();
    var title = app.$data.add_article_info.article_title;
    var docContent = editor.txt.html();
    var summary = app.$data.add_article_info.article_summary;
    //获取图片file
    var img_file = $("#img_file")[0].files[0];
    var type = app.$data.add_article_info.article_type;
    if(isNull(title) || isNull(docContent) ||isNull(summary) ||isNull(img_file) ||isNull(type)){
        alert("数据不完整！");
    }else{
        //创建FormData
        var form = new FormData();
        form.append("article_title", title);
        form.append("article_content", docContent);
        form.append("article_summary", summary);
        //加入form
        form.append("article_logo", img_file);
        form.append("article_type", type);

        xhr = createXmlHttpRequest();
        xhr.onreadystatechange = add_state_Change;
        xhr.open("post", router+"/article/insertArticle", true);
        xhr.send(form);
    }
}

function update_article() {
    $.AMUI.progress.start();
    var article_id=app.$data.update_article_info.article_id;
    var title = app.$data.update_article_info.article_title;
    var docContent = update_editor.txt.html();
    var summary = app.$data.update_article_info.article_summary;
    //获取图片file
    var img_file = $("#update_img_file")[0].files[0];
    var type = app.$data.update_article_info.article_type;
    if(isNull(article_id)){
        alert("数据不完整！");
    }else{
        //创建FormData
        var form = new FormData();
        form.append("article_id", article_id);
        if(!isNull(title))form.append("article_title", title);
        if(!isNull(docContent))form.append("article_content", docContent);
        if(!isNull(summary))form.append("article_summary", summary);
        //加入form
        if(!isNull(img_file))form.append("article_logo", img_file);
        if(!isNull(type))form.append("article_type", type);

        xhr = createXmlHttpRequest();
        xhr.onreadystatechange = update_state_Change;
        xhr.open("post", router+"/article/updateArticle", true);
        xhr.send(form);
    }
}

//使用XmlHttpRequest上传
function createXmlHttpRequest() {
    if (window.ActiveXObject) { //如果是IE浏览器
        return new ActiveXObject("Microsoft.XMLHTTP");
    } else if (window.XMLHttpRequest) { //非IE浏览器
        return new XMLHttpRequest();
    }
}
//XmlHttpRequest状态改变时触发
function add_state_Change() {
    if (xhr.readyState == 4 && xhr.status == 200) {
        var b = JSON.parse(xhr.responseText);
        console.log(b.code)
        if(b.code==1){
            getBlogList(app.$data.page_index);
            app.$data.add_article_info={};
            $("#imgs")[0].src="/pic/null.png";
            editor.txt.html('');
        }
    }
}

function update_state_Change() {
    if (xhr.readyState == 4 && xhr.status == 200) {
        var b = JSON.parse(xhr.responseText);
        console.log(b.code)
        if(b.code==1){
            getBlogList(app.$data.page_index);
            add_article_block();
            $("#update_imgs")[0].src="/pic/null.png";
            update_editor.txt.html('');
        }
    }
}

function add_article_block() {
    app.$data.update_article_info={
        article_id:undefined,
        article_type:undefined,
        article_title:undefined,
        article_summary:undefined
    };

    setTimeout(function () {
        $("#imgs")[0].src="/pic/null.png";
        var E = window.wangEditor;
        editor= new E('#container');
        editor.customConfig.uploadImgShowBase64 = true;    // 或者 var editor = new E( document.getElementById('#editor') )
        editor.customConfig.pasteFilterStyle = false;
        editor.create();
        editor.txt.html("");
        // var a = document.getElementById('file');
        //获取图片file，input的位置
        var b = $("#img_file")[0];
        //当input type="file" ，的那个值发生改变时
        b.onchange = function(evt) {
            // 如果浏览器不支持FileReader，则不处理
            if (!window.FileReader)
                return;
            //获取到file的值，当前
            // FileList object
            var files = evt.target.files;
            for (var i = 0, f; f = files[i]; i++) {
                //匹配图片
                if (!f.type.match('image.*')) {
                    continue;
                }
                //FileReader对象
                var reader = new FileReader();
                //读取文件成功后触发
                reader.onload = (function(theFile) {
                    return function(e) {
                        // img 元素
                        $("#imgs")[0].src = e.target.result;
                    };
                })(f);
                //读取文件
                reader.readAsDataURL(f);
            }
        }
    },200);
}

function isNull(obj){
    if(obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}
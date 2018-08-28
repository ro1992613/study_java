var app=new Vue({
    el: '#app',
    data: {
        vue_user:{
            user_id:undefined,
            user_alias:undefined
        },
        vue_reply_content:undefined,
        reply_user_name:undefined,
        article_author_name:undefined,
        article_type:undefined,
        article_createtime:undefined,
        article_title:undefined,
        search_key:undefined,
        vue_hottest_article_list:[],
        vue_reply_list:[],
        vue_image_list:[],
        vue_article_type_list:[],
        vue_latest_reply_list:[],
        page_index: 1,
        page_count: 1
    },
    methods: {
        show_reply_first:function () {
            getReplyList(1);
        },show_reply_last:function () {
            if(app.$data.page_index>1){
                app.$data.page_index=app.$data.page_index-1;
            }
            getReplyList(app.$data.page_index);
        },show_reply_skip:function () {
            var page=parseInt($("#input_page_no").val());
            if(isNaN(page)){
                page=1;
            }else if(page<1){
                page=1;
            }else if(page>=app.$data.page_count){
                page=app.$data.page_count;
            }
            app.$data.page_index=page;
            getReplyList(app.$data.page_index);
        },show_reply_next:function () {
            if(app.$data.page_count<app.$data.page_index){
                app.$data.page_index=app.$data.page_index+1;
            }
            getReplyList(app.$data.page_index);
        },show_reply_final:function () {
            getReplyList(app.$data.page_count);
        },searchKey:function(){
            if(app.$data.search_key){
                var href="/index.html?search_key="+app.$data.search_key;
                window.location.href=href;
            }
        },searchType:function (type) {
            if(type){
                var href="/index.html?article_type="+type;
                window.location.href=href;
            }
        }
    },
    created:function () {
        getArticle_type();
        getArticle();
        getUserInfo();
        getHottestArticleList();
        getLatestReplyList();
        getJandanImg(1);
    }
});

function getArticle() {
    var article_id=GetQueryString('article_id');
    if(article_id){
        $.post(router+"/article/getArticle",{"article_id":article_id},function (data) {
            if(data.code==1){
                app.$data.article_author_name=data.data.article_author_name;
                app.$data.article_type=data.data.article_type;
                app.$data.article_createtime=moment(data.data.article_createtime).format("YYYY-MM-DD HH:mm");
                app.$data.article_title=data.data.article_title;
                $('#article_content').html(data.data.article_content);
                getReplyList(1);
            }else {
                window.history.back();
            }
        });
    }else{
        window.history.back();
    }
}

function getHottestArticleList() {
    $.post(router+"/article/getHottestArticleList",{},function (data) {
        if(data.code==1){
            app.$data.vue_hottest_article_list=data.data;
        }else{
            app.$data.vue_hottest_article_list=[];
        }
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

function getLatestReplyList() {
    $.post(router+"/reply/getLatestReplyList",{},function (data) {
        if(data.code==1){
            reply_scoll(data.data,0);
        }else{
            app.$data.vue_latest_reply_list=[];
        }
    });
}

function reply_scoll(data,index) {
    var input=[];
    var size=5;
    for(var i=0;i<=size;i++){
        if(index+size>=data.length){
            index=index+size-data.length;
        }
        input.push(data[index+i]);
    }
    app.$data.vue_latest_reply_list=input;
    index=index+1;
    setTimeout(function () {
        reply_scoll(data,index);
    },3000);
}
function getJandanImg(page) {
    $.post(router+"/pic/getJandanImg",{"page":page},function (data) {
        if(data.code==1){
            reply_img(data.data,0);
        }else{
            app.$data.vue_image_list=[];
        }
    });
}

function reply_img(data,index) {
    var input=[];
    var size=5;
    for(var i=0;i<=size;i++){
        if(index+size>=data.length){
            index=index+size-data.length;
        }
        input.push(data[index+i]);
    }
    app.$data.vue_image_list=input;
    index=index+1;
    setTimeout(function () {
        reply_img(data,index);
    },20000);
}

function getReplyList(page) {
    var article_id=GetQueryString('article_id');
    $.post(router+"/reply/getReplyList",{"article_id":article_id,"page":page},function (data) {
        if(data.code==1){
            data.data.result=data.data.result.map(function (v) {
                v.reply_time=moment(v.reply_time).format("YYYY-MM-DD HH:mm");
                return v;
            });
            app.$data.vue_reply_list=data.data.result;
            app.$data.page_count=data.data.totalPageCount;
            app.$data.page_index=page;
        }
    });
}

function getUserInfo() {
    $.post(router+"/login/get_login_info",{},function (data) {
        if(data.code==1){
            app.$data.vue_user.user_id=data.data.user_id;
            app.$data.vue_user.user_alias=data.data.user_alias;
        }
    });
}

function save_reply() {
    var reply_user_name= app.$data.reply_user_name;
    var user_id= app.$data.vue_user.user_alias;
    var user_alias= app.$data.vue_user.user_alias;
    var vue_reply_content= app.$data.vue_reply_content;
    var postdata={};
    if(reply_user_name||user_alias){
        postdata.article_id=GetQueryString('article_id');
        postdata.article_title=app.$data.article_title;
        postdata.reply_name=reply_user_name?reply_user_name:user_alias;
        postdata.user_id=user_id;
        postdata.user_alias=user_alias;
        postdata.reply_content=vue_reply_content;
        if(vue_reply_content && postdata.article_id && postdata.article_title){
            $.post(router+"/reply/insertReply",postdata,function (data) {
                getReplyList(app.$data.page_index);
                app.$data.vue_reply_content=undefined;
            });
        }
    }else{

    }
}

function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = decodeURI(window.location.search).substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}
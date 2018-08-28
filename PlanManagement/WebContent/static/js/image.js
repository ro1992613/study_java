var app=new Vue({
    el: '#app',
    data: {
        search_key:undefined,
        vue_image_list:[],
        vue_hottest_article_list:[],
        vue_reply_list:[],
        vue_article_type_list:[],
        vue_latest_reply_list:[],
        page_index: 1,
        page_count: 1
    },
    methods: {
        show_img_first:function () {
            app.$data.page_index=1;
            getJandanImg(1);
        },show_img_last:function () {
            if(app.$data.page_index>1){
                app.$data.page_index=app.$data.page_index-1;
            }
            getJandanImg(app.$data.page_index);
        },show_img_skip:function () {
            var page=parseInt($("#input_page_no").val());
            if(isNaN(page)){
                page=1;
            }else if(page<1){
                page=1;
            }
            app.$data.page_index=page;
            getJandanImg(app.$data.page_index);
        },show_img_next:function () {
            app.$data.page_index=app.$data.page_index+1;
            getJandanImg(app.$data.page_index);
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
        getHottestArticleList();
        getLatestReplyList();
        getJandanImg(1);
    }
});

$(function(){
    $('#container').pinto();
});

function getJandanImg(page) {
    $.AMUI.progress.start();
    app.$data.vue_image_list=[];
    $.post(router+"/pic/getJandanImg",{"page":page},function (data) {
        if(data.code==1){
            app.$data.vue_image_list=data.data;
            setTimeout(function () {
                $('#container').pinto();
                $.AMUI.progress.done();
            },500);
        }
    });
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

function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = decodeURI(window.location.search).substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}
var search_key;
var app=new Vue({
    el: '#app',
    data: {
        vue_article_list:[],
        vue_image_list:[],
        vue_hottest_article_list:[],
        vue_article_type_list:[],
        vue_latest_reply_list:[],
        search_key:undefined,
        page_index: 1,
        page_count: 1
    },
    methods: {
        show_article_first:function () {
            getBlogList(1);
        },show_article_last:function () {
            if(app.$data.page_index>1){
                app.$data.page_index=app.$data.page_index-1;
            }
            getBlogList(app.$data.page_index);
        },show_article_skip:function () {
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
            if(app.$data.page_count<app.$data.page_index){
                app.$data.page_index=app.$data.page_index+1;
            }
            getBlogList(app.$data.page_index);
        },show_article_final:function () {
            getBlogList(app.$data.page_count);
        },searchKey:function(){
            if(app.$data.search_key){
                var href="/index.html?search_key="+app.$data.search_key;
                if(GetQueryString('article_type')){
                    href=href+"&article_type="+GetQueryString('article_type');
                }
                window.location.href=href;
            }
        },searchType:function (type) {
            if(type){
                var href="/index.html?article_type="+type;
                if(GetQueryString('search_key')){
                    href=href+"&search_key="+GetQueryString('search_key');
                }
                window.location.href=href;
            }
        }
    },
    created:function () {
        getArticle_type();
        getHottestArticleList();
        getLatestReplyList();
        getBlogList(1);
        getJandanImg(1);
    }
});


function getBlogList(page) {
    var postdata={};
    if(!page){page=1;}
    if(page<1){page=1;}
    postdata.page=page;
    if(GetQueryString('search_key')){
        postdata.key=GetQueryString('search_key');
    }
    if(GetQueryString('article_type')){
        postdata.article_type=GetQueryString('article_type');
    }
    $.post(router+"/article/getArticleList",postdata,function (data) {
        if(data.code==1){
            app.$data.page_index=page;
            data.data.result=data.data.result.map(function(v){
                v.article_createtime=moment(v.article_createtime).format("YYYY-MM-DD HH:mm");
                return v;
            });
            app.$data.vue_article_list=data.data.result;
            app.$data.page_count=data.data.totalPageCount;
        }else{
            app.$data.vue_article_list=[];
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

function getHottestArticleList() {
    $.post(router+"/article/getHottestArticleList",{},function (data) {
        if(data.code==1){
            app.$data.vue_hottest_article_list=data.data;
        }else{
            app.$data.vue_hottest_article_list=[];
        }
    });
}

function getLatestReplyList() {
    $.post(router+"/reply/getLatestReplyList",{},function (data) {
        if(data.code==1){
            //app.$data.vue_latest_reply_list=data.data;
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

function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = decodeURI(window.location.search).substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}
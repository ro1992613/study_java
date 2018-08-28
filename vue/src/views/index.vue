<style scoped>
    .layout{
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        position: relative;
        border-radius: 4px;
        overflow: hidden;
    }
    .layout-header-bar{
        background: #fff;
        box-shadow: 0 1px 1px rgba(0,0,0,.1);
    }
</style>
<template>
    <div class="layout">
        <Sider :style="{position: 'fixed', height: '100vh', left: 0, overflow: 'auto'}">
            <Menu active-name="" theme="dark" width="auto" :open-names="['1']" @on-select="clickMenuItem">
                <MenuItem name="/">
                        <Icon type="ios-navigate"></Icon>
                        <span class="layout-text">总览</span>
                </MenuItem>
                <Submenu name="/richangjianguan">
                    <template slot="title">
                        <Icon type="ios-navigate"></Icon>
                        日常监管
                    </template>
                    <!-- <MenuItem name="/richangjianguan">
                        <Icon type="ios-navigate"></Icon>
                        <span class="layout-text">信息总览</span>
                    </MenuItem> -->
                    <MenuItem name="/richangjianguan/area">
                        <Icon type="ios-navigate"></Icon>
                        <span class="layout-text">区域监管</span>
                    </MenuItem>
                    <!-- <MenuItem name="/richangjianguan/bike">
                        <Icon type="ios-navigate"></Icon>
                        <span class="layout-text">车辆监管</span>
                    </MenuItem>
                    <MenuItem name="/richangjianguan/person">
                        <Icon type="ios-navigate"></Icon>
                        <span class="layout-text">人员监管</span>
                    </MenuItem> -->
                    <MenuItem name="/richangjianguan/complaint">
                        <Icon type="ios-navigate"></Icon>
                        <span class="layout-text">投诉监管</span>
                    </MenuItem>
                </Submenu>

                <Submenu name="/xinxichaxun">
                    <template slot="title">
                        <Icon type="ios-navigate"></Icon>
                        信息查询
                    </template>
                    <!-- <MenuItem name="/xinxichaxun">
                        <Icon type="ios-navigate"></Icon>
                        <span class="layout-text">信息总览</span>
                    </MenuItem> -->
                    <MenuItem name="/xinxichaxun/company_info">
                        <Icon type="ios-navigate"></Icon>
                        <span class="layout-text">企业信息</span>
                    </MenuItem>
                    <MenuItem name="/xinxichaxun/bike_info">
                        <Icon type="ios-navigate"></Icon>
                        <span class="layout-text">单车信息</span>
                    </MenuItem>
                    <MenuItem name="/xinxichaxun/person_info">
                        <Icon type="ios-navigate"></Icon>
                        <span class="layout-text">人员信息</span>
                    </MenuItem>
                </Submenu>
                <Submenu name="#">
                    <template slot="title">
                        <Icon type="ios-navigate"></Icon>
                        权限管理
                    </template>
                    <MenuItem name="/access/user_role">
                        <Icon type="ios-navigate"></Icon>
                        <span class="layout-text">用户角色管理</span>
                    </MenuItem>
                    <MenuItem name="/access/role_permission">
                        <Icon type="ios-keypad"></Icon>
                        <span class="layout-text">角色权限管理</span>
                    </MenuItem>
                    <MenuItem name="/access/permission">
                        <Icon type="ios-analytics"></Icon>
                        <span class="layout-text">权限信息管理</span>
                    </MenuItem>
                </Submenu>
            </Menu>
        </Sider>
        <Layout :style="{marginLeft: '200px'}">
            <Header :style="{background: '#fff', boxShadow: '0 2px 3px 2px rgba(0,0,0,.1)'}">
                <span style="float:left;">
                    <h1><Icon type="android-bicycle"></Icon> 共享单车监管平台</h1>
                </span>
                <span style="float:right;width:100px;"> <router-link to="/login"><Icon type="log-out"></Icon> 注销</router-link></span>
                <span style="float:right;width:100px;"> <router-link to="/show_data"><Icon type="easel"></Icon> 数据展示</router-link></span>
            </Header>
            <Content :style="{padding: '0 16px 16px'}">
                <Breadcrumb :style="{margin: '16px 0'}">
                    <BreadcrumbItem>{{this.$router.currentRoute.meta.title}}</BreadcrumbItem>
                </Breadcrumb>
                <!-- <tab-menu :prop_value="tag_page"></tab-menu> -->
                <Card>
                    <div style="height: auto;min-height:600px;">
                        <router-view></router-view>
                    </div>
                </Card>
            </Content>
        </Layout>
    </div>
</template>

<script>
    import tabMenu from './page/components/tab_menu.vue';

    export default {
        data () {
            return {
                tag_page:{
                    currentpage:{},
                    opened_page:[]
                }
            };
        },
        components:{
           tabMenu 
        },
        computed: {
        },
        methods: {
            clickMenuItem (name) {
                this.$router.push(name);
            },
            getCurrentPath:function(){
                return this.$router.currentRoute.path;
            }
        },
        mounted:function(){
            let is_exsited=false;
            for(let i in this.$data.tag_page.opened_page){
                if(this.$data.tag_page.opened_page[i].path==this.$router.currentRoute.path){
                    is_exsited=true;
                }
            }
            if(!is_exsited){
                let page={};
                page.title=this.$router.currentRoute.meta.title;
                page.path=this.$router.currentRoute.path;
                this.$data.tag_page.opened_page.push(page);
            }
            console.log( this.$data.tag_page.opened_page);
        },
        updated:function(){
            let is_exsited=false;
            for(let i in this.$data.tag_page.opened_page){
                if(this.$data.tag_page.opened_page[i].path==this.$router.currentRoute.path){
                    is_exsited=true;
                }
            }
            if(!is_exsited){
                let page={};
                page.title=this.$router.currentRoute.meta.title;
                page.path=this.$router.currentRoute.path;
                this.$data.tag_page.opened_page.push(page);
            }
            console.log( this.$data.tag_page.opened_page);
        }
    }
</script>
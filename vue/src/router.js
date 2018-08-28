import Main from './views/index.vue';

const otherRouter = 
{
    path: '/',
    name: 'home',
    component: Main,
    children:[
        {
            title:'主页',
            path: '/',
            name: 'home',
            meta:{
                title:'总览'
            },
            component:  (resolve) => require(['./views/page/home.vue'], resolve)
        }
    ]
};

const login={
    path: '/login',
    name: 'login',
    meta:{
        title: '登录'
    },
    component:  (resolve) => require(['./views/page/login/login.vue'], resolve)
};

const showData={
    path: '/show_data',
    name: 'show_data',
    meta:{
        title: '共享单车监管平台'
    },
    component:  (resolve) => require(['./views/show/show_data.vue'], resolve)
};

const showDataTotal={
    path: '/show_data_total',
    name: 'show_data_total',
    meta:{
        title: '数据详情'
    },
    component:  (resolve) => require(['./views/show/show_data_total.vue'], resolve)
};
const map={
    path: '/map',
    name: 'map',
    meta:{
        title: '数据详情'
    },
    component:  (resolve) => require(['./views/show/map.vue'], resolve)
};
const showDataWeihu={
    path: '/show_data_weihu',
    name: 'show_data_weihu',
    meta:{
        title: '维护现状'
    },
    component:  (resolve) => require(['./views/show/show_data_weihu.vue'], resolve)
};

const richangjianguan={
    path: '/richangjianguan',
    icon: 'key',
    name: 'richangjianguan',
    meta:{
        title: '日常监管',
    },
    component: Main,
    children: [
        {
            meta:{
                title:'监管信息'
            },
            path: '/',
            name: 'richangjianguan_info',
            component:  (resolve) => require(['./views/page/richangjianguan/index.vue'], resolve)
        },
        {
            meta:{
                title:'车辆监管'
            },
            path: 'bike',
            name: 'bike_supervise',
            component:  (resolve) => require(['./views/page/richangjianguan/bike.vue'], resolve)
        },
        {
            meta:{
                title:'区域监管'
            },
            path: 'area',
            name: 'area_supervise',
            component:  (resolve) => require(['./views/page/richangjianguan/area.vue'], resolve)
        },
        {
            path: 'addRegion',
            name: 'addRegion',
            meta:{
                title: '区域编辑'
            },
            component:  (resolve) => require(['./views/page/richangjianguan/addRegion/area_add_region.vue'], resolve)
        },
        {
            meta:{
                title:'人员监管'
            },
            path: 'person',
            name: 'person_supervise',
            component:  (resolve) => require(['./views/page/richangjianguan/person.vue'], resolve)
        },
        {
            meta:{
                title:'投诉监管'
            },
            path: 'complaint',
            name: 'complaint_supervise',
            component:  (resolve) => require(['./views/page/richangjianguan/complaint.vue'], resolve)
        },
        {
            meta:{
                title:'投诉监管详情'
            },
            path: 'complaint_more',
            name: 'complaint_more_supervise',
            component:  (resolve) => require(['./views/page/richangjianguan/complaint_more.vue'], resolve)
        }
    ]
}

const xinxichaxun={
    path: '/xinxichaxun',
    icon: 'key',
    name: 'xinxichaxun',
    meta:{
        title: '信息查询',
    },
    component: Main,
    children: [
        {
            meta:{
                title:'信息总览'
            },
            path: '/',
            name: 'xinxichaxun_info',
            component:  (resolve) => require(['./views/page/xinxichaxun/index.vue'], resolve)
        },
        {
            meta:{
                title:'企业信息'
            },
            path: 'company_info',
            name: 'company_info',
            component:  (resolve) => require(['./views/page/xinxichaxun/company_info.vue'], resolve)
        },
        {
            meta:{
                title:'企业详细信息'
            },
            path: 'company_info_more',
            name: 'company_info_more',
            component:  (resolve) => require(['./views/page/xinxichaxun/company_info_more.vue'], resolve)
        },
        {
            meta:{
                title:'单车信息'
            },
            path: 'bike_info',
            name: 'bike_info',
            component:  (resolve) => require(['./views/page/xinxichaxun/bike_info.vue'], resolve)
        },
        {
            meta:{
                title:'人员信息'
            },
            path: 'person_info',
            name: 'person_info',
            component:  (resolve) => require(['./views/page/xinxichaxun/person_info.vue'], resolve)
        }
    ]
}

const quanxianguanli={
    path: '/access',
    icon: 'key',
    name: 'access',
    meta:{
        title: '权限管理',
    },
    component: Main,
    children: [
        {
            meta:{
                title:'用户管理'
            },
            path: 'user_role',
            name: 'user_role',
            component:  (resolve) => require(['./views/page/permission/user.vue'], resolve)
        },
        {
            
            meta:{
                title:'角色管理'
            },
            path: 'role_permission',
            name: 'role_permission',
            component:  (resolve) => require(['./views/page/permission/role.vue'], resolve)
        },
        {
            meta:{
                title:'权限管理'
            },
            path: 'permission',
            name: 'permission',
            component:  (resolve) => require(['./views/page/permission/permission.vue'], resolve)
        }
    ]
}

const appRouter = [
    quanxianguanli,
    richangjianguan,
    xinxichaxun
];




// 所有上面定义的路由都要写在下面的routers里
const routers = [
    login,
    showData,
    map,
    showDataTotal,
    showDataWeihu,
    ...appRouter,
    otherRouter
];
export default routers;
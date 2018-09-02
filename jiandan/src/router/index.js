import Vue from 'vue'
import Router from 'vue-router'
import Main from '../page/manager.vue';

Vue.use(Router)

const index={
  path: '/',
  name: 'index',
  component: (resolve) => require(['../page/index.vue'], resolve)
};

const manager={
  path:'/manager',
  name:'manager',
  component:Main
}

const menu1={
  path:'/menu1',
  name:'',
  component:Main,
  children:[
    {
      path:'/',
      name:'',
      component:(resolve) => require(['../page/manager/page1.vue'], resolve),
    }
  ]
}


var menus=[
  menu1
]
export default new Router({
  routes: [
    index,
    manager,
    menu1
  ]
})

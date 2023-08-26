//创建router文件夹,创建index.js
// 导入相关函数
import { createRouter,createWebHistory } from 'vue-router';

const routes = [
    //首页重定向至登录页
    {
        path: '/',
        redirect: '/index',
    },

    //展示登录页对应容器
    {
        path: '/login',
        component: ()=>import('../views/login.vue'),
        name: 'login'
    },
    //404页面
    {
        path:'/404',
        component:()=>import('../views/404.vue'),
        name: '404'
    },

    {
        path:'/index',
        component:()=>import('../views/index.vue'),
        name: 'index'

    },

    //路径查不到重定向到404
    {
        path:'/:pathMatch(.*)*',
        redirect: '/404',
        name:'any'
    }

];

// 创建路由实例（`router`）并传递路由映射配置（`route`）
const router = createRouter({
    history: createWebHistory(),
    routes,
});


export default router;
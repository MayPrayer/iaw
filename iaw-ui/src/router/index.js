import { createRouter, createWebHashHistory,createWebHistory } from 'vue-router';

const routes = [
    //首页重定向至登录页
    {
        path: '/',
        redirect: '/login',
    },

    //展示登录页对应容器
    {
        path: '/login',
        component: ()=>import('../views/login.vue'),
    },

    {
        path: '/home',
        component:()=>import('../views/home.vue')
    }


];

// 创建路由实例（`router`）并传递路由映射配置（`route`）
const router = createRouter({
    // 配置导航模式，此处采用 hash 模式
    // history: createWebHashHistory(),

    //h5模式
    history: createWebHistory(),
    routes,
});


export default router;
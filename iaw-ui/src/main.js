import { createApp } from 'vue'
import App from './App.vue'
import { createPinia } from "pinia";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import vueRouter from  './router/index'
import { loadIcon } from './util/icon'

const app = createApp(App);
    loadIcon(app);//全局注册图标库
    app.use(createPinia())
    .use(vueRouter)
    .use(ElementPlus)
    .mount('#app');

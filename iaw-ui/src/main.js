import { createApp } from 'vue'
import App from './App.vue'
import { createPinia } from "pinia";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import vueRouter from  './router/index'

createApp(App)
    .use(createPinia())
    .use(ElementPlus)
    .use(vueRouter)
    .mount('#app')

import { createApp } from 'vue'
import App from './App.vue'
import { createPinia } from "pinia";
import vueRouter from  './router/index'

createApp(App)
    .use(createPinia())
    .use(vueRouter)
    .mount('#app')

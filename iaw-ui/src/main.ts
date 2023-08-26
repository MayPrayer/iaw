import { createApp } from 'vue'
import App from './App.vue'
import { createPinia } from "pinia";
import Antd from "ant-design-vue";
import 'ant-design-vue/dist/reset.css';
import GolbalComponents from './components/index'
import defaultcss from './styles/defalt.css'  //默认样式
import router  from "./router/index";


createApp(App)
    .use(createPinia())
    .use(Antd)
    .use(router)
    .use(GolbalComponents)
    .mount('#app')

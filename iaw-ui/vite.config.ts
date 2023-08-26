import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],

  //配置启动端口，项目启动是否打开页面
  server:{
    host:'127.0.0.1',
    port:9999,
    open:true,
    //配置代理
    proxy:{
      '/api/web':{
        target:'http://localhost:8999/',
        changeOrigin:true,
        rewrite:(path)=>path.replace(/^\/api\/web/, "")
      },
      '/api/admin':{
        target:'http://localhost:8071/',
        changeOrigin:true,
        rewrite:(path)=>path.replace(/^\/api/, "")
      },



    }
  },



})

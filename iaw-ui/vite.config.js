import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import {resolve} from 'path'

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
      '/api':{
        target:'http://localhost:8888/blog',
        changeOrigin:true,
        rewrite:(path)=>path.replace(/^\/api/, "")
      }
    },

    //配置目录别名
    resolve: {
      alias: {
        '@': resolve(__dirname, 'src') // 设置 `@` 指向 `src` 目录
      }
    }
  }




})

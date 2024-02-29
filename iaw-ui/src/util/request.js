import axios from 'axios'
import { ElMessage } from 'element-plus'


let request = axios.create({
    timeout:100000
})


//请求拦截
request.interceptors.request.use(
    (conifg)=>{
        return conifg;
    },error => {
        return Promise.reject(error);
    }

)

//响应拦截
request.interceptors.response.use(
    //响应 以及失败
    response=>{
        if (response.status==200&&response.data.code>=5000){
            ElMessage.error(response.data.msg)
        }
        return response
    },error => {
        if (error.response.status==500){
            ElMessage.error('服务器异常')
        }
        return Promise.reject(error);
    }
)

export  default  request;

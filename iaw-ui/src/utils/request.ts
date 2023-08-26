import axios from 'axios'


let request = axios.create()


//请求拦截
request.interceptors.request.use(
    (conifg)=>{


        return conifg;
    }

)

//响应拦截
request.interceptors.response.use(
    //响应 以及失败
    response=>{

        return response
    },error => {

    }
)

export  default  request;


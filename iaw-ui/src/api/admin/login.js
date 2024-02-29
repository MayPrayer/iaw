import request  from "../../util/request";


export  const  login = (data)=>{
    return request({
        url: '/api/admin/login',
        method: 'post',
        data: data
    })
}
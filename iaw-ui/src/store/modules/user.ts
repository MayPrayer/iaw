import {defineStore} from "pinia";

const userStore = defineStore('user', {
    //存储数据
    state: () => {
        return {
            token:''
        };
    },
    //异步更新数据
    actions: () => {

    },
    getters: ()=>{

    }

})

export  default userStore
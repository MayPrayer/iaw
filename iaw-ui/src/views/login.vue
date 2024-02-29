<template xmlns:el-col="http://www.w3.org/1999/html">

  <div class="content">

      <div class="loginDiv">
        <el-form ref="loginForm" :model="loginBO" :rules="rules">
        <el-row type="flex" justify="center">
         <el-col :span="10" class="title">管理后台</el-col>
        </el-row>

        <el-row :gutter="10" type="flex" justify="center" style="margin-top: 20px">
          <el-col :span="18">
            <el-input placeholder="请输入用户名" prefix-icon="User" v-model="loginBO.userName" />
          </el-col>
        </el-row>

        <el-row :gutter="10" type="flex" justify="center" style="margin-top: 30px">
          <el-col :span="18">
            <el-input placeholder="请输入密码" prefix-icon="Lock"  v-model="loginBO.password" />
          </el-col>
        </el-row>

        <el-row  style="margin-top: 30px" type="flex" justify="center">
          <el-button type="primary"  style="width: 75%"  @click="submit">登 录</el-button>
        </el-row>
        </el-form>
      </div>


  </div>


</template>

<script setup>
import  {login} from "../api/admin/login"
import { ref } from 'vue';

const loginForm = ref(null);
const loginBO = ref({
  userName:'',
  password:''
})


const rules = {
  userName: [
    { required: true, message: '请输入用户名', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' }
  ]
};


    /**
     * 提交
     */
const  submit = ()=>{

    loginForm.value.validate((valid)=>{
    if (valid){
      login(loginBO.value).then(
          response=> {
            console.log(response)
          }
      )
    }else{
      return false
    }
  })

}


</script>

<style scoped>
  .content{
    position: relative;
    background-image: url("https://project-1258741617.cos.ap-shanghai.myqcloud.com/BLUE.jpg");
    height: 1080px;
    opacity: 0.7;
    background-repeat: no-repeat; /* 设置背景不重复 */
    background-size: cover; /* 背景图片覆盖整个元素 */
    background-position: center; /* 居中显示背景图片 */
  }

  .loginDiv{
    width: 400px;
    height:350px;
    background-color:rgba(255, 255, 255, 0.5); /*半透明白色背景*/
    border-radius: 10px; /*圆角*/
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
    position: absolute;
    top: 20%;
    right: 5%;
  }

  .title{
    margin-top: 40px;
    font-family: "Microsoft YaHei";
    font-size: 25px;
    font-weight: bold;
    text-align: center;
  }


</style>
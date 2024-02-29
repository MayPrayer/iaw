<template xmlns:el-col="http://www.w3.org/1999/html">

  <div class="content">

      <div class="loginForm">
        <el-row type="flex" justify="center">
         <el-col :span="10" class="title">管理后台</el-col>
        </el-row>

        <el-row :gutter="10" type="flex" justify="center" style="margin-top: 20px">
          <el-col :span="18">
            <el-input placeholder="请输入用户名" prefix-icon="User" name ="userName"/>
          </el-col>
        </el-row>

        <el-row :gutter="10" type="flex" justify="center" style="margin-top: 30px">
          <el-col :span="18">
            <el-input placeholder="请输入密码" prefix-icon="Lock" name ="password" />
          </el-col>
        </el-row>

        <el-row  style="margin-top: 30px" type="flex" justify="center">
          <el-button type="primary"  style="width: 75%"  @click="login">登 录</el-button>
        </el-row>

      </div>


  </div>


</template>

<script setup>

const  login = ()=>{

  alert("登录提交")



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

  .loginForm{
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
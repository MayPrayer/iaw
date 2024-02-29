<template>

  <div class="content">
    <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        status-icon
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm">
      <el-form-item label="Password" prop="pass">
        <el-input v-model="ruleForm.pass" type="password" autocomplete="off" />
      </el-form-item>
      <el-form-item label="Confirm" prop="checkPass">
        <el-input
            v-model="ruleForm.checkPass"
            type="password"
            autocomplete="off"
        />
      </el-form-item>
      <el-form-item label="Age" prop="age">
        <el-input v-model.number="ruleForm.age" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm(ruleFormRef)"
        >Submit</el-button
        >
        <el-button @click="resetForm(ruleFormRef)">Reset</el-button>
      </el-form-item>
    </el-form>

  </div>


</template>

<script setup>
</script>

<style scoped>
  .content{
    background-image: url("https://project-1258741617.cos.ap-shanghai.myqcloud.com/BLUE.jpg");
    height: 1080px;
    opacity: 0.7;
    background-repeat: no-repeat; /* 设置背景不重复 */
    background-size: cover; /* 背景图片覆盖整个元素 */
    background-position: center; /* 居中显示背景图片 */
  }

  .loginForm{
    height: 300px;
    width: 200px;
    display: flex;
  }

</style>
<template>
    <div class="main">
       <nav class="navbar">
           <div class="logo">MayPrayer's 工具集</div>
           <ul class="nav-links">
               <li><a href="#home">首页</a></li>
               <li class="dropdown">
                   <a href="#about">关于我们</a>
                   <ul class="dropdown-menu">
                       <li><a href="#team">团队</a></li>
                       <li><a href="#history">历史</a></li>
                       <li><a href="#careers">招聘</a></li>
                   </ul>
               </li>
               <li class="dropdown">
                   <a href="#services">服务</a>
                   <ul class="dropdown-menu">
                       <li><a href="#webdesign">网页设计</a></li>
                       <li><a href="#seo">SEO</a></li>
                       <li><a href="#marketing">市场营销</a></li>
                   </ul>
               </li>
               <li><a href="#contact">联系我们</a></li>
           </ul>
       </nav>

       <div class="container">
           <aside class="sidebar-left">左侧栏</aside>
           <main class="content">主要内容区</main>
           <aside class="sidebar-right">右侧栏</aside>
       </div>

       <footer class="footer">
           Copyright © 梅州学相伴网络科技有限公司 粤ICP备 - 2020109190号
       </footer>
    </div>
</template>

<script setup>
</script>

<style scoped>

.main{
    display: flex;
    flex-direction: column;
}

.container{
    flex: 1;
    /*设置内部元素弹性布局*/
    display: flex;
    justify-content: space-around;
}
.container .sidebar-left{
    flex: 1;
}
.container .content{
    flex: 1;
}
.container .sidebar-right{
    flex: 1;
}



.footer{
    margin-bottom: 0px;
    height: 20px;
}

.navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: transparent;
    padding: 10px 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.logo {
    color: rgb(102,102,102);
    font-size: 24px;
    font-weight: bold;
}

.nav-links {
    list-style: none;
    display: flex;
    align-items: center;
}

.nav-links li {
    position: relative;
    margin-left: 20px;
}

.nav-links a {
    text-decoration: none;
    color: rgb(102,102,102);
    font-size: 18px;
    transition: color 0.3s;
}

.nav-links a:hover {
    color: #f4a261;
}

.dropdown:hover .dropdown-menu {
    display: block;
}

.dropdown-menu {
    display: none;
    position: absolute;
    top: 100%;
    left: 0;
    background-color: #444;
    list-style: none;
    padding: 10px 0;
    min-width: 150px;
}

.dropdown-menu li {
    padding: 10px 20px;
}

.dropdown-menu a {
    color: #fff;
    font-size: 16px;
    transition: color 0.3s;
}

.dropdown-menu a:hover {
    color: #f4a261;
}



</style>
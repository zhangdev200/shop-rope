<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  //路由跳转时滚动条回到顶部
  watch:{
    '$route': function(){
      document.body.scrollTop = 0
      document.documentElement.scrollTop = 0
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
body {
  margin: 0;
  background: linear-gradient(35deg, #CCFFFF, #FFCCCC) fixed;
  padding: 0 !important;
  overflow-y: scroll !important; /*解决messageBox打开关闭页面抖动问题*/
}


</style>
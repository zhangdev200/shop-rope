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

</style>
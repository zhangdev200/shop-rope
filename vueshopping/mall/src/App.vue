<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
// window.onbeforeunload = () => {
//   localStorage.setItem('token', '123');
//   localStorage.setItem('nickname', '好名字');
//   localStorage.setItem('avatar', 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png');
//   localStorage.setItem('isMembership', 'true');
// }
export default {
  //路由跳转时滚动条回到顶部
  watch:{
    '$route':function(){
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
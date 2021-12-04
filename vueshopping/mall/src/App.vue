<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  created() {
    document.querySelector('body').setAttribute('style', 'overflow-y: scroll');
  }
}
</script>

<style src="./assets/icon/iconfont.css"></style>
<style>
@import '//at.alicdn.com/t/font_2987071_5djbiom76v.css';

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

</style>

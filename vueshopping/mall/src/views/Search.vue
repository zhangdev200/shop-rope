<template>
  <div>
    <div style="width: 100%;
    height: 120px;
    z-index: 3;
    background: linear-gradient(35deg, #CCFFFF, #FFCCCC) fixed;
    position: fixed; top: 0">
      <div id="search">
        <el-input ref="input" placeholder="搜索您想要的商品" v-model="input" @keyup.enter.native="search">
          <el-button slot="append" icon="el-icon-search" id="button" @click="search"></el-button>
        </el-input>
      </div>
    </div>
    <div id="container">
      <keep-alive include="DefaultResult">
        <router-view></router-view>
      </keep-alive>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Search',
  data() {
    return {
      input: null,
    }
  },
  methods: {
    search() {
      if (this.input) {
        if (this.$route.name !== 'search') {
          this.$router.push('/search/' + this.input);
        } else {
          this.$router.replace({
            params: {
              ...this.$route.params,
              keyword: this.input
            }
          });
        }
      } else {
        this.$message.warning('请输入关键词！');
      }
    }
  },
  created() {

  }
}
</script>

<style scoped>
#search {
  z-index: 4;
  width: 40%;
  position: fixed;
  left: 30%;
  top: 68px;
  border: #cccccc solid 1px;
  border-radius: 5px;
}

#container {
  position: relative;
  top: 68px;
  z-index: 1;
  width: 70%;
  margin: 0 auto;
}

#button {
  color: deepskyblue;
  font-size: 18px;
  font-weight: bolder;
}

#button:active {
  -webkit-transform: scale(1.2) translateY(1px) translateX(1px);
  -moz-transform: scale(1.2) translateY(1px) translateX(1px);
  transform: scale(1.2) translateY(1px) translateX(1px);

}
</style>
<template>
  <div>
    <div style="width: 100%; height: 120px; z-index: 3; background-color: white; position: fixed; top: 0">
      <div id="search">
        <el-input placeholder="搜索您想要的商品" v-model="input">
          <el-button slot="append" icon="el-icon-search" id="button"></el-button>
        </el-input>
      </div>
    </div>
    <div id="container">
      <el-carousel :interval="2000" type="card">
        <el-carousel-item v-for="item in 6" :key="item">
          <img src="../assets/img.png" style="width: 600px;">
        </el-carousel-item>
      </el-carousel>
      <div>
        <h2 class="category">
          手机
        </h2>
        <div class="categoryBlock">
          <el-row>
            <el-col :span="6" v-for="o in 6" :key="o">
              <Card :id="o" @click="showDetail(id)"></Card>
            </el-col>
          </el-row>
        </div>
        <h2 class="category">
          家电
        </h2>
        <div class="categoryBlock">
          <el-row>
            <el-col :span="6" v-for="o in 6" :key="o">
              <Card :id="o"></Card>
            </el-col>
          </el-row>
        </div>
        <h2 class="category">
          图书
        </h2>
        <div class="categoryBlock">
          <el-row>
            <el-col :span="6" v-for="o in 6" :key="o">
              <Card :id="o" @click="showDetail(id)"></Card>
            </el-col>
          </el-row>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Card from "../components/Card";
export default {
  components: {Card},
  data() {
    return {
      input: '',
      select: '',
      price: '￥28.00'
    }
  },
  methods: {
    showDetail(id) {
      this.$router.push('goodsDetail?id=' + id);
    }
  }
}
</script>

<style scoped>
#search {
  z-index: 4;
  width: 30%;
  position: fixed;
  left: 36%;
  top: 70px;
  border: #cccccc solid 1px;
  border-radius: 5px;
}

#container {
  position: relative;
  top: 70px;
  z-index: 1;
  width: 70%;
  margin: 0 auto;
  /*background-color: #99a9bf;*/
}

#container::-webkit-scrollbar { /*滚动条整体样式*/
  width: 10px; /*高宽分别对应横竖滚动条的尺寸*/
  height: 1px;
}

#container::-webkit-scrollbar-thumb { /*滚动条里面小方块*/
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background: #dddddd;
}

#container::-webkit-scrollbar-track { /*滚动条里面轨道*/
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  border-radius: 10px;
  background: #fff;
}

.category {
  text-align: left;
}

.categoryBlock {
  padding: 20px;
  width: 100%;
  /*height: 500px;*/
  background-color: #eeeeee;
  border-radius: 20px;
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
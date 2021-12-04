<template>
  <div>
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

</template>

<script>
import Card from "../components/Card";
export default {
  components: {Card},
  name: "DefaultResult",
  methods: {
    showDetail(id) {
      this.$router.push('/goodsDetail?id=' + id);
    },
  }
}
</script>

<style scoped>
.category {
  text-align: left;
}

.categoryBlock {
  padding: 20px;
  background-color: #eeeeee;
  border-radius: 20px;
}
</style>
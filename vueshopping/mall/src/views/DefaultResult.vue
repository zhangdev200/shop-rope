<template>
    <div style="margin-bottom: 100px">
      <el-carousel :interval="1500" type="card">
        <el-carousel-item v-for="item in this.indexImg" :key="item.imgId">
          <img :src="item.imgUrl" style="width: 100%; height: 100%; border-radius: 20px" alt="">
        </el-carousel-item>
      </el-carousel>
      <div v-for="(category, index) in this.categoryList" :key=index>
        <h2 class="category">
          {{ category }}
        </h2>
        <div class="categoryBlock">
          <el-row>
            <el-col :lg="6" :md="8" :sm="12" :xs="24" v-for="o in goodsData.get(category)" :key="o.productId">
              <Card :data="o"></Card>
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
  data() {
    return {
      indexImg: null,
      goodsData: new Map,
      categoryList: [],
    }
  },
  methods: {},
  created() {
    this.$http
    .get('/index/indeximg')
    .then(res => {
      if (res.code === 10000) {
        this.indexImg = res.data;
      } else {
        this.$message.error('未知错误');
      }
    })
    this.$http
        .get('/index/category-recommends')
        .then(res => {
          if (res.code === 10000) {
            for (let item of res.data) {
              this.categoryList.push(item.categoryName);
              this.goodsData.set(item.categoryName, item.products);
            }
          } else if (res.msg === 'fail') {
            this.$message.info('轮播图为空');
          } else {
            this.$message.error('未知错误');
          }
        });
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
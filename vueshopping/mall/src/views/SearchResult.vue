<template>
  <div>
    <div style="text-align: left">
      <h3>搜索结果</h3>
    </div>
    <div class="block">
      <div v-if="count">
        <el-row>
          <el-col :lg="6" :md="8" :sm="12" :xs="24" v-for="o in result" :key="o.productId">
            <Card :data="o"></Card>
          </el-col>
        </el-row>
      </div>
      <div v-else>
        <p>还没有您想要的商品哦</p>
      </div>
    </div>
    <br>
    <div style="width: 100%;">
      <el-pagination
          ref="page"
          background
          layout="total, prev, pager, next, jumper"
          :page-size="8"
          :total="count"
          @current-change="currentChange">
      </el-pagination>
    </div>
    <br>
  </div>
</template>

<script>
import Card from "@/components/Card";

export default {
  name: "SearchResult",
  components: {Card},
  data() {
    return {
      result: [],
      count: null,
      pageCount: null,
    }
  },
  methods: {
    getResult(pageNum, limit) {
      this.$http
          .get('/product/listbykeyword', {
            pageNum: pageNum,
            limit: limit,
            keyword: this.$route.params.keyword,
          })
          .then(res => {
            if (res.code === 10000) {
              this.count = res.data.count;
              this.pageCount = res.data.pageCount;
              this.result = res.data.list;
            }
          });
    },
    currentChange(pageNum) {
      this.getResult(pageNum, 8);
    },
  },
  created() {
    this.getResult(1, 8);
  },
  watch: {
    $route() {
      this.getResult(1, 8);
    }
  }
}
</script>

<style scoped>
.block {
  padding: 20px;
  background-color: #eeeeee;
  border-radius: 20px;
}
</style>
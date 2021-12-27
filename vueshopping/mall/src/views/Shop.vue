<template>
  <div id="shop">
    <h2>欢迎光临 {{shopName}}</h2>
    <div class="block">
      <el-row>
        <el-col :lg="6" :md="8" :sm="12" :xs="24" v-for="o in productList" :key="o.productId">
          <Card :data="o"></Card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import Card from "@/components/Card";

export default {
  name: "Shop",
  components: {Card},
  data() {
    return {
      shopName: null,
      productList: [],
    }
  },
  methods: {
    getProducts() {
      this.$http
          .get('/shop/list', {
            shopID: this.$route.params.id
          })
          .then(res => {
            if (res.code === 10000) {
              this.productList = res.data;
              this.$http
                  .get('shop/listshops')
                  .then(res => {
                    if (res.code === 10000) {
                      for (let i of res.data) {
                        if (i.shopID === this.$route.params.id) {
                          this.shopName = i.shopName;
                        }
                      }
                    }
                  })
            } else {
              this.$message.error('获取店铺信息失败')
            }
          })
    }
  },
  created() {
    this.getProducts();
  }
}
</script>

<style scoped>
#shop {
  width: 70%;
  margin: 0 auto;
}

.block {
  padding: 20px;
  background-color: #eeeeee;
  border-radius: 20px;
}
</style>
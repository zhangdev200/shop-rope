<template>
  <el-card :body-style="{ padding: '15px' }" class="card">
    <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
         class="image"
         @click="showDetail(id)">
    <div style="padding: 14px; text-align: left">
      <p style="text-align: left">好吃的汉堡，你值得拥有，快来购买吧！！！</p>
      <div class="bottom clearfix">
        <span class="price">￥{{ price }}</span>
        <el-button type="text" @click="showDetail(id)">查看详情</el-button>
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "Card",
  props: ['id'],
  data() {
    return {
      goodsId: null,
      price: 28.00
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
.card {
  margin: 10px;
  border-radius: 10px;
}

.card:hover {
  transform: scale(1.03);
}

.bottom button {
  float: right;
  font-size: 18px;
}

.price {
  font-size: 28px;
  font-weight: bold;
  color: red;
}

.image {
  border-radius: 10px;
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>
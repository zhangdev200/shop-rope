<template>
  <el-card :body-style="{ padding: '15px' }" class="card">
    <img :src="goodsData.imgs && goodsData.imgs.length !== 0 ? goodsData.imgs[0].url : null"
         onerror="this.src = '//iconfont.alicdn.com/s/210a299f-edad-4fc5-8396-9f743633f209_origin.svg'"
         class="image"
         @click="showDetail(goodsData.productId)" alt="">
    <div style="padding: 14px; text-align: left">
      <p>{{goodsData.productName ? goodsData.productName : '加载失败'}}</p>
      <p class="description">{{ goodsData.content }}</p>
      <div class="bottom clearfix">
        <span class="price">￥{{ goodsData.skus && goodsData.skus.length !== 0 ? goodsData.skus[0].sellPrice : '加载失败' }}</span>
        <el-button type="text" @click="showDetail(goodsData.goodsId)">查看详情</el-button>
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "Card",
  props: ['data'],
  data() {
    return {
      goodsData: {
        goodsId: null,
        goodsName: '',
        img: null,
        description: '',
        price: null
      },
    }
  },
  methods: {
    showDetail() {
      this.$router.push('/goods/' + this.goodsData.productId);
    }
  },
  mounted() {
    this.goodsData = this.data;
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
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.5);
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
  width: 100%;
  border-radius: 10px;
  height: 200px;
  display: block;
}

.description {
  color: #444444;
  text-align: left;
  height: 80px;
  overflow-y: scroll
}

.description::-webkit-scrollbar {
  display: none
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
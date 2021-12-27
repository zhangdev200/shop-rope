<template>
  <div id="container">
    <el-row id="description">
      <el-col :span="4">
        <img :src="detail.img" alt=""
             onerror="this.src='//iconfont.alicdn.com/s/210a299f-edad-4fc5-8396-9f743633f209_origin.svg';"
             style="width: 100%; border-radius: 15px"/>
      </el-col>
      <el-col :span="20">
        <div style="text-align: left; padding: 10px 20px;">
          <p style="font-weight: bold; margin-top: 5px">{{ detail.goodsName }}</p>
          <p style="word-wrap: break-word">{{ detail.description }}</p>
          <p style="color: #666666; position: absolute; bottom: 12px">
            <i class="el-icon-s-shop" style="font-size: 25px"></i>
            {{ detail.storeName }}
            <el-button type="text" style="padding: 0; font-size: 18px" @click="enterShop"> 进店逛逛>></el-button>
          </p>
          <br>
        </div>
      </el-col>
      <span class="price">￥{{ detail.price }}</span>
      <div style="position: absolute; bottom: 20px; right: 20px">
        <el-button type="primary" round @click="addToCart">加入购物车</el-button>
        <el-button type="primary" round @click="buyNow">立即购买</el-button>
      </div>
    </el-row>
    <div id="comment">
      <br>
      <h3>
        <i class="el-icon-chat-dot-square" style="font-size: larger; position: relative; top: 3px"></i>
        用户评价
      </h3>
      <hr>
      <br>
      <div v-if="comments.length === 0">
        <p>暂无评价</p>
      </div>
      <Comment v-else v-for="comment in comments"
               :data="comment"
               :key="comment.id">
      </Comment>
      <el-pagination
          ref="page"
          background
          layout="total, prev, pager, next"
          :page-size="5"
          :total="totalComments"
          @current-change="currentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import Comment from "@/components/Comment";

export default {
  components: {Comment},
  data() {
    return {
      detail: {
        goodsName: null,
        img: null,
        storeId: null,
        description: null,
        price: null,
        skuId: null,
      },
      totalComments: 0,
      totalCommentsPages: 0,
      comments: []
    }
  },
  methods: {
    addToCart() {
      if (!localStorage.getItem('userInform')) {
        this.$message.warning('请先登录！');
        return;
      }
      this.$http
          .post('/shopcart/add', {
            productId: this.$route.params.id,
            userId: JSON.parse(localStorage.getItem('userInform')).userId,
            productPrice: this.detail.price,
            skuId: this.detail.skuId,
          })
          .then(res => {
            if (res.code === 10000) {
              this.$message.success('添加成功！')
            }
          });
    },
    buyNow() {
      if (!localStorage.getItem('userInform')) {
        this.$message.warning('请先登录！');
        return;
      }
      if (!JSON.parse(localStorage.getItem('userInform')).realname ||
          !JSON.parse(localStorage.getItem('userInform')).userMobile ||
          !JSON.parse(localStorage.getItem('userInform')).userAddress) {
        this.$message.warning('请完善个人信息！');
        setTimeout(() => {
          this.$router.push('/personal/personal');
        }, 2500);
        return;
      }
      this.$http
          .post('/order/addInstance?productId=' + this.$route.params.id, {
            userId: JSON.parse(localStorage.getItem('userInform')).userId,
            receiverName: JSON.parse(localStorage.getItem('userInform')).realname,
            receiverMobile: JSON.parse(localStorage.getItem('userInform')).userMobile,
            receiverAddress: JSON.parse(localStorage.getItem('userInform')).userAddress,
          })
          .then(res => {
            if (res.code === 10000) {
              this.$message.success('购买成功！')
              console.log(res.data.orderId)
            } else {
              this.$message.error(res.msg)
            }
          });
    },
    enterShop() {
      this.$router.push('/shop/' + this.detail.storeId);
    },
    getCommentByPage(pageNum, limit) {
      this.$http
          .get('/product/detail-comments/' + this.$route.params.id, {
            pageNum: pageNum,
            limit: limit,
          })
          .then(res => {
            if (res.code === 10000) {
              this.totalComments = res.data.count;
              this.totalCommentsPages = res.data.pageCount;
              this.comments = [];
              for (let item of res.data.list) {
                this.comments.push({
                  id: item.commId,
                  stars: item.commType,
                  avatar: item.userImg,
                  nickname: item.nickname,
                  date: item.replyTime ? item.replyTime.substring(0,10) : null,
                  content: item.commContent,
                  numOfZan: Math.floor((Math.random() * 100)),
                  numOfCai: Math.floor((Math.random() * 100)),
                })
              }
            }
          });
    },
    currentChange(pageNum) {
      this.getCommentByPage(pageNum, 5);
    },
  },
  created() {
    this.$http
        .get('/product/detail-info/' + this.$route.params.id)
        .then(res => {
          if (res.code === 10000) {
            this.detail = {
              goodsName: res.data.product.productName,
              img: res.data.productImgs && res.data.productImgs.length ?
                  res.data.productImgs[0].url :
                  '//iconfont.alicdn.com/s/210a299f-edad-4fc5-8396-9f743633f209_origin.svg',
              storeId: res.data.product.shopID,
              description: res.data.product.content,
              price: res.data.productSkus && res.data.productSkus.length ? res.data.productSkus[0].sellPrice : null,
              skuId: res.data.productSkus && res.data.productSkus.length ? res.data.productSkus[0].skuId : null,
            }
            this.getCommentByPage(1, 5);
          }
        });
  }
}
</script>

<style scoped>
#container {
  width: 55%;
  margin: 85px auto 20px auto;

}

#description {
  padding: 20px;
  border-radius: 15px;
  background-color: #eeeeee;
}

#comment {
  text-align: left;
}

.price {
  position: absolute;
  bottom: 20px;
  right: 280px;
  font-size: 35px;
  font-weight: bold;
  color: red;
}
</style>
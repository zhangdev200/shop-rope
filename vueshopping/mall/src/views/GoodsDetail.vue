<template>
  <div id="container">
    <el-row id="description">
      <el-col :span="4">
        <img src="../assets/Hamburger.png" alt="" style="width: 100%; border-radius: 15px"/>
      </el-col>
      <el-col :span="20">
        <div style="text-align: left; padding: 10px 20px;">
          <p style="font-weight: bold; margin-top: 5px">{{ detail.goodsName }}</p>
          <p style="word-wrap: break-word">{{ detail.description }}</p>
          <p style="color: #666666; position: absolute; bottom: 12px">
            <i class="el-icon-s-shop" style="font-size: 25px"></i>
            {{ detail.storeName }}
            <el-button type="text" style="padding: 0; font-size: 18px"> 进店逛逛>></el-button>
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
      <Comment v-for="comment in comments"
               :data="comment"
               :key="comment.id">
      </Comment>
    </div>
  </div>
</template>

<script>
import Comment from "@/components/Comment";

export default {
  components: {Comment},
  data() {
    return {
      detail: null,
      comments: []
    }
  },
  methods: {
    addToCart() {
      alert(this.$route.params.id);
    },
    buyNow() {

    }
  },
  created() {
    // this.$http
    //     .get('api')
    //     .then(res => {
    //       this.detail = res.data;
    //     });
    // this.$http
    //     .get('api')
    //     .then(res => {
    //       this.comments = res.data;
    //     });
    //todo 根据商品id获得商品详情
    this.detail =
        {
          goodsName: '汉堡',
          img: '../assets/Hamburger.png',
          storeId: 1,
          storeName: '好名字店铺',
          description: '好吃的汉堡，你值得拥有，快来购买吧！！！' +
              'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
          price: 28.00
        }
    //todo 根据商品id评论
    this.comments =
        [
          {
            id: 1,
            stars: 5,
            avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
            nickname: '用户名',
            date: '2021-12-5',
            content: '五星好评！！！',
            numOfZan: 100,
            numOfCai: 100,
          },
          {
            id: 2,
            stars: 3,
            avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
            nickname: '用户名',
            date: '2021-12-4',
            content: '评论pt>export default {name: "Comment",data() {return {avatar:' +
                ' \'https: //cube.elemecdn.com /3/7c/3ea6beec643 69c2642b92c6726f1e png.png\'' +
                '          }pneumonoul  tramicroscopic silicovolcan oconiosis }}',
            numOfZan: 50,
            numOfCai: 50,
          },
          {
            id: 3,
            stars: 1,
            avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
            nickname: '用户名',
            date: '2021-12-4',
            content: '差评！！！',
            numOfZan: 100,
            numOfCai: 100,
          },
          {
            id: 4,
            stars: 4,
            avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
            nickname: '用户名',
            date: '2021-12-4',
            content: '评论pt>export default {name: "Comment",data() {return {avatar:' +
                ' \'https: //cube.elemecdn.com /3/7c/3ea6beec643 69c2642b92c6726f1e png.png\'' +
                '          }pneumonoul  tramicroscopic silicovolcan oconiosis }}',
            numOfZan: 100,
            numOfCai: 100,
          },
          {
            id: 5,
            stars: 5,
            avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
            nickname: '用户名',
            date: '2021-12-4',
            content: '评论pt>export default {name: "Comment",data() {return {avatar:' +
                ' \'https: //cube.elemecdn.com /3/7c/3ea6beec643 69c2642b92c6726f1e png.png\'' +
                '          }pneumonoul  tramicroscopic silicovolcan oconiosis }}',
            numOfZan: 100,
            numOfCai: 100,
          },
          {
            id: 6,
            stars: 5,
            avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
            nickname: '用户名',
            date: '2021-12-4',
            content: '评论pt>export default {name: "Comment",data() {return {avatar:' +
                ' \'https: //cube.elemecdn.com /3/7c/3ea6beec643 69c2642b92c6726f1e png.png\'' +
                '          }pneumonoul  tramicroscopic silicovolcan oconiosis }}',
            numOfZan: 100,
            numOfCai: 100,
          },
          {
            id: 7,
            stars: 5,
            avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
            nickname: '用户名',
            date: '2021-12-4',
            content: '评论pt>export default {name: "Comment",data() {return {avatar:' +
                ' \'https: //cube.elemecdn.com /3/7c/3ea6beec643 69c2642b92c6726f1e png.png\'' +
                '          }pneumonoul  tramicroscopic silicovolcan oconiosis }}',
            numOfZan: 100,
            numOfCai: 100,
          },
        ];
  }
}
</script>

<style scoped>
#container {
  width: 55%;
  margin: 0 auto;

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
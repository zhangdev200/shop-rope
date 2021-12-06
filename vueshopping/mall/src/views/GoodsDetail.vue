<template>
  <div id="container">
    <el-row id="description">
      <el-col :span="6">
        <img src="../assets/Hamburger.png" alt="" style="width: 100%; border-radius: 15px"/>
      </el-col>
      <el-col :span="18" >
        <div style="text-align: left; padding: 0 20px; margin-bottom: 80px">
          <span style="word-wrap: break-word">{{ detail.description }}</span>
          <br>
        </div>
      </el-col>
      <span class="price">￥{{ detail.price }}</span>
      <div style="position: absolute; bottom: 20px; right: 20px">
        <el-button type="primary">加入购物车</el-button>
        <el-button type="primary">立即购买</el-button>
      </div>
    </el-row>
    <div id="comment">
      <br>
      <h3>
        <i class="el-icon-chat-dot-square" style="font-size: larger; position: relative; top: 3px"></i>
        用户评价
      </h3>
      <hr><br>
      <Comment></Comment>
      <Comment></Comment>
      <Comment></Comment>
      <Comment></Comment>
      <Comment></Comment>
      <Comment></Comment>
      <Comment></Comment>
      <Comment></Comment>
      <Comment></Comment>
      <Comment></Comment>
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
        img: '../assets/Hamburger.png',
        description: null,
        price: null,
      }
    }
  },
  created() {
    //   this.axios
    //       .get('api')
    //       .then(response => {
    //         this.detail = response.data.detail;
    //       })
    //       .catch(err => console.log(err));
    this.detail = {
      img: '../assets/Hamburger.png',
      description: '好吃的汉堡，你值得拥有，快来购买吧！！！' +
          'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      price: 28.00
    }
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
  right: 260px;
  font-size: 35px;
  font-weight: bold;
  color: red;
}
</style>
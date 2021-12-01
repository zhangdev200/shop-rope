<template>
<div>
<img :src="detail.img">

  <div>
    <span class="price">￥{{detail.price}}</span>
    <br>
    <span>{{detail.description}}</span>
  </div>
</div>
</template>

<script>
export default {
  data() {
    return {
      detail: {
        img: null,
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
      img: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png',
      description: '好吃的汉堡，你值得拥有，快来购买吧！！！',
      price: 28.00
    }
  }

}
</script>

<style scoped>
.price {
  font-size: 28px;
  font-weight: bold;
  color: red;
}
</style>
<template>
  <div>
    <div id="container">
      <div>
        <div style="width: 50%; display: inline-block; text-align: left">
          <el-button type="primary" round @click="selectReverse" class="myButton">反选</el-button>
          <el-button type="primary" round @click="selectAll" ref="selectAll" class="myButton">全选</el-button>
        </div>
        <div style="width:50%; display: inline-block; text-align: right">
          <el-button type="danger" round @click="deleteItems" class="myButton">删除</el-button>
          <el-button type="success" round @click="check" class="myButton">结算</el-button>
        </div>
      </div>

      <CartItem v-for="i in cartList" :item="i" :key="i.goodsId" ref="cart"></CartItem>
    </div>

  </div>
</template>

<script>
import CartItem from "@/components/CartItem";

export default {
  name: "Cart",
  components: {CartItem},
  data() {
    return {
      cartList: [
        {
          goodsId: 1,
          img: null,
          description: '描述信息描述信息描述信息描述信息描述信息描述信息描述信息' +
              '描述信息描述信息描述信息描述信息描述信息描述信息描述信息',
          price: 28.0,
          amount: 1
        },
        {
          goodsId: 2,
          img: null,
          description: '描述信息描述信息描述信息描述信息描述信息描述信息描述信息' +
              '描述信息描述信息描述信息描述信息描述信息描述信息描述信息',
          price: 28.0,
          amount: 2
        },
        {
          goodsId: 3,
          img: null,
          description: '描述信息描述信息描述信息描述信息描述信息描述信息描述信息' +
              '描述信息描述信息描述信息描述信息描述信息描述信息描述信息',
          price: 28.0,
          amount: 3
        },
        {
          goodsId: 4,
          img: null,
          description: '描述信息描述信息描述信息描述信息描述信息描述信息描述信息' +
              '描述信息描述信息描述信息描述信息描述信息描述信息描述信息',
          price: 28.0,
          amount: 1
        },
      ]
    }
  },
  methods: {
    check() {
      let list = [];
      for (let i in this.$refs.cart) {
        if (this.$refs.cart[i].selected === true) {
          list.push(this.$refs.cart[i].$data.itemData.goodsId);
        }
      }
      alert(list.toString())
    },
    selectAll() {
      if (this.$refs.selectAll.$el.children[0].innerHTML === '全选') {
        for (let i in this.$refs.cart) {
          if (this.$refs.cart[i].selected === false) {
            this.$refs.cart[i].select();
          }
        }
        this.$refs.selectAll.$el.children[0].innerHTML = '取消全选';
      }
      else {
        for (let i in this.$refs.cart) {
          if (this.$refs.cart[i].selected === true) {
            this.$refs.cart[i].select();
          }
        }
        this.$refs.selectAll.$el.children[0].innerHTML = '全选';
      }
    },
    selectReverse() {
      for (let i in this.$refs.cart) {
        this.$refs.cart[i].select();
      }
    },
    deleteItems() {
      // for (let i in this.$refs.cart) {
      //   if (this.$refs.cart[i].selected === true) {
      //
      //   }
      // }
    }
  }
}
</script>

<style scoped>
#container {
  width: 50%;
  margin: 0 auto;
}

.myButton {
  width: 120px;
}
</style>
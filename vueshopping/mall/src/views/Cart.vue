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
      <div v-if="this.cartList.length === 0">
        <el-empty description="你的购物车还没有商品哦" :image-size="400"></el-empty>
      </div>
      <CartItem
          v-for="i in cartList"
          :item="i"
          :key="i.goodsId"
          ref="cart"
          @select="select"
          @unselect="unselect">
      </CartItem>
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
      selectSet: new Set,
      cartList: [
        {
          goodsId: 1,
          img: null,
          description: 'goodsId:1 描述信息描述信息描述信息描述信息描述信息描述信息描述信息' +
              '描述信息描述信息描述信息描述信息描述信息描述信息描述信息',
          price: 28.0,
          amount: 1
        },
        {
          goodsId: 2,
          img: null,
          description: 'goodsId:2 描述信息描述信息描述信息描述信息描述信息描述信息描述信息' +
              '描述信息描述信息描述信息描述信息描述信息描述信息描述信息',
          price: 28.0,
          amount: 2
        },
        {
          goodsId: 3,
          img: null,
          description: 'goodsId:3 描述信息描述信息描述信息描述信息描述信息描述信息描述信息' +
              '描述信息描述信息描述信息描述信息描述信息描述信息描述信息',
          price: 28.0,
          amount: 3
        },
        {
          goodsId: 4,
          img: null,
          description: 'goodsId:4 描述信息描述信息描述信息描述信息描述信息描述信息描述信息' +
              '描述信息描述信息描述信息描述信息描述信息描述信息描述信息',
          price: 28.0,
          amount: 1
        },
      ]
    }
  },
  methods: {
    select(goodsId) {
      this.selectSet.add(goodsId);
    },
    unselect(goodsId) {
      this.selectSet.delete(goodsId);
    },
    check() {
      let list = [];
      for (let i of this.$refs.cart) {
        if (i.selected) {
          list.push(i.itemData.goodsId);
        }
      }
      alert(list.toString());
    },
    selectAll() {
      if (this.$refs.selectAll.$el.children[0].innerHTML === '全选') {
        for (let i of this.$refs.cart) {
          if (i.selected === false) {
            i.select();
          }
        }
        this.$refs.selectAll.$el.children[0].innerHTML = '取消全选';
      } else {
        for (let i of this.$refs.cart) {
          if (i.selected === true) {
            i.select();
          }
        }
        this.$refs.selectAll.$el.children[0].innerHTML = '全选';
      }
    },
    selectReverse() {
      for (let i of this.$refs.cart) {
        i.select();
      }
    },
    deleteItems() {
      for (let i of this.selectSet) {
        for (let j = 0; j < this.cartList.length; j++) {
          if (this.cartList[j].goodsId === i) {
            this.cartList.splice(j, 1);
          }
        }
      }
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
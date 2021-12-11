<template>
  <div>
    <div style="margin-top: 300px" v-if="!isLogin">
      <h1><i class="el-icon-error" style="color: red"></i> 请先登录！</h1>
    </div>
    <div v-else>
      <div id="container">
        <div v-if="this.cartList.length === 0">
          <el-empty description="你的购物车还没有商品哦" :image-size="400"></el-empty>
        </div>
        <CartItem
            v-for="i in cartList"
            :item="i"
            :key="i.goodsId"
            ref="cart"
            @select="select"
            @unselect="unselect"
            @check="checkPriceChange"
        >
        </CartItem>
      </div>
      <div class="bottomBar">
        <div style="width: 50%; margin: 0 auto">
          <div style="width: 50%; display: inline-block; text-align: left">
            <el-button type="primary" round @click="selectReverse" class="myButton">反选</el-button>
            <el-button type="primary" round @click="selectAll" ref="selectAll" class="myButton">全选</el-button>
            <div style="position:relative; left: 30px; top: 3px; display: inline-block; text-align: center">
              <span>合计：<span style="color: red;">￥{{ checkPrice }}</span></span>
            </div>
          </div>
          <div style="width: 50%; display: inline-block; text-align: right">
            <el-popconfirm title="确定删除吗？" @confirm="deleteItems" class="myButton" style="margin: 0 10px">
              <el-button type="danger" round class="myButton" slot="reference">删除</el-button>
            </el-popconfirm>
            <el-popconfirm title="确定结算吗？" @confirm="check" class="myButton">
              <el-button type="success" round class="myButton" slot="reference">结算</el-button>
            </el-popconfirm>
          </div>
        </div>
      </div>
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
      checkPrice: 0,
      checkId: [],
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
        {
          goodsId: 5,
          img: null,
          description: 'goodsId:5 描述信息描述信息描述信息描述信息描述信息描述信息描述信息' +
              '描述信息描述信息描述信息描述信息描述信息描述信息描述信息',
          price: 28.0,
          amount: 1
        },
        {
          goodsId: 6,
          img: null,
          description: 'goodsId:6 描述信息描述信息描述信息描述信息描述信息描述信息描述信息' +
              '描述信息描述信息描述信息描述信息描述信息描述信息描述信息',
          price: 28.0,
          amount: 1
        },
      ]
    }
  },
  methods: {
    checkPriceChange() {
      this.checkPrice = 0;
      this.checkId = [];
      for (let i of this.$refs.cart) {
        if (i.selected) {
          this.checkPrice += i.totalPrice;
          this.checkId.push(i.itemData.goodsId);
        }
      }
    },
    select(goodsId) {
      this.selectSet.add(goodsId);
    },
    unselect(goodsId) {
      this.selectSet.delete(goodsId);
    },
    check() {
      alert(this.checkId.toString() + '\n总金额：' + this.checkPrice);
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
    },
    toLogin() {
      this.$router.replace('/login');
    }
  },
  computed: {
    isLogin() {
      return localStorage.getItem('nickname') !== null;
    }
  }
}
</script>

<style scoped>
#container {
  width: 50%;
  margin: 0 auto 100px auto;
  z-index: 1;
}

.myButton {
  width: 120px;
}

.bottomBar {
  position: fixed;
  bottom: 0;
  width: 100%;
  height: 60px;
  background-color: rgba(210, 210, 210, 1);
  z-index: 10;
  padding-top: 20px;
}

</style>
<template>
  <div>
    <div v-if="!isLogin" style="margin-top: 300px">
      <h1><i class="el-icon-error" style="color: red"></i> 请先登录！</h1>
    </div>
    <div v-else>
      <div id="container">
        <div v-if="this.cartList.length === 0">
          <el-empty description="你的购物车还没有商品哦" :image-size="400"></el-empty>
        </div>
        <div v-else>
          <h3>我的购物车</h3>
          <div class="cartItemContainer">
            <CartItem
                v-for="i in cartList"
                :item="i"
                :key="i.goodsId"
                ref="cart"
                @select="select"
                @unselect="unselect"
                @check="checkPriceChange">
            </CartItem>
          </div>
        </div>
      </div>
      <div class="bottomBar">
        <div style="width: 60%; margin: 0 auto">
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
      selectCartIdSet: new Set,
      checkPrice: 0,
      cartList: [],
    }
  },
  methods: {
    checkPriceChange() {
      this.checkPrice = 0;
      for (let i of this.$refs.cart) {
        if (i.selected) {
          this.checkPrice += i.totalPrice;
        }
      }
    },
    select(cartId) {
      this.selectCartIdSet.add(cartId);
    },
    unselect(cartId) {
      this.selectCartIdSet.delete(cartId);
    },
    check() {
      if (!JSON.parse(localStorage.getItem('userInform')).realname ||
          !JSON.parse(localStorage.getItem('userInform')).userMobile ||
        !JSON.parse(localStorage.getItem('userInform')).userAddress) {
        this.$message.warning('请完善个人信息！');
        setTimeout(() => {
          this.$router.push('/personal/personal');
        }, 2500);
        return;
      }
      if (this.selectCartIdSet.size === 0) {
        this.$message.warning('请先选择商品！');
        return;
      }
      let str = '';
      for (let i of this.selectCartIdSet) {
        str += i + ',';
      }
      str = str.substring(0, str.length - 1);
      this.$http
          .post('/order/add?cids=' + str, {
            userId: JSON.parse(localStorage.getItem('userInform')).userId,
            receiverName: JSON.parse(localStorage.getItem('userInform')).realname,
            receiverMobile: JSON.parse(localStorage.getItem('userInform')).userMobile,
            receiverAddress: JSON.parse(localStorage.getItem('userInform')).userAddress,
          })
          .then(res => {
            if (res.code === 10000) {
              this.$message.success('结算成功！')
              this.selectCartIdSet.clear();
              this.checkPrice = 0;
              this.getCarts();
            }
            else {
              this.$message.error('未知错误');
            }
          });
    },
    selectAll() {
      if (this.$refs.cart) {
        if (this.$refs.selectAll.$el.children[0].innerHTML === '全选') {
          for (let i of this.$refs.cart) {
            if (!i.selected) {
              i.select();
            }
          }
          this.$refs.selectAll.$el.children[0].innerHTML = '取消全选';
        } else {
          for (let i of this.$refs.cart) {
            if (i.selected) {
              i.select();
            }
          }
          this.$refs.selectAll.$el.children[0].innerHTML = '全选';
        }
      }
    },
    selectReverse() {
      if (this.$refs.cart) {
        for (let i of this.$refs.cart) {
          i.select();
        }
      }
    },
    deleteItems() {
      if (this.selectCartIdSet.size === 0) {
        this.$message.warning('请选择要删除的商品！');
      } else {
        let str = '';
        for (let i of this.selectCartIdSet) {
          str += i + ',';
        }
        str = str.substring(0, str.length - 1)
        this.$http
            .get('/shopcart/deletebycids?cids=' + str)
            .then(res => {
              if (res.code === 10000) {
                let newSet = this.selectCartIdSet;
                for (let i of newSet) {
                  for (let j = 0; j < this.cartList.length; j++) {
                    if (this.cartList[j].cartId === i) {
                      this.cartList.splice(j, 1);
                    }
                  }
                  for (let k of this.$refs.cart) {
                    if (k.selected && k.itemData.cartId === i) {
                      k.select();
                    }
                  }
                }
                this.$message.success('删除成功！');
              }
            });
      }
    },
    toLogin() {
      this.$router.replace('/login');
    },
    getCarts() {
      this.$http
          .get('/shopcart/list', {
            userId: JSON.parse(localStorage.getItem('userInform')).userId
          })
          .then(res1 => {
            if (res1.code === 10000) {
              this.cartList = [];
              for (let cart of res1.data) {
                this.cartList.push({
                  cartId: cart.cartId,
                  amount: cart.cartNum,
                  goodsId: cart.productId,
                  goodsName: cart.productName,
                  img: cart.productImg,
                  description: cart.content,
                  price: cart.productPrice,
                });
              }
            } else {
              this.$message.error(res1.msg);
            }
          });
    }
  },
  computed: {
    isLogin() {
      return localStorage.getItem('token') !== null;
    }
  },
  created() {
    this.getCarts();
  }
}
</script>

<style scoped>
#container {
  z-index: 1;
}

.myButton {
  width: 120px;
}

.cartItemContainer {
  width: 60%;
  margin: 0 auto 100px auto;
}

.search {
  position: fixed;
  top: 57px;
  width: 100%;
  height: 80px;
  margin: 0 auto;
  background: linear-gradient(35deg, #CCFFFF, #FFCCCC) fixed;
  z-index: 12;
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
<template>
  <transition name="el-zoom-in-top">
    <div>
      <el-row class="cart">
        <el-col :span="3">
          <img :src="itemData.img"
               onerror="this.src='//iconfont.alicdn.com/s/210a299f-edad-4fc5-8396-9f743633f209_origin.svg';"
               alt="" style="width: 100%; border-radius: 15px"/>
        </el-col>
        <el-col :span="21">
          <div style="text-align: left; padding: 0 20px; margin-bottom: 70px; font-size: 16px">
            <p style="margin: 0; font-weight: bold">{{ itemData.goodsName }}</p>
            <p style="word-wrap: break-word; font-size: 16px">{{ itemData.description }}</p>
          </div>
        </el-col>
        <div class="select" @click="select">
          <i class="el-icon-circle-check" ref="select"></i>
        </div>
        <span class="price">￥{{ totalPrice }}</span>
        <div style="position: absolute; bottom: 20px; right: 20px; border-radius: 10px">
          <el-input-number v-model="amount" @change="change" :min="0" :step="step" label="描述文字"></el-input-number>
        </div>
      </el-row>
    </div>
  </transition>

</template>

<script>
export default {
  name: "CartItem",
  props: ['item'],
  data() {
    return {
      step: 1,
      itemData: {},
      amount: 1,
      totalPrice: 0,
      selected: false,
    }
  },

  methods: {
    change() {
      this.totalPrice = this.amount * this.itemData.price;
      this.$http.put('/shopcart/update/' + this.itemData.cartId + '/' + this.amount);
      this.$emit('check');
    },
    select() {
      this.selected = !this.selected;
      if (this.selected) {
        this.$refs.select.style.color = 'red';
        this.$emit('select', this.itemData.cartId);
        this.$emit('check');
      } else {
        this.$refs.select.style.color = 'black';
        this.$emit('unselect', this.itemData.cartId);
        this.$emit('check');
      }
    }
  },
  created() {
    this.itemData = this.item;
    this.amount = this.itemData.amount;
    this.totalPrice = this.itemData.price * this.amount;
  },

}
</script>

<style scoped>
.cart {
  background-color: #eeeeee;
  border-radius: 10px;
  padding: 20px;
  margin: 0 auto 25px auto;
}

.select {
  font-size: xx-large;
  position: absolute;
  left: -60px;
  color: black;
  width: 50px;
  height: 100px;
}

.price {
  position: absolute;
  bottom: 20px;
  right: 240px;
  font-size: 35px;
  font-weight: bold;
  color: red;
}

</style>
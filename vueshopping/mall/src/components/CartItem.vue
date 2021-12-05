<template>
  <transition name="el-zoom-in-top">
  <div>
    <el-row class="cart">
      <el-col :span="5">
        <img src="../assets/Hamburger.png" alt="" style="width: 100%; border-radius: 15px"/>
      </el-col>
      <el-col :span="18">
        <div style="text-align: left; padding: 0 20px; margin-bottom: 80px">
          <span style="word-wrap: break-word">{{ itemData.description }}</span>
          <br>
        </div>
      </el-col>
      <div class="select" @click="select">
        <i class="el-icon-circle-check" ref="select"></i>
      </div>
      <span class="price">￥{{ totalPrice }}</span>
      <div style="position: absolute; bottom: 20px; right: 20px; border-radius: 10px">
        <el-input-number v-model="itemData.amount" @change="change" :min="0" :step="step" label="描述文字"></el-input-number>
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
      itemData: {
        goodsId: null,
        img: null,
        description: null,
        price: null,
        amount: 1,
      },
      totalPrice: null,
      selected: false,
    }
  },
  methods: {
    change() {
      this.totalPrice = this.itemData.amount * this.itemData.price;
    },
    select() {
      this.selected = !this.selected;
      if (this.selected === true) {
        this.$refs.select.style.color = 'red';
        this.$emit('select', this.itemData.goodsId);
      }
      else {
        this.$refs.select.style.color = 'black';
        this.$emit('unselect', this.itemData.goodsId);
      }
    }
  },
  created() {
    this.itemData = this.item;
    this.totalPrice = this.itemData.price * this.itemData.amount;
  },

}
</script>

<style scoped>
.cart {
  background-color: #eeeeee;
  border-radius: 20px;
  padding: 20px;
  margin: 25px auto;
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
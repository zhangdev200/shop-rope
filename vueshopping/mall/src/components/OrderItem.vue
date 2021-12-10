<template>
  <transition>
    <div>
      <el-row class="order">
        <el-col :span="4">
          <img src="../assets/Hamburger.png" alt="" style="width: 100%; border-radius: 15px"/>
        </el-col>
        <el-col :span="20">
          <div style="text-align: left; padding: 0 20px; margin-bottom: 70px">
            <span style="word-wrap: break-word">{{ itemData.description }}</span>
            <br>
          </div>
        </el-col>
        <div class="select" @click="select">
          <i class="el-icon-circle-check" ref="select"></i>
        </div>
        <span class="price">￥{{ itemData.price }}</span>
        <div style="position: absolute; bottom: 20px; right: 20px; border-radius: 10px">
          <el-button type="primary" round>评价</el-button>
          <el-button type="primary" round @click="showDetail">再次购买</el-button>
        </div>
      </el-row>
    </div>
  </transition>
</template>

<script>
export default {
  name: "OrderItem",
  props: ['item'],
  data() {
    return {
      step: 1,
      itemData: {
        goodsId: null,
        img: null,
        description: null,
        price: null,
      },
      selected: false,
    }
  },

  methods: {
    showDetail() {
      this.$router.push('/goodsDetail/' + this.itemData.goodsId);
    },
    select() {
      this.selected = !this.selected;
      if (this.selected === true) {
        this.$refs.select.style.color = 'red';
        this.$emit('select', this.itemData.goodsId);
        this.$emit('check');
      }
      else {
        this.$refs.select.style.color = 'black';
        this.$emit('unselect', this.itemData.goodsId);
        this.$emit('check');
      }
    }
  },
  created() {
    this.itemData = this.item;
  },

}
</script>

<style scoped>
.order {
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
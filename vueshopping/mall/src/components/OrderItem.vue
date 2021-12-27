<template>
    <div>
      <div class="order">
        <div class="select" @click="select">
          <i class="el-icon-circle-check" ref="select"></i>
        </div>
        <Order v-for="item in itemData.orderItems" :key="item.itemId" :item="item"></Order>
      </div>
    </div>
</template>

<script>
import Order from "@/components/Order";
export default {
  name: "OrderItem",
  components: {Order},
  props: ['item'],
  data() {
    return {
      step: 1,
      itemData: {},
      selected: false,
    }
  },

  methods: {
    select() {
      this.selected = !this.selected;
      if (this.selected) {
        this.$refs.select.style.color = 'red';
        this.$emit('select', this.itemData.orderId);
        this.$emit('check');
      } else {
        this.$refs.select.style.color = 'black';
        this.$emit('unselect', this.itemData.orderId);
        this.$emit('check');
      }
    },
  },
  created() {
    this.itemData = this.item;
  },

}
</script>

<style scoped>
.order {
  position: relative;
  width: 100%;
  background-color: #eee;
  border-radius: 10px;
  padding: 20px;
  margin: 25px auto;
}

.select {
  position: absolute;
  left: -60px;
  float: left;
  font-size: xx-large;
  color: black;
  width: 50px;
  height: 100px;
}
</style>
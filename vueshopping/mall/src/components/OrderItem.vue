<template>
  <div>
    <div class="order">
      <div v-if="type === 'user' && item.status == 3" class="select" @click="select">
        <i class="el-icon-circle-check" ref="select"></i>
      </div>
      <el-col :span="item.status == 3 ? 8 : 12">
        <div style="text-align: left; padding: 0 20px; font-size: 16px; height: 40px">
          <p style="word-wrap: break-word">订单号：{{ item.orderId }}</p>
        </div>
      </el-col>
      <el-col v-if="item.status == 3" :span="item.status == 3 ? 8 : 12">
        <div style="text-align: left; padding: 0 20px; font-size: 16px; height: 40px">
          <p style="word-wrap: break-word; text-align: center">
            下单时间：{{ item.createTime.substring(0, 19).replace('T', '&nbsp;&nbsp;') }}
          </p>
        </div>
      </el-col>
      <el-col v-else :span="item.status == 3 ? 8 : 12">
        <div style="text-align: left; padding: 0 20px; font-size: 16px; height: 40px">
          <p style="word-wrap: break-word; text-align: right">
            下单时间：{{ item.createTime.substring(0, 19).replace('T', '&nbsp;&nbsp;') }}
          </p>
        </div>
      </el-col>
      <el-col v-if="item.status == 3" :span="item.status == 3 ? 8 : 12">
        <div style="text-align: left; padding: 0 20px; font-size: 16px; height: 40px">
          <p style="word-wrap: break-word; text-align: right">
            完成时间：{{ item.flishTime.substring(0, 19).replace('T', '&nbsp;&nbsp;') }}
          </p>
        </div>
      </el-col>
      <Order v-for="(i, index) in item.orderItems"
             :key="i.itemId"
             :item="i"
             :type="type"
             :status="item.status"
             :index="index"
             :length="item.orderItems.length">
      </Order>
      <div style="float: right; position: relative; right: 18px">
        <el-button v-show="item.status == 2 && type === 'user'" type="primary" round @click="receive">收货</el-button>
        <el-button v-show="(item.status == 1 || item.status == 2) && type === 'user'"
                   type="primary" round @click="reject">退货
        </el-button>
        <el-button v-show="item.status == 1 && type === 'shopKeeper'" type="primary" round @click="deliver">发货</el-button>
      </div>
      <div v-show="item.status == 1 || item.status == 2" style="height: 35px"></div>
    </div>
  </div>
</template>

<script>
import Order from "@/components/Order";

export default {
  name: "OrderItem",
  components: {Order},
  props: ['item', 'type'],
  data() {
    return {
      step: 1,
      selected: false,
    }
  },

  methods: {
    select() {
      this.selected = !this.selected;
      if (this.selected) {
        this.$refs.select.style.color = 'red';
        this.$emit('select', this.item.orderId);
        this.$emit('check');
      } else {
        this.$refs.select.style.color = 'black';
        this.$emit('unselect', this.item.orderId);
        this.$emit('check');
      }
    },
    deliver() {
      this.$http
          .get('order/updatestatus', {
            orderId: this.item.orderId,
            status: 2
          })
          .then(res => {
            if (res.code === 10000) {
              let obj = this.$parent.$parent.$parent;
              obj.getOrders(obj.activeName, 1, obj.pageSize);
              this.$message.success('发货成功！')
            } else {
              this.$message.error(res.msg);
            }
          });
    },
    receive() {
      this.$http
          .get('order/updatestatus', {
            orderId: this.item.orderId,
            status: 3
          })
          .then(res => {
            if (res.code === 10000) {
              let obj = this.$parent.$parent.$parent;
              obj.getOrders(obj.activeName, 1, obj.pageSize);
              this.$message.success('收货成功！')
            } else {
              this.$message.error(res.msg);
            }
          });
    },
    reject() {
      this.$http
          .get('/order/delete', {
            orderIds: this.item.orderId
          })
          .then(res => {
            if (res.code === 10000) {
              let obj = this.$parent.$parent.$parent;
              obj.getOrders(obj.activeName, 1, obj.pageSize);
              this.$message.success('退货成功！')
            } else {
              this.$message.error(res.msg);
            }
          });
    }
  },
  created() {

  },
}
</script>

<style scoped>
.order {
  position: relative;
  width: 1380px;
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
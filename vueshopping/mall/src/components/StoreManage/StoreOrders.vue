<template>
  <div style="position: relative; left: 250px; width: 84%;">
    <div v-if="storeStatus === 1">
      <h4 style="text-align: left;">订单管理</h4>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane v-for="item in status.keys()"
                     :label="item"
                     :key="status.get(item)"
                     :name="status.get(item).toString()">
          <div v-if="totalOrders === 0">
            <el-empty description="还没有相关订单哦" :image-size="400"></el-empty>
          </div>
          <div v-else>
            <OrderItem
                v-for="i in orderList"
                :item="i"
                type="shopKeeper"
                :key="i.orderId"
                ref="order">
            </OrderItem>
            <div style="position: relative; left: 45%; transform: translateX(-50%)">
              <el-pagination
                  ref="page"
                  background
                  layout="total, prev, pager, next"
                  :page-size="pageSize"
                  :total="totalOrders"
                  @current-change="currentChange">
              </el-pagination>
              <br>
            </div>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
    <div v-else>
      <p style="margin-top: 38vh">您还没有店铺哦</p>
    </div>
  </div>
</template>

<script>
import OrderItem from "@/components/OrderItem";

export default {
  name: 'StoreOrders',
  components: {OrderItem},
  data() {
    return {
      storeStatus: null,
      shopId: null,
      totalOrders: 0,
      selectSet: new Set,
      activeName: '1',
      status: new Map([['待发货', 1], ['待收货', 2], ['已完成', 3]]),
      orderList: [],
      currentPage: 1,
      pageSize: 4,
    }
  },
  methods: {
    handleClick() {
      this.getOrders(this.activeName, 1, this.pageSize);
    },
    currentChange(pageNum) {
      this.currentPage = pageNum;
      this.getOrders(this.activeName, pageNum, this.pageSize);
    },
    getOrders(status, pageNum, limit) {
      this.$http
          .get('/order/listByShopId', {
            shopId: this.shopId,
            status: status,
            pageNum: pageNum,
            limit: limit
          })
          .then(res => {
            if (res.code === 10000) {
              this.totalOrders = res.data.count;
              this.orderList = res.data.list;
            } else {
              this.$message.error('错误:' + res.msg);
            }
          });
    },
  },
  created() {
    this.$http
        .get('/shop/listshopsbyuserid', {
          userId: JSON.parse(localStorage.getItem('userInform')).userId
        })
        .then(res => {
          if (res.code === 10000) {
            this.storeStatus = res.data[0].status;
            this.shopId = res.data[0].shopID
            if (this.storeStatus === 1) {
              this.getOrders(this.activeName, 1, this.pageSize);
            }
          } else {
            this.$message.error('获取店铺信息失败');
          }
        });
  }
}
</script>

<style>

</style>


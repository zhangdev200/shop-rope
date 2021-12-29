<template>
  <div>
    <div id="container">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane v-for="item in status.keys()"
                     :label="item"
                     :key="status.get(item)"
                     :name="status.get(item).toString()">
          <div v-if="totalOrders === 0">
            <el-empty description="您还没有相关订单哦" :image-size="400"></el-empty>
          </div>
          <div v-else>
            <OrderItem
                v-for="i in orderList"
                :item="i"
                type="user"
                :key="i.orderId"
                ref="order"
                @select="select"
                @unselect="unselect">
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
            </div>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
    <div v-show="activeName == 3" class="bottomBar">
      <div style="width: 77%; margin: 0 0 0 300px">
        <div style="width: 50%; display: inline-block; text-align: left">
          <el-button type="primary" round @click="selectReverse" class="myButton">反选</el-button>
          <el-button type="primary" round @click="selectAll" ref="selectAll" class="myButton">全选</el-button>
        </div>
        <div style="width: 50%; display: inline-block; text-align: right">
          <el-popconfirm title="确定删除吗？" @confirm="deleteItems" style="margin: 0 10px">
            <el-button type="danger" round class="myButton" slot="reference">删除</el-button>
          </el-popconfirm>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import OrderItem from "@/components/OrderItem";

export default {
  name: "OrderManage",
  components: {OrderItem},
  data() {
    return {
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
    select(orderId) {
      this.selectSet.add(orderId);
    },
    unselect(orderId) {
      this.selectSet.delete(orderId);
    },
    selectAll() {
      if (this.$refs.selectAll.$el.children[0].innerHTML === '全选') {
        for (let i of this.$refs.order) {
          if (i.selected === false) {
            i.select();
          }
        }
        this.$refs.selectAll.$el.children[0].innerHTML = '取消全选';
      } else {
        for (let i of this.$refs.order) {
          if (i.selected === true) {
            i.select();
          }
        }
        this.$refs.selectAll.$el.children[0].innerHTML = '全选';
      }
    },
    selectReverse() {
      for (let i of this.$refs.order) {
        i.select();
      }
    },
    deleteItems() {
      if (this.selectSet.size === 0) {
        this.$message.warning('请选择要删除的订单！');
      } else {
        let str = '';
        for (let i of this.selectSet) {
          str += i + ',';
        }
        str = str.substring(0, str.length - 1);
        this.$http
            .get('/order/delete?orderIds=' + str)
            .then(res => {
              if (res.code === 10000 || res.msg === '找不到记录' ||res.msg === '部分删除失败') {
                this.getOrders(this.activeName, this.currentPage, this.pageSize);
                this.$message.success('删除成功！');
              } else {
                this.$message.error(res.msg)
              }
            });
      }
    },
    getOrders(status, pageNum, limit) {
      this.$http
          .get('/order/list', {
            userId: JSON.parse(localStorage.getItem('userInform')).userId,
            status: status,
            pageNum: pageNum,
            limit: limit
          })
          .then(res => {
            if (res.code === 10000) {
              this.totalOrders = res.data.count;
              this.orderList = res.data.list;
            } else {
              this.$message.error('未知错误');
            }
          });
    },
    handleClick() {
      this.getOrders(this.activeName, 1, this.pageSize);
    },
    currentChange(pageNum) {
      this.currentPage = pageNum;
      this.getOrders(this.activeName, pageNum, this.pageSize);
    },
  },
  created() {
    this.getOrders(this.activeName, 1, this.pageSize);
  }
}
</script>

<style scoped>
#container {
  width: 1550px;
  margin: 10px 10px 100px 250px;
  z-index: 1;
}

.myButton {
  width: 20%;
}

.bottomBar {
  position: fixed;
  bottom: 0;
  width: 100%;
  height: 60px;
  background-color: rgba(217, 217, 217, 1);
  z-index: 10;
  padding-top: 20px;
}

</style>
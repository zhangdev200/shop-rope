<template>
  <div>
    <div id="container">
      <div v-if="this.totalOrders === 0">
        <el-empty description="您还没有订单哦" :image-size="400"></el-empty>
      </div>
      <OrderItem
          v-for="i in orderList"
          :item="i"
          :key="i.orderId"
          ref="order"
          @select="select"
          @unselect="unselect">
      </OrderItem>
      <div style="position: fixed; bottom: 100px; left: 50%; transform: translateX(-50%)">
        <el-pagination
            ref="page"
            background
            layout="total, prev, pager, next"
            :page-size="6"
            :total="totalOrders"
            @current-change="currentChange">
        </el-pagination>
      </div>
    </div>
    <div class="bottomBar">
      <div style="width: 71%; margin: 0 0 0 300px">
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
      totalOrders: null,
      selectSet: new Set,
      orderList: [],
      currentPage: 1,
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
                let newSet = this.selectSet;
                for (let i of newSet) {
                  for (let j = 0; j < this.orderList.length; j++) {
                    if (this.orderList[j].orderId === i) {
                      this.orderList.splice(j, 1);
                    }
                  }
                  for (let k of this.$refs.order) {
                    if (k.selected && k.itemData.orderId === i) {
                      k.select();
                    }
                  }
                }
                this.getOrders(this.currentPage, 6);
                this.$message.success('删除成功！');
              } else {
                this.$message.error(res.msg)
              }
            });
      }
    },
    getOrders(pageNum, limit) {
      this.$http
          .get('/order/list', {
            userId: JSON.parse(localStorage.getItem('userInform')).userId,
            pageNum: pageNum,
            limit: limit
          })
          .then(res => {
            if (res.code === 10000) {
              this.orderList = [];
              this.totalOrders = res.data.count;
              for (let i of res.data.list) {
                  this.orderList.push(i);
              }
            } else {
              this.$message.error('未知错误');
            }
          });
    },
    currentChange(pageNum) {
      this.currentPage = pageNum;
      this.getOrders(pageNum, 6);
    },
  },
  created() {
    this.getOrders(1, 6);
  }
}
</script>

<style scoped>
#container {
  width: 1400px;
  margin: 0 10px 100px 300px;
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
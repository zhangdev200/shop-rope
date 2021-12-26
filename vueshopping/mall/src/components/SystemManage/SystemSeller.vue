<template>
  <div id="seller">
    <el-tabs v-model="activeName" type="card">
      <el-tab-pane label="店铺审核" name="first">
        <el-table :data="checkingShops"
                  style="width: 100%; font-size: 16px; border-radius: 10px;box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.3);"
                  border stripe empty-text="暂无数据">
          <el-table-column label="店铺名称" prop="shopName" width="200"></el-table-column>
          <el-table-column label="店主姓名" prop="shopKeeperName" width="200"></el-table-column>
          <el-table-column label="店铺描述" prop="shopDescription"></el-table-column>
          <el-table-column label="操作" width="200" align="center">
            <template slot-scope="scope">
              <el-button type="success" round @click="pass(scope.row.shopID)">通过</el-button>
              <el-button type="danger" round @click="reject(scope.row.shopID)">拒绝</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="店铺管理" name="second">
        <el-table :data="passedShops"
                  style="width: 100%; font-size: 16px; border-radius: 10px;box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.3);"
                  border stripe empty-text="暂无数据">
          <el-table-column label="店铺名称" prop="shopName" width="200"></el-table-column>
          <el-table-column label="店主姓名" prop="shopKeeperName" width="200"></el-table-column>
          <el-table-column label="店铺描述" prop="shopDescription"></el-table-column>
          <el-table-column label="操作" width="200" align="center">
            <template slot-scope="scope">
              <el-button type="danger" round @click="reject(scope.row.shopID)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
<!--    <div v-show="activeName === 'first'">-->
<!--      -->
<!--    </div>-->
<!--    <div v-show="activeName === 'second'">-->
<!--      -->
<!--    </div>-->

  </div>
</template>

<script>
export default {
  name: "SystemSeller",
  data() {
    return {
      activeName: 'first',
      checkingShops: [],
      passedShops: [],
    }
  },
  methods: {
    pass(shopID) {
      this.$http
          .post('/shop/pass', {
            ID: shopID
          })
          .then(res => {
            if (res.code === 10000) {
              this.$message.success('操作成功！')
            } else {
              this.$message.error(res.msg);
            }
          });
    },
    reject(shopID) {
      if (shopID == 16) {
        this.$message.error('管理员店铺，无法删除')
      } else {
        this.$http
            .post('/shop/delete', {
              shopID: shopID
            })
            .then(res => {
              if (res.code === 10000) {
                this.$message.success('操作成功！')
              } else {
                this.$message.error(res.msg);
              }
            });
      }
    },
  },
  created() {
    this.$http
        .get('/shop/listcheckingshops')
        .then(res => {
          if (res.code === 10000) {
            this.checkingShops = res.data;
            this.$http
                .get('/shop/listshops')
                .then(res => {
                  if (res.code === 10000) {
                    this.passedShops = res.data;
                  } else {
                    this.$message.error(res.msg);
                  }
                });
          } else {
            this.$message.error(res.msg);
          }
        });
  },
}
</script>

<style>
#seller {
  margin: 20px 0;
  position: relative;
  left: 250px;
  width: 84%;
}

.el-tabs__header {
  font-size: 30px;
}


</style>
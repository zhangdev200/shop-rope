<template>
  <div id="user">
    <el-table :data="tableData" border stripe empty-text="暂无数据"
              style="width: 100%; font-size: 16px; border-radius: 10px;box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.3);">
      <el-table-column label="用户id" sortable prop="userId" width="180" align="center"></el-table-column>
      <el-table-column label="用户名" sortable prop="username" width="180" align="center"></el-table-column>
      <el-table-column label="性别" sortable prop="userSex" width="180" align="center"></el-table-column>
      <el-table-column label="昵称" sortable prop="nickname" align="center"></el-table-column>
      <el-table-column label="真实姓名" sortable prop="realname" width="180" align="center"></el-table-column>
      <el-table-column label="店主" sortable prop="shopKeeper" width="180" align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium" :type="scope.row.shopKeeper === '是' ? '':'danger'">
              {{ scope.row.shopKeeper }}
            </el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="会员" sortable prop="vip" width="180" align="center">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium" :type="scope.row.vip === '是' ? '':'danger'">
              {{ scope.row.vip }}
            </el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-popconfirm title="确定删除吗？" @confirm="deleteUser(scope.row.userId)">
            <el-button type="danger" round slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "SystemUser",
  data() {
    return {
      tableData: [],

    }
  },
  methods: {
    deleteUser(userId) {
      if (userId == 16) {
        this.$message.error('不能删除管理员！');
      } else {
        this.$http
            .get('user/deleteUser', {
              userId: userId,
            })
            .then(res => {
              if (res.code === 10000) {
                this.getUsers();
                this.$message.success('操作成功！');
              } else {
                this.$message.error('未知错误')
              }
            });
      }
    },
    getUsers() {
      this.$http
          .get('user/listUsers')
          .then(res => {
            if (res.code === 10000) {
              for (let i of res.data) {
                this.tableData = [];
                this.tableData.push({
                  userId: i.userId,
                  username: i.username,
                  userSex: i.userSex,
                  nickname: i.nickname,
                  realname: i.realname,
                  shopKeeper: i.shopKeeper ? '是' : '否',
                  vip: i.vip ? '是' : '否',
                })
              }
            } else {
              this.$message.error('未知错误')
            }
          })
    }
  },
  created() {
    this.getUsers();
  }
}
</script>

<style scoped>
#user {
  margin: 20px 0;
  position: relative;
  left: 250px;
  width: 84%;
}
</style>
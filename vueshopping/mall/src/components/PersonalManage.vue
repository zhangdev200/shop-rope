<template>
  <div id="personalManage"
       v-loading.fullscreen="this.loading"
       element-loading-background="rgba(0, 0, 0, 0)">
    <div style="height: 100px">
      <div style="text-align: left; float:left;">
        <el-upload
            style="width: 100px"
            class="upload-demo"
            action="https://jsonplaceholder.typicode.com/posts/"
            :on-preview="handlePreview"
            :show-file-list="false"
            :file-list="fileList">
          <el-avatar :size="100" :src="fileList[0].url"></el-avatar>
        </el-upload>
      </div>
      <div style="float: left; font-size: 35px; color: #666666; position: relative; top: 65px; left: 20px">
        {{ form1.nickname }}
      </div>
    </div>

    <el-divider></el-divider>
    <div style="text-align: left">
      <h4>基本信息</h4>
    </div>
    <div class="card">
      <el-form ref="form" :model="form1" label-width="90px" style="text-align: left">
        <el-form-item label="昵称">
          <el-input v-model="form1.nickname"></el-input>
        </el-form-item>
        <el-form-item label="真实姓名">
          <el-input v-model="form1.realname"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio v-model="form1.userSex" label="男">男</el-radio>
          <el-radio v-model="form1.userSex" label="女">女</el-radio>
        </el-form-item>
        <el-form-item label="收货地址">
          <el-input v-model="form1.userAddress"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form1.userEmail"></el-input>
        </el-form-item>
        <el-form-item label="电话号码">
          <el-input v-model="form1.userMobile"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" round style="float: right; width: 100px" @click="submitBasicInfo">修改</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div style="text-align: left">
      <h4>我的会员</h4>
    </div>
    <div class="card">
      <div v-if="form1.vip">
        <p style="color: red">您是尊贵的会员用户</p>
      </div>
      <div v-else>
        <p>您还不是会员哦</p>
        <div>
          立即
          <el-button type="text" style="font-size: 20px" @click="dialogVisible = true">注册会员</el-button>
        </div>
      </div>
    </div>
    <div style="text-align: left">
      <h4>修改密码</h4>
    </div>
    <div class="card">
      <el-form ref="form" :model="form2" label-width="90px" style="text-align: left">
        <el-form-item label="旧密码">
          <el-input v-model="form2.oldPassword"></el-input>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="form2.newPassword"></el-input>
        </el-form-item>
        <el-form-item label="确认新密码">
          <el-input v-model="form2.newPasswordConfirm"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" round style="float: right; width: 100px" @click="submitPassword">修改</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-dialog
        title="注册会员"
        :visible.sync="dialogVisible"
        width="25%"
        :lock-scroll="false">
      <span style="font-size: 16px">确定注册会员吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="registerMembership">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "PersonalManage",
  data() {
    return {
      form1: {
        nickname: '',
        userSex: '',
        email: '',
        address: '',
        phoneNumber: ''
      },
      form2: {
        oldPassword: '',
        newPassword: '',
        newPasswordConfirm: '',
      },
      fileList: [{url: ''}],
      isMembership: true,
      isStoreOwner: true,
      isAdministrator: true,
      dialogVisible: false,
      loading: true
    }
  },
  methods: {
    submitBasicInfo() {
      this.$confirm('确定修改个人资料吗')
          .then(() => {
            this.$message.success('修改个人资料成功！');
            // this.$http.get('api', this.form1)
            // .then(() => {
            //   this.$message.success('修改成功！');
            // })
            // .catch(err => {
            //   this.$message.success('错误：' + err);
            // })
          })
    },
    submitPassword() {
      this.$confirm('确定修改密码吗？')
          .then(() => {
            this.$message.success('修改密码成功！');
            // this.$http.get('api', this.form2)
            //     .then(() => {
            //         this.$message.success('修改密码成功！');
            //       })
            //       .catch(err => {
            //         this.$message.success('错误：' + err);
            //       });
          })
    },
    registerMembership() {
      this.dialogVisible = false;
      this.$http
          .get('user/becomevip', {
            username: JSON.parse(localStorage.getItem('userInform')).username
          })
          .then((res) => {
            if (res.code === 10000) {
              this.$message.info('注册会员成功！');
              this.userInform.vip = true;
            }
          });
    },
    handlePreview() {

    },
  },
  created() {
    this.$http
        .get('user/info')
        .then((res) => {
          if (res.code === 10000) {
            this.fileList[0].url = res.data.userImg;
            this.form1 = res.data;
          } else {
            this.$message.error('网络请求失败！');
          }
          this.loading = false;
        })
        .catch(err => {
          this.$message.error(err + '获取个人信息失败');
          this.loading = false
        });
  },
}
</script>

<style scoped>
#personalManage {
  position: relative;
  left: 250px;
  width: 84%;
}

.card {
  padding: 30px 50px 30px 30px;
  border-radius: 15px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.3);
  width: 50%;
}
</style>
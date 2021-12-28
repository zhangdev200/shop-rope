<template>
  <div id="personalManage">
    <div style="height: 100px">
      <div style="text-align: left; float:left;">
        <el-upload
            style="width: 100px"
            class="upload-demo"
            action=""
            :before-upload="beforeUpload"
            :show-file-list="false"
            :file-list="fileList">
          <el-avatar :size="100" :src="fileList[0].url"></el-avatar>
        </el-upload>
      </div>
      <div style="float: left; font-size: 35px; color: #666666; position: relative; top: 65px; left: 20px">
        {{ this.nickname }}
      </div>
    </div>
    <br>
    <hr style="color: black">
    <el-tabs v-model="activeName" type="card">
      <el-tab-pane label="基本信息" name="first">
        <div class="card">
          <el-form ref="form" :model="form1" label-width="90px" style="text-align: left">
            <el-row>
              <el-col :span="12">
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
                  <el-button type="primary" round style="float: right; width: 100px" @click="submitBasicInfo">修改
                  </el-button>
                </el-form-item>
              </el-col>
            </el-row>

          </el-form>
        </div>
      </el-tab-pane>
      <el-tab-pane label="修改密码" name="second">
        <div class="card">
          <el-form ref="form" :model="form2" label-width="90px" style="text-align: left">
            <el-row>
              <el-col :span="12">
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
                  <el-button type="primary" round style="float: right; width: 100px" @click="submitPassword">修改
                  </el-button>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </div>
      </el-tab-pane>
      <el-tab-pane label="我的会员" name="third">
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
      </el-tab-pane>
      <el-tab-pane label="申请开店" name="fourth">
        <div class="card">
          <div v-if="!isStoreOwner">
            <el-button type="text" style="font-size: 20px" @click="openShop">
              立即申请开店
            </el-button>
          </div>
          <div v-else>
            <div v-if="shopStatus === 1">
              <el-button type="text" style="font-size: 20px" @click="$router.replace('/personal/store')">
                你已经是店主了，快去管理你的店铺吧>>
              </el-button>
            </div>
            <div v-else>
              <el-button type="text" style="font-size: 20px">
                你已成功申请开店，请等待管理员审核！
              </el-button>
            </div>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>
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
    <el-dialog
        title="申请开店"
        :visible.sync="dialogVisible2"
        width="30%"
        :lock-scroll="false">
      <el-form :model="form">
        <el-form-item label="店铺名称">
          <el-input
              v-model="form.shopName">
          </el-input>
        </el-form-item>
        <el-form-item label="店铺描述">
          <el-input
              v-model="form.shopDescription">
          </el-input>
        </el-form-item>
        <el-form-item label="开店人姓名">
          <el-input
              v-model="form.shopKeeperName">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible2 = false">取 消</el-button>
          <el-button type="primary" @click="confirm">确 定</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "PersonalManage",
  data() {
    return {
      nickname: null,
      form1: {},
      form2: {
        oldPassword: '',
        newPassword: '',
        newPasswordConfirm: '',
      },
      form: {
        shopName: '',
        shopDescription: '',
        shopKeeperName: '',
      },
      activeName: 'first',
      fileList: [{url: ''}],
      applied: false,
      isMembership: true,
      isStoreOwner: true,
      shopStatus: 0,
      isAdministrator: true,
      dialogVisible: false,
      dialogVisible2: false,
    }
  },
  methods: {
    submitBasicInfo() {
      this.$confirm('确定修改个人资料吗')
          .then(() => {
            this.$http
                .post('user/updateInfo', this.form1)
                .then((res) => {
                  if (res.code === 10000) {
                    this.getInfo();
                    this.$message.success('修改成功！');
                  }
                });
          });
    },
    submitPassword() {
      this.$confirm('确定修改密码吗？')
          .then(() => {
            if (this.form2.newPassword !== this.form2.newPasswordConfirm) {
              this.$message.warning('密码和确认密码不一致！');
              return;
            } else if (JSON.parse(localStorage.getItem('userInform')).password !== this.form2.oldPassword) {
              this.$message.warning('旧密码错误！');
              return;
            }
            this.$http
                .post('user/updateInfo', {
                  userId: this.form1.userId,
                  password: this.form2.newPassword
                })
                .then((res) => {
                  if (res.code === 10000) {
                    this.$message.success('修改密码成功，请重新登录！');
                    setTimeout(() => {
                      localStorage.clear();
                      this.$router.replace('/login');
                    }, 3000);
                  } else {
                    this.$message.error(res.msg);
                  }
                });
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
              this.form1.vip = true;
            }
          });
    },
    openShop() {
      this.dialogVisible2 = true;
    },
    confirm() {
      this.$http
          .post('/shop/add', this.form)
          .then(res => {
            if (res.code === 10000) {
              this.$message.success(res.msg);
              this.getInfo();
            } else {
              this.$message.error(res.msg);
            }
            this.dialogVisible2 = false;
          });
    },
    beforeUpload(file) {
      let fd = new FormData();
      fd.append('file', file);
      fd.append('productId', JSON.parse(localStorage.getItem('userInform')).userId);
      this.$http.post('file/userimg', fd)
          .then(res => {
            if (res.code === 10000) {
              this.$message.success('操作成功！');
            } else {
              this.$message.error(res.msg);
            }
          });
      return false;
    },
    getInfo() {
      this.$http
          .get('user/info')
          .then((res) => {
            if (res.code === 10000) {
              localStorage.setItem('userInform', JSON.stringify(res.data));
              this.fileList[0].url = res.data.userImg;
              this.form1 = res.data;
              this.nickname = res.data.nickname;
              this.isStoreOwner = res.data.shopKeeper;
              if (this.isStoreOwner) {
                this.$http
                    .get('/shop/listshopsbyuserid', {
                      userId: JSON.parse(localStorage.getItem('userInform')).userId
                    })
                    .then(res1 => {
                      if (res1.code === 10000) {
                        this.shopStatus = res1.data[0].status;
                      } else {
                        this.$message.error('获取店铺状态失败')
                      }
                    })
              }
            } else {
              this.$message.error(res.msg);
            }
          });
    }
  },
  created() {
    this.getInfo();
  },
}
</script>

<style scoped>
#personalManage {
  position: relative;
  left: 250px;
  width: 84%;
  margin: 20px 0;
}

.card {
  background: rgba(255, 255, 255, 0.5);
  padding: 30px 50px 30px 30px;
  width: 93%;
}


</style>
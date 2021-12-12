<template>
  <div id="personalManage">
    <div style="text-align: left">
      <h1>修改资料</h1>
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
        <el-form-item label="头像">
          <el-upload
              style="width: 100px"
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :file-list="fileList"
              list-type="picture">
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
          <div class="el-upload__tip">只能上传jpg/png文件，且宽高比例为1:1</div>
        </el-form-item>
        <el-form-item label="收货地址">
          <el-input v-model="form1.address"></el-input>
        </el-form-item>
        <el-form-item label="电话号码">
          <el-input v-model="form1.phoneNumber"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" round style="float: right; width: 100px" @click="submitBasicInfo">修改</el-button>
        </el-form-item>
      </el-form>
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
    <div style="text-align: left">
      <h4>我的会员</h4>
    </div>
    <div class="card">
      <div v-if="isMembership">
        <p style="color: red">您是尊贵的会员用户</p>
      </div>
      <div v-else>
        <p>您还不是会员哦</p>
        <div>
          立即
          <el-button type="text" style="font-size: 20px" @click="registerMembership">注册会员</el-button>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  name: "PersonalManage",
  data() {
    return {
      form1: {
        nickname: '',
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
      this.$confirm('确定修改密码吗')
          .then(() => {
            this.$message.success('修改密码成功！');
            // this.$http.get('api', this.form2)
            //     .then(() => {
            //         this.$message.success('修改密码成功！');
            //       })
            //       .catch(err => {
            //         this.$message.success('错误：' + err);
            //       })
          })
    },
    registerMembership() {
      this.$confirm('确定注册会员吗')
          .then(() => {
            this.isMembership = true;
            this.$message.success('注册会员成功！');
            // this.$http   //请求注册会员
            //     .get('api')
            //     .then(res => {
            //       this.$message.info(res.msg);
            //     });
            // this.$http  //更新用户数据
            //     .get('api')
            //     .then(res => {
            //       this.isMembership = res.data.isMembership;
            //     });
          });
    }
    ,
    handlePreview() {

    }
    ,
    handleRemove() {

    }
  },
  created() {
    //todo 根据token获得用户信息
    let info = {
      nickname: '好名字',
      avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      address: '江西省南昌市南昌大学前湖校区19栋',
      phoneNumber: '13888888888',
      isMembership: false,
      isStoreOwner: true,
      isAdministrator: true,
    }
    this.form1.nickname = info.nickname;
    this.fileList[0].url = info.avatar;
    this.form1.address = info.address;
    this.form1.phoneNumber = info.phoneNumber;
    this.isMembership = info.isMembership
    this.isStoreOwner = info.isStoreOwner;
    this.isAdministrator = info.isAdministrator;
    // this.$http.get('api')
    //     .then((res) => {
    //       this.form1.nickname = res.data.nickname;
    //       this.form1.avatar = res.data.avatar;
    //       this.form1.address = res.data.address;
    //       this.form1.phoneNumber = res.data.phoneNumber;
    //       http.get('api')
    //       .then((res2) => {
    //         this.fileList = [{url: res2}];
    //       })
    //     });
  }
}
</script>

<style scoped>
#personalManage {
  position: relative;
  left: 250px;
  width: 80%;
}

.card {
  padding: 30px 50px 30px 30px;
  border-radius: 15px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.3);
  width: 50%;
}
</style>
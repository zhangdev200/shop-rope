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
          <el-input v-model="form1.nickname" class="inputWidth"></el-input>
          <div style="float: right">
            <el-button type="primary" style="margin-left: 20px" @click="submitNickname">修改</el-button>
          </div>
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
          <el-input v-model="form1.address" class="inputWidth"></el-input>
          <div style="float: right;">
            <el-button type="primary" style="margin-left: 20px" @click="submitAddress">修改</el-button>
          </div>
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
          <div>
            <el-button type="primary" style="float: right; margin: 20px 0" @click="submitPassword">修改</el-button>
          </div>
        </el-form-item>
      </el-form>
    </div>

  </div>
</template>

<script>
export default {
  name: "PersonalManage",
  data() {
    return {
      form1: {
        nickname: '好名字',
        address: '江西省南昌市南昌大学前湖校区19栋',
        phoneNumber: '13888888888'
      },
      form2: {
        oldPassword: '',
        newPassword: '',
        newPasswordConfirm: '',
      },
      fileList: [{url: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'}]
    }
  },
  methods: {
    submitNickname() {

    },
    submitAddress() {

    },
    submitPassword() {

    },
    handlePreview() {

    },
    handleRemove() {

    }
  },
  created() {
    //todo 根据token获得用户信息
    let info = {
      nickname: '好名字',
      avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      address: '江西省南昌市南昌大学前湖校区19栋',
      phoneNumber: '13888888888'
    }
    this.form1.nickname = info.nickname;
    this.fileList[0].url = info.avatar;
    this.form1.address = info.address;
    this.form1.phoneNumber = info.phoneNumber;
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
  width: 50%;
  margin: 0 auto;
}

.card {
  padding: 30px;
  border-radius: 15px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.3);
  width: 60%;
}

.inputWidth {
  width: 70%;
}
</style>
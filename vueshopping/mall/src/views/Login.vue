<template>
  <div id="form">
    <h1>
      欢 迎 登 录
    </h1><br>
    <hr>
    <br><br>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="80px" class="demo-ruleForm">
      <el-form-item label="用户名" prop="username">
        <el-input type="text" v-model="ruleForm.username" autocomplete="off"></el-input>
      </el-form-item>
      <br>
      <el-form-item label="密码" prop="pass">
        <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
      </el-form-item>
      <br>
      <el-form-item style="float: right">
        <el-button type="primary" @click="submitForm">登录</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    let validateUsername = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'));
      } else {
        callback();
      }
    };
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        username: '',
        pass: '',
      },
      rules: {
        username: [
          {validator: validateUsername, trigger: 'blur'}
        ],
        pass: [
          {validator: validatePass, trigger: 'blur'}
        ],
      }
    };
  },
  methods: {
    submitForm() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          //todo 登录后返回数据
          let data = {
            token: 'abc',
            userInfo: {
              nickname: '好名字',
              avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
              isMembership: false,
            },
          }
          localStorage.setItem('token', data.token);
          localStorage.setItem('nickname', data.userInfo.nickname);
          localStorage.setItem('avatar', data.userInfo.avatar);
          localStorage.setItem('isMembership', data.userInfo.isMembership);
          this.$message.success('登录成功！');
          this.$router.replace('/');
          setTimeout(() => {
            location.reload();
          }, 300);
          // this.$http
          //     .get('user/login', {
          //       username: this.username,
          //       password: this.pass
          //     })
          //     .then(res => {
          //       localStorage.setItem('token', res.data.token);
          //       localStorage.setItem('nickname', res.data.nickname);
          //       localStorage.setItem('avatar', res.data.avatar);
          //       localStorage.setItem('isMembership', data.userInfo.isMembership);
          //     });
        } else {
          return false;
        }
      });
    },
    resetForm() {
      this.$refs.ruleForm.resetFields();
    }
  }
}
</script>

<style scoped>
#form {
  padding: 50px 50px 120px 50px;
  width: 20%;
  margin: 160px auto;
  border-radius: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.4);
}
</style>
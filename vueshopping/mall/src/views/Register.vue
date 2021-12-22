<template>
  <div id="form">
    <h1 style="color: #444444">
      欢 迎 注 册
    </h1><br>
    <hr>
    <br><br>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" class="demo-ruleForm">
      <el-form-item prop="username">
        <el-input prefix-icon="el-icon-user" placeholder="请输入用户名" type="text" v-model="ruleForm.username"></el-input>
      </el-form-item>
      <br>
      <el-form-item prop="pass">
        <el-input prefix-icon="el-icon-lock" placeholder="请输入密码" type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
      </el-form-item>
      <br>
      <el-form-item prop="checkPass">
        <el-input prefix-icon="el-icon-lock" placeholder="请输入确认密码" type="password" v-model="ruleForm.checkPass" autocomplete="off" @keyup.enter.native="submitForm"></el-input>
      </el-form-item>
      <br>
      <el-form-item>
        <div style="float: right; width: 300px">
          <div style="float: right">
            <el-button round type="primary" @click="submitForm">注册</el-button>
            <el-button round @click="resetForm">重置</el-button>
          </div>
        </div>
      </el-form-item>
    </el-form>
    <div style="font-size: 12px; float: right">
      已有账号？立即<el-button type="text" @click="toLogin">登录</el-button>
    </div>
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
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    let validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        username: '',
        pass: '',
        checkPass: '',
      },
      rules: {
        username: [
          {validator: validateUsername, trigger: 'blur'}
        ],
        pass: [
          {validator: validatePass, trigger: 'blur'}
        ],
        checkPass: [
          {validator: validatePass2, trigger: 'blur'}
        ],
      }
    };
  },
  methods: {
    submitForm() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.$http
              .post('user/regist', {
                username: this.ruleForm.username,
                password: this.ruleForm.pass
              })
              .then(res => {
                this.$message.success(res.msg);
              });
        } else {
          return false;
        }
      });
    },
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },
    toLogin() {
      this.$router.replace('login');
    }
  }
}
</script>

<style scoped>
#form {
  background: rgba(255, 255, 255, 0.7);
  padding: 40px 50px 80px 50px;
  width: 20%;
  margin: 160px auto;
  border-radius: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.4)
}
</style>
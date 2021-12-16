<template>
  <div id="form">
    <h1>
      欢 迎 注 册
    </h1><br>
    <hr>
    <br><br>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="80px" class="demo-ruleForm">
      <el-form-item label="用户名">
        <el-input type="text" v-model="ruleForm.username"></el-input>
      </el-form-item>
      <br>
      <el-form-item label="密码" prop="pass">
        <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
      </el-form-item>
      <br>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" @keyup.enter.native="submitForm"></el-input>
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
              })
              .catch(err => this.$message.error(err));
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
  padding: 50px 50px 120px 50px;
  width: 20%;
  margin: 160px auto;
  border-radius: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.4)
}
</style>
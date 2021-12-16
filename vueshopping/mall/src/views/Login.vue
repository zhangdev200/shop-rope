<template>
  <div id="form"
       v-loading.fullscreen="this.loading"
       element-loading-background="rgba(0, 0, 0, 0)">
    <h1>
      欢 迎 登 录
    </h1>
    <br>
    <hr>
    <br><br>
    <el-form
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        label-width="80px"
        class="demo-ruleForm">
      <el-form-item label="用户名" prop="username">
        <el-input type="text" v-model="ruleForm.username" autocomplete="off"></el-input>
      </el-form-item>
      <br>
      <el-form-item label="密码" prop="pass">
        <el-input type="password" v-model="ruleForm.pass" autocomplete="off" @keyup.enter.native="submitForm"></el-input>
      </el-form-item>
      <br>
      <el-form-item style="width: 100%; height: 40px">
        <div style="float: right; width: 300px">
          <div style="float: right">
            <el-button round type="primary" @click="submitForm">登录</el-button>
            <el-button round @click="resetForm">重置</el-button>
          </div>
        </div>
      </el-form-item>
    </el-form>
    <div style="font-size: 12px; float: right">
      还没有账号？立即<el-button type="text" @click="toRegister">注册</el-button>
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
        callback();
      }
    };
    return {
      loading: false,
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
      this.loading = true;
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.$http
              .get('/user/login', {
                username: this.ruleForm.username,
                password: this.ruleForm.pass
              })
              .then(res => {
                this.loading = false;
                if (res.code === 10000) {
                  this.$message.success('登录成功！');
                  localStorage.setItem('token', res.msg);
                  localStorage.setItem('userInform', JSON.stringify(res.data));
                  this.$router.replace('/');
                } else {
                  this.$message.error(res.msg);
                }
              })
              .catch(err => {
                this.loading = false;
                this.$message.error(err);
              });
        } else {
          return false;
        }
      });
      this.$emit('login');
    },
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },
    toRegister() {
      this.$router.replace('register');
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
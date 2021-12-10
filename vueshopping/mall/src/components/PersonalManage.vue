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
        </el-form-item>
        <el-form-item label="收货地址">
          <el-input v-model="form1.address" class="inputWidth"></el-input>
          <div class="inputWidth">
            <el-button type="primary" style="float: right; margin: 20px 0" @click="submit1">修改</el-button>
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
          <el-input v-model="form2.oldPassword" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="form2.newPassword" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="确认新密码">
          <el-input v-model="form2.newPasswordConfirm" class="inputWidth"></el-input>
          <div class="inputWidth">
            <el-button type="primary" style="float: right; margin: 20px 0" @click="submit2">修改</el-button>
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
      },
      form2: {
        oldPassword: '',
        newPassword: '',
        newPasswordConfirm: '',
      }
    }
  },
  methods: {
    submit1() {

    },
    submit2() {

    },
  },
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
  width: 80%;
}
</style>
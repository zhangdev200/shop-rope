<template>
  <div id="personalManage">
    <div style="text-align: left">
      <h1>修改资料</h1>
    </div>
    <el-divider></el-divider>

    <div style="text-align: left">
      <h4>基本信息</h4>
    </div>
    <div style="padding: 30px; border-radius: 15px; box-shadow:  0 2px 12px 0 rgba(0, 0, 0, 0.3)">
      <el-form ref="form" :model="form" label-width="60px" style="text-align: left">
        <el-form-item label="用户名">
          <el-input v-model="form.name" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickname" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" class="inputWidth"></el-input>
          <div class="inputWidth">
            <el-button type="primary" style="float: right; margin: 20px 0">修改</el-button>
          </div>
        </el-form-item>
      </el-form>
    </div>
    <div style="text-align: left">
      <h4>修改密码</h4>
    </div>
    <div style="padding: 30px; border-radius: 15px; box-shadow:  0 2px 12px 0 rgba(0, 0, 0, 0.3)">
      <el-form ref="form" :model="form" label-width="90px" style="text-align: left">
        <el-form-item label="旧密码">
          <el-input v-model="form.name" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="form.nickname" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="确认新密码">
          <el-input v-model="form.address" class="inputWidth"></el-input>
          <div class="inputWidth">
            <el-button type="primary" style="float: right; margin: 20px 0">修改</el-button>
          </div>
        </el-form-item>
      </el-form>
    </div>
    <div style="text-align: left">
      <h4>基本信息</h4>
    </div>
    <div style="padding: 30px; border-radius: 15px; box-shadow:  0 2px 12px 0 rgba(0, 0, 0, 0.3)">
      <el-form ref="form" :model="form" label-width="60px" style="text-align: left">
        <el-form-item label="用户名">
          <el-input v-model="form.name" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickname" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" class="inputWidth"></el-input>
          <div class="inputWidth">
            <el-button type="primary" style="float: right; margin: 20px 0">修改</el-button>
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
      form: {
        name: '',
        nickname: '',
        address: '',
      }
    }
  },
  methods: {},
}
</script>

<style scoped>
#personalManage {
  width: 50%;
  margin: 0 auto;
}

.inputWidth {
  width: 60%;
}
</style>
<template>
  <div>
    <el-backtop><i class="el-icon-top"></i></el-backtop>
    <el-container style="border-radius: 20px">
      <el-header id="header">
        <div>
          <el-menu
              id="header-menu"
              class="el-menu-demo"
              default-active="/goods"
              mode="horizontal"
              background-color="#545c64"
              text-color="#fff"
              active-text-color="#ffd04b"
          >
            <div id="logo">
              {{ mallName }}
            </div>
            <div style="float: right">
              <el-menu-item index="/goods" class="menu-item" @click="routerLink('/index')">
                首页
              </el-menu-item>
              <el-menu-item index="/cart" class="menu-item" @click="routerLink('/cart')">
                购物车
              </el-menu-item>
                <div v-if="userName !== null" style="display: inline-block;">
                  <el-menu-item index="/personal" class="menu-item"
                                @click="routerLink('/personal')">
                    <el-avatar size="small">
                      <img :src="avatar" alt="">
                    </el-avatar>
                    &nbsp;{{ userName }}
                  </el-menu-item>
                  <el-popconfirm>
                    <el-menu-item slot="reference" index="/logout" class="menu-item"
                                  @click="logout">退出登录
                    </el-menu-item>
                  </el-popconfirm>

                </div>
                <div v-else style="display: inline-block;">
                  <el-menu-item index="/login" class="menu-item"
                                @click="routerLink('/login')">
                    登录
                  </el-menu-item>
                  <el-menu-item index="/register" class="menu-item"
                                @click="routerLink('/register')">
                    注册
                  </el-menu-item>
                </div>
            </div>
          </el-menu>
        </div>
      </el-header>
    </el-container>
    <div id="bottom">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      mallName: '好 名 字 商 城',
      userName: '用户',
      avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
    }
  },
  methods: {
    routerLink(location) {
      this.$router.replace(location);
    },
    logout() {
      this.userName = null;
      this.$router.replace('/index');
    }
  },
  created() {
    this.$router.replace('/index');
  }

}
</script>

<style scoped>
#header {
  z-index: 10;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  padding: 0;

}

#logo {
  position: relative;
  top: 5px;
  float: left;
  padding-left: 40px;
  color: white;
  font-family: 'Microsoft YaHei', serif;
  font-size: 30px;
}

#header-menu {
}

.menu-item {
  display: inline-block;
  font-size: 15px;
}

#bottom {
  margin-top: 70px;
}


</style>

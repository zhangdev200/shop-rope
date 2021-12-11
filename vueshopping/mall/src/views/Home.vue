<template>
  <div>
    <el-backtop><i class="el-icon-top"></i></el-backtop>
    <el-container style="border-radius: 20px">
      <el-header id="header">
        <div>
          <el-menu
              id="header-menu"
              class="el-menu-demo"
              :default-active="activePath"
              mode="horizontal"
              background-color="rgba(210,210,210,1)"
              text-color="#000"
              active-text-color="rgb(44,138,255)"
          >
            <div id="logo">
              {{ mallName }}
            </div>
            <div style="float: right">
              <el-menu-item index="/index" class="menu-item" @click="routerLink('/index')">
                首页
              </el-menu-item>
              <el-menu-item index="/cart" class="menu-item" @click="routerLink('/cart')">
                购物车
              </el-menu-item>
              <div v-if="this.token !== null" style="display: inline-block;">
                <el-menu-item index="/personal" class="menu-item"
                              @click="routerLink('/personal')">
                  <el-avatar size="small">
                    <img :src="avatar" alt="">
                  </el-avatar>
                  &nbsp;{{ nickName }}
                </el-menu-item>
                <el-menu-item slot="reference" index="/logout" class="menu-item"
                              @click="logout">退出登录
                </el-menu-item>

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
      token: null,
      nickName: '',
      avatar: ''
    }
  },
  methods: {
    routerLink(location) {
      this.$router.replace(location);
    },
    logout() {
      localStorage.clear();
      setTimeout(() => {
        location.reload();
      }, 300);
    }
  },
  created() {
    // localStorage.setItem('token', '123');
    // localStorage.setItem('nickname', '好名字');
    // localStorage.setItem('avatar', 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png');
    this.token = localStorage.getItem('token');
    this.nickName = localStorage.getItem('nickname');
    this.avatar = localStorage.getItem('avatar');
  },
  computed: {
    activePath() {
      if (this.$route.path === '/personalManage' ||
          this.$route.path === '/orderManage' ||
          this.$route.path === '/storeManage' ||
          this.$route.path === '/systemManage') {
        return '/personal';
      } else return this.$route.path;
    }
  }

}
</script>

<style scoped>
#header {
  z-index: 10;
  position: fixed;
  width: 100%;
  padding: 0;
}

#logo {
  position: relative;
  top: 5px;
  float: left;
  padding-left: 40px;
  color: black;
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

<template>
  <div>
    <el-backtop><i class="el-icon-top"></i></el-backtop>
    <el-container style="border-radius: 20px">
      <el-header id="header">
        <div>
          <el-menu
              id="header-menu"
              class="el-menu-demo"
              :default-active="activePath()"
              mode="horizontal"
              background-color="rgba(210,210,210,1)"
              text-color="#000"
              active-text-color="rgb(44,138,255)"
          >
            <div id="logo">
              {{ mallName }}
            </div>
            <div style="float: right">
              <el-menu-item index="/index" class="menu-item" @click="routerLink('/main')">
                首页
              </el-menu-item>
              <el-menu-item index="/cart" class="menu-item" @click="routerLink('/cart')">
                购物车
              </el-menu-item>
              <div v-show="this.hasToken" style="display: inline-block;">
                <el-menu-item index="/personal" class="menu-item"
                              @click="routerLink('/personal')">
                  <el-avatar size="small">
                    <img :src="this.userInform.userImg" alt="">
                  </el-avatar>
                  &nbsp;{{ this.userInform.nickname }}
                </el-menu-item>
                <el-menu-item slot="reference" index="/logout" class="menu-item"
                              @click="logout">退出登录
                </el-menu-item>
              </div>
              <div v-show="!this.hasToken" style="display: inline-block;">
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
      <keep-alive include="Search">
        <router-view></router-view>
      </keep-alive>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      hasToken: false,
      mallName: '好 名 字 商 城',
      userInform: {
        nickname: '',
        userImg: '',
        vip: false
      },
    }
  },
  methods: {
    activePath() {
      if (this.$route.path === '/personal/personal' ||
          this.$route.path === '/personal/order' ||
          this.$route.path === '/store/goods' ||
          this.$route.path === '/store/orders' ||
          this.$route.path === '/system/seller' ||
          this.$route.path === '/system/user' ||
          this.$route.path === '/system/carousel') {
        return '/personal';
      }
      return this.$route.path;
    },
    isVip() {
      return this.userInform.vip;
    },
    routerLink(location) {
      this.$router.replace(location);
    },
    logout() {
      localStorage.clear();
      this.$message({
        type: "success",
        duration: 1000,
        message: '已退出登录'
      });
      if (this.$route.path !== '/index') {
        this.$router.replace('/index');
      } else {
        this.$forceUpdate();
      }
    },
    getUserInform() {
      if (JSON.parse(localStorage.getItem('userInform'))){
        this.userInform = JSON.parse(localStorage.getItem('userInform'));
      }
    }
  },
  created() {
    this.getUserInform();
    if (JSON.parse(localStorage.getItem('userInform'))){
      this.hasToken = true;
    }
  },
  watch: {
    $route() {
      this.getUserInform();
      this.hasToken = (JSON.parse(localStorage.getItem('userInform')) !== null);
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

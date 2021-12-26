<template>
  <div id="personal">
    <el-container>
      <el-aside id="aside" width="200px">
        <el-menu
            id="menu"
            :default-active="$route.path"
            class="el-menu-vertical-demo"
            style="height: 110vh"
            background-color="rgb(217,217,217)"
            text-color="#000"
            active-text-color="rgb(44,138,255)">
          <el-menu-item index="/personal/personal" class="item" @click="routerLink('/personal/personal')">
            <div class="inner-item">
              <i class="el-icon-user" style="font-size: 20px"></i>
              <span slot="title">个人中心</span>
            </div>
            <el-divider></el-divider>
          </el-menu-item>
          <el-menu-item index="/personal/order" class="item" @click="routerLink('/personal/order')">
            <div>
              <i class="el-icon-goods" style="font-size: 20px"></i>
              <span slot="title">我的订单</span>
            </div>
          </el-menu-item>
          <el-menu-item v-if="isShopKeeper" index="/personal/store" class="item" @click="routerLink('/personal/store')">
            <div>
              <i class="el-icon-s-shop" style="font-size: 20px"></i>
              <span>我的店铺</span>
            </div>
          </el-menu-item>
          <el-submenu index="1" class="item" v-if="isAdministrator">
            <template slot="title">
              <div class="item">
                <i class="el-icon-s-tools" style="font-size: 20px"></i>
                <span slot="title">系统管理</span>
              </div>
            </template>
            <el-menu-item index="/system/seller" class="item" @click="routerLink('/system/seller')">
              <div class="subItem">
                <span slot="title">商家管理</span>
              </div>
            </el-menu-item>
            <el-menu-item index="/system/user" class="item" @click="routerLink('/system/user')">
              <div class="subItem">
                <span slot="title">用户管理</span>
              </div>
            </el-menu-item>
<!--            <el-menu-item index="/system/category" class="item" @click="routerLink('/system/category')">-->
<!--              <div class="subItem">-->
<!--                <span slot="title">类别管理</span>-->
<!--              </div>-->
<!--            </el-menu-item>-->
            <el-menu-item index="/system/carousel" class="item" @click="routerLink('/system/carousel')">
              <div class="subItem">
                <span slot="title">轮播图管理</span>
              </div>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <keep-alive include="StoreManage">
        <router-view></router-view>
      </keep-alive>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Personal",
  data() {
    return {
      isShopKeeper: false,
      isAdministrator: false
    };
  },
  methods: {
    routerLink(location) {
      this.$router.replace(location);
    }
  },
  mounted() {
    this.$http
        .get('user/info')
        .then((res) => {
          if (res.code === 10000) {
            this.isShopKeeper = res.data.shopKeeper;
            this.isAdministrator = res.data.admin;
          } else {
            this.$message.error('网络请求失败！');
          }
        });
  }
}
</script>

<style scoped>

#container {
  width: 100%;
  padding: 20px 0;
}

#aside {
  position: fixed;
  top: 76px;
  border-bottom-right-radius: 12px;
  border-top-right-radius: 12px;
  z-index: 11;
}

#personal {
  margin-top: 20px;
}

.item {
  margin: 15px 0;
  font-size: 20px;
  text-align: left;
}

.subItem {
  position: relative;
  left: 20px;
}

</style>
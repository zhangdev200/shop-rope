<template>
  <div id="personal">
    <el-container>
      <el-aside id="aside" width="200px">
        <el-menu
            id="menu"
            :default-active="$route.path"
            class="el-menu-vertical-demo"
            style="height: 110vh;"
            background-color="rgb(217,217,217)"
            text-color="#000"
            active-text-color="rgb(44,138,255)">
          <el-menu-item index="/personalManage" class="item" @click="routerLink('/personalManage')">
            <div class="inner-item">
              <i class="el-icon-user" style="font-size: 20px"></i>
              <span slot="title">个人中心</span>
            </div>
            <el-divider></el-divider>
          </el-menu-item>
          <el-menu-item index="/orderManage" class="item" @click="routerLink('/orderManage')">
            <div class="inner-item">
              <i class="el-icon-goods" style="font-size: 20px"></i>
              <span slot="title">我的订单</span>
            </div>
          </el-menu-item>
          <el-menu-item index="/storeManage" class="item" @click="routerLink('/storeManage')">
            <div class="inner-item">
              <i class="el-icon-s-shop" style="font-size: 20px"></i>
              <span slot="title">我的店铺</span>
            </div>
          </el-menu-item>
          <el-menu-item v-if="isAdministrator" index="/systemManage" class="item" @click="routerLink('/systemManage')">
            <div class="inner-item">
              <i class="el-icon-s-tools" style="font-size: 20px"></i>
              <span slot="title">系统管理</span>
            </div>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main id="container">
        <router-view></router-view>
      </el-main>
    </el-container>

  </div>
</template>

<script>
export default {
  name: "Personal",
  data() {
    return {
      isStoreOwner: false,
      isAdministrator: false
    };
  },
  methods: {
    routerLink(location) {
      this.$router.replace(location);
    }
  },
  created() {
    let info =
        {
          nickname: '好名字',
          avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
          address: '江西省南昌市南昌大学前湖校区19栋',
          phoneNumber: '13888888888',
          isMembership: true,
          isStoreOwner: true,
          isAdministrator: true,
        };
    this.isStoreOwner = info.isStoreOwner;
    this.isAdministrator = info.isAdministrator;
    // this.$http.get('api')
    //     .then((res) => {
    //       this.isStoreOwner = res.data.isStoreOwner;
    //       this.isAdministrator = res.data.isAdministrator;
    //     });
  }
}
</script>

<style scoped>

#container {

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
  border-radius: 10px;
  margin: 15px;
  font-size: 20px;
  text-align: left;
}

</style>
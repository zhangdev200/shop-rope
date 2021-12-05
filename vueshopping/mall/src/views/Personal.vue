<template>
  <div id="personal">
    <el-container>
      <el-aside id="aside" width="200px">
        <el-menu
            id="menu"
            default-active="/personalManage"
            class="el-menu-vertical-demo"
            style="height: 90vh;"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
          <el-menu-item index="/personalManage" class="item" @click="routerLink('/personalManage')">
            <div class="inner-item">
              <i class="el-icon-user" style="font-size: 20px"></i>
              <span slot="title">个人中心</span>
            </div>
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
          <el-menu-item index="/sellerManage" class="item" @click="routerLink('/sellerManage')">
            <div class="inner-item">
              <i class="el-icon-s-tools" style="font-size: 20px"></i>
              <span slot="title">商家管理</span>
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

    };
  },
  methods: {
    routerLink(location) {
      this.$router.replace(location);
    }
  },
  mounted() {
    this.$router.replace('/personalManage');
  }
}
</script>

<style scoped>

#container {

}

#aside {
  position: relative;
  left: -8px;
  border-bottom-right-radius: 15px;
  border-top-right-radius: 15px;
}

#personal {
  margin-top: 20px;
}

.item {
  border-radius: 10px;
  margin: 15px;
  font-size: 20px;
}

.inner-item {
  position: relative;
  left: -10px;
}

</style>
<template>
  <transition>
    <div>
      <el-row class="order">
        <el-col :span="3">
          <img src="../assets/Hamburger.png" alt="" style="width: 100%; border-radius: 15px"/>
        </el-col>
        <el-col :span="21">
          <div style="text-align: left; padding: 0 20px; margin-bottom: 70px; font-size: 16px">
            <span style="word-wrap: break-word">{{ itemData.description }}</span>
            <br>
          </div>
        </el-col>
        <div class="select" @click="select">
          <i class="el-icon-circle-check" ref="select"></i>
        </div>
        <span class="price">￥{{ itemData.price }}</span>
        <div style="position: absolute; bottom: 20px; right: 20px; border-radius: 10px">
          <el-button type="primary" round @click="comment">评价</el-button>
          <el-button type="primary" round @click="showDetail">再次购买</el-button>
        </div>
      </el-row>
    </div>
  </transition>
</template>

<script>
export default {
  name: "OrderItem",
  props: ['item'],
  data() {
    return {
      step: 1,
      itemData: {
        goodsId: null,
        goodsName: '商品名称',
        img: null,
        description: null,
        price: null,
      },
      selected: false,
    }
  },

  methods: {
    showDetail() {
      this.$router.push('/goodsDetail/' + this.itemData.goodsId);
    },
    select() {
      this.selected = !this.selected;
      if (this.selected === true) {
        this.$refs.select.style.color = 'red';
        this.$emit('select', this.itemData.goodsId);
        this.$emit('check');
      } else {
        this.$refs.select.style.color = 'black';
        this.$emit('unselect', this.itemData.goodsId);
        this.$emit('check');
      }
    },
    comment() {
      this.$prompt('对商品 ' + this.itemData.goodsName + ' 进行评价：', '评价', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /.{2,100}/,
        inputErrorMessage: '请输入至少2个字符，至多100个字符！'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '评价成功！'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消评论！'
        });
      });
    }
  },
  created() {
    this.itemData = this.item;
  },

}
</script>

<style scoped>
.order {
  background-color: #eeeeee;
  border-radius: 20px;
  padding: 20px;
  margin: 25px auto;
}

.select {
  font-size: xx-large;
  position: absolute;
  left: -60px;
  color: black;
  width: 50px;
  height: 100px;
}

.price {
  position: absolute;
  bottom: 20px;
  right: 240px;
  font-size: 35px;
  font-weight: bold;
  color: red;
}

</style>
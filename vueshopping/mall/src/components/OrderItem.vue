<template>
    <div>
      <div class="order">
        <div class="select" @click="select">
          <i class="el-icon-circle-check" ref="select"></i>
        </div>
        <el-row v-for="item in itemData.orderItems" :key="item.itemId">
          <el-col :span="5">
            <div style="text-align: left; padding: 0 20px; font-size: 16px">
              <p style="font-weight: bold">{{ item.productName }}</p>
            </div>
          </el-col>
          <el-col :span="7">
            <div style="text-align: left; padding: 0 20px; font-size: 16px">
              <p style="word-wrap: break-word">订单号：{{ item.orderId }}</p>
            </div>
          </el-col>
          <el-col :span="6">
            <div style="text-align: left; padding: 0 20px; font-size: 16px">
              <p style="word-wrap: break-word">
                下单时间：{{item.buyTime.substring(0, 19).replace('T' , '&nbsp;&nbsp;')}}
              </p>
            </div>
          </el-col>
          <el-col :span="6">
            <span class="price">￥{{ item.productPrice }}</span>
            <div style="position: absolute; right: 20px; border-radius: 10px">
              <el-button type="primary" round @click="dialogVisible = true; form.textarea = ''">评价</el-button>
              <el-button type="primary" round @click="showDetail">再次购买</el-button>
            </div>
          </el-col>
        </el-row>

      </div>

      <el-dialog
          title="评价"
          :visible.sync="dialogVisible"
          width="30%"
          :lock-scroll="false">
        <p style="font-size: 16px">{{ '对商品 ' + this.itemData.productName + ' 进行评价：' }}</p>
        <el-form :model="form">
          <el-form-item>
            <div style="height: 50px; float:left;">
              <span style="display: inline-block">评分：</span>
              <el-rate
                  style="display: inline-block"
                  v-model="form.stars"
                  :colors="colors">
              </el-rate>
            </div>
            <el-input
                type="textarea"
                :rows="4"
                placeholder="请输入评价内容"
                v-model="form.textarea">
            </el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="comment()">确 定</el-button>
        </span>
      </el-dialog>
    </div>
</template>

<script>
export default {
  name: "OrderItem",
  props: ['item'],
  data() {
    return {
      step: 1,
      itemData: {
        productId: null,
        productName: '商品名称',
        productImg: null,
        orderId: null,
        productPrice: null,
        buyTime: null,
      },
      selected: false,
      colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
      dialogVisible: false,
      form: {
        stars: 0,
        textarea: '',
      },
    }
  },

  methods: {
    showDetail() {
      this.$router.push('/goods/' + this.itemData.productId);
    },
    select() {
      this.selected = !this.selected;
      if (this.selected) {
        this.$refs.select.style.color = 'red';
        this.$emit('select', this.itemData.orderId);
        this.$emit('check');
      } else {
        this.$refs.select.style.color = 'black';
        this.$emit('unselect', this.itemData.orderId);
        this.$emit('check');
      }
    },
    comment() {
      if (!this.form.textarea.match(/.{2,100}/)) {
        this.$message.warning('输入字符数应在2-100之间！');
      } else if (this.form.stars === 0) {
        this.$message.warning('请进行评分!');
      } else {
        this.$http
            .post('/product/addcomment', {
              userId: JSON.parse(localStorage.getItem('userInform')).userId,
              productId: this.itemData.productId,
              commType: this.form.stars,
              commContent: this.form.textarea,
            })
            .then(res => {
              if (res.code === 10000) {
                this.$message.success('评价成功！');
              } else this.$message.error(res.msg);
              this.dialogVisible = false;
            });
      }
    }
  },
  created() {
    this.itemData = this.item;
  },

}
</script>

<style scoped>
.order {
  position: relative;
  width: 100%;
  background-color: #eee;
  border-radius: 10px;
  padding: 20px;
  margin: 25px auto;
}

.select {
  position: absolute;
  left: -60px;
  float: left;
  font-size: xx-large;
  color: black;
  width: 50px;
  height: 100px;
}

.price {
  position: absolute;
  right: 240px;
  font-size: 35px;
  font-weight: bold;
  color: red;
}

</style>
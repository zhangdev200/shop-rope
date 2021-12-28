<template>
  <div>
    <el-row>
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
            下单时间：{{ item.buyTime.substring(0, 19).replace('T', '&nbsp;&nbsp;') }}
          </p>
        </div>
      </el-col>
      <el-col :span="6">
        <div style="position: relative; top: 8px">
          <span class="price" style="display: inline">￥{{ item.productPrice }}</span>
          <span style="display: inline"> × {{ item.buyCounts }}</span>
          <div style="position: absolute; right: 20px; border-radius: 10px">
            <el-button type="primary" round @click="dialogVisible = true; form.textarea = ''">评价</el-button>
            <el-button type="primary" round @click="showDetail()">再次购买</el-button>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-dialog
        title="评价"
        :visible.sync="dialogVisible"
        width="30%"
        :lock-scroll="false">
      <p style="font-size: 16px">{{ '对商品 ' + this.item.productName + ' 进行评价：' }}</p>
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
  name: "Order",
  props: ['item'],
  data() {
    return {
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
      this.$router.push('/goods/' + this.item.productId);
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
              productId: this.item.productId,
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
    },
  }
}
</script>

<style scoped>
.price {
  position: absolute;
  top: -3px;
  right: 240px;
  font-size: 35px;
  font-weight: bold;
  color: red;
}
</style>
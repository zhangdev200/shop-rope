<template>
  <transition name="el-zoom-in-top">
    <div>
      <el-row class="order">
        <el-col :span="2">
          <img src="../assets/Hamburger.png" alt="" style="width: 100%; border-radius: 15px"/>
        </el-col>
        <el-col :span="22">
          <div style="text-align: left; padding: 0 20px; margin-bottom: 70px; font-size: 16px">
            <p style="margin: 0; font-weight: bold">{{ itemData.goodsName }}</p>
            <p style="word-wrap: break-word">{{ itemData.description }}</p>
          </div>
        </el-col>
        <div class="select" @click="select">
          <i class="el-icon-circle-check" ref="select"></i>
        </div>
        <span class="price">￥{{ itemData.price }}</span>
        <div style="position: absolute; bottom: 20px; right: 20px; border-radius: 10px">
          <el-button type="primary" round @click="dialogVisible = true; form.textarea = ''">评价</el-button>
          <el-button type="primary" round @click="showDetail">再次购买</el-button>
        </div>
      </el-row>
      <el-dialog
          title="评价"
          :visible.sync="dialogVisible"
          width="25%"
          :lock-scroll="false">
        <p style="font-size: 16px">{{ '对商品 ' + this.itemData.goodsName + ' 进行评价：' }}</p>
        <el-form :model="form">
          <el-form-item>
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
      dialogVisible: false,
      form: {
        textarea: '',
      },
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
      if (this.form.textarea.match(/.{2,100}/)) {
        this.$http.post('api', {
          content: this.form.textarea,
        })
        .then(res => {
          if (res === 10000) {
            this.$message.success('评价成功！');
          } else this.$message.error(res.msg);
        });
      } else {
        this.$message.warning('输入字符数应在2-100之间！');
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
  width: 100%;
  background-color: #eee;
  border-radius: 10px;
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
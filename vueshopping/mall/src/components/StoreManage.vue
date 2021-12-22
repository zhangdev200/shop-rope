<template>
  <div style="position: relative; left: 250px; width: 80%;">
    <h4 style="text-align: left;">商品管理</h4>
    <div style="height: 30px">
      <el-button
          type="primary"
          round
          style="float: left; margin-bottom: 20px">
        添加商品
      </el-button>
    </div>
    <el-table :data="tableData"
              style="width: 100%; font-size: 16px; border-radius: 15px;box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.3);"
              border
              stripe
              empty-text="暂无数据">
      <el-table-column
          label="商品 ID"
          prop="goodsId"
      >
      </el-table-column>
      <el-table-column
          label="商品名称"
          prop="name"
      >
      </el-table-column>
      <el-table-column
          label="分类"
          prop="category"
          width="120">
      </el-table-column>
      <el-table-column
          label="图片"
          prop="img"
      >
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="right-end" :close-delay=0>
            <img :src="scope.row.img" alt="加载失败">
            <div slot="reference" class="name-wrapper" style="width: 70px">
              <el-tag size="medium">查看图片</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
          label="描述"
          prop="description"
          width="500">
      </el-table-column>
      <el-table-column
          label="操作"
      >
        //todo
        <template slot-scope="scope">
          <el-button type="success" round @click="dialogFormVisible = true">编辑</el-button>
          <el-button type="danger" round @click="deleteGoods(scope.row.goodsId)">删除</el-button>
        </template>

      </el-table-column>
    </el-table>
    <br>
    <el-pagination
        background
        layout="prev, pager, next"
        :total="1000">
    </el-pagination>
    <br>
    <div style="text-align: left; margin-top: 25px">
      <el-form label-width="90px">
        <el-form-item label="店铺名称" class="form1">
          <span style="font-size: 18px; margin-right: 30px">{{ this.storeName }}</span>
          <el-button type="primary" round>修改</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-dialog title="编辑商品" :visible.sync="dialogFormVisible">
      <el-form :model="form" style="text-align: left">
        <el-form-item label="商品名称" :label-width="formLabelWidth">
          <el-input v-model="form.productName" autocomplete="off" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="商品类别" :label-width="formLabelWidth">
          <el-select v-model="form.category" placeholder="请选择商品类别">
            <el-option label="零食" value="零食"></el-option>
            <el-option label="图书" value="图书"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品图片" :label-width="formLabelWidth">
          <el-upload
              style="width: 300px"
              class="upload-demo"
              ref="upload"
              action="https://jsonplaceholder.typicode.com/posts/"
              :limit="1"
              list-type="picture"
              :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <div slot="tip" class="el-upload__tip">图片比例为1:1</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="售价" :label-width="formLabelWidth">
          <el-input v-model="form.price" autocomplete="off" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="描述信息" :label-width="formLabelWidth">
          <el-input
              type="textarea"
              :rows="3"
              placeholder="请输入描述信息"
              v-model="form.content">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitModify">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      storeName: '好名字店铺',
      tableData: [],
      dialogFormVisible: false,
      form: {
        productName: '',
        category: '',
        productImg: '',
        price: null,
        content: '',
      },
      formLabelWidth: '120px',
    }
  },
  methods: {
    submitModify() {
      this.dialogFormVisible = false;

    },
    deleteGoods(a) {
      alert(a);
    },

    submitAdd() {

    }
  },
  created() {

    this.$http
        .get('shop/list', {
          shopID: 1
        })
        .then(res => {
          if (res.code === 10000) {
            for (let item of res.data) {
              this.tableData.push({
                goodsId: item.productId,
                name: item.productName,
                category: item.categoryId,
                description: item.content,
                img: null,
              })
            }
          } else {
            this.$message.error('获取店铺信息失败')
          }
        });
    this.storeName = '好名字店铺';
    // this.tableData =
    //     [
    //       {
    //         goodsId: '12987122',
    //         name: '好滋好味鸡蛋仔',
    //         category: '零食',
    //         description: '荷兰优质淡奶，奶香浓而不腻',
    //         img: 'https://2d.zol-img.com.cn/product/211_200x150/655/ce9W4dWqmCrd2.jpg',
    //         address: '上海市普陀区真北路',
    //         storeId: '10333',
    //         storeName: '好名字店铺',
    //       },
    //       {
    //         goodsId: '12987123',
    //         name: '好滋好味鸡蛋仔',
    //         category: '零食',
    //         description: '荷兰优质淡奶，奶香浓而不腻',
    //         img: 'https://2d.zol-img.com.cn/product/211_200x150/655/ce9W4dWqmCrd2.jpg',
    //         address: '上海市普陀区真北路',
    //         storeId: '10333',
    //         storeName: '好名字店铺',
    //       },
    //       {
    //         goodsId: '12987125',
    //         name: '好滋好味鸡蛋仔',
    //         category: '零食',
    //         description: '荷兰优质淡奶，奶香浓而不腻',
    //         img: 'https://2d.zol-img.com.cn/product/211_200x150/655/ce9W4dWqmCrd2.jpg',
    //         address: '上海市普陀区真北路',
    //         storeId: '10333',
    //         storeName: '好名字店铺',
    //       },
    //       {
    //         goodsId: '12987126',
    //         name: '好滋好味鸡蛋仔',
    //         category: '零食',
    //         description: '荷兰优质淡奶，奶香浓而不腻',
    //         img: 'https://2d.zol-img.com.cn/product/211_200x150/655/ce9W4dWqmCrd2.jpg',
    //         address: '上海市普陀区真北路',
    //         storeId: '10333',
    //         storeName: '好名字店铺',
    //       },
    //       {
    //         goodsId: '12987128',
    //         name: '好滋好味鸡蛋仔',
    //         category: '零食',
    //         description: '荷兰优质淡奶，奶香浓而不腻',
    //         img: 'https://2d.zol-img.com.cn/product/211_200x150/655/ce9W4dWqmCrd2.jpg',
    //         address: '上海市普陀区真北路',
    //         storeId: '10333',
    //         storeName: '好名字店铺',
    //       },
    //       {
    //         goodsId: '12987129',
    //         name: '好滋好味鸡蛋仔',
    //         category: '零食',
    //         description: '荷兰优质淡奶，奶香浓而不腻',
    //         img: 'https://2d.zol-img.com.cn/product/211_200x150/655/ce9W4dWqmCrd2.jpg',
    //         address: '上海市普陀区真北路',
    //         storeId: '10333',
    //         storeName: '好名字店铺',
    //       },
    //       {
    //         goodsId: '12987130',
    //         name: '好滋好味鸡蛋仔',
    //         category: '零食',
    //         description: '荷兰优质淡奶，奶香浓而不腻',
    //         img: 'https://2d.zol-img.com.cn/product/211_200x150/655/ce9W4dWqmCrd2.jpg',
    //         address: '上海市普陀区真北路',
    //         storeId: '10333',
    //         storeName: '好名字店铺',
    //       },
    //     ];
  }
}
</script>

<style>

.form1 .el-form-item__label {
  font-size: 18px;
  font-weight: bold;
}

.inputWidth {
  width: 300px;
}

.demo-table-expand {
  font-size: 0;
}

.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>


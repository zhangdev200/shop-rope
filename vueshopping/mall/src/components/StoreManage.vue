<template>
  <div style="position: relative; left: 250px; width: 84%;">
    <h4 style="text-align: left;">商品管理</h4>
    <div style="height: 30px">
      <el-button
          type="primary" round @click="add"
          style="float: left; margin-bottom: 20px">
        添加商品
      </el-button>
    </div>
    <el-table :data="singlePageOrdersList"
              style="width: 100%; font-size: 16px; border-radius: 15px;box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.3);"
              border stripe empty-text="暂无数据">
      <el-table-column
          label="商品 ID"
          prop="goodsId">
      </el-table-column>
      <el-table-column
          label="商品名称"
          prop="name">
      </el-table-column>
      <el-table-column
          label="价格"
          width="120"
          prop="sellPrice">
      </el-table-column>
      <el-table-column
          label="分类"
          prop="category"
          width="150">
      </el-table-column>
      <el-table-column
          label="图片"
          prop="img">
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
          width="200">
        <template slot-scope="scope">
          <el-button type="success" round @click="modify(scope.row.goodsId)">编辑</el-button>
          <el-button type="danger" round @click="deleteGoods(scope.row.goodsId)">删除</el-button>
        </template>

      </el-table-column>
    </el-table>
    <br>
    <el-pagination
        background
        layout="total, prev, pager, next, jumper"
        @current-change="currentChange"
        :page-size="pageSize"
        :total="totalOrders">
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
    <el-dialog :visible.sync="dialogFormVisible" @close="closeDialog">
      <el-form :model="form" style="text-align: left">
        <el-form-item label="商品名称" :label-width="formLabelWidth">
          <el-input v-model="form.productName" autocomplete="off" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="商品类别" :label-width="formLabelWidth">
          <el-select v-model="form.categoryId" placeholder="请选择商品类别">
            <el-option v-for="item in this.categories"
                       :key="item.categoryId"
                       :label="item.categoryName"
                       :value="item.categoryId">
            </el-option>
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
        <el-button type="primary" @click="submitForm1">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      storeId: 0,
      storeName: '',
      tableData: [],
      selectedGoodsId: null,
      dialogFormVisible: false,
      dialogType: 0, //0为新增，1为编辑
      form: {
        productName: '',
        categoryId: '',
        productImg: '',
        price: null,
        content: '',
      },
      categories: [],
      currentPage: 1,
      totalOrders: 0,
      pageSize: 8,
      singlePageOrdersList: [],
      formLabelWidth: '120px',
    }
  },
  methods: {
    add() {
      this.dialogFormVisible = true;
      this.dialogType = 0
    },
    modify(goodsId) {
      this.selectedGoodsId = goodsId;
      this.dialogFormVisible = true;
      this.dialogType = 1;
      for (let i of this.tableData) {
        if (this.selectedGoodsId === i.goodsId) {
          this.form.productName = i.name;
          this.form.categoryId = i.categoryId;
          this.form.price = i.sellPrice;
          this.form.content = i.description;
          break;
        }
      }
    },
    deleteGoods(a) {
      alert(a);
    },
    closeDialog() {
      this.form = {
        productName: '',
        categoryId: '',
        productImg: '',
        price: null,
        content: '',
      };
    },
    submitForm1() {
      let obj = {
        productName: this.form.productName,
        categoryId: this.form.categoryId,
        rootCategoryId: 0,
        content: this.form.content,
        shopID: this.storeId,
        productStatus: 1,
        skus:
            [
              {
                sellPrice: this.form.price,
                status: 1,
              }
            ],
      };
      if (this.dialogType === 1) {
        obj.productId = this.selectedGoodsId;
      }
      this.$http
          .post(this.dialogType === 1 ? '/shop/updateproduct' : '/shop/addproduct', obj)
          .then(res => {
            if (res.code === 10000) {
              this.$message.success('操作成功');
              this.getOrders();
            } else {
              this.$message.error('未知错误')
            }
          });
      this.dialogFormVisible = false;
    },
    getOrders() {
      this.$http
          .get('shop/list', {
            shopID: this.storeId,
          })
          .then(res => {
            if (res.code === 10000) {
              this.tableData = [];
              for (let item of res.data) {
                let categoryName;
                for (let i of this.categories) {
                  if (i.categoryId === item.categoryId) {
                    categoryName = i.categoryName;
                  }
                }
                this.tableData.push({
                  goodsId: item.productId,
                  name: item.productName,
                  sellPrice: item.skus !== null && item.skus.length !== 0 ? item.skus[0].sellPrice : null,
                  categoryId: item.categoryId,
                  category: categoryName,
                  description: item.content,
                  img: item.imgs !== null && item.imgs.length !== 0 ? item.imgs[0].url : null,
                });
              }
              this.totalOrders = this.tableData.length;
              this.currentChange(this.currentPage);
            } else {
              this.$message.error('获取店铺信息失败');
            }
          });
    },
    currentChange(pageNum) {
      this.currentPage = pageNum;
      let start = (pageNum - 1) * this.pageSize;
      this.singlePageOrdersList = this.tableData.slice(start, start + this.pageSize);
    }
  },
  created() {
    this.$http
        .get('/index/category-recommends')
        .then(res => {
          if (res.code === 10000) {
            for (let item of res.data) {
              this.categories.push({
                categoryId: item.categoryId,
                categoryName: item.categoryName,
              });
            }
            this.$http
                .get('/shop/listshopsbyuserid', {
                  userId: JSON.parse(localStorage.getItem('userInform')).userId
                })
                .then(res => {
                  if (res.code === 10000) {
                    this.storeId = res.data[0].shopID;
                    this.storeName = res.data[0].shopName;
                    this.getOrders();
                  } else {
                    this.$message.error('未知错误');
                  }
                });
          } else {
            this.$message.error('未知错误')
          }
        });
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


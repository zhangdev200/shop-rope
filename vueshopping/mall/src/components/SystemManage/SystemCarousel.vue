<template>
  <div id="carousel">
    <div style="height: 30px">
      <el-button
          type="primary" round @click="add"
          style="float: left; margin-bottom: 20px">
        添加轮播图
      </el-button>
    </div>
    <el-table :data="tableData" border stripe empty-text="暂无数据"
              style="width: 100%; font-size: 16px; border-radius: 10px;box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.3);">
      <el-table-column label="商品 ID" prop="prodId">
      </el-table-column>
      <el-table-column label="图片" prop="imgUrl">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="right-end" :close-delay="0">
            <img :src="scope.row.imgUrl" alt="加载失败">
            <div slot="reference" class="name-wrapper" style="width: 70px">
              <el-tag size="medium">查看图片</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="danger" round @click="deleteItem(scope.row.imgId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :visible.sync="dialogFormVisible">
      <el-form :model="form" style="text-align: left">
        <el-form-item label="商品id" :label-width="formLabelWidth">
          <el-input v-model="form.productId" autocomplete="off" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="商品图片" :label-width="formLabelWidth">
          <el-upload
              style="width: 300px"
              class="upload-demo"
              action=""
              ref="upload"
              :before-upload="beforeUpload"
              :limit="1"
              list-type="picture"
              :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <div slot="tip" class="el-upload__tip">图片比例为1:1</div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "SystemCarousel",
  data() {
    return {
      form: {
        productId: 1
      },
      tableData: [],
      dialogFormVisible: false,
      formLabelWidth: '120px',
    }
  },
  methods: {
    add() {
      this.dialogFormVisible = true;
      this.form.productId = null;
    },
    deleteItem(imgId) {
      this.$http
      .post('/index/deleteIndexImg?imgId='+imgId)
      .then(res => {
        if (res.code === 10000) {
          this.$message.success('操作成功！');
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    beforeUpload(file) {
      let fd = new FormData();
      fd.append('file', file);
      fd.append('productId', this.form.productId);
      this.$http.post('file/indexImg', fd)
          .then(res => {
            if (res.code === 10000) {
              this.$message.success('操作成功！');
            } else {
              this.$message.error(res.msg);
            }
          });
      return false;
    },
    submitForm() {
      this.$refs.upload.submit();
      this.dialogFormVisible = false;
    },
  },
  created() {
    this.$http
        .get('/index/indeximg')
        .then(res => {
          if (res.code === 10000) {
            this.tableData = res.data;
          }
        });
  }

}
</script>

<style scoped>
#carousel {
  margin: 20px 0;
  position: relative;
  left: 250px;
  width: 84%;
}
</style>
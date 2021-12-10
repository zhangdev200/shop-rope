<template>
  <div style="width: 75%; margin: 100px 0 100px 15%;">
    <el-table :data="tableData" style="font-size: 20px">
      <el-table-column
          label="商品 ID"
          prop="id">
      </el-table-column>
      <el-table-column
          label="商品名称"
          prop="name">
      </el-table-column>
      <el-table-column
          label="分类"
          prop="category">
      </el-table-column>
      <el-table-column
          label="图片"
          prop="image">
      </el-table-column>
      <el-table-column
          label="描述"
          prop="desc">
      </el-table-column>
      <el-table-column
          label="操作">
        <el-button type="success" plain>编辑</el-button>
        <el-button type="danger" plain>删除</el-button>
      </el-table-column>
    </el-table>
  </div>

</template>

<style>
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

<script>
export default {
  data() {
    return {
      tableData: [{
        id: '12987122',
        name: '好滋好味鸡蛋仔',
        category: '零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        image: null,
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
      }, {
        id: '12987123',
        name: '好滋好味鸡蛋仔',
        category: '零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        image: null,
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
      }, {
        id: '12987125',
        name: '好滋好味鸡蛋仔',
        category: '零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        image: null,
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
      }, {
        id: '12987126',
        name: '好滋好味鸡蛋仔',
        category: '零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        image: null,
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
      }]
    }
  }
}
</script>
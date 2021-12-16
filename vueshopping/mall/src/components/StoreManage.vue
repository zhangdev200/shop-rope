<template>
  <div style="position: relative; left: 250px; width: 80%;">
    <h4 style="text-align: left;">商品管理</h4>
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
        <el-button type="success" round>编辑</el-button>
        <el-button type="danger" round>删除</el-button>
      </el-table-column>
    </el-table>
    <br>
    <div style="text-align: left; margin-top: 25px">
      <el-form label-width="90px">
        <el-form-item label="店铺名称" class="form1">
          <span style="font-size: 18px; margin-right: 30px">{{ this.tableData[0].storeName }}</span>
          <el-button type="primary" round>修改</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
    }
  },
  created() {
    //todo 跟据token获得店铺商品信息(需要是店主)
    this.tableData =
        [
          {
            goodsId: '12987122',
            name: '好滋好味鸡蛋仔',
            category: '零食',
            description: '荷兰优质淡奶，奶香浓而不腻',
            img: 'https://2d.zol-img.com.cn/product/211_200x150/655/ce9W4dWqmCrd2.jpg',
            address: '上海市普陀区真北路',
            storeId: '10333',
            storeName: '好名字店铺',
          },
          {
            goodsId: '12987123',
            name: '好滋好味鸡蛋仔',
            category: '零食',
            description: '荷兰优质淡奶，奶香浓而不腻',
            img: 'https://2d.zol-img.com.cn/product/211_200x150/655/ce9W4dWqmCrd2.jpg',
            address: '上海市普陀区真北路',
            storeId: '10333',
            storeName: '好名字店铺',
          },
          {
            goodsId: '12987125',
            name: '好滋好味鸡蛋仔',
            category: '零食',
            description: '荷兰优质淡奶，奶香浓而不腻',
            img: 'https://2d.zol-img.com.cn/product/211_200x150/655/ce9W4dWqmCrd2.jpg',
            address: '上海市普陀区真北路',
            storeId: '10333',
            storeName: '好名字店铺',
          },
          {
            goodsId: '12987126',
            name: '好滋好味鸡蛋仔',
            category: '零食',
            description: '荷兰优质淡奶，奶香浓而不腻',
            img: 'https://2d.zol-img.com.cn/product/211_200x150/655/ce9W4dWqmCrd2.jpg',
            address: '上海市普陀区真北路',
            storeId: '10333',
            storeName: '好名字店铺',
          },
          {
            goodsId: '12987128',
            name: '好滋好味鸡蛋仔',
            category: '零食',
            description: '荷兰优质淡奶，奶香浓而不腻',
            img: 'https://2d.zol-img.com.cn/product/211_200x150/655/ce9W4dWqmCrd2.jpg',
            address: '上海市普陀区真北路',
            storeId: '10333',
            storeName: '好名字店铺',
          },
          {
            goodsId: '12987129',
            name: '好滋好味鸡蛋仔',
            category: '零食',
            description: '荷兰优质淡奶，奶香浓而不腻',
            img: 'https://2d.zol-img.com.cn/product/211_200x150/655/ce9W4dWqmCrd2.jpg',
            address: '上海市普陀区真北路',
            storeId: '10333',
            storeName: '好名字店铺',
          },
          {
            goodsId: '12987130',
            name: '好滋好味鸡蛋仔',
            category: '零食',
            description: '荷兰优质淡奶，奶香浓而不腻',
            img: 'https://2d.zol-img.com.cn/product/211_200x150/655/ce9W4dWqmCrd2.jpg',
            address: '上海市普陀区真北路',
            storeId: '10333',
            storeName: '好名字店铺',
          },
        ];
  }
}
</script>

<style>

.form1 .el-form-item__label {
  font-size: 18px;
  font-weight: bold;
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


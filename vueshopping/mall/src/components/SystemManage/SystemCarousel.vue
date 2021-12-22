<template>
  <div id="carousel">
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
          label="图片"
          prop="img"
      >
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="right-end" close-delay="0">
            <img :src="scope.row.img" alt="加载失败">
            <div slot="reference" class="name-wrapper" style="width: 70px">
              <el-tag size="medium">查看图片</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
          label="操作"
      >
        <el-button type="success" round>编辑</el-button>
        <el-button type="danger" round>删除</el-button>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "SystemCarousel"

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
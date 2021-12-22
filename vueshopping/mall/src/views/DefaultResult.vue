<template>
  <div>
    <el-carousel :interval="1500" type="card">
      <el-carousel-item v-for="item in 6" :key="item">
        <img src="../assets/img.png" style="width: 100%; height: 100%; border-radius: 20px" alt="">
      </el-carousel-item>
    </el-carousel>
    <div v-for="(category, index) in this.categoryList" :key=index>
      <h2 class="category">
        {{ category }}
      </h2>
      <div class="categoryBlock">
        <el-row>
          <el-col :lg="6" :md="12" :sm="12" :xs="24" v-for="o in goodsData[category]" :key="o.goodsId">
            <Card :data="o"></Card>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
import Card from "../components/Card";

export default {
  components: {Card},
  name: "DefaultResult",
  data() {
    return {
      goodsData: new Map,
      categoryList: null,
    }
  },
  methods: {},
  created() {
    //todo 首页分类列表
    this.categoryList = ['手机', '家电', '图书'];
    //todo 各分类的推荐商品
    this.goodsData[this.categoryList[0]] =
        [
          {
            goodsId: 1,
            goodsName: 'vivo X70 Pro+',
            img: 'https://2d.zol-img.com.cn/product/211_200x150/655/ce9W4dWqmCrd2.jpg',
            description: 'vivo X70 Pro+,全四摄光学防抖,全都稳才是真的稳',
            price: 2999
          },
          {
            goodsId: 2,
            goodsName: 'vivo X70 Pro+',
            img: 'https://pro-fd.zol-img.com.cn/g6/M00/04/09/ChMkKmGs8BaIIN92AAAqyiz3pd4AAWTBQE3XkQAACri224.jpg',
            description: 'vivo X70 Pro+,全四摄光学防抖,全都稳才是真的稳',
            price: 3999
          },
          {
            goodsId: 3,
            goodsName: 'vivo X70 Pro+',
            img: 'https://2d.zol-img.com.cn/product/211_200x150/655/ce9W4dWqmCrd2.jpg',
            description: 'vivo X70 Pro+,全四摄光学防抖,全都稳才是真的稳',
            price: 1999
          },
          {
            goodsId: 4,
            goodsName: 'vivo X70 Pro+',
            img: 'https://img11.360buyimg.com/n1/s450x450_jfs/t1/143950/30/22734/217452/61add5aeE15c61a95/db95a6416c8f0fe4.jpg',
            description: 'vivo X70 Pro+,全四摄光学防抖,全都稳才是真的稳',
            price: 4999
          },
          {
            goodsId: 5,
            goodsName: 'vivo X70 Pro+',
            img: 'https://wwwstatic.vivo.com.cn/vivoportal/files/image/detail/20210907/b4c60f465a89cbf1f7206ad6cfd844f8.png',
            description: 'vivo X70 Pro+,全四摄光学防抖,全都稳才是真的稳',
            price: 2899
          },
          {
            goodsId: 6,
            goodsName: 'vivo X70 Pro+',
            img: 'https://wwwstatic.vivo.com.cn/vivoportal/files/image/detail/20210908/7687bce9daae9cad530442358af28957.png',
            description: 'vivo X70 Pro+,全四摄光学防抖,全都稳才是真的稳',
            price: 2499
          }
        ];
    this.goodsData[this.categoryList[1]] =
        [
          {
            goodsId: 7,
            goodsName: '电视',
            img: 'https://img12.360buyimg.com/cms/s300x300_jfs/t1/169371/23/26170/212093/61aed6f5Ea394628d/519c45ca0f0c66d6.jpg',
            description: 'vivo X70 Pro+,全四摄光学防抖,全都稳才是真的稳',
            price: 4939
          },
          {
            goodsId: 8,
            goodsName: '',
            img: 'https://img12.360buyimg.com/cms/s300x300_jfs/t1/169371/23/26170/212093/61aed6f5Ea394628d/519c45ca0f0c66d6.jpg',
            description: 'vivo X70 Pro+,全四摄光学防抖,全都稳才是真的稳',
            price: 4939
          },
          {
            goodsId: 9,
            goodsName: '',
            img: 'https://img12.360buyimg.com/cms/s300x300_jfs/t1/169371/23/26170/212093/61aed6f5Ea394628d/519c45ca0f0c66d6.jpg',
            description: 'vivo X70 Pro+,全四摄光学防抖,全都稳才是真的稳',
            price: 4939
          },
          {
            goodsId: 10,
            goodsName: '',
            img: 'https://img12.360buyimg.com/cms/s300x300_jfs/t1/169371/23/26170/212093/61aed6f5Ea394628d/519c45ca0f0c66d6.jpg',
            description: 'vivo X70 Pro+,全四摄光学防抖,全都稳才是真的稳',
            price: 4939
          },
          {
            goodsId: 11,
            goodsName: '',
            img: 'https://img12.360buyimg.com/cms/s300x300_jfs/t1/169371/23/26170/212093/61aed6f5Ea394628d/519c45ca0f0c66d6.jpg',
            description: 'vivo X70 Pro+,全四摄光学防抖,全都稳才是真的稳',
            price: 4939
          },
          {
            goodsId: 12,
            goodsName: '',
            img: 'https://img12.360buyimg.com/cms/s300x300_jfs/t1/169371/23/26170/212093/61aed6f5Ea394628d/519c45ca0f0c66d6.jpg',
            description: 'vivo X70 Pro+,全四摄光学防抖,全都稳才是真的稳',
            price: 4939
          },
        ];
    this.goodsData[this.categoryList[2]] =
        [
          {
            goodsId: 13,
            goodsName: '',
            img: 'https://img13.360buyimg.com/n1/jfs/t1/192206/18/10919/354510/60db49a2E04ebe42c/c429f4aa4bb8d6be.jpg',
            description: '以贯穿项目介绍流行的Spring Boot和Vue技术，实现技术与项目的融汇贯通',
            price: 100
          },
          {
            goodsId: 14,
            goodsName: '',
            img: 'https://img13.360buyimg.com/n1/jfs/t1/192206/18/10919/354510/60db49a2E04ebe42c/c429f4aa4bb8d6be.jpg',
            description: '以贯穿项目介绍流行的Spring Boot和Vue技术，实现技术与项目的融汇贯通',
            price: 100
          },
          {
            goodsId: 15,
            goodsName: '',
            img: 'https://img13.360buyimg.com/n1/jfs/t1/192206/18/10919/354510/60db49a2E04ebe42c/c429f4aa4bb8d6be.jpg',
            description: '以贯穿项目介绍流行的Spring Boot和Vue技术，实现技术与项目的融汇贯通',
            price: 100
          },
          {
            goodsId: 16,
            goodsName: '',
            img: 'https://img13.360buyimg.com/n1/jfs/t1/192206/18/10919/354510/60db49a2E04ebe42c/c429f4aa4bb8d6be.jpg',
            description: '以贯穿项目介绍流行的Spring Boot和Vue技术，实现技术与项目的融汇贯通',
            price: 100
          },
        ];
    // this.$http
    //     .get('api')
    //     .then(res => {
    //       this.categoryList = res.data;
    //       for (let i of this.categoryList) {
    //         this.$http
    //             .get('api')
    //             .then(res2 => {
    //               this.goodsData[i] = res2.data;
    //             });
    //       }
    //     });
  }
}
</script>

<style scoped>
.category {
  text-align: left;
}

.categoryBlock {
  padding: 20px;
  background-color: #eeeeee;
  border-radius: 20px;
}
</style>
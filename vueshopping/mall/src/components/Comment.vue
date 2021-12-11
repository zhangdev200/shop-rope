<template>
  <div class="comment">
    <el-row>
      <span style="position: absolute; right: 20px; color: #888888">{{ commentData.date }}</span>
      <div style="position: absolute;">
        <img :src="commentData.avatar" style="width: 50px">
      </div>
      <div style="margin-left: 70px">
        <div style="font-weight: bold; margin-bottom: 10px">
          {{ commentData.nickname }}
        </div>
        <el-rate
            v-model="commentData.stars"
            :colors="colors"
            disabled>
        </el-rate>
        <div class="comment-content">
          {{ commentData.content }}
        </div>
      </div>
      <div>
        <span style="font-size: 20px; float: right"> ({{ commentData.numOfCai }})</span>
        <i class="iconfont icon-ali-cai icons" ref="cai" style="position: relative; top: 2px;" @click="cai">
        </i>
        <span style="font-size: 20px; float: right"> ({{ commentData.numOfZan }})</span>
        <i class="iconfont icon-ali-zan icons" ref="zan" @click="zan">
        </i>
        <i class=" el-icon-chat-line-round icons" style="font-size: 28px"></i>
      </div>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Comment",
  props: ['data'],
  data() {
    return {
      commentData: {
        id:1,
        stars: 5,
        avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
        nickname: 'a',
        date: '2021-12-4',
        content: '评论pt>export default {name: "Comment",data() {return {avatar:' +
            ' \'https: //cube.elemecdn.com /3/7c/3ea6beec643 69c2642b92c6726f1e png.png\'' +
            '          }pneumonoul  tramicroscopic silicovolcan oconiosis }}',
        numOfZan: 100,
        numOfCai: 100,
      },
      colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
      zanClicked: false,
      caiClicked: false
    }
  },
  methods: {
    zan() {
      if (this.caiClicked) {
        if (!this.zanClicked) {
          this.zanClicked = true;
          this.commentData.numOfZan++;
          this.caiClicked = false;
          this.commentData.numOfCai--;
          this.$refs.zan.style.color = 'rgb(18,150,219)';
          this.$refs.cai.style.color = 'black';
        } else {
          this.zanClicked = false;
          this.commentData.numOfZan--;
          this.$refs.zan.style.color = 'black';
        }
      } else {
        this.zanClicked = !this.zanClicked;
        if (this.zanClicked) {
          this.commentData.numOfZan++;
          this.$refs.zan.style.color = 'rgb(18,150,219)';
        } else {
          this.commentData.numOfZan--;
          this.$refs.zan.style.color = 'black';
        }
      }
    },
    cai() {
      if (this.zanClicked) {
        if (!this.caiClicked) {
          this.caiClicked = true;
          this.commentData.numOfCai++;
          this.zanClicked = false;
          this.commentData.numOfZan--;
          this.$refs.cai.style.color = 'rgb(18,150,219)';
          this.$refs.zan.style.color = 'black';
        } else {
          this.caiClicked = false;
          this.numOfCai--;
          this.$refs.cai.style.color = 'black';
        }
      } else {
        this.caiClicked = !this.caiClicked;
        if (this.caiClicked) {
          this.commentData.numOfCai++;
          this.$refs.cai.style.color = 'rgb(18,150,219)';
        } else {
          this.commentData.numOfCai--;
          this.$refs.cai.style.color = 'black';
        }
      }
    }
  },
  created() {
    this.commentData = this.data;
  }
}
</script>

<style scoped>
.comment {
  background-color: #eeeeee;
  border-radius: 20px;
  padding: 20px;
  margin: 25px 0;
}

.comment-content {
  word-wrap: break-word;
  padding: 20px 0;

}

.icons {
  margin: 0 20px;
  font-size: 22px;
  float: right
}

.icons:active {
  -webkit-transform: scale(1.2) translateY(1px) translateX(1px);
  -moz-transform: scale(1.2) translateY(1px) translateX(1px);
  transform: scale(1.2) translateY(1px) translateX(1px);
}
</style>
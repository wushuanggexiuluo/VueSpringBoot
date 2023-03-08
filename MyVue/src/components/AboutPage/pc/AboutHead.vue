<template>
  <div>
    <el-card class="card1">
      <el-alert
          title="正确的凡尔赛"
          type="success"
          :description="myText"
          :closable="false"
          class="el-icon-edit"
      >
      </el-alert>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "AboutHead",
  data() {
    return {
      myText:"向往巅峰，向往高度，结果巅峰只是一道刚能立足的狭地。不能横行，不能直走，只享一时俯视之乐，怎可长久驻足安坐？上已无路，下又艰难，我感到从未有过的孤独和惶恐。世间真正温煦的美色，都熨帖着大地，潜伏在深谷。君临万物的高度，到头来只构成自我嘲弄。——余秋雨《文化苦旅》"
    }
  },
  mounted() {
  }
}
</script>

<style scoped>
.card1{
  width: 1400px;
  margin: 20px auto;
}
</style>

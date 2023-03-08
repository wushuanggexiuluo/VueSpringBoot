<template>
  <div>
    <MyHeader></MyHeader>
    <div class="SuiData">
      <!--      标签-->
      <label>
        <svg t="1669181166053" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
             p-id="2711" width="16" height="16">
          <path
              d="M995.126867 592.38l-360.08 360.08a53.333333 53.333333 0 0 1-71.333334 3.68l356.22-356.22a64 64 0 0 0 0-90.506667L495.8402 85.333333h45.573333a52.986667 52.986667 0 0 1 37.713334 15.62l416 416a53.4 53.4 0 0 1 0 75.426667z m-128 0l-360.08 360.08a53.333333 53.333333 0 0 1-75.426667 0l-416-416A52.986667 52.986667 0 0 1 0.0002 498.746667V138.666667a53.393333 53.393333 0 0 1 53.333333-53.333334h360.08a52.986667 52.986667 0 0 1 37.713334 15.62l416 416a53.4 53.4 0 0 1 0 75.426667zM341.333533 341.333333a85.333333 85.333333 0 1 0-85.333333 85.333334 85.426667 85.426667 0 0 0 85.333333-85.333334z"
              fill="#1296db" p-id="2712"></path>
        </svg>
        <a style="margin: 0 5px" v-for="(its,index) in item.category" @click="it=its.categoryName" :key="index" href="#">{{
            its.categoryName
          }}</a>
      </label>
      <!--      更新时间-->
      <label>
        <svg t="1669181764267" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
             p-id="3919" width="16" height="16">
          <path
              d="M352 128h32v128h-32zM640 576h128v32H640zM416 192h192v32H416zM640 128h32v128h-32zM448 448h128v32H448zM640 448h128v32H640zM448 576h128v32H448zM256 576h128v32H256z"
              p-id="3920" fill="#1296db"></path>
          <path
              d="M864 192H704v32h160v64H160v-64h160v-32H160c-17.6 0-32 14.4-32 32v640c0 17.6 14.4 32 32 32h704c17.6 0 32-14.4 32-32V224c0-17.6-14.4-32-32-32z m0 672H160V320h704v544z"
              p-id="3921" fill="#1296db"></path>
          <path d="M256 704h128v32H256zM256 448h128v32H256zM448 704h128v32H448z" p-id="3922" fill="#1296db"></path>
        </svg>
        {{ timeFormate(item.articleCreateTime) }}</label>
      <!--      创造时间-->
      <label>
        <svg t="1669181764267" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
             p-id="3919" width="16" height="16">
          <path
              d="M352 128h32v128h-32zM640 576h128v32H640zM416 192h192v32H416zM640 128h32v128h-32zM448 448h128v32H448zM640 448h128v32H640zM448 576h128v32H448zM256 576h128v32H256z"
              p-id="3920" fill="#1296db"></path>
          <path
              d="M864 192H704v32h160v64H160v-64h160v-32H160c-17.6 0-32 14.4-32 32v640c0 17.6 14.4 32 32 32h704c17.6 0 32-14.4 32-32V224c0-17.6-14.4-32-32-32z m0 672H160V320h704v544z"
              p-id="3921" fill="#1296db"></path>
          <path d="M256 704h128v32H256zM256 448h128v32H256zM448 704h128v32H448z" p-id="3922" fill="#1296db"></path>
        </svg>
        {{
          timeFormate(item.articleCreateTime)
        }}</label>
      <!--      浏览量-->
      <label>
        <svg t="1669181802209" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
             p-id="4348" width="16" height="16">
          <path
              d="M754.133333 242.773333c-10.88-10.88-26.666667-14.933333-41.386666-10.88-14.72 4.053333-26.24 15.786667-30.08 30.506667-2.986667 11.306667-21.333333 69.973333-40.32 130.133333-48.64-69.333333-119.04-181.12-138.026667-271.146666-1.28-8.533333-5.333333-16.853333-12.16-23.466667a42.666667 42.666667 0 0 0-60.16 0.213333l-0.426667 0.426667c-88.96 90.666667-161.28 261.12-155.093333 395.306667-19.2-24.96-33.28-49.066667-36.906667-67.84-3.626667-18.346667-18.56-32.213333-37.12-34.346667-18.56-2.133333-36.266667 8.106667-43.733333 25.173333-30.08 67.626667-42.666667 142.506667-36.053333 218.24 17.28 134.826667 117.546667 244.906667 261.333333 287.146667 37.12 11.093333 75.52 16.853333 114.346667 17.066667 147.413333 0 325.973333-85.76 382.293333-249.813334 71.68-208-48.426667-369.28-126.506667-446.72z m46.293334 418.346667C757.546667 785.92 612.906667 853.333333 498.986667 853.333333c-30.506667-0.213333-61.013333-4.693333-90.453334-13.44-87.04-25.386667-185.6-95.573333-200.746666-214.186666-2.346667-26.453333-1.493333-53.333333 2.133333-79.573334 49.066667 64.426667 114.346667 120.106667 125.013333 128.853334 14.72 12.373333 36.053333 13.226667 51.84 2.346666 15.786667-11.093333 22.186667-31.146667 15.786667-49.28l-30.933333-86.613333c-28.373333-79.146667 7.466667-221.653333 74.24-323.84 56.96 141.653333 173.226667 286.933333 178.986666 294.186667 9.813333 12.16 25.6 18.133333 40.96 15.146666 15.36-2.773333 27.946667-14.08 32.853334-29.013333 3.84-11.946667 26.026667-81.493333 43.946666-138.666667 53.12 71.04 100.693333 177.066667 57.813334 301.866667z"
              p-id="4349" fill="#d81e06"></path>
        </svg>
        {{ item.articleViewCount }}
      </label>
    </div>

    <div style="display: flex;flex-direction: column;    align-items: center;">
      <CenterContent :MyContent="item.articleContent"></CenterContent>
      <DianZanData :item="item"></DianZanData>
    </div>
    <div>
    </div>
    <CommentZoom :title="title"></CommentZoom>
  </div>
</template>
<script>
import CenterContent from "@/components/DetailsPage/mobile/CenterContent/CenterContent";
import MyHeader from "@/components/Common/mobile/MyHeader";
import DianZanData from "@/components/DetailsPage/mobile/Bottom/DianZanData";
import CommentZoom from "@/components/DetailsPage/mobile/Bottom/CommentZoom";
import CharsetFilter from "@/utils/CharsetFilter";
import TimeFilter from "@/utils/TimeFilter";
import {getArticleContent_User} from "@/api/UserApi";

export default {
  name: "MyDetails",
  data() {
    return {
      it:"",
      item: {},
      title:"",
    }
  },
  watch:{
    it:{
      handler(val){
        this.$router.push({name:"sortDetails",params:{tag:val}})
      }
    }
  },
  components: {
    MyHeader,
    CenterContent,
    DianZanData,
    CommentZoom
  },
  created() {
    let that = this;
    this.title =CharsetFilter(this.$route.params.title)
    //从后台获取文章内容
    getArticleContent_User(that.title).then((res) => {
      that.item = res.data.data
    })
  },
  methods: {
    timeFormate(str) {
      return TimeFilter(str)
    },
  },
}
</script>

<style scoped>
.SuiData {
  display: flex;
  flex-direction: row;
  background: content-box;
  flex-wrap: wrap;
  justify-content: space-evenly;
}

.SuiData label {
  margin: 10px 10px 25px 10px;
}

</style>

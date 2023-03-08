<template>
  <div >
<!--    嵌套for循环实现动态加载-->
    <div class="Allcontent" v-for="(da,index) in alldata" :key="index">
      <OurArticleCard v-for="(item,index) in da" :key="index" :item="item"/>
    </div>
  </div>

</template>

<script>
import OurArticleCard from "./OurArticleCard";
import {getAllArticle_User} from "@/api/UserApi";

export default {
  name: "CenterContent",
  components: {
    OurArticleCard
  },
  data() {
    return {
      //页面不同组的所有数据
      alldata:[],
      //当前的页数
      page: 1,
    }
  },
  mounted() {
    this.getAticlePage(1, 10);
    window.addEventListener('scroll', this.getScrollLoding) // 监听页面滚动
  },

  methods: {
    //页面滚动监听方法
    getScrollLoding() {
      //获取滚动距离
      let scrolllen = document.documentElement.scrollTop;
      //获取当前页数
      let page = this.page
      //大于指定距离就请求数据（这个指定距离需要你自行调试的）
      if (scrolllen > 1600 * page) {
        this.getAticlePage(page + 1, 10)
        //增加页数计数
        this.page = page + 1
      }
    },
    getAticlePage(currPage, pageSize) {
      let that = this;
      let data = {
        currPage: currPage,
        pageSize: pageSize,
        search: {
          content: "",
          title: "",
          tag: "",
        }
      }
      getAllArticle_User(data).then((res) => {
        //将获取的数据重新放在一个大的数组中
        that.alldata.push(res.data.data.pageDataList)
      })
    },
  }
}
</script>

<style scoped>

</style>

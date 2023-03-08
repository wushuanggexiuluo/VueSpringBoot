<template>
<div class="myArticle">
  <div class="back"></div>

  <MyHeader></MyHeader>
  <div>
    <SearchArticleCard v-for="(item,index) in tableData" :key="index" :item="item"></SearchArticleCard>
  </div>
</div>
</template>
<script>
import MyHeader from "@/components/Common/pc/MyHeader";
import SearchArticleCard from "@/components/SearchPage/pc/SearchArticleCard";
import {searchArticle_User} from "@/api/UserApi";
export default {
  name: "SearchUser",
  components: {SearchArticleCard, MyHeader},
  mounted() {
    this.getAllData()
  },
  data(){
    return{
      tableData:[],
      search:"",
    }
  },
  watch:{
    $route:{
      immediate:true,
      handler(val){
        this.search=val.params.search;
        this.getAllData()
      }
    }
  },
  methods:{
    getAllData(){
      let that=this;
      searchArticle_User(this.search).then((res)=>{
        that.tableData=res.data.data
      })
    }
  }
}
</script>

<style scoped>
.back{
  position: fixed;
  z-index: -999;
  background-image: linear-gradient(to right, #434343 0%, black 100%);
  height: 70rem;
  width: 101.2%;
  min-width: 1410px;

}
</style>

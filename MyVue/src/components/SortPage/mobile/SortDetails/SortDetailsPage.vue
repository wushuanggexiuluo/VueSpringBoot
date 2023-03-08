<template>
<div class="myArticle">
  <MyHeader></MyHeader>
  <div>
    <SortArticleCard v-for="(item,index) in tableData" :key="index" :item="item"></SortArticleCard>
  </div>
</div>
</template>
<script>
import MyHeader from "@/components/Common/mobile/MyHeader";
import SortArticleCard from "@/components/SortPage/mobile/SortDetails/SortArticleCard";
import {getArticleByTag_User} from "@/api/UserApi";
export default {
  name: "SortDetailsPage",
  components: {SortArticleCard, MyHeader},
  mounted() {
    this.getAllData()
  },
  data(){
    return{
      tableData:[],
      tag:"",
    }
  },
  watch:{
    $route:{
      immediate:true,
      handler(val){
        this.tag=val.params.tag;
        this.getAllData()
      }
    }
  },
  methods:{
    getAllData(){
      let that=this;
      getArticleByTag_User(this.tag).then((res)=>{
        that.tableData=res.data.data
      })
    }
  }
}
</script>

<style scoped>
.myArticle{
  background-image: linear-gradient(to right, #434343 0%, black 100%);
  height: 70rem;
  width: 101.2%;

}
</style>

<template>
<div class="myArticle">
  <MyHeader></MyHeader>
  <div>
    <SortLayOut></SortLayOut>
<!--    <router-view></router-view>-->
<!--    <SortTagCard v-for="(item,index) in tableData" :key="index" :item="item"></SortTagCard>-->
  </div>
</div>
</template>
<script>
import MyHeader from "@/components/Common/pc/MyHeader";
import SortLayOut from "@/components/SortPage/pc/SortIndex/SortLayOut";
import {searchArticle_User} from "@/api/UserApi";
export default {
  name: "SortUSer",
  components: {SortLayOut, MyHeader},
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
.myArticle{
  background-image: linear-gradient(to right, #434343 0%, black 100%);
  height: 70rem;
  width: 101.2%;
  min-width: 1410px;
}
</style>

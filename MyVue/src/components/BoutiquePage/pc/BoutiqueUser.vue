<template>
<div class="myArticle">
  <div class="back">
  </div>
  <MyHeader></MyHeader>

  <div>
    <BoutiqueArticleCard v-for="(item,index) in tableData" :key="index" :item="item"></BoutiqueArticleCard>
  </div>
</div>
</template>
<script>
import BoutiqueArticleCard from "@/components/BoutiquePage/pc/BoutiqueArticleCard";
import MyHeader from "@/components/Common/pc/MyHeader";
import {crud_R} from "@/mixs/CRUD";
import {ApiOrigin} from "@/config/ApiOrigin";
export default {
  name: "BoutiqueUser",
  components: {MyHeader, BoutiqueArticleCard},
  mounted() {
    this.getAllData()
  },
  data(){
    return{
      tableData:[],
    }
  },
  methods:{
    getAllData(){
      crud_R.selectAll(this,ApiOrigin+"/Blog/getAllBoutique")
    }
  }
}
</script>

<style scoped>
/*.myArticle{*/
/*  background-image: linear-gradient(to top, #cd9cf2 0%, #f6f3ff 100%);*/
/*  height: 70rem;*/
/*  width: 101.2%;*/
/*  min-width: 1410px;*/
/*}*/
.back{
  z-index: -999;
  position: fixed;
  background-image: linear-gradient(to top, #cd9cf2 0%, #f6f3ff 100%);
  height: 70rem;
  width: 101.2%;
  min-width: 1410px;
}
</style>

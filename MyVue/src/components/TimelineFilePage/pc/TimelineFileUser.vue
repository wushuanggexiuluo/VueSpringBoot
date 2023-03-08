<template>
<div>
  <MyHeader></MyHeader>
  <el-timeline style="margin: 20px auto;width: 800px;min-width: 800px">
    <el-timeline-item v-for="(item,index) in tableData"  style="color: #2c3e50" :timestamp="time(item.articleCreateTime)" :key="index" placement="top">
      <el-card >
        <h2 style="cursor:pointer" @click="it=item.articleTitle">{{item.articleTitle||"空"}}</h2>
        <p>{{ item.articleSummary||"空" }}</p>
      </el-card>
    </el-timeline-item>
  </el-timeline>
</div>
</template>

<script>
import {crud_R} from "@/mixs/CRUD";
import MyHeader from "@/components/Common/pc/MyHeader";
import {ApiOrigin} from "@/config/ApiOrigin";
export default {
  name: "TimelineFileUser",
  components: {MyHeader},
  data(){
    return{
      tableData:[],
      it:"",
    }
  },
  mounted() {
    this.getAllData();
  },
  watch:{
    it:{
      deep:true,
      handler(val){
        this.$router.push({ name:"details",params:{title:val}})
      }
    }
  },
  methods:{
    getAllData(){
      crud_R.selectAll(this,ApiOrigin+"/Blog/getArticlesByTimeLine")
    },
    time(str)
    {
      return str!=null?str.split("T")[0]:str
    }
  }
}
</script>

<style scoped>
h2{
  font-weight: bold;
}
</style>

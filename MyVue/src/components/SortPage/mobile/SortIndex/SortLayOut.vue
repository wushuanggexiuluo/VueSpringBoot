<template>
  <div style="background-image: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);">
    <el-form :inline="true" class="ddd">
      <el-form-item label="搜名称">
        <el-input v-model="search.categoryName" placeholder="随便输入"></el-input>
      </el-form-item>
      <el-form-item label="搜描述">
        <el-input v-model="search.categoryDescription" placeholder="随便输入"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>
    <div class="myTags">
      <div @click="it=item.categoryName" class="tagCard" v-for="(item,index) in tableData" :key="index">
        <img alt="s" :src="item.categoryIcon||'https://oss.qfya.com/wp-content/uploads/2022/07/20220708120500539.png'">
        <div>{{item.categoryName}}</div>
      </div>
    </div>
  </div>
</template>

<script>
import {crud_R} from "@/mixs/CRUD";
import {ApiOrigin} from "@/config/ApiOrigin";

export default {
  name: "SortLayOut",
  data() {
    return {
      search: {
        categoryName:"",
        categoryDescription:"",
      },
      tableData:[],
      it:"",
    }
  },
  mounted() {
    this.getAll()
  },
  watch:{
    it:{
      handler(val){
        this.$router.push({name:"sortDetails",params:{tag:val}})
      }
    }
  },
  methods:{
    getAll() {
      let data={
        currPage:"",
        pageSize: "",
        search:this.search
      }
      crud_R.selectAllBySearchData(this,data,ApiOrigin+"/Blog/getAllTag")
    },
    onSubmit(){
      this.getAll()
    }
  }
}
</script>

<style scoped>
.myTags {
  margin: 20px auto;
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  flex-direction: row;
}

.tagCard {
  border-radius: 9px;
  margin: 9px;
  background-image: linear-gradient(122deg, #13547a 0%, #80d0c7 100%);
  width: 64px;
  height: 72px;
  text-align: center;
  font-family: cursive;
  font-weight: bold;
  font-size: 14px;
  color: white;
  transition: all 0.6s;
}
.tagCard:hover{
  background-image: linear-gradient(to right, #f83600 0%, #f9d423 100%);
  color: #69EACB;
  cursor:pointer;
  transform: translateY(-3px);
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.3), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  /*animation: xx 0.8s forwards;*/
  text-align: center;
}
.tagCard > img {
  border-radius: 8px;
  width: 40px;
  min-width: 40px;
  height: 40px;
  min-height: 40px;
}

.ddd {
  display: flex;
  padding: 11px;
  align-items: center;
  flex-direction: column;
}
</style>

<template>
  <div class="LeftContentTop">
    <div>
      <label>热门标签</label>
      <hr>
    </div>
    <div class="labelItem">
      <label @click="tagName=item" v-for="(item,index) in items" :key="index">{{item}}</label>
    </div>
  </div>
</template>

<script>
export default {
  name: "OurCard1",
  props:["CardData"],
  mounted() {
    this.items=this.CardData
  },
  watch:{
    CardData:{
      immediate:true,
      deep:true,
      handler(val){
        this.items=val;
      }
    },
    tagName:{
      handler(val){
        this.$router.push({name:"sortDetails",params:{tag:val}})

      }
    }
  },
  data(){
    return{
      tagName:"",
      items:[]
    }
  }
};
</script>

<style scoped>

.LeftContentTop{
  background-color: lavender;
  width: 244px;
  min-width: 100px;
  height: 218px;
  margin: auto;
  transition:0.8s all;

}
.LeftContentTop:hover{
 transform: translateY(-2px);
  box-shadow: 6px 8px 10px #fecfef;
}
.LeftContentTop>div:first-child{
  background-color: aquamarine;
  margin: 11px;
}
.LeftContentTop>div:last-child{
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.labelItem>label{
  /*width: 244px;*/
  margin: 5px auto
}
.labelItem>label:hover{
  cursor: pointer;
  border-bottom: 1px solid black;
}
</style>

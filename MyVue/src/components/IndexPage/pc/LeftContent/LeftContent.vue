<template>
  <div class="LeftContent">
    <OurCard1 :CardData="Card1"></OurCard1>
    <OurCard1 :CardData="Card2" style="top: 400px;height: 400px"></OurCard1>
  </div>
</template>

<script>
import OurCard1 from "@/components/IndexPage/pc/LeftContent/FixConetntCard/OurCard1";
import {getAllHotTag_User} from "@/api/UserApi";
export default {
  name: "LeftContent",
  data(){
    return{
      tableData:[],
      Card1:[],
      Card2:[]
    }
  },
  methods:{
    getAllData() {
      getAllHotTag_User().then((res)=>{
        this.tableData=res.data.data;
        let data1=this.tableData;
        let length=data1.length
        if(length>4){
          for (let i = 0; i < length; i++) {
            if(i<4){
              this.Card1.push(data1[i]);
            }
            else {
              this.Card2.push(data1[i])
            }
          }
        }

      })
    }
  },
  mounted() {
     this.getAllData()
  },
  components:{
    OurCard1
  }
}
</script>

<style scoped>
.LeftContent{
  background-image: linear-gradient(to top, #ff9a9e 0%, #fecfef 99%, #fecfef 100%);
  width: 300px;
  height: 100vh;
  margin: 20px 20px 20px 50px;
  padding: 10px;
  min-width: 257px;
}

</style>

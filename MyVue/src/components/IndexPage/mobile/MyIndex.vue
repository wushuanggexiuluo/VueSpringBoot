<template>
  <div  @click="RandomColor">
  <MyHeader v-wave="VaStyle"></MyHeader>
  <div  id="app1" style="display: flex;" >
<!--    <LeftContent  v-wave="VaStyle" ></LeftContent>-->
    <CenterContent ></CenterContent>
  </div>
  </div>
</template>

<script>
import CenterContent from "@/components/IndexPage/mobile/CenterContent/CenterContent";
import MyHeader from "@/components/Common/mobile/MyHeader";

export default {
  name: "MyIndex",
  components: {
    MyHeader,
    CenterContent,
  },
  data(){
    return{
      VaStyle: {
        color: 'blue',
        initialOpacity: 0.5,
        duration: 1,
        easing: 'ease-in'
      }
    }
  },
  methods:{
    RandomColor(){
      this.VaStyle.color=this.color16()
    },
    color16(){//十六进制颜色随机
      let r = Math.floor(Math.random()*256);
      let g = Math.floor(Math.random()*256);
      let b = Math.floor(Math.random()*256);
      return '#' + r.toString(16) + g.toString(16) + b.toString(16);
    }
  }
}
</script>

<style scoped>
#app1 {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

</style>

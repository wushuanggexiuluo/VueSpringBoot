<template>
  <div class="error-page">
    <div class="butG">
      <button @click="toIndex" >返回首页</button>
    </div>
    <div class="back">

    </div>
    <div class="content-con">
      <img :src="src" :alt="code">
      <div class="text-con">
        <h4>{{ code }}</h4>
        <h5>{{ desc }}</h5>
      </div>
    </div>


  </div>
</template>

<script>
export default {
  name: 'ErrContent',
  props: {
    code: String,
    desc: String,
    src: String
  },
  methods:{
    toIndex(){
      this.$router.push({name:"index"})
    }
  }
}
</script>
<style scoped>
.back{
  top: 1px;
  position: fixed;
  height: 49rem;
  width: 94rem;
  margin: -21px;
  background-color: skyblue;
}
.butG{
  position: relative;
  top: 145px;
  left: 716px;
  z-index: 99;
  width: 50px;
}
.butG>button:hover{
  cursor: pointer;
}
.butG>button{
  height: 50px;
  width: 50px;
  border-radius: 25px;
}
.content-con>img{
  width: 1100px;
  position: absolute;
}

.text-con{
  text-shadow: 2px 2px grey;
  position: relative;
  top: 159px;
  color: white;
  right: -696px;
  width: 119px;
}
</style>

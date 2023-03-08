<template>
  <div>
    <!--       :options=""（必填项，对应动画数据）   v-on:animCreated（非必填）-->
    <lottie :options="defaultOptions" :width="80" :height="80" v-on:animCreated="handleAnimation"/>
  </div>
</template>

<script>
// 引入组件
import lottie from "vue-lottie"
//引入动画
import * as animationData from "../../../animation/loadinganimation.json"

export default {
  name: "LoadAmination",
  //加载组件
  components: {
    lottie: lottie
  },
  data() {
    return {
      //必填项
      defaultOptions: {animationData: animationData.default},
    }
  },
  methods: {
    //动画被加载时，触发的方法(非必填项)
    handleAnimation(anim) {
      this.defaultAnim = anim;

    }
  }
}
</script>

<style scoped>

</style>

<template>
  <div>
    <div class="myPage">
      <MyHeader></MyHeader>
      <AboutHead></AboutHead>
      <AboutCenter></AboutCenter>
      <AboutBottom></AboutBottom>
    </div>
  </div>
</template>

<script>
import AboutBottom from "@/components/AboutPage/mobile/AboutBottom";
import MyHeader from "@/components/Common/mobile/MyHeader";
import AboutCenter from "@/components/AboutPage/mobile/AboutCenter";
import AboutHead from "@/components/AboutPage/mobile/AboutHead";

export default {
  name: "AboutPage",
  components: {AboutHead, AboutCenter, MyHeader, AboutBottom}
}
</script>

<style scoped>
.myPage {
  min-width: 100%;
  height: 360vh;
  background-image: linear-gradient(to top, #fddb92 0%, #d1fdff 100%)
}
</style>

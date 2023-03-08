<template>
<div>
  <div style="width: 100vw;min-width: 93rem; height: 190vh; background-image: linear-gradient(to top, #fddb92 0%, #d1fdff 100%);">
  <MyHeader></MyHeader>

    <AboutHead></AboutHead>
    <AboutCenter></AboutCenter>
    <AboutBottom></AboutBottom>
  </div>
</div>
</template>

<script>
import AboutBottom from "@/components/AboutPage/pc/AboutBottom";
import MyHeader from "@/components/Common/pc/MyHeader";
import AboutCenter from "@/components/AboutPage/pc/AboutCenter";
import AboutHead from "@/components/AboutPage/pc/AboutHead";
export default {
  name: "AboutPage",
  components: {AboutHead, AboutCenter, MyHeader, AboutBottom}
}
</script>

<style scoped>

</style>

<template>
  <div style="display: flex;">
    <MyLeftSystem></MyLeftSystem>
    <MyRightSystem></MyRightSystem>
  </div>
</template>

<script>
import MyRightSystem from "@/components/SystemAdmin/mobile/MyRightSystem";
import MyLeftSystem from "@/components/SystemAdmin/mobile/MyLeftSystem";
export default {
  name: "MyAdmin",
  components:{
    MyRightSystem,
    MyLeftSystem,
  }
}
</script>

<style scoped>

</style>

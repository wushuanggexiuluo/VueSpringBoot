<template>
  <div style="display: flex;">
    <MyLeftSystem></MyLeftSystem>
    <MyRightSystem></MyRightSystem>
  </div>
</template>

<script>
import MyRightSystem from "@/components/SystemAdmin/pc/MyRightSystem";
import MyLeftSystem from "@/components/SystemAdmin/pc/MyLeftSystem";
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

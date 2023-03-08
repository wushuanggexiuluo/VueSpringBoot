<template>
<div style="z-index: 998">
  <ShowMarkDown :MyContent="MyContent" style="margin: auto">

</ShowMarkDown></div>
</template>

<script>
import ShowMarkDown from "@/components/DetailsPage/pc/Utils/ShowMarkDown";
export default {
  name: "CenterContent",
  components:{
    ShowMarkDown
  },
  props:["MyContent"],
}
</script>

<style scoped>
.markdown  .border{
  height: 1200px;
}
</style>

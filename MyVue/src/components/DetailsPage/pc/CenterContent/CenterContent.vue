<template>
<div>
  <ShowMarkDown :MyContent="MyContent" style="z-index:999;width: 800px;min-width:600px;margin: auto">

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

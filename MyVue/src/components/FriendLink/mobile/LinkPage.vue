<template>
<div>
  <div style="background-image: linear-gradient(to top, #fddb92 0%, #d1fdff 100%);">
  <MyHeader></MyHeader>

    <LinkHead></LinkHead>
    <LinkCenter></LinkCenter>
    <LinkBottom></LinkBottom>
  </div>
</div>
</template>

<script>

import LinkHead from "@/components/FriendLink/mobile/LinkHead";
import LinkCenter from "@/components/FriendLink/mobile/LinkCenter";
import LinkBottom from "@/components/FriendLink/mobile/LinkBottom";
export default {
  name: "LinkPage",
  components: {LinkBottom, LinkCenter, LinkHead}
}
</script>

<style scoped>

</style>

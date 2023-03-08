<template>
<div>
  <div style="width: 100rem;min-width: 100rem; height: 190vh; background-image: linear-gradient(to top, #fddb92 0%, #d1fdff 100%);">
  <MyHeader></MyHeader>

    <LinkHead></LinkHead>
    <LinkCenter></LinkCenter>
    <LinkBottom></LinkBottom>
  </div>
</div>
</template>

<script>

import LinkHead from "@/components/FriendLink/pc/LinkHead";
import LinkCenter from "@/components/FriendLink/pc/LinkCenter";
import LinkBottom from "@/components/FriendLink/pc/LinkBottom";
export default {
  name: "LinkPage",
  components: {LinkBottom, LinkCenter, LinkHead}
}
</script>

<style scoped>

</style>

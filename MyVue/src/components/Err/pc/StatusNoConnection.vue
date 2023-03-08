
<template>
  <ErrContent code="外星人" desc="外星人偷走了你的网络！" :src="src"/>
</template>

<script>
import error404 from '../../../assets/img/common/error-404.svg'
import ErrContent from "@/components/Err/pc/ErrContent";
export default {
  name: 'StatusNoConnection',
  components: {
    ErrContent
  },
  data () {
    return {
      src: error404
    }
  }
}
</script>

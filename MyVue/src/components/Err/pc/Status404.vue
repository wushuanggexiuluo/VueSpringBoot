<template>
  <ErrContent code="404" desc="谢特！这该死的猫偷走了你的页面！" :src="src"/>
</template>

<script>
import error404 from '../../../assets/img/common/error-404.svg'
import ErrContent from "@/components/Err/pc/ErrContent";
export default {
  name: 'Status404',
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

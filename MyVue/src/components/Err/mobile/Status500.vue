<template>
  <ErrContent code="500" desc="服务器好像饿了，正在投喂中...." :src="src"/>
</template>

<script>
import error404 from '../../../assets/img/common/error-404.svg'
import ErrContent from "@/components/Err/pc/ErrContent";
export default {
  name: 'Status500',
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

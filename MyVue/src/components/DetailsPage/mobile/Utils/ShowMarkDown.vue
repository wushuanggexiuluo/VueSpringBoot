<template>
  <div class="MarkShow">
    <MavonEditor
        :toolbars="markdownOption"
                 :subfield="false"
                 :defaultOpen="MarkStatus"
                 v-model="myMarkDownData"/>
  </div>
</template>

<script>
// 导入markdown组件
import {mavonEditor} from 'mavon-editor'

export default {
  name: "ShowMarkDown",
  //使用该组件
  components: {
    MavonEditor: mavonEditor,
  },
  props:["MyContent"],
  watch:{
    MyContent:{
      immediate:true,
      deep:true,
      handler(newval){
       this.myMarkDownData=newval
      }
    }
  },
  data() {
    return {
      markdownOption: {},
      MarkStatus:"preview",
      myMarkDownData:this.MyContent
    }
  },
  methods: {
    submit() {

      var res = this.filterHtmlCharset(this.myMarkDownData);

    },
    //将markdown字符排除，出来并返回
    filterHtmlCharset(str) {
      var reg = /([\u4e00-\u9fa5])|(\d)|(\w)|([,\\.!。，?])/g
      var reg2 = /\\!\[\\]\(.{1,}\w\)/g
      str = str.replace(reg2, "")
      return str.match(reg).join("")
    }
  }

}
</script>

<style scoped>
</style>

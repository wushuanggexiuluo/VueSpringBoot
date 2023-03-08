<template>
  <div>
    ksiugv
    <MavonEditor :toolbars="markdownOption"
        v-model="myMarkDownData"             :defaultOpen="MarkStatus" />
    <button  @click="submit">提交</button>
  </div>
</template>

<script>
// 导入markdown组件
import MavonEditor from 'mavon-editor'
export default {
  name: "MyMarkDown",
  //使用该组件
  components: {
    MavonEditor: MavonEditor,
  },
  data() {
    return {
      MarkStatus:"auto",
      markdownOption:{},
      myMarkDownData: "sdc"
    }
  },
  methods:{
    submit()
    {

      var res = this.filterHtmlCharset(this.myMarkDownData);

      },
    //将markdown字符排除，出来并返回
    filterHtmlCharset(str)
    {
      var reg = /([\u4e00-\u9fa5])|(\d)|(\w)|([,\\.!。，?])/g
      var reg2=/\\!\[\\]\(.{1,}\w\)/g
      str=str.replace(reg2,"")
      return str.match(reg).join("")
    }
  }

}
</script>


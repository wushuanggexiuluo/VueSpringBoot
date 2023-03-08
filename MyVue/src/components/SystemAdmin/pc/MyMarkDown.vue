<template>
  <div>
    <MavonEditor
        v-model="myMarkDownData"/>
  </div>
</template>

<script>
// 导入markdown组件
import MavonEditor from 'vue-meditor'
export default {
  name: "MyMarkDown",
  props:["MyMarkDownData"],
  //使用该组件
  components: {
    MavonEditor: MavonEditor,
  },
  data() {
    return {
      myMarkDownData: this.MyMarkDownData,
    }
  },
  watch:{
    MyMarkDownData(val){
      this.myMarkDownData=val;
    },
    myMarkDownData(newdata){
      this.$emit('formSubmit', newdata);
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
      return str.match(reg).join("")
    }
  }

}
</script>

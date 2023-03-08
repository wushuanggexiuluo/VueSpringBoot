<template>
  <div>
    <el-tag
        v-for="(tag,index) in category"
        :key="index"
        :closable="isEdit"
        :disable-transitions="false"
        @close="handleCloseTag(tag)"
        type="success">
      {{ tag.categoryName }}
    </el-tag>
    <el-input
        class="input-new-tag"
        v-show="isEdit"
        v-if="inputVisible"
        v-model="inputValue"
        ref="saveTagInput"
        size="small"
        @keyup.enter.native="handleInputConfirm"
        @blur="handleInputConfirm"
    ></el-input>
    <el-button v-else class="button-new-tag" size="small" @click="showInput" v-show="isEdit">+新建标签</el-button>

  </div>
</template>

<script>
import userStatus from "@/utils/userStatus";

export default {
  name: "ArticleTags",
  props:["categoryContent","isEdit"],
  mounted() {
    if(this.isEdit){
      this.inputVisible=false
    }
  },
  watch:{
    categoryContent:{
      immediate:true,
      deep:true,
      handler(newval){
        this.category=newval
      }
    },
    category:{
      immediate:true,
      deep:true,
      handler(newval){
        userStatus.setData("tag",JSON.stringify(newval))
        this.$emit('getNewTag', newval);
      }
    }
  },
  methods: {
    //展示标签添加框
    showInput() {
      this.inputVisible = true;
      this.$nextTick(() => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    //添加操作
    handleInputConfirm() {
      let inputValue = this.inputValue.trim();
      if(this.category==null){
        this.category=[]
      }
      let tags=this.category
      let isCF=false;
      if (inputValue) {
        for (let i = 0; i < tags.length; i++) {
          if(tags[i].categoryName===inputValue)
          {
            this.$message({
              type:"error",
              message: "标签不能重复"
          })
            isCF=true
          break
          }
        }
        if(!isCF){
          this.category.push({categoryName:inputValue})
        }
      }
      this.inputVisible = false;
      this.inputValue = '';
    },
    //标签删除
    handleCloseTag(tag) {

      this.category.splice(this.category.indexOf(tag), 1);
    },
  },
  data() {
    return {
      inputVisible: false,
      inputValue: '',
      category:this.categoryContent,
    }
  }
}
</script>

<style scoped>

</style>

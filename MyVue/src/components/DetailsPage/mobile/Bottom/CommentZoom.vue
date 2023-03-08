<template>
  <div>
    <div class="myEidt">
      <VueEidtor
          ref="Quill" style="" class="quill-editor" v-model="FormSendData.commentContent"></VueEidtor>
      <div style="z-index: 999">
        <label style="margin: 1px 10px;">名字</label>
        <input v-model="FormSendData.commentAuthorName"/>
      </div>
      <div style="z-index: 999">
        <label style="    margin: 1px 10px;">邮箱</label>
        <input v-model="FormSendData.commentAuthorEmail"/>
      </div>
      <button @click="submitBt1" class="commentBtu1">评论</button>
    </div>
    <div style="">
      <div class="AllComment">
        <div v-for="(it1) in AllCommentData" :key="it1.commentId">
          <div v-html="it1.commentContent"></div>
          <div class="commentX">
            <div>
              <img alt="NULL" src="https://markdown.com.cn/assets/img/philly-magic-garden.9c0b4415.jpg"/></div>
            <div class="commentX1">
              <div>
                评论人
              </div>
              <div>
                {{it1.commentAuthorName}}
              </div>
            </div>
            <div class="commentX1">
              <div>
                评论时间
              </div>
              <div>
                {{ timeFormate(it1.commentCreateTime) }}
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// 注意不能使用其他名字，当使用{}引入组件的时候！！！！！！
import {quillEditor} from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import ShowAdminMessage from "@/mixs/ShowAdminMessage";
import {addArticleComment_User, getArticleComment_User} from "@/api/UserApi";


export default {
  name: "CommentZoom",
  components: {
    VueEidtor: quillEditor
  },
  props:["title"],
  data() {
    return {
      FormSendData: {},
      AllCommentData: {}
    }
  },
  mounted() {
    this.getAllComment()
  },
  methods: {
    //提交评论
    submitBt1() {
      this.FormSendData.articleTitle = this.title
      let that = this;
      addArticleComment_User(that.FormSendData).then((res) => {
        if (res.data.code === ShowAdminMessage.data().SAVE_OK) {
          that.FormSendData = {}
          ShowAdminMessage.methods.AddCommentOk(that)
          this.getAllComment()
        }
        else {
          ShowAdminMessage.methods.AddCommentErr(that)
        }
      })
    },
    getAllComment() {
      let that = this;
      getArticleComment_User(that.title).then((res) => {
        that.AllCommentData = res.data.data
      })
    },
    timeFormate(str) {

      if (str !== null) {

        return str.split("T")[0]
      } else {
        return str
      }
    },
  }
}
</script>

<style scoped>
.myEidt{
  margin: 10px auto;
  height: 400px;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: rgb(253 253 253);
  opacity: 0.8;
}
.quill-editor {
  min-height: 300px;
}
.commentBtu1 {
  margin: auto;
  display: block;
  background-color: #30cfd0;
  min-width: 112px;
  min-height: 29px;
  z-index: 999;
}

.commentBtu1:hover {
  cursor: pointer;
}

.AllComment {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  height: auto;
  margin: 20px auto;
  background-color: #00dbde;
}

.AllComment > div {
  margin: 10px 10px;
  background-color: rgb(254, 207, 239);
  padding: 10px;
  min-width: 90%;
  height: auto;
  flex-direction: row;
  justify-content: center;
}
.commentX{

  margin: 10px 0 5px 0;display: flex;
  font-size: 0.1rem;
  opacity: 0.8;
  background-color: rgb(255 224 225);
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
}
.commentX img{
  min-width: 38px;
  min-height: 38px;
  width: 38px;
  height: 38px;
  border-radius: 38px;
  margin: 3px;
}
.commentX1 {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 10px;
}
</style>

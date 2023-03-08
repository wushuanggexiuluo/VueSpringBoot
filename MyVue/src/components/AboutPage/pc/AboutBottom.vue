<template>
  <div>
    <el-card style="width: 1300px;
  margin: 10px auto;">
      <div slot="header" class="clearfix">
        <span style="margin: auto;display: block">吐槽卡</span>
      </div>
      <el-collapse class="cardTc">
        <el-collapse-item class="cardTc2" name="1">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <el-button style="float: right; padding: 3px 0" type="text" @click="changeBQ">{{ BZWZ }}</el-button>
            </div>
            <el-input
                type="textarea"
                :rows="4"
                placeholder="吐槽一下吧"
                v-model="addData.messageContent">
            </el-input>
            <picker v-if="isShow" style="  width: 702px;" :include="['people','Smileys']" :showSearch="true"
                    :showPreview="true" :showCategories="false" @select="addEmoji"/>
            <div class="card3">
              <div style=" margin: 5px 10px;    width: 10rem;">大名</div>
              <el-input
                  v-model="addData.messageAuthorName">
              </el-input>
              <div style=" margin: 5px 10px;   width: 10rem;">邮箱</div>

              <el-input
                  v-model="addData.messageAuthorEmail">
              </el-input>
              <div style=" margin: 5px 10px;    width: 10rem;">站点</div>

              <el-input
                  v-model="addData.url">
              </el-input>

            </div>
            <div style="display: flex;align-items: center;justify-content: space-evenly;">
              <el-button type="primary" @click="addDataCli">提交</el-button>
              <br>
              <el-button type="danger" @click="clearAllCli">全部清空</el-button>
            </div>
          </el-card>

        </el-collapse-item>
      </el-collapse>
    </el-card>


  </div>
</template>

<script>
import {Picker} from "emoji-mart-vue"; //引入组件

import ShowAdminMessage from "@/mixs/ShowAdminMessage";
import showAdminMessage from "@/mixs/ShowAdminMessage";
import {emailRegx} from "@/utils/Regx";
import {addMessage_User} from "@/api/UserApi";

export default {
  name: "AboutBottom",
  mounted() {
  },
  data() {
    return {
      isShow: false,
      BZWZ: "显示表情",
      tableData: [],
      addData: {
        messageContent: "",
        messageAuthorName: "",
        messageAuthorEmail: "",
        url: "",
      }
    }
  },
  components: {
    Picker
  },
  methods: {
    changeBQ() {
      this.isShow = !this.isShow
      if (this.isShow) {
        this.BZWZ = "隐藏表情"
      } else {
        this.BZWZ = "显示表情"
      }
    },
    addEmoji(e) {
      this.addData.messageContent += e.native;
    },
    clearAllCli() {
      this.addData = {}
    },
    //新增吐槽
    addDataCli() {
      if (emailRegx(this.addData.messageAuthorEmail)) {
        let this_ = this;
        addMessage_User(this.addData).then((res) => {
          if (res.data.code === ShowAdminMessage.data().SAVE_OK) {
            showAdminMessage.methods.AddOk(this_);
            this_.clearAllCli();
          }
        }).catch(() => {
          ShowAdminMessage.methods.AddErr(this, " 请填写必要信息")

        })
      } else {
        showAdminMessage.methods.emailErr(this);
      }
    }
  }
}
</script>

<style scoped>
.box-card {
  display: flex;
  width: 1200px;
  margin: 10px auto;
  flex-direction: column;
}

.cardTc {
  width: 1200px;
  margin: 10px auto;
  position: relative;
}

.el-collapse-item__header {
  margin: auto;
}

.card3 {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin: 10px auto;
}

.emoji-mart[data-v-7bc71df8] {

  margin: 10px auto;
  font-family: -apple-system, BlinkMacSystemFont, "Helvetica Neue", sans-serif;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-direction: column;
  flex-direction: column;
  height: 320px;
  color: #ffffff !important;
  border: 1px solid #d9d9d9;
  border-radius: 5px;
  background: #fff;
}
</style>

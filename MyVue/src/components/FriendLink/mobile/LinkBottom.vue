<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <el-button style="float: right; padding: 3px 0" type="text" @click="changeBQ">{{ BZWZ }}</el-button>
    </div>
    <el-input
        type="textarea"
        :rows="4"
        placeholder="大佬的网站介绍"
        v-model="addData.linkDescription">
    </el-input>
    <picker v-if="isShow" style="  width: 90%;
" :include="['people','Smileys']" :showSearch="true"
            :showPreview="true" :showCategories="false" @select="addEmoji"/>
    <div class="card3">
      <div style=" margin: 5px 5px; width: 10rem;">网站名称</div>
      <el-input
          v-model="addData.linkName">
      </el-input>
      <div style=" margin: 5px 5px;   width: 10rem;">QQ邮箱</div>
      <el-input
          v-model="addData.linkOwnerContact">
      </el-input>
      <div style=" margin: 5px 5px;    width: 10rem;">网址</div>
      <el-input
          v-model="addData.linkUrl">
      </el-input>
      <div style=" margin: 5px 5px;    width: 3.3rem;">昵称</div>
      <el-input
          v-model="addData.linkOwnerNickname">
      </el-input>
    </div>
    <div style="display:flex; flex-direction: row;    width: 254px;">

      <img alt="NULL" style="width: 148px;height: 148px;" :src="linkImage">
      <el-upload
          action=""
          list-type="picture-card"
          :show-file-list="false"
          :before-upload="beforeAvatarUpload">
        <div>点击上传网站封面</div>
      </el-upload>
    </div>

    <div class="butGroud">
      <el-button type="primary" @click="addDataCli">提交</el-button>
      <br>
      <el-button type="danger" @click="clearAllCli">全部清空</el-button>
    </div>
  </el-card>

</template>

<script>
import {Picker} from "emoji-mart-vue";
import ShowAdminMessage from "@/mixs/ShowAdminMessage";
import {emailRegx, internetRegx} from "@/utils/Regx";
import showAdminMessage from "@/mixs/ShowAdminMessage";
import {addLink_User, uploadFile_User} from "@/api/UserApi";


export default {
  name: "LinkBottom",
  mounted() {
  },
  data() {
    return {
      isShow: false,
      BZWZ: "显示表情",
      addData: {
        linkDescription: ""
      },
      linkImage: ""
    }
  },
  components: {
    Picker
  },
  methods: {
    beforeAvatarUpload(file) {
      const isJPG = file.type;
      const isLt2M = file.size / 1024 / 1024 < 3;
      let errms = ""
      if (isJPG !== 'image/jpeg' && isJPG !== 'image/png') {
        errms += "上传封面图片只能是 JPG或PNG格式!"
      }
      if (!isLt2M) {
        errms += "上传封面图片大小不能超过 3MB!";
      }
      if (errms.length > 0) {
        this.$message.error(errms)
      } else {
        let this_ = this;
        this.file = file
        let data = {"file": file}
        uploadFile_User(data).then((resp) => {
          this_.linkImage = resp.data.data
        }).catch(() => {
          ShowAdminMessage.methods.AddErr(this, " 服务器跑路了")
        })
      }
      return errms.length <= 0;
    },
    changeBQ() {
      this.isShow = !this.isShow
      if (this.isShow) {
        this.BZWZ = "隐藏表情"
      } else {
        this.BZWZ = "显示表情"
      }
    },
    clearAllCli() {
      this.addData = {}
    },
    addEmoji(e) {
      this.addData.linkDescription += e.native;
    },
    addDataCli() {
      if (emailRegx(this.addData.linkOwnerContact)) {
        if (this.addData.linkName !== undefined) {
          if (this.addData.linkOwnerNickname !== undefined) {
            if (internetRegx(this.addData.linkUrl)) {
              let that = this;
              addLink_User(this.addData).then((res) => {
                if (20001 === res.data.code) {
                  this.$message({
                    type: "success",
                    message: "你的请求，我们已收到"
                  })
                  that.addData = {}
                }
              }).catch(() => {
                ShowAdminMessage.methods.AddErr(this, " 请填写必要信息")
              })
            } else {
              showAdminMessage.methods.internetErr(this)
            }
          } else {
            showAdminMessage.methods.notNull(this, "昵称")
          }
        } else {
          showAdminMessage.methods.notNull(this, "网站名")
        }
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
  width: 100%;
  margin: 5px auto;
  flex-direction: column;
}

.cardTc {
  width: 100%;
  margin: 5px auto;
  position: relative;
}

.el-collapse-item__header {
  margin: auto;
}

.card3 {
  display: flex;
  flex-direction: column;
  align-items: stretch;
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

.butGroud {
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  margin: 17px;
}
</style>

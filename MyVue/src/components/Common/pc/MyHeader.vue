<template>
  <div class="myHeader">
    <ul>
      <li></li>
      <li @click="addressName='index'">主页</li>
      <li @click="addressName='jh'">
        精华
        <ul class="Span1"></ul>
      </li>

      <li @click="addressName='sort'">
        分类
        <ul class="Span1">
        </ul>
      <li @click="addressName='file'">
        归档
      </li>
      <li @click="addressName='link'">
        友链
      </li>
      <li @click="addressName='about'">
        吐槽家族
      </li>
      <li @click="drawer=true">
        点我搜索
      </li>
      <li v-if="isLogin" @click="addressName='login'" style="margin-right: 13%">
        登陆/注册
      </li>
      <li v-if="!isLogin" style="margin-right: 13%">
        <img @click="isVisCard=!isVisCard" style="width: 38px;height: 38px;border-radius: 38px" alt="s"
             src="https://is3-ssl.mzstatic.com/image/thumb/Purple128/v4/bd/0f/17/bd0f1731-eab9-978b-45ae-c46978dba494/AppIcon-0-1x_U007emarketing-0-0-85-220-6.png/1024x1024bb.jpeg">
        <el-card v-show="isVisCard" class="box-card">
          <div style="display: flex;    flex-direction: column;
    align-items: center;">
            <!--            名称-->
            <div style="align-items: center; padding-bottom: 3px; border-bottom: 2px solid black;width: 180px;min-width: 180px;
    text-align: center; margin: 4px">
              name
            </div>
            <!--            点赞数据-->
            <div class="CardData" style="align-items: center;     flex-direction: column;display: flex">
              <div>
                粉丝
              </div>
              <div>
                关注
              </div>
              <div style="padding-bottom: 3px; border-bottom: 2px solid #2c3e50">
                获赞
              </div>
            </div>
            <!--          个人管理-->
            <div class="CardData" style="display: flex;flex-direction: column;align-items: center">
              <div>
                个人中心
              </div>
              <div>
                内容管理
              </div>
              <div @click="CancelLogin">
                退出登录
              </div>

            </div>
          </div>
        </el-card>
      </li>


    </ul>

    <el-drawer
        :visible.sync="drawer"
        :with-header="false">
      <div style="display: flex;flex-direction: column;
    align-items: center">
        <el-form style="min-width: 200px;width: 200px;margin: 10px auto">
          <el-form-item>
            <div style="margin: 10px auto;color: black;text-align: center">全站搜</div>
            <el-input v-model="search" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div class="demo-drawer__footer">
          <el-button round type="success" @click="submitForm">搜索</el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import ShowAdminMessage from "@/mixs/ShowAdminMessage";
import userStatus from "@/utils/userStatus";
import {cancelLogin_User} from "@/api/UserApi";

export default {
  name: "MyHeader",
  data() {
    return {
      drawer: false,
      isLogin: true,
      isVis: false,
      isVisCard: false,
      addressName: "",
      search: "",
    }
  },
  created() {
    let user = localStorage.getItem("user")
    this.isLogin = user == null;
  },
  watch: {
    addressName: {
      immediate: true,
      handler(name) {
        this.$router.push({name: name})
      }
    }
  },
  methods: {
    submitForm() {
      if (this.search.length > 0) {
        this.$router.push("/Search/" + this.search)
      } else {
        ShowAdminMessage.methods.IllegalSearch(this)
      }
    },
    CancelLogin() {
      let that = this;
      let token = userStatus.getData("token");
      // 判断本地的cookie中是否有token
      if (token) {
        cancelLogin_User(token).then((res) => {
          //得到后台认可，可以取消登陆
          if (res.data.code === ShowAdminMessage.data().Cancel_OK) {
            localStorage.removeItem("user")
            localStorage.removeItem("token")
          }
          that.isLogin = true
        })
      }
    }
  }
}
</script>

<style scoped>
.myHeader {
  font-weight: bolder;
  font-size: 22px;
  font-family: cursive;
  height: 47px;
  background-image: linear-gradient(233deg, #00dbde 0%, #fc00ff 100%);
  width: 100vw;
  min-width: 88rem;
  transition: all .5s;

}

.myHeader:hover {
  box-shadow: inset 0 0 5px 1vmax rgb(85 65 65 / 50%);
  color: white;
  text-shadow: 2px 2px 3px white;

}

.myHeader > ul {
  height: 47px;
  margin: 0;
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
}

.myHeader > ul > li {
  position: relative;
  margin: 2px 30px 2px 30px;
}

.myHeader > ul > li:hover {
  cursor: pointer;
  color: #2c3e50;
  text-shadow: 2px 2px 3px grey;
}


@keyframes myFade {
  0% {
    height: 0;
  }
  100% {
    height: 100px;
  }
}

/*.Span1 {*/
/*  display: none;*/
/*  position: absolute;*/
/*  right: -92px;*/
/*  padding: 0;*/
/*  width: 200px;*/
/*  animation: myFade 1s;*/
/*  overflow: hidden;*/
/*}*/

.Span1 > li:hover {
  background-color: aqua;
}

.box-card {
  position: absolute;
  width: 200px;
  height: 400px;
  z-index: 999;
  left: -80px;
}

.CardData > div {
  margin: 10px 0 10px 0;
  width: 180px;
  text-align: center;
  user-select: none;
}

.CardData > div:hover {
  background-color: #667eea;
}
</style>

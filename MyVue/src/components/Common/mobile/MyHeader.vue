<template>
  <div class="myHeader">
    <div>
      <van-tag class="tags" type="primary">幻</van-tag>
      <van-tag class="tags" type="success">梦</van-tag>
      <van-tag class="tags" type="danger">博</van-tag>
      <van-tag class="tags" type="warning">客</van-tag>
    </div>
    <div class="myHeader2"><van-dropdown-menu class="mymenu" :overlay="false">
        <van-dropdown-item class="myitem" v-model="value1" :options="option1" />
      </van-dropdown-menu>
      <div>
        <li v-if="isLogin" @click="ToLogin">
          登陆/注册
        </li>
        <li v-if="!isLogin">
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
      </div>
      <van-action-sheet style="top: 0" v-model="drawer" title="全站搜索">
        <van-form >
          <van-search
              v-model="search"
              shape="round"
              background="#4fc08d"
              placeholder="请输入搜索关键词"
          />
          <div style="margin: 16px;">
            <van-button style="margin: auto;display: block;" type="primary" @click="submitForm()">搜寻</van-button>
          </div>
        </van-form>
      </van-action-sheet>

    </div>
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
      //vant
      value1: 7,
      option1: [
        { text: '首页', value: 0 },
        { text: '精华', value: 1 },
        { text: '分类', value: 2 },
        { text: '归档', value: 3 },
        { text: '友链', value: 4 },
        { text: '吐槽家族', value: 5 },
        { text: '全站搜索', value: 6 },
        { text: '更多', value: 7 },
      ],
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
    value1:{
      handler(newval){
        let name="";
        switch (newval) {
          case 0:
            name="index";
            break
          case 1:
            name="jh";
            break
          case 2:
            name="sort";
            break
          case 3:
            name="file";
            break
          case 4:
            name="link";
            break
          case 5:
            name="about";
            break
          case 6:
            this.drawer=true;
            break
        }
        if(name.length>0){
          this.$router.push({name: name})
        }
      }
    },
  },
  methods: {
    ToLogin(){
      this.$router.push({name: "login"})
    },
    submitForm() {
      if (this.search.length > 0) {
        this.$router.push("/Search/" + this.search)
        this.search=""
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
  font-family: cursive;
  background-image: linear-gradient(233deg, #00dbde 0%, #fc00ff 100%);
  width: 100%;
  transition: all .5s;
  height: 67px;
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


.Span1 > li:hover {
  background-color: aqua;
}

.box-card {
  position: absolute;
  width: 136px;
  height: 288px;
  z-index: 999;
  left: 253px;
  top: 50px;
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

.mymenu{
  z-index: 999;
  width: 82%;
  margin: 0 2rem 0 0;
}
.myHeader2{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}
.content {
  padding: 16px 16px 160px;
}
.myitem{
  width: 82%;
}
.myHeader>div{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}
.tags{
  width: 25%;
  height: 1.2rem;
  display: block;
  text-align: center;
  font-size: 16px;

}
.myHeader>div van-tag{
}

</style>

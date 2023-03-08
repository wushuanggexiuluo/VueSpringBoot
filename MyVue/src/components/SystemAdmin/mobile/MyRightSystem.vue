<template>
  <div class="leftCl">

    <div class="headerBox">
      <!--    面包屑-->
      <el-breadcrumb class="MBX" separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ name: 'admin' }">后台首页</el-breadcrumb-item>
        <el-breadcrumb-item v-if="this.RouterName>0" @click="getRouterByName(this.RouterName)">
          {{this.RouterName }}管理
        </el-breadcrumb-item>
      </el-breadcrumb>
      <!--    打开过的窗口-->
      <div class="tags">
        <el-tag v-for="(item,index) in openArrs"
                :key="index"
                closable
                @click="toWin=item"
                @close="handleClose(item)"
                :disable-transitions="false" type="success">{{ item }}
        </el-tag>
      </div>
      <!--    大标题-->
      <div class="titleCl">
        {{ item.length < 11 ? item : "后台管理" }}
        <div class="imgdata">
          <div v-if="isLogin" @click="toLogin" class="loginText">
            登陆
          </div>
          <div>
            <img v-if="!isLogin" src="../../../assets/img/admin/head_image.jpg" alt="NULL" @click="isVisCard=!isVisCard">
            <el-card v-if="!isLogin" v-show="isVisCard" class="box-card">
              <div class="detailData">
                <!--            名称-->
                <div class="myName">
                  name {{ name }}
                </div>
                <div class="CardData">
                  <div @click="CancelLogin">
                    退出登录
                  </div>
                </div>
              </div>
            </el-card>
          </div>
        </div>
      </div>
      <router-view :dealId="index"></router-view>
    </div>
  </div>

</template>

<script>
import ShowAdminMessage from "@/mixs/ShowAdminMessage";
import status from "@/utils/userStatus";
import userStatus from "@/utils/userStatus";
import {ApiOrigin} from "@/config/ApiOrigin";

export default {
  name: "MyRightSystem",
  data() {
    return {

      tabPosition: "right",
      index: "",
      nameKey: "",
      item: "",
      isVisCard: false,
      isLogin: false,
      name: "",
      RouterName: "",
      openArrs: [],
      //前往指定窗口
      toWin:"",
    }
  },
  mounted() {
    this.userStatusUpdate()
  },
  watch: {
    //监控前往哪里
    toWin:{
      immediate:true,
      handler(newval){
        this.getRouterByName(newval)
      }
    },
    //路由监听
    $route: {
      immediate: true,
      handler(newval) {
        const Name = newval.query.name;
        if (newval.name !== "admin")
          if (Name.length < 11) {
            //获取结果
            this.openArrs = JSON.parse(userStatus.getData("MyTags"))
            //新增标签
            if (this.openArrs === null) {
              this.openArrs = [Name]
            } else {
              let isC = false
              for (let i = 0; i < this.openArrs.length; i++) {
                if (this.openArrs[i] === Name) {
                  isC = true
                }
              }
              if (!isC) {
                this.openArrs.push(Name)
              }
            }
            //类型转换
            var s = JSON.stringify(this.openArrs);
            //持久化
            userStatus.setData("MyTags", s)
          }
        this.RouterName = Name
        this.dataFill(Name)
        this.name = userStatus.getData("user")
      }
    }
  },
  methods: {
    //关闭标签
    handleClose(newval){
      //获取结果
      this.openArrs = JSON.parse(userStatus.getData("MyTags"))
      var arrs = this.openArrs;
      for (let i = 0; i <arrs.length; i++) {
        if (arrs[i]===newval){
        this.openArrs.splice(i,1)
      }
      }
      //持久化
      userStatus.setData("MyTags",  JSON.stringify(this.openArrs))
    },
    dataFill(val) {
      this.item = val + "管理"
    },
    getRouterByName(val) {
      switch (val) {
        case "文章":
          this.$router.push({path: "/Admin/Article", query: {name: "文章"}})
          break
        case "评论":
          this.$router.push({path: "/Admin/Comment", query: {name: "评论"}})
          break
        case "留言":
          this.$router.push({path: "/Admin/Message", query: {name: "留言"}})
          break
        case "友链":
          this.$router.push({path: "/Admin/FriendLink", query: {name: "友链"}})
          break
        case "标签":
          this.$router.push({path: "/Admin/Tag", query: {name: "标签"}})
          break
        case "精华":
          this.$router.push({path: "/Admin/Boutique", query: {name: "精华"}})
          break
        case "归档":
          this.$router.push({path: "/Admin/TimelineFile", query: {name: "归档"}})
          break
      }
    },
    userStatusUpdate() {
      if (status.getUser("user") !== "admin") {
        this.isLogin = true;
        this.$router.push({name: "index"})
      } else {
        this.isLogin = false;

      }
    },
    toLogin() {
      this.$router.push({name: "login"})

    },
    CancelLogin() {
      let this_ = this;
      this.$http.get(ApiOrigin + "/Blog/CancelLogin").then((res) => {
        if (res.data.code === ShowAdminMessage.data().Cancel_OK) {
          status.removeUser("user");
          status.removeUser("token");
          this_.userStatusUpdate();
          ShowAdminMessage.methods.cancelLoginOk(this_)
        }
      })
    }
  }
}
</script>

<style scoped>
.tags {
  border-top: 2px solid #0053ff;
  margin: 5px;
}

.headerBox {
  background-image: linear-gradient(101deg, #96fbc4 0%, #f9f586 100%)
}

.MBX {
  padding: 15px;
}

.leftCl {
  z-index: 1;
  width: 100vw;
  background-image: linear-gradient(to top, #fbc2eb 0%, #a6c1ee 100%);
  overflow: auto;
}

.titleCl {
  border-top: 4px solid #0053ff;
  height: 73px;
  display: flex;
  color: black;
  font-family: cursive;
  align-items: center;
  font-size: 28px;
  justify-content: center;
}

.imgdata {
  right: 3rem;
  position: absolute;
  font-size: 20px;

}

.detailData {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 20px;
}

.imgdata img {
  width: 45px;
  height: 45px;
  border-radius: 45px;
  transition: all 2s;
}

.imgdata img:hover {
  transform: scale(1.25);
  cursor: pointer;
}

.myName {
  align-items: center;
  padding-bottom: 3px;
  border-bottom: 2px solid black;
  width: 180px;
  min-width: 180px;
  text-align: center;
  margin: 4px
}

.miandaoxiu {
  background-color: white;
  display: flex;
  min-width: 400px;
  width: 100vw;
  height: 5vh;
  align-items: center;
}

.box-card {
  position: absolute;
  width: 144px;
  height: 198px;
  z-index: 999;
  left: -53px;
}

.CardData {
  display: flex;
  flex-direction: column;
  align-items: center;
  user-select: none;
}

.CardData:hover {
  cursor: pointer;
  background-color: #2c3e50;
  color: whitesmoke;
  width: 144px;
}

.loginText:hover {
  cursor: pointer;
}
</style>

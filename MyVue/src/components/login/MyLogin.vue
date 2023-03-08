<template>
  <div>
    <img class="backImg1"/>
  <el-card class="loginCard">
    <div class="box1" id="show">

      <div class="container">
        <div class="inputdata">
          <label>用户名</label>
          <input v-model="userName" @change="onchange(1)" placeholder="你的名字" type="text" name="userName"
                 id="userNameid">
          <span class="tsdata">{{ namets }}</span>
        </div>
        <div class="inputdata">
          <label>&nbsp;密码&nbsp;</label>
          <input v-model="password" @change="onchange(2)" placeholder="你的密码" type="password" name="userName"
                 id="password">
          <span class="tsdata2">*未注册的用户会自动注册</span>
          <span class="tsdata">{{ passwordts }}</span>
        </div>
        <div class="inputdata">
          <button @click="login1">登陆</button>
        </div>
        <div class="text2" @click="login2">
          <label>暂不登陆随便逛逛</label>
          <div class="svgdiv">
            <svg ref="svg1" id="svg1" t="1666753922078" class="icon" viewBox="0 0 1024 1024" version="1.1"
                 xmlns="http://www.w3.org/2000/svg" p-id="2696" width="32" height="32">
              <path
                  d="M298.048 266.752l127.392 229.376a40 40 0 0 1 0 38.88l-127.28 228.768 430.304-248.48-430.4-248.544z m-70.016-27.216A32 32 0 0 1 272 196.288l504.448 291.296a32 32 0 0 1 0 55.424L272.048 834.272a32 32 0 0 1-43.968-43.264l153.248-275.456-153.296-276.016z"
                  p-id="2697" fill="#d81e06"></path>
            </svg>
          </div>
        </div>
      </div>
    </div>
  </el-card>
  </div>

</template>

<script>
import md5 from "js-md5"
import {ApiOrigin} from "@/config/ApiOrigin";

export default {
  name: "MyLogin",
  mounted() {
  },

  data() {
    return {
      userName: "",
      password: "",
      namets: "",
      passwordts: "",
    }
  },
  methods: {
    //登陆注册
    login1() {
      let this_ = this;
      if (this.onRegxeuserName(this.userName) && this.onRegxePassword(this.password)) {
        let data1 = {
          "userName": this.userName,
          "userPassword": md5(this.password),
        }

        this.$http.post(ApiOrigin+"/Blog/Login", data1, {withCredentials: true}).then((resp) => {
          let code = resp.data.code
          if (code === 20006) {
            //设置本地缓存，进行路由守卫
            localStorage.setItem("user", this.userName)
            this_.$router.push({name: "admin"})
            this.$message({
              type: "success",
              message: "欢迎您管理员！"
            })
          } else if (code === 20008) {
            //设置本地缓存，进行路由守卫
            localStorage.setItem("user", this.userName)

            this.$message({
              type: "success",
              message: `自动注册成功！自动登陆成功！欢迎${this.userName}大神！`
            })
          } else if (code === 20005) {
            //设置本地缓存，进行路由守卫
            localStorage.setItem("user", this.userName)

            this.$message({
              type: "success",
              message: `登陆成功！欢迎${this.userName}大神！`
            })
          } else {
            this.$message({
              type: "error",
              message: "密码错误，请大神再试试喵~"
            })
          }
        })
      } else {
        alert("请填写合法的数据！");
      }
    },
    login2() {
      this.namets = "";
      this.passwordts = "";
      location.href = "";
    },
    onchange(p) {
      if (p === 1) {
        if (!this.onRegxeuserName(this.userName)) {
          this.namets = "名字3~20位必须字母开头、可带数字、“_”、“.”的字串"
        } else {
          this.namets = "";
          try {
            let _this_ = this;
            let data = {userName: _this_.userName}
            this.$http.post(ApiOrigin+"/Blog/isExistUser", data).then((resp) => {
              let d = resp.data.code;
              if (d === 10006) {
                this.$message({
                  type: "success",
                  message: "此用户不存在，将自动为大佬注册喵~"
                })
                this.namets = "此用户不存在，将自动为大佬注册喵~";
              }
            })
          } catch (e) {
            alert("请求数据错误！");
          }
        }
      } else {
        if (!this.onRegxePassword(this.password)) {
          this.passwordts = "密码6~20位且必须只能包含数字，字母和下划线";
        } else {
          this.passwordts = ""
        }
      }
    },
    onRegxeuserName(str) {
      return /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){2,19}$/.exec(str);
    },
    onRegxePassword(str) {
      return /^(\w){6,20}$/.exec(str);
    },
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}

.loginCard {
  top: 20%;
  left: 30%;
  position: absolute;
  color: white;
  background-image: linear-gradient(to top, #09203f 0%, #537895 100%);
  width: 40rem;
  min-width: 40rem;
  min-height: 25rem;
  height: 50vh;
  margin: auto;
  display: flex;
  align-items: center;
  justify-content: center;
}
.container {
  display: flex;
  margin: auto;
  width: 30rem;
  horiz-align: center;
  vertical-align: middle;
  align-content: center;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: space-around;
}

.inputdata > label {
  display: block;
  margin: auto auto auto auto;
  width: 5rem;
  height: 2.5rem;
  font-size: 1.5rem;

}

.inputdata > input {
  width: 17rem;
  height: 1.7rem;
  border-left: 0.2rem solid #2196F3;
  background-color: #ddffff;
}

.inputdata > button {
  width: 7rem;
  height: 2.2rem;
  margin-right: auto;
  display: block;
  margin-left: auto;
  margin-top: 4rem;
  font-size: 1.2rem;
  background-color: cornflowerblue;
}

h5 {
  color: black;
}

.container2 {
  display: flex;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  margin: auto;
  /* margin: auto auto auto auto; */
  width: 24rem;
  height: 3.5rem;
  font-size: 1.5rem;
  flex-direction: row;
}

.text2 {
  height: 1.6rem;
  user-select: none;
}

.text2:hover {
  cursor: pointer;
}

.svgdiv {
  height: 2rem;
  width: 18rem;
}

.svgdiv:hover {
  cursor: pointer;
}

#svg1 {
  margin-left: 3.2rem;
  position: absolute;
}

.text2 > label {
  font-size: 1.2rem;
  display: block;
  margin-right: auto;
  width: 10rem;
  margin-left: auto;
}

.tsdata {
  position: absolute;
  width: 11rem;
  color: red;
  font-size: 0.8rem;
}

.tsdata2 {
  display: block;
  position: absolute;
  width: 13rem;
  color: red;
  font-size: 0.8rem;
}

@media screen and (min-height: 850px) and (min-width: 360px) {
  .box1 {
    width: 100%;
    height: 113rem;
  }
}
.backImg1 {
  z-index: -99;
  position: fixed;
  min-width: 800px;
  min-height: 800px;
  width: 100vw;
  height: 100% ;
  opacity: 1;
  background-image: linear-gradient(-20deg, #ddd6f3 0%, #faaca8 100%, #faaca8 100%);
}
</style>

<template>
  <div style=" background-image: linear-gradient(141deg, #fff1eb 0%, #ace0f9 100%);">
    <div class="myBack">
      <img alt="NULL" src="../../assets/img/index/login_back.jpg">
    </div>
    <div class="mylogin">
      <img alt="NULL" src="../../assets/img/index/logo.png">
      <div class="myLogin1">
        <div class="RorL">
          <div @click="isRegister=false">登陆</div>
          <div @click="isRegister=true">注册</div>
        </div>
        <div class="myinput">
          <input v-model="userName" type="text" required value=""/>
          <div class="mytext">用户名</div>
        </div>
        <div style="top: 80px;" class="myinput">
          <input v-model="password" type="password" required value=""/>
          <div class="mytext">密码</div>
        </div>
        <div v-if="isRegister" style="top: 131px;" class="myinput">
          <input v-model="password2" type="password" required value=""/>
          <div class="mytext">确认密码</div>
        </div>
        <div ref="ButG" class="myButton">
          <button @click="LorRclick">{{ isRegister === false ? ButText : "注册" }}</button>
          <button @click="clearAll">清空</button>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import {ApiOrigin} from "@/config/ApiOrigin";
import ShowAdminMessage from "@/mixs/ShowAdminMessage";
import showAdminMessage from "@/mixs/ShowAdminMessage";

export default {
  name: "MyLogins",
  data() {
    return {
      userName: "",
      password: "",
      password2: "",
      isRegister: false,
      ButText: "登陆",
    }
  },
  mounted() {
    // this.controllerButG()
  },
  watch:{
    isRegister:{
      // immediate:true,
      handler(newval){
        if (newval){
          this.$refs.ButG.style.top = "170px"
        }else {
          this.$refs.ButG.style.top = "120px"
        }
      }
    }
  },
  methods: {
    clearAll(){
      this.userName=""
      this.password=""
      this.password2=""
    },
    //校验函数
    RegexFun(){
      let nameB = this.onRegxeuserName(this.userName);
      let password1B = this.onRegxePassword(this.password);
      let x=true;
      let err=""
      if (!nameB){
        x=false
        err="名字3~20位必须字母开头、可带数字、“_”、“.”的字串"
      }
      if (!password1B){
        x=false
        err+="密码6~20位且必须只能包含数字，字母和下划线"
      }
      if (!x){
        this.$message.error(err)
      }
      return x;
      },
    //登陆注册
    LorRclick() {
      let this_ = this;
      if (this.RegexFun()) {
        //登陆操作
        if (this.isRegister !== true) {
          let data1 = {
            "userName": this.userName,
            "userPassword": this.password,
          }

          this.$http.post(ApiOrigin + "/Blog/Login", data1, {withCredentials: true}).then((resp) => {
            let code = resp.data.code
            //管理员
            if (code === ShowAdminMessage.data().PASSWORD_OK_ADMIN) {
              //设置本地缓存，进行路由守卫
              localStorage.setItem("user", this.userName)
              //设置Token
              localStorage.setItem("token", resp.data.data.token)
              //路由跳转
              this_.$router.push({name: "admin"})
              showAdminMessage.methods.loginOk(this_,this.userName)
            }
            //普通用户
            else if (code ===  ShowAdminMessage.data().PASSWORD_OK) {
              //设置本地缓存，进行路由守卫
              localStorage.setItem("user", this.userName)
              //设置Token
              localStorage.setItem("token", resp.data.data.token)
              showAdminMessage.methods.loginOk(this_,this.userName)
              //路由跳转
              this_.$router.push({name: "index"})
            }
            else if(code===showAdminMessage.data().USER_ERR)
            {
              showAdminMessage.methods.userErr(this_)
            }//密码错误
            else {
              showAdminMessage.methods.passwordErr1(this_)
            }
          }).catch((e)=>{
            console.log(e)
            showAdminMessage.methods.passwordErr1(this_)
          })

        }
        //注册操作
        else {
          if (this.password === this.password2) {
            let data1 = {
              "userName": this.userName,
              "userPassword": this.password,
              "userPassword2": this.password2
            }
            this.$http.post(ApiOrigin + "/Blog/Register", data1, {withCredentials: true}).then((resp) => {
              console.log(resp)
              if (resp.data.code === ShowAdminMessage.data().REGISTER_OK) {
                showAdminMessage.methods.registerOK(this_, this.userName)
                this_.isRegister=false
              }
              else {
                showAdminMessage.methods.registerErr(this_)
              }
            })

          } else {
            showAdminMessage.methods.passwordErr2(this_)
          }
        }
      }
    }
    ,
    login2() {
      this.namets = "";
      this.passwordts = "";
      location.href = "";
    }
    ,
    onchange(p) {
      if (p === 1) {
        if (!this.onRegxeuserName(this.userName)) {
          this.namets = "名字3~20位必须字母开头、可带数字、“_”、“.”的字串"
        } else {
          this.namets = "";
          try {
            let _this_ = this;
            let data = {userName: _this_.userName}
            this.$http.post(ApiOrigin + "/Blog/isExistUser", data).then((resp) => {
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
    }
    ,
    onRegxeuserName(str) {
      return /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){2,19}$/.exec(str);
    }
    ,
    onRegxePassword(str) {
      return /^(\w){6,20}$/.exec(str);
    }
  }
}
</script>

<style scoped>
.myBack > img {
  width: 100rem;
  height: 58rem;
  position: fixed;
}

.mylogin {
  border-radius: 14px;
  background-image: linear-gradient(302deg, #6d54b2 0%, #7ec0ff 48%, #cc1ec0 100%);
  position: absolute;
  width: 400px;
  right: 0;
  height: 100%;
}

.myLogin1 {
  margin: 6px;
  border-radius: 14px;
  top: 37%;
  height: 366px;
  transform: translate(0, -41%);
  background-image: linear-gradient(141deg, #fff1eb 0%, #ace0f9 100%);
  position: relative;
  text-align: center;
}

.mytext {
  position: relative;
  pointer-events: none;
  top: -25px;
  transition: 0.3s;
  color: gray;
  font-family: cursive;
  font-size: 19px;
}

.myinput {
  z-index: -3;
  position: absolute;
  right: 14%;
  margin: 30px auto;
  box-shadow: 0 0 white;
}

.myinput > input {
  height: 24px;
  width: 251px;
  outline: none;
  transition: 0.3s;
  border: 1px solid #aa63dc;
  box-shadow: 1px 2px #ad53ff;
}

.myinput > input:valid,
.myinput > input:focus {
  /*border-color: #1dc797;*/
  box-shadow: 2px 3px #1dc797;
  border: 2px solid #8ec5fc;
}

.myinput > input:valid + .mytext,
.myinput > input:focus + .mytext {
  top: -43px;
  font-size: 1px;
  color: green;
}

.myButton {
  position: relative;
  top: 120px;
}

.myButton button {
  width: 83px;
  height: 27px;
  margin: 15px;
  background-image: linear-gradient(149deg, #a18cd1 0%, #fbc2eb 100%);
  transition: 0.3s;
  border: 1px darkmagenta;
  font-family: cursive;
  font-size: 17px;
}

.RorL {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: row;
  user-select: none;

}

.RorL > div:first-child {
  min-width: 60px;
  background-image: linear-gradient(90deg, #fff1eb 0%, #ace0f9 100%);
  width: 64px;
  height: 24px;
  margin: 5px 10px 0px 10px;
  text-shadow: 1px 1px #8fd4b9;
  transition: 0.3s;
}

.RorL > div:last-child {
  min-width: 60px;
  background-image: linear-gradient(270deg, #fff1eb 0%, #ace0f9 100%);
  width: 64px;
  height: 24px;
  margin: 5px 10px 0px 10px;
  text-shadow: 1px 1px #8fd4b9;
}

.RorL > div:first-child:hover {
  background-image: linear-gradient(270deg, #fff1eb 0%, #ace0f9 100%);
  text-shadow: 2px 2px #ffd0d0;
  color: #5c2223;
}

.RorL > div:last-child:hover {
  background-image: linear-gradient(90deg, #fff1eb 0%, #ace0f9 100%);
  text-shadow: 2px 2px #ffd0d0;
  color: #5c2223;
}

.myButton button:hover {
  box-shadow: 2px 2px salmon;
  background-image: linear-gradient(to top, #fbc2eb 0%, #a6c1ee 100%);
  color: white;
  text-shadow: 0 1px 1px yellow;
}

</style>

<template>
<!--  <div class="body">-->
    <div class="container">
      <div class="panda">
        <div class="ear left"></div>
        <div class="ear right"></div>
        <div class="face">
          <div class="eye-shadow left"></div>
          <div class="eye-white left">
            <div class="eye-ball"></div>
          </div>
          <div class="eye-shadow right"></div>
          <div class="eye-white right">
            <div class="eye-ball"></div>
          </div>
          <div class="nose"></div>
          <div class="mouth"></div>
        </div>
        <div class="body"></div>
        <div class="foot left">
          <div class="sole"></div>
        </div>
        <div class="foot right">
          <div class="sole"></div>
        </div>
      </div>
      <div class="login-box">
        <div class="hand left"></div>
        <div class="hand right"></div>
        <h1>用户登录</h1>
        <div class="ipt-box">
          <input type="text" required>
          <label>用户名</label>
        </div>
        <div class="ipt-box">
          <input type="password" id="password" required>
          <label>密码</label>
        </div>
        <button>登录</button>
      </div>
    </div>
<!--  </div>-->
</template>

<script>
export default {
  name: "OurLogin"
}
</script>

<style scoped>
* {
  /* 初始化 */
  margin: 0;
  padding: 0;
}


.body {
  margin: auto;
  /* 初始化 取消内外边距 */
  padding: 0;
  /* 100%窗口高度 */
  height: 100vh;
  /* 弹性布局 水平垂直居中 */
  display: flex;
  justify-content: center;
  align-items: center;
  /* 渐变背景 */
  background: linear-gradient(125deg, #2c3e50, #27ae60, #2980b9, #e74c3c, #8e44ad);
  /* 指定背景图像的大小 */
  background-size: 500%;
  /* 执行动画：动画名 时长 线性的 无限次播放 */
  animation: bgAnimation 15s linear infinite;
}

/* 开始画熊猫 */
.panda {
  /* 相对定位 */
  position: relative;
  width: 200px;
}

/* 脸部 */
.face {
  width: 200px;
  height: 200px;
  background-color: #fff;
  border-radius: 100%;
  box-shadow: 0 10px 15px rgba(0, 0, 0, 0.15);
  position: relative;
  z-index: 1;
}

/* 耳朵 */
.ear {
  width: 70px;
  height: 70px;
  background-color: #000;
  border-radius: 100%;
  position: absolute;
  top: -10px;
}

.ear.right {
  right: 0;
}

/* 黑眼圈 */
.eye-shadow {
  width: 50px;
  height: 80px;
  background-color: #000;
  border-radius: 50%;
  /* 绝对定位 */
  position: absolute;
  top: 40px;
}

.eye-shadow.left {
  transform: rotate(45deg);
  left: 30px;
}

.eye-shadow.right {
  transform: rotate(-45deg);
  right: 30px;
}

/* 眼白 */
.eye-white {
  width: 30px;
  height: 30px;
  background-color: #fff;
  border-radius: 100%;
  position: absolute;
  top: 68px;
}

.eye-white.left {
  left: 38px;
}

.eye-white.right {
  right: 38px;
}

/* 眼球 */
.eye-ball {
  width: 20px;
  height: 20px;
  background-color: #000;
  border-radius: 100%;
  position: absolute;
  left: 5px;
  top: 5px;
}

/* 鼻子 */
.nose {
  width: 35px;
  height: 20px;
  background-color: #000;
  position: absolute;
  left: 0;
  right: 0;
  margin: auto;
  bottom: 65px;
  border-radius: 42px 42px 60px 60px / 30px 30px 60px 60px;
}

/* 嘴巴 */
.mouth {
  width: 68px;
  height: 25px;
  border-bottom: 7px solid #000;
  border-radius: 50%;
  position: absolute;
  left: 0;
  right: 0;
  margin: auto;
  bottom: 35px;
}

/* 身体 */
.body {
  width: 250px;
  height: 280px;
  background-color: #fff;
  position: relative;
  left: -25px;
  top: -10px;
  border-radius: 100px 100px 100px 100px / 126px 126px 96px 96px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
}

/* 脚 */
.foot {
  width: 82px;
  height: 120px;
  background-color: #000;
  position: absolute;
  bottom: 8px;
  z-index: 3;
  border-radius: 40px 40px 35px 40px / 26px 26px 63px 63px;
  box-shadow: 0 5px 5px rgba(0, 0, 0, 0.2);
}

.foot.left {
  left: -80px;
}

.foot.right {
  right: -80px;
  transform: rotateY(180deg);
}

/* 脚掌-大椭圆 */
.foot::after {
  content: "";
  width: 55px;
  height: 65px;
  background-color: #222;
  position: absolute;
  border-radius: 50%;
  left: 0;
  right: 0;
  margin: auto;
  bottom: 10px;
}

/* 脚掌-三个小椭圆 */
.foot .sole,
.foot .sole::before,
.foot .sole::after {
  width: 20px;
  height: 30px;
  background-color: #222;
  position: absolute;
  border-radius: 50%;
  left: 0;
  right: 0;
  margin: auto;
  top: 8px;
}

.foot .sole::before {
  content: "";
  left: -50px;
}

.foot .sole::after {
  content: "";
  left: 25px;
}

/* 手 */
.hand,
.hand::before,
.hand::after {
  width: 40px;
  height: 30px;
  background-color: #000;
  border-radius: 50px;
  position: absolute;
  top: 70px;
  left: -20px;
}

.hand::before {
  content: "";
  top: 16px;
  left: 0;
}

.hand::after {
  content: "";
  top: 32px;
  left: 0;
}

.hand.right {
  right: -20px;
  left: auto;
}

/* 登录框 */
.login-box {
  width: 400px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -110px);
  z-index: 2;
  /* 弹性布局 居中 垂直排列 */
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  /* 设置过渡 */
  transition: 0.3s;
}

h1 {
  color: #1dc797;
  font-weight: normal;
  margin-bottom: 5px;
}

.ipt-box {
  width: 80%;
  margin-top: 25px;
  position: relative;
}

.ipt-box input {
  width: 100%;
  height: 35px;
  border: none;
  border-bottom: 1px solid #bbb;
  text-indent: 5px;
  outline: none;
  transition: 0.3s;
}

.ipt-box label {
  position: absolute;
  left: 5px;
  top: 5px;
  font-size: 14px;
  color: #888;
  transition: 0.3s;
  pointer-events: none;
}

/* 输入框选中或有值时输入框的样式 */
.ipt-box input:focus,
.ipt-box input:valid {
  border-color: #1dc797;
  box-shadow: 0 1px #1dc797;
}

/* 输入框选中或有值时label的样式 */
.ipt-box input:focus ~ label,
.ipt-box input:valid ~ label {
  color: #1dc797;
  font-size: 12px;
  transform: translateY(-15px);
}

button {
  width: 150px;
  height: 40px;
  background-color: #1dc797;
  border: none;
  border-radius: 3px;
  margin-top: 30px;
  color: #fff;
  letter-spacing: 10px;
  text-indent: 10px;
  cursor: pointer;
  transition: 0.3s;
}

button:hover {
  letter-spacing: 25px;
  text-indent: 25px;
  background-color: #2fa080;
}

/* 登录框向上举 */
.up {
  transform: translate(-50%, -180px);
}

@keyframes bgAnimation {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
</style>

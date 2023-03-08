<template>
  <div class="myinput">
    <div class="mytitle">
      <span>博客</span>
    </div>
    <el-menu default-active="1-1-1" class="el-menu-vertical-demo" >
      <el-menu-item index="1">
        <i class="el-icon-menu"></i>
        <span slot="title">控制台</span>
      </el-menu-item>

      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span slot="title">网站管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item @click="manageIndex=21" index="2-1">文章管理</el-menu-item>
          <el-menu-item @click="manageIndex=22" index="2-2">评论管理</el-menu-item>
          <el-menu-item @click="manageIndex=23" index="2-3">留言管理</el-menu-item>
          <el-menu-item @click="manageIndex=24" index="2-4">友链管理</el-menu-item>
          <el-menu-item @click="manageIndex=25" index="2-5">标签管理</el-menu-item>
          <el-menu-item @click="manageIndex=26" index="2-6">精华管理</el-menu-item>
          <el-menu-item @click="manageIndex=27" index="2-7">归档管理</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span slot="title">用户管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="3-1">用户管理</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="4">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span slot="title">系统管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="4-1">背景管理</el-menu-item>
          <el-menu-item index="4-2">异常管理</el-menu-item>
          <el-menu-item index="4-3">动画管理</el-menu-item>
          <el-menu-item index="4-3">音乐管理</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>

export default {
  name: "MyLeftSystem",
  data() {
    return {
      ruleForm: [],
      manageIndex: 0,
    }
  },
  watch: {
    manageIndex: {
      immediate: true,
      handler(newval) {

        this.routeSkip(newval)
      }
    }
  },
  methods: {
    routeSkip(val) {
      switch (val) {
        case 21:
          this.$router.push({path: "/Admin/Article", query: {name: "文章"}})
          break
        case 22:
          this.$router.push({path: "/Admin/Comment", query: {name: "评论"}})
          break
        case 23:
          this.$router.push({path: "/Admin/Message", query: {name: "留言"}})
          break
        case 24:
          this.$router.push({path: "/Admin/FriendLink", query: {name: "友链"}})
          break
        case 25:
          this.$router.push({path: "/Admin/Tag", query: {name: "标签"}})
          break
        case 26:
          this.$router.push({path: "/Admin/Boutique", query: {name: "精华"}})
          break
        case 27:
          this.$router.push({path: "/Admin/TimelineFile", query: {name: "归档"}})
          break
      }
    },
  }
}
</script>

<style scoped>
* {
  color: white;
}

.el-menu-item-group {
  background-image: linear-gradient(95deg, #f5f7fa 0%, #c3cfe2 100%);
}

.mytitle {
  background-image: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  min-width: 150px;
  display: flex;
  flex-direction: column;
  align-content: center;
  align-items: center;
}

.mytitle > span {
  display: flex;
  color: white;
  margin: 15px;
  font-size: 0.8rem;
}

.el-menu-vertical-demo {
  min-height: 120vh;
  min-width: 150px;
  background-image: linear-gradient(0deg, #667eea 0%, #764ba2 100%);
}

.el-submenu .el-menu-item {
  height: 50px;
  line-height: 50px;
  padding: 0 45px;
  min-width: 150px;
}

.but1 {
  width: 5rem;
}

.el-menu-item:focus, .el-menu-item:hover {
  background-color: unset;
  background-image: linear-gradient(182deg, #667eea 0%, #764ba2 100%);

}

</style>

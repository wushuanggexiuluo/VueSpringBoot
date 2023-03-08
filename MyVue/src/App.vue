<template>
  <div class="myapp">
<!--    <img class="backImg" style="" alt="">-->
    <router-view  :key="$route.path"></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  methods: {},
}
</script>
<style>
body {
  margin: 0;
  cursor:url("./assets/img/common/mouse_ico.png"),auto;
}

* {
  list-style: none;

}
.myapp{
  background-image: linear-gradient(to top, #9795f0 0%, #fbc8d4 100%);
}
.backImg {
  /*z-index: -999;*/
  /*opacity: 0.3;*/
  /*position: fixed;*/
  /*min-width: 800px;*/
  /*min-height: 800px;*/
  /*width: 100vw;*/
  /*height: 100% ;*/
  /*background-image: url("./assets/img/other/1161.png")*/
}
.ql-editor{
  height: 140px;
}
.el-tabs__nav-scroll{
  background-color: #8ec5fc;
}
.v-modal{
  display: unset;
  z-index: 0;
  overflow: hidden;

}
.el-dialog{
  width: 100%;
}
.el-dialog__wrapper{
  z-index: 0;
  display: unset;
  overflow: hidden;
}
/*全局修改admin更新新增页*/
.el-dialog11 .el-dialog__body {
  background-image: linear-gradient(to right, #4facfe 0%, #00f2fe 100%);
  height: 127vh;
}
.el-dialog11 .el-dialog__header{
  background-image: linear-gradient(20deg, #30cfd0 0%, #330867 100%);
  display: flex;
  align-items: center;
  justify-content: center;
}
.el-dialog11 .el-dialog__title{
  color: white;
}
/*element表格*/
.cell{
  font-size: 10px;
}
.el-table td.el-table__cell div {
  max-height: 150px;
}
.el-table .cell {
  max-height: 100px;
}
/*element面包屑*/
.el-breadcrumb__item:last-child .el-breadcrumb__inner, .el-breadcrumb__item:last-child .el-breadcrumb__inner a, .el-breadcrumb__item:last-child .el-breadcrumb__inner a:hover, .el-breadcrumb__item:last-child .el-breadcrumb__inner:hover{
  color: white;
}
.el-breadcrumb__separator[class*=icon]{
  color: #0c2a70;
}

.is-fullscree
{background-image: linear-gradient(88deg, #88d3ce 0%, #6e45e2 100%);
}

/*markdown显示*/
.v-note-wrapper.shadow{
  z-index: 999;
}
</style>

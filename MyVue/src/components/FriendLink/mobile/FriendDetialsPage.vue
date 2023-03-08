<template>
  <div>

    <MyHeader></MyHeader>

    <div class="MyLink">
      <h1>小伙伴</h1>
      <div style="display: flex;flex-direction: column;">
        <svg t="1669604868548" class="icon1" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
             p-id="2741" width="16" height="16">
          <path
              d="M882.607 620.29c-32.233-46.502-75.779-81.467-126.4-101.612 22.324-15.705 41.105-36.646 54.68-61.068 15.712-28.268 24.018-60.572 24.018-93.42 0-102.398-79.285-185.705-176.74-185.705-13.838 0-25.097 11.258-25.097 25.098 0 13.855 11.26 25.128 25.097 25.128 69.743 0 126.483 60.775 126.483 135.479 0 74.72-56.74 135.507-126.483 135.507-13.838 0-25.097 11.265-25.097 25.113v0.136c0 13.855 11.26 25.128 25.097 25.128 125.464 0 227.537 108.816 227.537 242.569 0 13.873 11.285 25.159 25.158 25.159 13.838 0 25.097-11.286 25.097-25.16 0.001-62.398-18.448-121.998-53.35-172.353z"
              fill="" p-id="2742"></path>
          <path
              d="M623.157 577.674c-26.348-18.662-55-33.4-85.316-43.896 64.02-38.906 103.316-108.15 103.316-183.412 0-118.333-96.263-214.605-214.588-214.605-118.359 0-214.65 96.272-214.65 214.605 0 40.29 11.283 79.585 32.63 113.642a215.99 215.99 0 0 0 71.568 70.318c-30.826 10.524-59.95 25.425-86.697 44.37a343.098 343.098 0 0 0-76.195 74.126c-44.566 59.376-68.122 130.018-68.122 204.29 0 13.838 11.258 25.098 25.097 25.098 13.856 0 25.129-11.26 25.129-25.098 0-77.456 30.203-150.434 85.044-205.489 54.71-54.925 127.125-85.207 203.921-85.283a24.91 24.91 0 0 0 7.632-0.516l1.991-0.446c74.72 1.513 144.954 31.659 198.33 85.262 54.815 55.05 85.003 128.013 85.003 205.448 0 13.838 11.273 25.097 25.128 25.097 13.839 0 25.098-11.26 25.098-25.097 0-74.272-23.556-144.914-68.121-204.29a343.214 343.214 0 0 0-76.198-74.124zM426.57 186.002c90.629 0 164.362 73.734 164.362 164.364 0 90.638-73.733 164.379-164.362 164.379-90.648 0-164.393-73.741-164.393-164.38-0.001-90.63 73.745-164.363 164.393-164.363z"
              fill="" p-id="2743"></path>
        </svg>

        <!--      添加-->
        <svg @click="addLink" t="1669606779830" class="icon2" viewBox="0 0 1024 1024" version="1.1"
             xmlns="http://www.w3.org/2000/svg"
             p-id="3692" width="16" height="16">
          <path
              d="M572.282915 587.416406A293.701506 293.701506 0 0 1 364.586038 86.028148a293.709605 293.709605 0 0 1 415.373506 415.363381 291.777918 291.777918 0 0 1-207.676629 86.024877z m0-523.195671a229.494165 229.494165 0 0 0-162.269829 391.804491 229.502265 229.502265 0 0 0 324.539658-324.590279 227.975543 227.975543 0 0 0-162.269829-67.214212z"
              fill="#333333" p-id="3693"></path>
          <path
              d="M139.617367 1024H75.399902a499.029333 499.029333 0 0 1 499.424174-499.424174v64.217464a434.811868 434.811868 0 0 0-435.206709 435.20671z"
              fill="#333333" p-id="3694"></path>
          <path
              d="M540.158997 773.96394m10.124147 0l388.19017 0q10.124147 0 10.124147 10.124147l0 44.546247q0 10.124147-10.124147 10.124147l-388.19017 0q-10.124147 0-10.124147-10.124147l0-44.546247q0-10.124147 10.124147-10.124147Z"
              fill="#333333" p-id="3695"></path>
          <path
              d="M711.986021 602.136916m10.124147 0l44.546247 0q10.124147 0 10.124147 10.124147l0 388.190171q0 10.124147-10.124147 10.124147l-44.546247 0q-10.124147 0-10.124147-10.124147l0-388.190171q0-10.124147 10.124147-10.124147Z"
              fill="#333333" p-id="3696"></path>
        </svg>
      </div>
      <LinkHead></LinkHead>
      <LinkCenter :tableData="tableData"></LinkCenter>
      <el-dialog
          title="新增友链"
          :visible.sync="dialogVisibleAdd"
          width="100%">
        <LinkBottom></LinkBottom>
      </el-dialog>

    </div>
  </div>
</template>

<script>
import MyHeader from "@/components/Common/mobile/MyHeader";
import LinkBottom from "@/components/FriendLink/mobile/LinkBottom";
import LinkCenter from "@/components/FriendLink/mobile/LinkCenter";
import LinkHead from "@/components/FriendLink/mobile/LinkHead";
import {crud_R} from "@/mixs/CRUD";
import {ApiOrigin} from "@/config/ApiOrigin";

export default {
  name: "FriendDetialsPage",
  components: {LinkHead, LinkCenter, MyHeader, LinkBottom},
  created() {
    this.getAllLink()
  },
  methods: {
    //前往目标链接
    toLinkCli(e) {
      window.location.href = "http://" + e
    },
    getAllLink() {
      crud_R.selectAll(this, ApiOrigin+"/Blog/getAllLink")
    },
    addLink() {
      this.dialogVisibleAdd = true;
    },
  },
  data() {
    return {
      labelPosition: 'top',
      formLabelAlign: {},
      dialogVisibleAdd: false,
      tableData: []
    }
  }
}
</script>

<style scoped>
.MyLink {
  display: flex;
width: 100%;
  background-image: linear-gradient(-225deg, #5D9FFF 0%, #B8DCFF 48%, #6BBBFF 100%);
  flex-direction: column;
  justify-content: center;
  align-items: center;;
}

.el-dialog {
  min-width: 100%;
}

.showLink img {
  min-width: 66px;
  min-height: 66px;
  width: 67px;
  height: 66px;
  border-radius: 66px;
  margin: auto;
}

.icon1 {
  margin: 10px;
}

.icon1:hover {
  scale: 2;
  cursor: pointer;
}

.icon2 {

  margin: 10px;
}

.icon2:hover {
  scale: 2;
  cursor: pointer;
}

.showLink > div {
  background-color: cornsilk;
  margin: 10px;
  user-select: none;
  max-width: 500px;
  flex-direction: column;
  min-width: 500px;
}

.showLink > div:hover {
  cursor: pointer;
}
</style>

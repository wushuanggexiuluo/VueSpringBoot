<template>
  <div class="pageDataCls">
    <div>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="搜内容">
          <el-input v-model="search.content" placeholder="随便输入"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="搜标题">
          <el-input v-model="search.title" placeholder="随便输入"></el-input>
        </el-form-item>
      </el-form>

    </div>

    <div style="display: flex;">
      <el-button type="primary" @click="onSubmit">查询</el-button>
      <el-button type="success" @click="dialogVisibleAdd=true" plain>新增</el-button>
      <el-button type="danger" @click="manyRemoveUser" plain>批量删除</el-button>
    </div>
    <template>
      <el-table
          ref="clearCheckBox"
          :data="tableData"
          style="width: 100%"
          :row-class-name="tableRowClassName"
          @selection-change="handleSelectionChange"
      >

        <el-table-column
            type="selection"
            width="50">
        </el-table-column>
        <el-table-column
            label="序号"
            type="index"
            width="48">
        </el-table-column>
        <el-table-column
            align="center"
            prop="articleId"
            label="文章ID">
        </el-table-column>

        <el-table-column
            align="center"
            prop="articleUserId"
            label="用户ID">
        </el-table-column>
        <el-table-column
            align="center"
            prop="articleTitle"
            label="文章标题">
        </el-table-column>

        <el-table-column
            align="center"
            prop="articleViewCount"
            sortable
            label="浏览量">
        </el-table-column>
        <el-table-column
            align="center"
            prop="articleCommentCount"
            sortable
            label="评论量">
        </el-table-column>
        <el-table-column
            align="center"
            prop="articleDianzanCount"
            sortable
            label="点赞量">
        </el-table-column>
        <el-table-column
            align="center"
            prop="articleUpdateTime"
            label="更新时间"
            sortable
        >
        </el-table-column>
        <el-table-column
            align="center"
            prop="articleCreateTime"
            label="创建时间"
            sortable
        >
        </el-table-column>
        <el-table-column
            align="center"
            prop=""
            label="摘要">
        </el-table-column>
        <el-table-column
            align="center"
            prop="articleThImg"
            label="封面地址">
          <template slot-scope="scope">
            <el-popover trigger="hover" placement="top">
              <p>{{ scope.row.articleThImg }}</p>
              <div slot="reference" class="name-wrapper">
                <img width="90" :src="scope.row.articleThImg">
<!--                {{ scope.row.articleThImg }}-->
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            prop="operation"
              label="操作"   fixed="right">
          <template slot-scope="scope">
            <el-button type="primary" size="small" @click="editUser(scope.row)">修改</el-button>
            <br>
            <br>
            <el-button type="danger" size="small" @click="removeUser(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>

    <!--分页工具条-->
    <el-pagination
        small
        :pager-count="1"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10,15,20]"
        :page-size="pageSize"
        layout="total, prev, pager, next, jumper"
        :total="totalCount">
    </el-pagination>

    <!--新增-->
    <el-dialog
        class="el-dialog11"
        :fullscreen="true"
        :modal="false"
        title="新增文章"
        :visible.sync="dialogVisibleAdd"
        width="30%"
        :before-close="handleClose">
      <MyMarkDown MyMarkDownData="hello" @formSubmit="translateText">
      </MyMarkDown>


      <el-form style="margin: 10px auto " label-width="80px" :model="addForm">

        <el-form-item label="标题">
          <el-input v-model="addForm.articleTitle"></el-input>
        </el-form-item>
        <el-form-item label="摘要">
          <el-input v-model="addForm.articleSummary"></el-input>
        </el-form-item>
        <div style="display: flex;align-items: center;justify-content: space-between">
          <el-form-item style="background-color: wheat;height: 200px;" label="文章便签">
            <ArticleTags :isEdit="true"
                         :categoryContent="addForm.category"></ArticleTags>
          </el-form-item>
          <img alt="NULL" style="width: 100px;height: 100px;" :src=addForm.articleThImg>
          <el-upload
              action=""
              list-type="picture-card"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
            <div>点击上传文章封面</div>
          </el-upload>
        </div>

        <div style="display: flex;justify-content: space-between">
          <el-button type="success" @click="SubmitCli" round>提交</el-button>
          <el-button type="info" @click="ZanCunCli" round>暂存</el-button>
          <el-button type="danger" @click="QingKongCli" round>清空</el-button>
        </div>
      </el-form>


    </el-dialog>

    <!--修改-->
    <el-dialog
        class="el-dialog11"
        :fullscreen="true"
        :modal="false"
        title="修改文章"
        :visible.sync="dialogVisibleUpdate"
        width="30%"
        :before-close="handleClose">
      <MyMarkDown @formSubmit="translateText" :MyMarkDownData="this.updateForm.articleContent">
      </MyMarkDown>
      <el-form class="EditForm" label-width="80px" :model="updateForm">
        <el-form-item v-show="false" label="标题">
          <el-input v-model="updateForm.articleContent"></el-input>
        </el-form-item>
        <el-form-item label="标题">
          <el-input v-model="updateForm.articleTitle"></el-input>
        </el-form-item>
        <el-form-item label="摘要">
          <el-input v-model="updateForm.articleSummary"></el-input>
        </el-form-item>
        <div style="">

          <el-form-item style="background-color: wheat;height: 200px;    /* height: 200px; */
    display: flex;
    flex-direction: column;" label="文章标签">

            <ArticleTags  :isEdit="true" :getNewTag="getNewTag"
                         :categoryContent="updateForm.category"></ArticleTags>
          </el-form-item>
          <div class="imggroup">
            <img alt="请上传图片" style="width: 148px;height: 148px;" :src="updateForm.articleThImg">
            <el-upload
                action=""
                list-type="picture-card"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload">
              <div>点击上传文章封面</div>
            </el-upload>
          </div>

        </div>

        <div style="display: flex;justify-content: space-between">
          <el-button type="success" @click="UpadetCli" round>提交</el-button>
          <el-button type="danger" @click="dialogVisibleUpdate=false" round>返回</el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>

</template>
<script>
import MyMarkDown from "@/components/SystemAdmin/mobile/MyMarkDown";
import ArticleTags from "@/components/SystemAdmin/mobile/ManageFact/ArticleTags";
import {crud_D, crud_R, crud_U} from "@/mixs/CRUD";
import showAdminMessage from "@/mixs/ShowAdminMessage";
import ShowAdminMessage from "@/mixs/ShowAdminMessage";
import userStatus from "@/utils/userStatus";
import {ApiOrigin} from "@/config/ApiOrigin";
import {uploadFile_User} from "@/api/UserApi";

export default {
  name: "ArticleFact",
  components: {ArticleTags, MyMarkDown},
  props: ["dealId"],
  mounted() {
    this.selectAll()
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    async beforeAvatarUpload(file) {
      let this_ = this;
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

        this_.file = file
        let data = {"file": file}
        //异步任务
        await uploadFile_User(data).then((resp) => {
          this.$set(this.addForm,"articleThImg",resp.data.data)
          this.$set(this.updateForm,"articleThImg",resp.data.data)
        })
      }
      return errms.length <= 0;
    },
    ZanCunCli() {
    },
    QingKongCli() {
      this.addForm = {}
      this.updateForm = {}
    },
    getNewTag(val) {
      this.updateForm.category = val;
    },
    //获取所有标签

    //更新
    UpadetCli() {
      let _this = this;
      _this.updateForm.category = JSON.parse(userStatus.getData("tag"))
      userStatus.removeUser("tag")
      _this.updateForm.oldarticleTitle = this.oldarticleTitle
      crud_U.updateData(this, _this.updateForm, ApiOrigin + "/Admin/updateArticles")
      this.dialogVisibleUpdate = false;
      this.QingKongCli()
    },
    //新增
    SubmitCli() {
      let _this = this;
      _this.addForm.category = JSON.parse(userStatus.getData("tag"))
      this.$http.post(ApiOrigin + "/Admin/addArticles", _this.addForm).then((resp) => {
        if (resp.data.code === ShowAdminMessage.data().SAVE_OK) {
          _this.selectAll()
          userStatus.removeUser("tag")
          userStatus.removeUser("tag")
          showAdminMessage.methods.AddOk(_this)
        } else {
          showAdminMessage.methods.AddErr(_this, resp.data.message)
        }
      })
    },
    translateText(x) {
      this.addForm.articleContent = x
      this.updateForm.articleContent = x
    },
    //每页显示条数发生变化
    handleSizeChange(val) {

      this.pageSize = val;
      this.selectAll();
    },
    //当前页发生变化
    handleCurrentChange(val) {
      this.currentPage = val;
      this.selectAll();
    },
    //复选框

    //查询所有
    /**
     * 分页
     */
    selectAll() {
      let data = {
        currPage: this.currentPage,
        pageSize: this.pageSize,
        search: this.search
      }
      crud_R.selectPage(this, data, ApiOrigin + "/Admin/getArticles")
    },

    //点击查询按钮
    onSubmit() {
      this.selectAll();
    },
    handleClose() {
      this.dialogVisibleAdd = false;
      this.dialogVisibleUpdate = false;
    },

    tableRowClassName({row, rowIndex}) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';
    },
    //点击修改  用scope.row获取并回显数据
    editUser(row) {
      //弹出编辑窗口

      this.dialogVisibleUpdate = true;
      this.updateForm = row
      //保存原来的标题
      this.oldarticleTitle = this.updateForm.articleTitle
    },
    //删除
    removeUser(row) {
      let arrIDs = [row.articleId];
      crud_D.removeData(this, arrIDs, ApiOrigin + "/Admin/removeArticles")
      this.selectAll();
    },

    //check取消选中状态
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.clearCheckBox.toggleRowSelection(row);
        });
      } else {
        this.$refs.clearCheckBox.clearSelection();
      }
    },
    //批量删除
    manyRemoveUser() {
      let selectedIds = []

      for (let i = 0; i < this.multipleSelection.length; i++) {
        selectedIds.push(this.multipleSelection[i].articleId)
      }
      if (selectedIds.length > 0) {
        crud_D.removeData(this, selectedIds, ApiOrigin + "/Admin/removeArticles")
      } else {
        showAdminMessage.methods.IllegalDelete(this)
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

  },
  data() {
    return {
      file: {},
      //原来的文章标题
      oldarticleTitle: "",
      addForm: {},
      updateForm: {},
      totalCount: 0,
      checkedUsers: [],
      total: 400,
      currentPage: 1,
      pageSize: 5,
      dialogVisibleAdd: false,
      dialogVisibleUpdate: false,
      tableData: [],
      selectedIds: [],
      multipleSelection: [],
      search: {
        content: "",
        title: "",
      },
      imageUrl: "",
      dialogImageUrl: '',
      dialogVisibleImg: false

    }
  }
}
</script>

<style scoped>
td {
  font-size: 10px;
}

.pageDataCls {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: aliceblue;
}

.pageDataCls > div:first-child {
  display: flex;
  margin: 13px;
}
.imggroup{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin: 14px;
}
.EditForm{
  margin: 5px;
}
</style>

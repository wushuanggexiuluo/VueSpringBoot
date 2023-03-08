<template>
  <div class="pageDataCls">
    <div>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="搜内容">
          <el-input v-model="search.content" placeholder="随便输入"></el-input>
        </el-form-item>
        <el-form-item label="搜邮箱">
          <el-input v-model="search.email" placeholder="随便输入"></el-input>
        </el-form-item>
        <el-form-item label="搜姓名">
          <el-input v-model="search.name" placeholder="随便输入"></el-input>
        </el-form-item>
      </el-form>
    </div>

    <div style="display: flex;">
      <el-button type="primary" @click="onSubmit">查询</el-button>
      <el-button type="danger" @click="manyRemoveData" plain>批量删除</el-button>
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
            prop="commentId"
            label="评论ID">
        </el-table-column>
        <el-table-column
            align="center"
            prop="articleTitle"
            label="文章标题">
        </el-table-column>
        <el-table-column
            align="center"
            prop="commentAuthorName"
            label="评论人">
        </el-table-column>
        <el-table-column
            align="center"
            prop="commentAuthorEmail"
            label="邮箱">
        </el-table-column>

        <el-table-column
            align="center"
            prop="commentContent"
            label="内容">
        </el-table-column>
        <el-table-column
            align="center"
            prop="commentIp"
            label="IP地址">
        </el-table-column>
        <el-table-column
            align="center"
            prop="commentCreateTime"
            label="评论时间"
            sortable
        >
        </el-table-column>
        <el-table-column
            align="center"
            prop="commentRole"
            label="角色">
        </el-table-column>
        <el-table-column
            align="center"
            prop="operation"
              label="操作"   fixed="right">
          <template slot-scope="scope">
            <el-button type="primary" size="small" @click="editData(scope.row)">修改</el-button>
            <br>
            <br>
            <el-button type="danger" size="small" @click="removeData(scope.row)">删除</el-button>
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
    <!--修改-->
    <el-dialog
        class="el-dialog11"
        :fullscreen="true"
        :modal="false"
        title="修改评论"
        :visible.sync="dialogVisibleUpdate"
        width="30%"
        :before-close="handleClose">
      <el-form label-width="80px" :model="updateForm">
        <el-form-item label="评论人">
          <el-input v-model="updateForm.commentAuthorName"></el-input>
        </el-form-item>
        <el-form-item label="评论ip">
          <el-input v-model="updateForm.commentIp"></el-input>
        </el-form-item>
        <el-form-item label="评论邮箱">
          <el-input v-model="updateForm.commentAuthorEmail"></el-input>
        </el-form-item>
        <el-form-item label="评论内容">
          <VueEidtor
              ref="Quill"  class="quill-editor" v-model="updateForm.commentContent"></VueEidtor>
        </el-form-item>
        <el-table-column
            style="display: flex;    flex-direction: row;
    justify-content: space-evenly;"
             label="操作"   fixed="right">
          <el-button type="primary" @click="UpadetCli">提交</el-button>
          <br>
          <el-button type="danger" @click="updateCancel">取消</el-button>
        </el-table-column>

      </el-form>
    </el-dialog>
  </div>


</template>
<script>

import {crud_D, crud_R, crud_U} from "@/mixs/CRUD";
import {quillEditor} from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import showAdminMessage from "@/mixs/ShowAdminMessage";
import {ApiOrigin} from "@/config/ApiOrigin";
import {emailRegx} from "@/utils/Regx";

export default {
  name: "CommentFact",
  components: {
    VueEidtor: quillEditor
  },
  mounted() {
    this.selectAll()

  },
  methods: {
    updateCancel() {
      this.dialogVisibleUpdate = false;
      this.QingKongCli();
    },
    ZanCunCli() {
    },
    QingKongCli() {
      this.updateForm = {}
    },
    //获取所有标签

    //更新
    UpadetCli() {
      //成功的话更新
      console.log(this.updateForm)
      console.log(emailRegx(this.updateForm.commentAuthorEmail))
       if (emailRegx(this.updateForm.commentAuthorEmail)) {
         crud_U.updateData(this, this.updateForm, ApiOrigin+"/Admin/updateComment")
         this.dialogVisibleUpdate = false;
         this.QingKongCli()
       }
      // //不成功调用提示信息
       else {
        showAdminMessage.methods.emailErr(this)
       }
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
      var data = {
        currPage: this.currentPage,
        pageSize: this.pageSize,
        search: this.search
      }
      crud_R.selectPage(this, data, ApiOrigin+"/Admin/getCommentPage")
    },


    //点击查询按钮
    onSubmit() {
      this.selectAll();
    },
    handleClose() {
      this.dialogVisible = false;
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
    editData(row) {
      //弹出编辑窗口

      this.dialogVisibleUpdate = true;
      this.updateForm = row
    },
    //删除
    removeData(row) {
      var arrIDs = [row.commentId];
      crud_D.removeData(this, arrIDs, ApiOrigin+"/Admin/removeComment")
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
    manyRemoveData() {
      let selectedIds = []
      for (let i = 0; i <this.multipleSelection.length ; i++) {
        selectedIds.push(this.multipleSelection[i].commentId)
      }
      if (selectedIds.length > 0) {
        crud_D.removeData(this, selectedIds, ApiOrigin+"/Admin/removeComment")
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
      //原来的评论标题
      updateForm: {},
      totalCount: 0,
      checkedDatas: [],
      total: 400,
      currentPage: 1,
      pageSize: 5,
      dialogVisibleUpdate: false,
      tableData: [],
      selectedIds: [],
      multipleSelection: [],
      search: {
        title: "",
        content: "",
        email: "",
        name: "",
      },

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

.quill-editor {
  min-height: 40%;
  display: flex;
  flex-direction: column;
}

.el-dialog.is-fullscreen {
  background-color: #2c3e50;
}
</style>

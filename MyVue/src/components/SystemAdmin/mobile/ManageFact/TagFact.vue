<template>
  <div class="pageDataCls">
    <div>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="搜名称">
          <el-input v-model="search.categoryName" placeholder="随便输入"></el-input>
        </el-form-item>
        <el-form-item label="搜描述">
          <el-input v-model="search.categoryDescription" placeholder="随便输入"></el-input>
        </el-form-item>


      </el-form>
    </div>

    <div style="display: flex;">
      <el-button type="primary" @click="onSubmit">查询</el-button>
      <el-button type="success" @click="dialogVisibleAdd=true" plain>新增</el-button>
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
            prop="categoryId"
            label="标签ID">
        </el-table-column>
        <el-table-column
            align="center"
            prop="categoryName"
            label="标签名"
            sortable
        >

          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.categoryName }}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            prop="categoryDescription"
            label="标签描述">
          <template slot-scope="scope">
            <el-popover trigger="hover" placement="top">
              <p>{{ scope.row.categoryDescription }}</p>
              <div slot="reference" class="name-wrapper">
                {{ scope.row.categoryDescription }}
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            prop="categoryIcon"
            label="图标">
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
    <!--新增-->
    <el-dialog
        class="el-dialog11"
        :fullscreen="true"
        :modal="false"
        title="新增标签"
        :visible.sync="dialogVisibleAdd"
        width="30%"
        :before-close="handleClose">
      <el-form label-width="80px" :model="addForm">
        <el-form-item label="名称">
          <el-input v-model="addForm.categoryName"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="addForm.categoryDescription"></el-input>
        </el-form-item>
        <img alt="请上传图标" style="width: 148px;height: 148px;" :src="addForm.categoryIcon">
        <el-upload
            action=""
            list-type="picture-card"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
          <div>点击上传上传图标</div>
        </el-upload>
        <!--        <el-form-item label="标签内容">-->
        <!--          <VueEidtor-->
        <!--              ref="Quill" style="" class="quill-editor" v-model="updateForm.TagContent"></VueEidtor>-->
        <!--        </el-form-item>-->
        <el-table-column
            style="display: flex;    flex-direction: row;
    justify-content: space-evenly;"
             label="操作"   fixed="right">
          <el-button type="primary" @click="AddCli">提交</el-button>
          <br>
          <el-button type="danger" @click="updateCancel">取消</el-button>
        </el-table-column>

      </el-form>
    </el-dialog>
    <!--修改-->
    <el-dialog
        class="el-dialog11"
        :fullscreen="true"
        :modal="false"
        title="修改标签"
        :visible.sync="dialogVisibleUpdate"
        width="30%"
        :before-close="handleClose">
      <el-form label-width="80px" :model="updateForm">
        <el-form-item label="名称">
          <el-input v-model="updateForm.categoryName"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="updateForm.categoryDescription"></el-input>
        </el-form-item>
<!--        <el-form-item label="图标">-->
<!--          <el-input v-model="updateForm.categoryIcon"></el-input>-->
<!--        </el-form-item>-->
        <img alt="请上传图标" style="width: 148px;height: 148px;" :src="updateForm.categoryIcon">
        <el-upload
            action=""
            list-type="picture-card"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
          <div>点击上传上传图标</div>
        </el-upload>
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
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import showAdminMessage from "@/mixs/ShowAdminMessage";
import ShowAdminMessage from "@/mixs/ShowAdminMessage";
import {ApiOrigin} from "@/config/ApiOrigin";

export default {
  name: "TagFact",
  mounted() {
    this.selectAll()

  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);

    },
    beforeAvatarUpload(file) {
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
        let this_ = this;
        this.file = file
        let data = {"file": file}
        this.$http.post(ApiOrigin+"/Upload", data, {headers: {'Content-Type': 'multipart/form-data'}}).then((resp) => {

          this_.addForm.categoryIcon = resp.data.data
          this_.updateForm.categoryIcon = resp.data.data
        })
      }
      return errms.length <= 0;
    },
    updateCancel() {
      this.dialogVisibleAdd=false;
      this.dialogVisibleUpdate = false;
      this.QingKongCli();
    },
    ZanCunCli() {
    },
    QingKongCli() {
      this.updateForm = {}
    },
    //获取所有标签
    //新增
    AddCli(){
      if(this.addForm!==null){
        this.$http.post(ApiOrigin+"/Admin/addTag",this.addForm).then((res)=>{

          if(res.data.code===ShowAdminMessage.data().SAVE_OK){
            showAdminMessage.methods.AddOk(this)
          }
          else {
            showAdminMessage.methods.AddErr(this,res.data.message)
          }
        })
      }
    },
    //更新
    UpadetCli() {
      crud_U.updateData(this, this.updateForm, ApiOrigin+"/Admin/updateTag")
      this.dialogVisibleUpdate = false;
      this.QingKongCli()
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
      crud_R.selectPage(this, data, ApiOrigin+"/Admin/getTagPage")
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
    editData(row) {
      //弹出编辑窗口
      this.dialogVisibleUpdate = true;
      this.updateForm = row
    },
    //删除
    removeData(row) {
      var arrIDs = [row.categoryId];
      crud_D.removeData(this, arrIDs, ApiOrigin+"/Admin/removeTag")
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
      for (let i = 0; i < this.multipleSelection.length; i++) {
        selectedIds.push(this.multipleSelection[i].categoryId)
      }
      if (selectedIds.length > 0) {
        crud_D.removeData(this, selectedIds, ApiOrigin+"/Admin/removeTag")
      }
      else {
        showAdminMessage.methods.IllegalDelete(this);
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

  },
  data() {
    return {
      addForm: {},
      updateForm: {},
      totalCount: 0,
      checkedDatas: [],
      total: 400,
      currentPage: 1,
      pageSize: 5,
      dialogVisibleAdd:false,
      dialogVisibleUpdate: false,
      tableData: [],
      selectedIds: [],
      multipleSelection: [],
      search: {
        categoryName: "",
        categoryDescription: "",
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
  width: 1200px;
  min-width: 600px;
  min-height: 200px;
  height: 200px;
  display: flex;
  flex-direction: column;
}

.el-dialog.is-fullscreen {
  background-color: #2c3e50;
}
</style>

<template>
  <div class="pageDataCls">
    <div>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="友链名">
          <el-input v-model="search.linkName" placeholder="随便输入"></el-input>
        </el-form-item>
        <el-form-item label="主人名">
          <el-input v-model="search.linkOwnerNickname" placeholder="随便输入"></el-input>
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
            prop="linkId"
            label="友链ID">
        </el-table-column>
        <el-table-column
            align="center"
            prop="linkName"
            label="名称">
        </el-table-column>
        <el-table-column
            align="center"
            prop="linkUrl"
            label="地址">

        </el-table-column>
        <el-table-column
            align="center"
            prop="linkOwnerNickname"
            label="所属人">
        </el-table-column>
        <el-table-column
            align="center"
            prop="linkDescription"
            length="20"
            label="描述">
          <template slot-scope="scope">
            <el-popover trigger="hover" placement="top">
              <p>{{ scope.row.linkDescription }}</p>
              <div slot="reference" class="name-wrapper">
                {{ scope.row.linkDescription }}
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            prop="linkOwnerContact"
            label="联系方式">
        </el-table-column>
        <el-table-column
            align="center"
            prop="LinkCreateTime"
            label="提交时间"
            sortable
        >
        </el-table-column>
        <el-table-column
            align="center"
            prop="linkUpdateTime"
            label="更新时间"
            sortable
        >
        </el-table-column>
        <el-table-column
            align="center"
            prop="linkImage"
            label="友链图标">
          <template slot-scope="scope">
            <el-popover trigger="hover" placement="top">
              <p>{{ scope.row.linkImage }}</p>
              <div slot="reference" class="name-wrapper">
                <img width="90" :src="scope.row.linkImage">
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            prop="linkStatus"
            label="友链状态">
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
        title="修改友链"
        :visible.sync="dialogVisibleUpdate"
        width="30%"
        :before-close="handleClose">
      <el-form label-width="80px" :model="updateForm">
        <el-form-item label="链接">
          <el-input v-model="updateForm.linkUrl"></el-input>
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="updateForm.linkName"></el-input>
        </el-form-item>
        <el-form-item label="图像">
          <el-input v-model="updateForm.linkImage"></el-input>
        </el-form-item>
        <el-form-item label="所属名">
          <el-input v-model="updateForm.linkOwnerNickname"></el-input>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="updateForm.linkOwnerContact"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-input v-model="updateForm.linkStatus"></el-input>
        </el-form-item>
        <el-form-item label="友链内容">
          <el-input v-model="updateForm.linkDescription"></el-input>
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
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import showAdminMessage from "@/mixs/ShowAdminMessage";
import {emailRegx, internetRegx} from "@/utils/Regx";
import {ApiOrigin} from "@/config/ApiOrigin";

export default {
  name: "LinkFact",
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
      if (internetRegx(this.updateForm.linkUrl)) {
        if (emailRegx(this.updateForm.linkOwnerContact)) {
          crud_U.updateData(this, this.updateForm, ApiOrigin+"/Admin/updateLink")
          this.dialogVisibleUpdate = false;
          this.QingKongCli()
        } else {
          showAdminMessage.methods.emailErr(this)
        }
      } else {
        showAdminMessage.methods.internetErr(this)
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

      crud_R.selectPage(this, data, ApiOrigin+"/Admin/getLinkPage")
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
      var arrIDs = [row.linkId];
      crud_D.removeData(this, arrIDs, ApiOrigin+"/Admin/removeLink")
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
        selectedIds.push(this.multipleSelection[i].linkId)
      }
      if (selectedIds.length > 0) {
        crud_D.removeData(this, selectedIds, ApiOrigin+"/Admin/removeLink")
      } else {
        showAdminMessage.methods.IllegalDelete(this);
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

  },
  data() {
    return {
      initStatusVal: "0",
      initStatusVal2: "",
      initStatus: "#ff4949",
      initStatus2: "#13ce66",
      //原来的友链标题
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
        linkOwnerNickname: "",
        linkName: "",
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

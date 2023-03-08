<template>
  <div>
    <el-card style="margin: 10px auto;width: 1400px">
      <el-carousel :interval="2000" type="card" height="200px">
        <el-carousel-item v-for="(item,index) in tableData" :key="index">
          <div class="ourMs">
            <div>
              <label>姓名</label>
              {{ item.messageAuthorName ||"空"}}
            </div>
            <div>
              <label>邮箱</label>
              {{ item.messageAuthorEmail || "空" }}
            </div>
            <div>
              <label>内容</label>
              {{ item.messageContent || "空" }}
            </div>
            <div>
              <label>时间</label>
              {{ item.messageCreateTime || "空" }}
            </div>
          </div>
        </el-carousel-item>
      </el-carousel>
    </el-card>
  </div>
</template>

<script>
import {crud_R} from "@/mixs/CRUD";
import {APIGetAboutPage_User} from "@/api/UserApi";

export default {
  name: "AboutCenter",
  data() {
    return {
      tableData: [],
    }
  },
  watch:{
  },
  mounted() {
    this.getAll()
  },
  methods: {
    getAll() {
      crud_R.selectAll(this, APIGetAboutPage_User)
    },
  }
}
</script>

<style scoped>
.el-carousel__item h3 {
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.ourMs {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  font-family: "Adobe 楷体 Std R", serif;
}

.ourMs > div {
  font-size: 17px;
  color: white;
}

.ourMs label {
  color: #0c2a70;
  margin: 5px auto;
  font-size: 10px;
  display: block;
}

.el-carousel__item:nth-child(6n) {
  background-image: linear-gradient(to top, #a3bded 0%, #6991c7 100%);
}

.el-carousel__item:nth-child(6n+1) {
  background-image: linear-gradient(-20deg, #6e45e2 0%, #88d3ce 100%);
}
.el-carousel__item:nth-child(6n+2) {
  background-image: linear-gradient(to top, #09203f 0%, #537895 100%);
}
.el-carousel__item:nth-child(6n+3) {
  background-image: linear-gradient(to right, #868f96 0%, #596164 100%);
}
.el-carousel__item:nth-child(6n+4) {
  background-image: linear-gradient(45deg, #874da2 0%, #c43a30 100%);
}
.el-carousel__item:nth-child(6n+5) {
  background-image: linear-gradient(-225deg, #FF3CAC 0%, #562B7C 52%, #2B86C5 100%);
}


</style>

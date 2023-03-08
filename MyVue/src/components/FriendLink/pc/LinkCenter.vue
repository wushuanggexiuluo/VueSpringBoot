<template>
  <div>
    <el-card style="margin: 10px auto;width: 1400px">
      <el-carousel :interval="2000" type="card" height="200px">
        <el-carousel-item v-for="(item,index) in tableData1" :key="index">
          <div class="DataCard" @click="linkUrl=item.linkUrl">
            <div class="imgFrame">
              <img alt="KON" :src="item.linkImage||'https://is3-ssl.mzstatic.com/image/thumb/Purple128/v4/bd/0f/17/bd0f1731-eab9-978b-45ae-c46978dba494/AppIcon-0-1x_U007emarketing-0-0-85-220-6.png/1024x1024bb.jpeg'"/>
            </div>
            <div class="ourMs">
              <div>
                {{ item.linkName || "空" }}
              </div>
              <div>
                <label>描述</label>
                <div v-html="item.linkDescription || '空'"></div>
              </div>
              <div>
                <label>提供者</label>
                {{ item.linkOwnerNickname || "空" }}
              </div>
              <div>
                <label>时间</label>
                {{ item.linkUpdateTime || "空" }}
              </div>
            </div>
          </div>

        </el-carousel-item>
      </el-carousel>
    </el-card>

    <div style="background-image: linear-gradient(to top, #f3e7e9 0%, #e3eeff 99%, #e3eeff 100%);padding: 10px">
    <div v-for="(item,index) in tableData1" :key="index">
      <div class="DataCard1" @click="linkUrl=item.linkUrl">
        <div class="imgFrame1">
          <img alt="KON" :src="item.linkImage||'https://is3-ssl.mzstatic.com/image/thumb/Purple128/v4/bd/0f/17/bd0f1731-eab9-978b-45ae-c46978dba494/AppIcon-0-1x_U007emarketing-0-0-85-220-6.png/1024x1024bb.jpeg'"/>
        </div>
        <div class="ourMs1">
          <div>
            {{ item.linkName || "空" }}
          </div>
          <div>
            <label>描述</label>
            <div v-html="item.linkDescription || '空'"></div>
          </div>
          <div>
            <label>提供者</label>
            {{ item.linkOwnerNickname || "空" }}
          </div>
          <div>
            <label>时间</label>
            {{ item.linkUpdateTime || "空" }}
          </div>
        </div>
      </div>

    </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "LinkCenter",
  props: ["tableData"],
  data() {
    return {
      tableData1: this.tableData,
      linkUrl:""
    }
  },
  watch: {
    tableData: {
      immediate: true,
      deep: true,
      handler(newval) {
        this.tableData1 = newval
      }
    },
    linkUrl:{
      handler(val){
        window.location.href="http://"+val

      }
    }
  },
  mounted() {
  },
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
.ourMs1 {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  font-family: "Adobe 楷体 Std R", serif;
}
.ourMs1 > div {
  font-size: 17px;
  color: white;
}
.ourMs > div {
  font-size: 17px;
  color: white;
}
.imgFrame{
  background-color: black;
   width: 170px;
  height: 170px;
  display: block;
  border-radius: 6px;
}
.imgFrame1{
  display: block;
  border-radius: 6px;
}
.imgFrame1>img{
  width: 160px;
  height: 160px;

}
.imgFrame>img{
  width: 160px;
  height: 160px;

}
.ourMs1 label {
  color: powderblue;
  margin: 5px auto;
  font-size: 10px;
  display: block;
}
.ourMs label {
  color: #0c2a70;
  margin: 5px auto;
  font-size: 10px;
  display: block;
}
.DataCard{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-evenly;
}
.DataCard1{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-evenly;
  background-image: linear-gradient(196deg, #09203f 0%, #537895 100%);
  margin: 10px auto;
}
.DataCard1:hover{
  cursor: pointer;
}
.el-carousel__item:nth-child(6n) {
  background-image: linear-gradient(to top, #c471f5 0%, #fa71cd 100%);
}

.el-carousel__item:nth-child(6n+1) {
  background-image: linear-gradient(to top, #0ba360 0%, #3cba92 100%);
}

.el-carousel__item:nth-child(6n+2) {
  background-image: linear-gradient(to top, #9795f0 0%, #fbc8d4 100%);
}

.el-carousel__item:nth-child(6n+3) {
  background-image: linear-gradient(to top, #505285 0%, #585e92 12%, #65689f 25%, #7474b0 37%, #7e7ebb 50%, #8389c7 62%, #9795d4 75%, #a2a1dc 87%, #b5aee4 100%);;
}

.el-carousel__item:nth-child(6n+4) {
  background-image: linear-gradient(to top, #5f72bd 0%, #9b23ea 100%);
}

.el-carousel__item:nth-child(6n+5) {
  background-image: linear-gradient(-225deg, #FF3CAC 0%, #562B7C 52%, #2B86C5 100%);
}


</style>

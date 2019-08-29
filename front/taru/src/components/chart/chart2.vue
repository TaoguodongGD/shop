<template>
  <div id="Searchsale">

      <div class="title">
      <h2>年季度销售额</h2>
    </div>

    <div id="main-btn">
      <el-input v-model="year" placeholder="请输入年份" style="width:200px"></el-input>
      <el-button @click="search()">确定</el-button>
    </div>

    <div id="main-chart"><ve-line :data="chartData1"></ve-line></div>
  </div>
</template>

<script>

import Vue from 'vue'

import VCharts from 'v-charts'

Vue.use(VCharts)
export default {
  data() {
    return {
      year: "2019",

      chartData1: {
          columns: ['months', 'Sumer', ],
          rows: []
        }
    };
  },
  created(){
      this.search();  
  },
  methods: {

     search() {
      this.$axios
        .post(
          "http://localhost:8080/myproject/api/backstage/order/price",
          this.$qs.stringify({
            year: this.year
          })
        )
        .then(Response => {
          let result = Response.data;
          if (result.status == "200") {
            this.chartData1.rows = result.data;
            this.$message({
                message: result.message,
                type: 'success'
            });
          } else {
            this.$message({
                message: result.message,
                type: 'warning'
            });
          }
        });
    }
  }
};
</script>

<style scoped>
.title{
  width: 300px;
  height: 50px;
  text-align: center;
  line-height: 50px;
}

#main-btn{
  float: right;
  margin-right: 100px;
}

#main-chart{
  margin-top: 60px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
</style>
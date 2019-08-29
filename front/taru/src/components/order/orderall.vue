<template>
  <div class="body">
    <div class="A">
      <div class="B">订单管理</div>
      <div class="C">
        <div class="list1">
          <el-input placeholder="请输入订单编号" prefix-icon="el-icon-search" v-model="derid"></el-input>
        </div>
        <div class="list2">
          <el-button type="danger" plain @click="search()">搜索</el-button>
        </div>
        <div class="list3">
          <el-button type="danger" plain icon="el-icon-refresh" circle @click="Refresh()"></el-button>
        </div>
      </div>
      <el-table :data="tableData" :default-sort="{prop: 'orderDateTime'}" >
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="用户编号 :">
                <span>{{ props.row.user.userId }}</span>
              </el-form-item>
              <br>
              <el-form-item label="用户状态 :">
                <span v-if="props.row.user.userStatus=== 0">用户已注销</span>
                <span v-else>用户正常</span>
              </el-form-item>
              <br>
              <el-form-item label="用 户 名 :">
                <span>{{ props.row.user.userUserName }}</span>
              </el-form-item>
              <br>
              <el-form-item label="用户密码 :">
                <span>{{ props.row.user.userPassword }}</span>
              </el-form-item>
              <br>
              <el-form-item label="用户电话 :">
                <span>{{ props.row.user.userTelphone }}</span>
              </el-form-item>
              <br>
              <el-form-item label="店铺地址 :">
                <span>{{ props.row.user.userAddress }}</span>
              </el-form-item>
              <br>
              <el-form-item label="店铺名称 :">
                <span>{{ props.row.user.userShopName }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="orderId" label="订单编号" align="center"></el-table-column>
        <el-table-column prop="orderTotalPrice" label="订单总价" align="center"></el-table-column>
        <el-table-column prop="orderDateTime" label="下单时间" sortable align="center"></el-table-column>
        <el-table-column label="订单状态" align="center">
          <template slot-scope="scope">
            <el-button
              type="danger"
              size="mini"
              icon="el-icon-delete"
              circle
              plain
              @click.native.prevent="deleteorder(scope.$index, scope.row)"
            >
              <span v-if="scope.row.orderStatus=== 0">已作废</span>
              <span v-else>正常</span>
            </el-button>
          </template>
        </el-table-column>
        <el-table-column prop="orderIsSend" label="一键发货" align="center">
          <template slot-scope="scope">
            <el-button
              type="danger"
              size="mini"
              icon="el-icon-truck"
              circle
              plain
              @click="whether(scope.$index, scope.row)"
            >
              <span v-if="scope.row.orderIsSend=== 0">未发货</span>
              <span v-else>已发货</span>
            </el-button>
          </template>
        </el-table-column>
        <el-table-column label="订单下载" align="center">
          <template slot-scope="scope">
            <a
              :href="'http://localhost:8080/myproject/api/backstage/order/Download?orderid='+scope.row.orderId"
              download="test.txt"
            >
              <el-button type="danger" plain size="mini" icon="el-icon-download" circle></el-button>
            </a>
          </template>
        </el-table-column>
        <el-table-column label="购买详情" align="center">
          <template slot-scope="scope">
            <el-button
              type="danger"
              plain
              size="mini"
              icon="el-icon-postcard"
              circle
              @click="queryshop(scope.$index, scope.row)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog title="购买详情" :visible.sync="dialogTableVisible">
        <el-table :data="shoptableData" :default-sort="{prop: 'detailDateTime'}">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="商品名称:">
                  <span>{{ props.row.product.productName }}</span>
                </el-form-item>
                <br>
                <el-form-item label="商品价格:">
                  <span>{{ props.row.product.productIsLack }}</span>
                </el-form-item>
                <br>
                <el-form-item label="缺货状态:">
                  <span v-if="props.row.product.productIsLack=== 0">缺货</span>
                  <span v-else>有库存</span>
                </el-form-item>
                <br>
                <el-form-item label="商品图片:">
                  <span>
                    <img
                      :src="'http://localhost:8080/myproject/api/static/'+props.row.product.productPhoto"
                      width="60px"
                    >
                  </span>
                </el-form-item>
                <br>
                <el-form-item label="出产日期:">
                  <span>{{ props.row.product.productDateTime }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column prop="product.productName" label="商品名称" align="center"></el-table-column>
          <el-table-column prop="detailProductCount" label="购买数量" align="center"></el-table-column>
          <el-table-column prop="detailProductPrice" label="商品单价" align="center"></el-table-column>
          <el-table-column prop="detailTotalPrice" label="商品总价格" align="center"></el-table-column>
          <el-table-column prop="detailDateTime" label="下单时间" align="center" sortable></el-table-column>
          <el-table-column prop="product.productIsLack" label="商品状态" align="center">
            <template slot-scope="scope">
              <span v-if="scope.row.product.productIsLack=== 0">已下架</span>
              <span v-else>正常售卖</span>
            </template>
          </el-table-column>
        </el-table>
      </el-dialog>
      <div class="D">
        <el-pagination
          @current-change="handleCurrentChange"
          :current-page.sync="sizeone"
          layout="total, prev, pager, next, jumper"
          :total="total"
        ></el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import Qs from "qs";
import axios from "axios";

export default {
  data() {
    return {
      Num: 1,
      pages: "",
      derid: "",
      tableData: [],
      shoptableData: [],
      dialogTableVisible: false,
      total: 0,
      sizeone: 1
    };
  },
  mounted() {
    this.queryall(1);
  },

  methods: {
    //排序
    formatter(row, column) {
      return row.address;
    },
    //查询成功提示
    querysuccess() {
      this.$message({
        showClose: true,
        message: "查询成功",
        type: "success",
        center: true
      });
    },
    //不能为空提示
    nullwarning() {
      this.$message({
        showClose: true,
        message: "请输入订单编号",
        type: "warning",
        center: true
      });
    },
    //订单编号有错误提示
    errorwarning() {
      this.$message({
        showClose: true,
        message: "请输入正确的订单编号",
        type: "warning",
        center: true
      });
    },
    //发货提示
    success() {
      this.$message({
        showClose: true,
        message: "您的货物已出仓。",
        type: "success",
        center: true
      });
    },
    warning() {
      this.$message({
        showClose: true,
        message: "请您耐心等耐。",
        type: "warning",
        center: true
      });
    },
    //查询单个订单
    query() {
      var self = this;
      axios({
        method: "GET",
        url: "http://localhost:8080/myproject/api/backstage/queryById",
        params: {
          orderid: self.derid
        }
      })
        .then(function(x) {
          if (x.data.data == null) {
            self.errorwarning();
            self.tableData = [];
          } else {
            self.tableData = [];
            self.querysuccess();
            self.tableData[0] = x.data.data;
          }
        })
        .catch(function(error) {
          alert(x.data.message);
        });
    },
    //查询单个订单后的刷新
    queryRefresh() {
      var self = this;
      axios({
        method: "GET",
        url: "http://localhost:8080/myproject/api/backstage/queryById",
        params: {
          orderid: self.derid
        }
      })
        .then(function(x) {
          self.tableData = [];
          self.tableData[0] = x.data.data;
        })
        .catch(function(error) {
          alert(x.data.message);
        });
    },
    //搜索事件
    search() {
      if (this.derid !== "") {
        this.query();
      } else {
        this.nullwarning();
        this.tableData = [];
      }
    },

    //查询所有订单
    queryall(Num) {
      var self = this;
      axios({
        method: "POST",
        url: "http://localhost:8080/myproject/api/backstage/order/page/*",
        data: Qs.stringify({
          pageNum: Num,
          pageSize: 6
        })
      })
        .then(function(x) {
          self.tableData = x.data.data.list;
          self.pages = x.data.data.pages;
          self.total = x.data.data.total;
          self.querysuccess();
          self.derid = "";
        })
        .catch(function(error) {});
    },
    handleCurrentChange(val) {
      this.queryall(val);
    },
    //刷新
    Refresh() {
      this.queryallRefresh();
      this.querysuccess();
      this.sizeone = 1;
    },
    //查询所有订单后的刷新
    queryallRefresh() {
      var self = this;
      axios({
        method: "POST",
        url: "http://localhost:8080/myproject/api/backstage/order/page/*",
        data: Qs.stringify({
          pageNum: this.Num,
          pageSize: 6
        })
      })
        .then(function(x) {
          self.tableData = x.data.data.list;
          self.pages = x.data.data.pages;
        })
        .catch(function(error) {
          alert(x.data.message);
        });
    },

    //删除订单
    deleteorder(index, row) {
      var self = this;
      this.$confirm("此操作将永久删除这条订单, 是否继续?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      })
        .then(() => {
          axios({
            method: "GET",
            url: "http://localhost:8080/myproject/api/backstage/order/delete",
            params: {
              orderid: row.orderId
            }
          }).then(function(x) {
            if (x.data.status == 200) {
              if (self.tableData.length == 1) {
                self.queryRefresh();
              } else {
                self.queryallRefresh();
              }
              self.$message({
                type: "success",
                message: "删除成功!",
                center: true
              });
            }
          });
        })
        .catch(function(error) {
          self.$message({
            type: "info",
            message: "已取消删除",
            center: true
          });
        });
    },
    //是否发货
    whether(index, row) {
      var self = this;
      this.$confirm("是否一键发送这件订单?", "说明", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      })
        .then(() => {
          axios({
            method: "GET",
            url: "http://localhost:8080/myproject/api/backstage/order/update",
            params: {
              orderid: row.orderId
            }
          }).then(function(x) {
            if (x.data.status == 200) {
              if (self.tableData.length === 1) {
                self.queryRefresh();
              } else {
                self.queryallRefresh();
              }
              self.$message({
                type: "success",
                message: "成功发货!",
                center: true
              });
            }
          });
        })
        .catch(function(error) {
          self.$message({
            type: "info",
            message: "已取消发送",
            center: true
          });
        });
    },
    //购买详情
    queryshop(index, row) {
      this.dialogTableVisible = true;
      var self = this;
      axios({
        method: "GET",
        url: "http://localhost:8080/myproject/api/backstage/detail/queryId",
        params: {
          orderid: row.orderId
        }
      })
        .then(function(x) {
          if (x.data.status == 200) {
            self.shoptableData = x.data.data;
          }
        })
        .catch(function(error) {
          alert(x.data.message);
        });
    }
  }
};
</script>
<style scoped>
.body {
  margin: auto;
  width: 1500px;
  
}

.A {
  float: left;
  width: 1300px;
  position: relative;
  box-shadow: 0px 0px 10px lightgray;
}
.B {
  height: 40px;
  color: black;
  font-size: 20px;
  margin-top: 20px;
  margin-left: 40px;
}
.C {
  height: 60px;
}

.D {
  float: right;
  margin-right: 50px;
  margin-top: 50px;
  margin-bottom: 50px;
}
.C .list1 {
  width: 250px;
  float: left;
  margin-left: 870px;
}
.C .list2 {
  width: 50px;
  float: left;
  margin-left: 10px;
}
.C .list3 {
  width: 50px;
  float: left;
  margin-left: 45px;
}
</style>


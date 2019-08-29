<template>
  <div id="text">
    <el-tabs v-model="activeName" @tab-click="handleClick" style="padding-left:10px;">
      <el-tab-pane
        :key="item.categoryName"
        v-for="(item) in editableTabs"
        :label="item.categoryName"
        :name="item.categoryId"
      >
        <div id="allShop">
          <el-table :data="list" style="width: 100%">
            <!-- <el-table-column align="center" type="selection" width="55" fixed></el-table-column> -->
            <el-table-column align="center" type="index" width="50"></el-table-column>
            <el-table-column align="center" label="商品上架日期" width="200">
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px">{{ scope.row.productDateTime }}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" label="商品编号" width="150" prop="productCode"></el-table-column>
            <el-table-column align="center" label="商品名称" width="160" prop="productName"></el-table-column>
            <el-table-column align="center" label="商品图片" width="80" prop="productSmallPrice">
              <template slot-scope="scope" @click="change_img()">
                <img :src="web_static_url+scope.row.productPhoto" style="height: 50px;width: 50px;">
              </template>
            </el-table-column>
            <el-table-column align="center" label="商品价格" width="80" prop="productSmallPrice"></el-table-column>
            <el-table-column prop="zip" label="状态" width="60">
              <template slot-scope="scope">{{shop_status(scope.row.productStatus)}}</template>
            </el-table-column>
            <el-table-column prop="zip" label="是否缺货" width="80">
              <template slot-scope="scope">{{shop_islack(scope.row.productIsLack)}}</template>
            </el-table-column>
            <!-- <template slot="header">
              <el-input size="mini" placeholder="输入关键字搜索"/>
            </template> 
            slot-scope="scope"-->
            <el-table-column align="right" fixed="right" width="200" label="操作">
              <template slot-scope="scope">
                <el-button
                  @click.native.prevent="change_shop(scope.row.productId)"
                  type="danger"
                  plain
                  icon="el-icon-edit"
                  circle
                  size="small"
                ></el-button>
                <el-button
                  @click.native.prevent="open(scope.row.productId)"
                  type="danger"
                  plain
                  icon="el-icon-check"
                  circle
                  size="small"
                ></el-button>
                <el-button
                  @click.native.prevent="open1(scope.row.productId)"
                  type="danger"
                  plain
                  icon="el-icon-close"
                  circle
                  size="small"
                ></el-button>
                <!--  @click.native.prevent="deleteRow(scope.$index, list)" -->
                <el-button
                  @click="open2(scope.row.productId)"
                  type="danger"
                  plain
                  icon="el-icon-delete"
                  circle
                  size="small"
                ></el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <el-row>
          <el-col :span="10">
            <div class="pagination">
              <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange(item.categoryId)"
                :current-page.sync="paginations.page_index"
                :page-sizes="paginations.page_sizes"
                :page-size="paginations.page_size"
                :layout="paginations.layout"
                :total="paginations.total"
              ></el-pagination>
            </div>
          </el-col>
        </el-row>
      </el-tab-pane>
    </el-tabs>
  </div>
  <!-- 分页 -->
</template>
<style>
body {
  margin: 0;
}
</style>
<script>
export default {
  data() {
    return {
      loading: true,
      editableTabsValue: "2",
      editableTabs: [],
      tabIndex: 2,
      list: [],
      activeName: "second",
      id: "",
      web_static_url: "http://localhost:8080/myproject/api/static/",
      paginations: {
        page_index: 1, //当前位于哪页
        total: 20, //总数
        page_size: 5, //每页显示多少条
        page_sizes: [5, 10, 15, 20], //每页显示多少条
        layout: "total,sizes,prev,pager,next,jumper" //翻页属性
      }
    };
  },
  // watch: {
  //   // 如果 `question` 发生改变，这个函数就会运行
  //   question: function(newQuestion, oldQuestion) {
  //     this.search(question);
  //     alert("方法被条用"+question);
  //   }
  // },
  created() {
    this.init();
    this.search("02c6fd20-808b-441e-b02c-edb5b7e95df1");
  },
  methods: {
    handleClick(tab, event) {
      //console.log(tab, event);
      this.search(tab.name);
    },
    open(id) {
      this.$confirm("此操作将永久更改商品状态, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.add(id);
          // this.$message({
          //   type: "success",
          //   message: "更改成功!"
          // });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消更改"
          });
        });
    },
    open1(id) {
      this.$confirm("此操作将永久更改商品状态, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.reduce(id);
          // this.$message({
          //   type: "success",
          //   message: "更改成功!"
          // });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消更改"
          });
        });
    },
    open2(id) {
      this.$confirm("此操作将永久更改商品状态, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.drop(id);
          // this.$message({
          //   type: "success",
          //   message: "更改成功!"
          // });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消更改"
          });
        });
    },
    change_shop(productid) {
      this.$router.push({
        name: "updateshop",
        params: {
          Id: productid
        }
      });
    },
    drop(productid) {
      this.$axios
        .get("http://localhost:8080/myproject/api/backstage/product/del", {
          params: {
            productId: productid
          }
        })
        .then(response => {
          let result = response.data;
          if (result.status == "200" || "500") {
            this.$message({
              type: "success",
              message: result.message
            });
            this.search(this.id);
          }
        })
        .catch();
    },
    reduce(productid) {
      //alert(productid);
      this.$axios
        .get("http://localhost:8080/myproject/api/backstage/lack", {
          params: {
            id: productid
          }
        })
        .then(response => {
          let result = response.data;
          if (result.status == "200") {
            this.$message({
              type: "success",
              message: result.message
            });
            this.search(this.id);
          }
        })
        .catch();
    },
    add(productid) {
      //alert(productid);
      this.$axios
        .get("http://localhost:8080/myproject/api/backstage/noLack", {
          params: {
            id: productid
          }
        })
        .then(response => {
          let result = response.data;
          if (result.status == "200") {
             this.$message({
              type: "success",
              message: result.message
            });
            this.search(this.id);
          }
        })
        .catch();
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.paginations.page_size = val;
      this.search(this.id);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${this.paginations.page_index}`);
      this.search(val);
    },
    deleteRow(index, rows) {
      //console.log(index,"skksk", rows);
      //alert(index+"下标");
      rows.splice(index, 1);
    },
    shop_status(a) {
      if ((a = 1)) {
        return "上架";
      } else {
        return "下架";
      }
    },
    shop_islack(b) {
      //alert(b,name)
      if (b == 0) {
        return "缺货";
      } else {
        return "不缺货";
      }
    },
    init() {
      this.$axios
        .get("http://localhost:8080/myproject/api/backstage/category/list")
        .then(response => {
          let result = response.data;
          if (result.status == "200") {
            this.editableTabs = result.data;
            console.log(result.data);
          }
        })
        .catch();
    },
    search(id) {
      this.id = id;
      this.$axios
        .get("http://localhost:8080/myproject/api/backstage/product/list", {
          params: {
            pageNum: this.paginations.page_index,
            pageSize: this.paginations.page_size,
            categoryId: id,
            productName: "",
            isLack: ""
          }
        })
        .then(response => {
          let result = response.data;
          if (result.status == "200") {
            this.list = result.data.list;
            console.log(result.data.list);
            this.paginations.total = result.data.total;
          }
        })
        .catch();
    }
  }
};
</script>
<style scoped>
#text {
  box-shadow: 0px 0px 10px lightgray;
  /* background: #f5f5f5; */
}

.pagination {
  height: 40px;
  width: 890px;
  text-align: right;
  margin-top: 10px;
  margin-right: 10px;
  /* border: 1px solid black */
}
</style>

<template>
  <div id="select">
    <div id="header">
      <p>商品类别</p>
    </div>
    <div id="nav">
      <div class="nav-left"></div>

      <div class="nav-right">
        <el-input placeholder="请输入类别" style="width:250px"></el-input>
        <el-button type="danger" class="btnA" @click="findKind()" :plain="true">刷新</el-button>
        <el-button @click="dialogFormVisible = true" type="danger" plain icon="el-icon-plus" circle></el-button>
        <el-dialog title="添加商品类别" :visible.sync="dialogFormVisible">
          <el-form :model="form">
            <el-form-item label="类别名称" :label-width="formLabelWidth">
              <el-input v-model="form.categoryName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="类别状态" :label-width="formLabelWidth">
              <el-input v-model="form.categoryOrder" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <!-- 添加退出 -->
            <div class="tianjaituichu">
              <el-button
                type="danger"
                @click="dialogFormVisible = false"
                icon="el-icon-close"
                plain
              ></el-button>
            </div>
            <!-- 添加确定-->

            <el-button
              type="danger"
              @click="dialogFormVisible = false,addKind()"
              icon="el-icon-check"
              plain
            ></el-button>
          </div>
        </el-dialog>
      </div>
    </div>

    <div id="cav">
      <el-table
        :data="tableData"
        style="width: 1300px"
        :default-sort="{prop: 'categoryOrder', order: 'descending'}"
        class="table11"
      >
        <!-- <el-table-column label="类别编号" width="380">
          <template slot-scope="scope">
            <span style="margin-left: 0px">{{ scope.row.categoryId }}</span>
          </template>
        </el-table-column>-->

        <el-table-column label="类别名称" width="300" align="center">
          <template slot-scope="scope">
            <span style="margin-left: 0px">{{ scope.row.categoryName }}</span>
          </template>
        </el-table-column>

        <el-table-column label="类别排序" sortable prop="categoryOrder" width="300" align="center"></el-table-column>

        <el-table-column label="类别状态" :formatter="formatter" width="280" align="center">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.categoryStatus }}</span>
          </template>
        </el-table-column>

        <el-table-column label="类别操作" width="380px" >
          <!--xiugai删除  placement="right"-->
          <template slot-scope="scope">
            <!-- 修改 -->
            <el-popover width="600" trigger="click">
              <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="类别id">
                  <el-input v-model="form.categoryId" :disabled="true"></el-input>
                </el-form-item>

                <el-form-item label="类别名称">
                  <el-input v-model="form.categoryName"></el-input>
                </el-form-item>

                <el-form-item label="类别序号">
                  <el-input v-model="form.categoryOrder"></el-input>
                </el-form-item>

                <el-form-item label="类别状态">
                  <el-input v-model="form.categoryStatus" :disabled="true"></el-input>
                </el-form-item>

                <el-form-item>
                  <!-- 修改确定 -->
                  <div class="queding">
                    <el-button type="danger" @click="open1()" icon="el-icon-edit" plain></el-button>
                  </div>
                  <!-- 修改取消 -->
                  <div class="tuichu">
                    <el-button icon="el-icon-switch-button" type="danger" plain></el-button>
                  </div>
                </el-form-item>
              </el-form>

              <!-- 修改类别按钮 -->
              <el-button
                size="mini"
                @click="handleEdit(scope.row),dialogFormVisible = false"
                icon="el-icon-edit"
                circle
                slot="reference"
                type="danger"
                plain
              ></el-button>
            </el-popover>
            <!-- 删除类别按钮 -->
            <div class="shanchu" style="margin-left:15px">
              <el-button
                size="mini"
                type="danger"
                plain
                @click="open(scope.$index, scope.row.categoryId)"
                icon="el-icon-delete"
                circle
              ></el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>




<script>
import axios from "axios";
import Qs from "qs";
export default {
  
  name: "select",
  data() {
    return {
      tableData: [
        {
          categoryId: "",
          categoryName: "",
          categoryOrder: "",
          categoryStatus: ""
        }
      ],
      dialogFormVisible: false,
      dialogFormVisible: false,
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "",
        categoryId: "",
        categoryName: "",
        categoryOrder: "",
        categoryStatus: ""
      },
      formLabelWidth: "120px"
    };
  },
  created() {
    this.findKind();
  },
  methods: {
    //修改类别方法
    handleEdit(row) {
      // alert(row)
      console.log(row);
      this.form.categoryId = row.categoryId;
      this.form.categoryName = row.categoryName;
      this.form.categoryOrder = row.categoryOrder;
      this.form.categoryStatus = row.categoryStatus;
    },

    //删除特效
    open(index, row) {
      this.$confirm("此操作将永久删除该类别, 是否继续?", "温馨提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$message({
            type: "success",
            message: "删除类别成功!"
          });
          this.handleDelete(index, row);
        })

        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },

    //删除类别
    handleDelete(index, row) {
      // alert(row)
      this.$axios
        .get("http://localhost:8080/myproject/api/backstage/category/del", {
          params: {
            categoryId: row
          }
        })
        .then(Response => {
          let result = Response.data;
          if (result.status == "200") {
            // alert(result.message);
            this.findKind();
          }
        })
        .catch(Response => {
          alert(result.message);
        });
    },

    //排序方法
    formatter(row, column) {
      return row.categoryId;
    },

    //查询类别
    findKind() {
      this.$axios
        .get("http://localhost:8080/myproject/api/backstage/category/list")
        .then(res => {
          let result = res.data;
          if (result.status == 200) {
            // alert(result.message);
            this.tableData = result.data;
          }
        });
    },
    // open2() {
    //     this.$message({
    //       message: '恭喜你，这是一条成功消息',
    //       type: 'success'
    //     });
    //   },

    //添加类别
    addKind() {
      this.$axios({
        methods: "POST",
        url:
          "http://localhost:8080/myproject/api/backstage/category/add?categoryId&categoryName=" +
          this.form.categoryName +
          "&categoryOrder=" +
          this.form.categoryOrder +
          "&categoryStatus=1",
        data: this.$qs.stringify({})
      })
        .then(Response => {
          let result = Response.data;
          if (result.status == "200") {
            this.$message({
              showClose: true,
              message: "添加商品成功",
              type: "success"
            });
            this.findKind();
          } else if (result.status == "500") {
            this.$message({
              showClose: true,
              message: "添加商品异常",
              type: "error"
            });
          } else {
            alert("失败");
          }
        })
        .catch(Response => {});
    },

    //修改确定按钮
    onSubmit() {
      // alert("w");
      let that = this;
     // alert(that.form.categoryName);

       axios({
        method: "POST",
        url: "http://localhost:8080/myproject/api/backstage/category/UpdateCategory",
        data: Qs.stringify({
              categoryId: that.form.categoryId,
              categoryName: that.form.categoryName,
              categoryOrder: that.form.categoryOrder,
              categoryStatus: that.form.categoryStatus
        })
      })
        .then(Response => {
          let result = Response.data;
          if (result.status == "200") {
            // this.$message({
            //   showClose: true,
            //   message: "修改商品成功",
            //   type: "success"
            // });
            that.findKind();
          } else {
            alert(result.message);
          }
        })
        .catch(Response => {
          alert(result.message);
        });
    },
    //修改特效
    open1() {
      let that = this;
      this.$confirm("此操作将对商品类别进行修改, 是否继续?", "温馨提示", {
        confirmButtonText: "确定修改",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          that.onSubmit();
          this.$message({
            type: "success",
            message: "修改类别成功"
          });
         
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消修改"
          });
        });
    }
  }
};
</script>





<style scoped>
#select {
  box-shadow: 0px 0px 10px lightgray;
  /* background: #f5f5f5; */
}
#header {
  height: 60px;
  width: 1300px;
  /* background-color: bisque; */
}
#header p {
  font-size: 1.2rem;
  margin-left: 50px;
  padding-top: 20px;
  color: black;
}

#nav {
  height: 50px;
  width: 1300px;
  /* background-color: khaki; */
}
#cav {
  height: 580px;
  width: 1300px;
  /* background-color: blueviolet; */
}
.nav-left {
  float: left;
  /* background-color: #f5f5f5; */
  width: 820px;
  height: 50px;
}
.nav-right {
  float: right;
  width: 480px;
  height: 50px;
  text-align: left;
}
.nav-right .btnA {
  margin-left: 20px;
}
.shanchu {
  float: right;
  margin-right: 285px;
}
.tuichu {
  float: right;
  padding-right: 150px;
}
.queding {
  float: left;
  padding-left: 100px;
}
.tianjaituichu {
  float: left;
  padding-left: 80px;
}
/* .lbmc{
  float: left;
  padding-left: 200px;
} */
</style>


<template>
  <div class="usermodel">
    <div class="u-header">
      <p>用户列表</p>
    </div>
    <div class="A">
      <el-button
        type="danger"
        plain
        class="u-btn1 u-btn"
        @click="userQuery()"
        style="width:80px; "
      >搜索</el-button>
    </div>
    <div class="u-input">
      <el-input v-model="input" placeholder="请输入商店名称" style="width:250px"></el-input>
    </div>

    <div class="u-table">
      <el-table :data="list" border header-align="center">
        <el-table-column prop="userUserName" label="姓名"  align="center"></el-table-column>
        <el-table-column prop="userPassword" label="密码"  align="center"></el-table-column>
        <el-table-column prop="userTelphone" label="电话"  align="center"></el-table-column>
        <el-table-column prop="userAddress" label="地址"  align="center"></el-table-column>
        <el-table-column prop="userShopName" label="商店名称"  align="center"></el-table-column>
        <el-table-column prop="userComment" label="评论" width="280" align="center"></el-table-column>
        <el-table-column prop="userStatus" label="状态"  align="center">
          <template slot-scope="scope">{{user_isDel(scope.row.userStatus)}}</template>
        </el-table-column>
        <el-table-column label="操作" width="120" align="center">
          <template slot-scope="scope">
            <el-popover placement="right" trigger="click">
              <div class="l">
                <el-form ref="form" :modell="lists" label-width="70px" :inline="true">
                  <el-form-item label="编号">
                    <el-input v-model="lists.userId" :disabled="true"></el-input>
                  </el-form-item>
                  <el-form-item label="姓名">
                    <el-input v-model="lists.userUserName"></el-input>
                  </el-form-item>
                  <el-form-item label="密码">
                    <el-input v-model="lists.userPassword"></el-input>
                  </el-form-item>
                  <el-form-item label="电话">
                    <el-input v-model="lists.userTelphone"></el-input>
                  </el-form-item>
                  <el-form-item label="地址">
                    <el-input v-model="lists.userAddress"></el-input>
                  </el-form-item>
                  <el-form-item label="商店名称">
                    <el-input v-model="lists.userShopName"></el-input>
                  </el-form-item>
                  <el-form-item label="评论">
                    <el-input v-model="lists.userComment"></el-input>
                  </el-form-item>
                  <el-form-item label="状态">
                    <el-input v-model="lists.userStatus" :disabled="true"></el-input>
                  </el-form-item>
                  <el-form-item size="min" :inline="false">
                    <el-button @click="open3()" style="margin-left:355px">取消</el-button>
                    <el-button type="primary" @click="open2()">保存</el-button>
                  </el-form-item>
                </el-form>
              </div>
              <el-button
                size="mini"
                plain
                round
                @click="handleEdit(scope.row.userId)"
                icon="el-icon-edit"
                circle
                slot="reference"
              ></el-button>
            </el-popover>
            <div class="u-button">
              <el-button
                size="mini"
                type="danger"
                plain
                round
                @click="open(scope.row.userId)"
                icon="el-icon-delete"
                circle
              ></el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="block">
      <el-pagination
        @next-click="nextPage"
        @prev-click="prePage"
        layout="total, prev,  next"
        :total="total"
      ></el-pagination>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      list: [],
      total: 0,
      currentPage: 1,
      pageSize: 7,
      lists: "",
      id: "",
      input: ""
    };
  },

  created() {
    this.userQuery();
  },
  methods: {
    open(id) {
      this.$confirm("是否删除此用户?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.deleteRow(id);
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    open2() {
      this.$confirm("是否保存当前信息?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.userEdit();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消保存"
          });
        });
    },
    open3() {
      this.$alert("是否放弃修改当前信息?", "提示", {
        confirmButtonText: "确定"
      });
    },

    //显示是否删除
    user_isDel(status) {
      if (status == 0) {
        return "已删除";
      } else if (status == 1) {
        return "正常";
      }
    },
    //查询用户
    userQuery() {
      this.$axios
        .get("http://localhost:8080/myproject/api/backstage/user/query", {
          params: {
            telphone: "",
            shopName: this.input,
            pageNum: this.currentPage,
            pageSize: this.pageSize
          }
        })
        .then(response => {
          let result = response.data;
          if (result.status == 200) {
            this.list = result.data.list;
            this.total = result.data.total;
          }
        })
        .catch(error => {});
    },

    //删除用户
    deleteRow(id) {
      this.$axios
        .get("http://localhost:8080/myproject/api/backstage/user/del", {
          params: {
            uid: id
          }
        })
        .then(response => {
          let result = response.data;
          if (result.status == 200) {
            this.$message({
              type: "success",
              message: "删除成功!"
            });
            this.userQuery();
          } else {
            this.$message({
              message: result.message,
              type: "warning"
            });
          }
        })
        .catch(error => {
          this.$message({
            message: "未知错误",
            type: "warning"
          });
        });
    },

    handleEdit(id) {
      this.$axios
        .get("http://localhost:8080/myproject/api/backstage/user/findById", {
          params: {
            uid: id
          }
        })
        .then(res => {
          let result = res.data;
          if (result.status == 200) {
            this.lists = result.data;
          }
        })
        .catch(error => {
          this.$message({
            message: "未知错误",
            type: "warning"
          });
        });
    },

    userEdit() {
      this.$axios
        .post(
          "http://localhost:8080/myproject/api/backstage/user/update?userId=" +
            this.lists.userId +
            "&userUserName=" +
            this.lists.userUserName +
            "&userPassword=" +
            this.lists.userPassword +
            "&userTelphone=" +
            this.lists.userTelphone +
            "&userAddress=" +
            this.lists.userAddress +
            "&userShopName=" +
            this.lists.userShopName +
            "&userComment=" +
            this.lists.userComment,
          this.$qs.stringify({
            data: {
              userId: this.lists.userId,
              userUserName: this.lists.userUserName,
              userPassword: this.lists.userPassword,
              userTelphone: this.lists.userTelphone,
              userAddress: this.lists.userAddress,
              userShopName: this.lists.userShopName,
              userComment: this.lists.userComment
            }
          })
        )
        .then(res => {
          let result = res.data;
          if (result.status == 200) {
            this.$message({
              type: "success",
              message: "修改成功!"
            });
            this.userQuery();
          } else {
            alert(result.message);
          }
        })
        .catch(error => {
          alert("未知错误");
        });
    },
    nextPage() {
      this.currentPage++;
      this.userQuery();
    },
    prePage() {
      this.currentPage--;
      this.userQuery();
    }
  }
};
</script>
<style>
.block {
  margin: 30px;
}
.l {
  width: 540px;
  height: 300px;
}
.usermodel {
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  width: 1300px;
  height: 690px;
  margin: 0 auto;
  padding: 0;
  box-shadow: 0px 0px 10px lightgray;
  background: #ffffff;
}
.u-header {
  padding: 0px;
  height: 50px;
  line-height: 50px;
  margin-bottom: 20px;
  font-size: 20px;
  color: black;
}

.u-header p {
  margin-left: 50px;
}

.A {
  float: right;
  margin-bottom: 20px;
  margin-right: 20px;
}
.u-input {
  float: right;
  margin-bottom: 20px;
  margin-right: 10px;
}
.u-text1 {
  width: 180px;
  height: 30px;
  margin-left: 20px;
  margin-right: 20px;
}
.u-btn {
  width: 100px;
}
.u-table {
  margin-top: 40px;
}
.u-button {
  margin-right: 20px;
  float: right;
}
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.block {
  margin-top: 30px;
  float: right;
}
.u-add {
  margin-top: 20px;
  margin-left: 600px;
}
</style>

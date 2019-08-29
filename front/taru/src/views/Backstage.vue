<template>
  <div class="backstage">
    <el-container>
      <el-header style="background-color:#cdecee;height:100px;">
        <!-- -->
        <div id="logo">
                <img src="../../src/assets/login2.png" alt="">
          </div>
        <div class="header-left">
            <p>后台管理系统</p> 
        </div>
        <div id="usermes">
          <p>
            欢迎 <a href="javascript:void(0);" style="color:black;"><i class="el-icon-user"></i>{{username}} </a><span class="sep" style="margin-left:10px;">|</span>
            <a href="javascript:void(0);" style="color:black; font-size:15px; margin-left:10px;" @click="open()">注销</a>
          </p>
           
        </div>
        <div class="header-right">
            
        </div>
      </el-header>

      <el-container>
        <el-aside width="200px">
          <el-row class="tac">
            <div class="tac-title">
              <h2>管理中心</h2>
            </div>
            <el-col>
              <el-menu
                default-active="1"
                class="el-menu-vertical-demo"
                background-color="#f9f9f9"
                text-color="#303133"
                active-text-color="#ffd04b"
                >
                <el-submenu index="1">
                  <template slot="title">
                    <i class="el-icon-user"></i>
                    <span>用户管理</span>
                  </template>
                  <el-menu-item-group>
                    <router-link to='/userquery'><el-menu-item index="1-1">用户列表</el-menu-item></router-link>
                    <router-link to='/useradd'><el-menu-item index="1-2">用户添加</el-menu-item></router-link>
                  </el-menu-item-group>
                </el-submenu>

                <el-submenu index="2">
                  <template slot="title">
                    <i class="el-icon-tickets"></i>
                    <span>订单管理</span>
                  </template>
                  <el-menu-item-group>
                    <router-link to='/orderall'><el-menu-item index="2-1">订单列表</el-menu-item></router-link>
                  </el-menu-item-group>
                </el-submenu>

                <el-submenu index="3">
                  <template slot="title">
                    <i class="el-icon-goods"></i>
                    <span>商品管理</span>
                  </template>
                  <el-menu-item-group>
                    <router-link to='/text'><el-menu-item index="3-1">商品列表</el-menu-item></router-link>
                    <router-link to='/addshop'><el-menu-item index="3-2">商品添加</el-menu-item></router-link>
                  </el-menu-item-group>
                </el-submenu>

                <el-submenu index="4">
                  <template slot="title">
                    <i class="el-icon-menu"></i>
                    <span>类别管理</span>
                  </template>
                  <el-menu-item-group>
                    <router-link to='/selectcategory'><el-menu-item index="4-1">类别列表</el-menu-item></router-link>
                  </el-menu-item-group>
                </el-submenu>
                
                <el-submenu index="5">
                  <template slot="title">
                    <i class="el-icon-pie-chart"></i>
                    <span>数据统计</span>
                  </template>
                  <el-menu-item-group>
                    <router-link to='/chart1'><el-menu-item index="5-1">商品销售情况</el-menu-item></router-link>
                    <router-link to='/chart2'><el-menu-item index="5-2">季度销售额</el-menu-item></router-link>
                  </el-menu-item-group>
                </el-submenu>
                
              </el-menu>
            </el-col>
          </el-row>
        </el-aside>
        <div class="rightBox">
          <router-view></router-view>
        </div>
      </el-container>
    </el-container>
  </div>
</template>

<script>

export default {
  name:'backstage',
  data(){
    return{
        username:sessionStorage.getItem("adminuserUserName")
    }
  },
  components:{

  },
    methods: {
      exit(){
        sessionStorage.removeItem("adminuserUserName");
        //sessionStorage.removeItem("adminuserId");
        this.$router.push("/adminlogin")
      },
       open() {
        this.$confirm('您确定要退出吗？, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.exit();
          this.$message({
            type: 'success',
            message: '已退出!',
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '退出已取消' 
          });          
        });
      }
    }
};
</script>

<style>
.tac-title{
  height: 50px;
  line-height: 50px;
}

.tac-title p{
  height: 100px;
  
  
}
#usermes{
  float: right;
  height: 100%;
  line-height:65px;
  width: 200px;
  margin-top: 10px;
  color: black;
}
a{
  text-decoration: none;
  color: rgb(255, 255, 255)
}

.header-left{
  float: left;
  font-size: 18px;
  margin-top: 32.5px;
  margin-left: 5px;
}

.header-left img{
  width: 60px;
  height: 60px;
}
 #logo img{
    /* background-color: black; */
    height: 80px;
    width: 250px;
    float: left;
    margin-top: 10px;
  }
.header-left h2{
  color: #333;
  float: right;
  font-size: 25px;
}

.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #e1f1f0;
  color: #333;
  text-align: center;
  line-height: 200px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.rightBox{
    width: 1300px;
    height: 690px;
  margin: 10px;
}
</style>



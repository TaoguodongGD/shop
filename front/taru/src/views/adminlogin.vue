<template>
  <div id="login">
    <div id="header" >
      <div class="herder-left">
            <div><p>欢迎来到后台管理系统</p></div>
      </div>
       </div>
    <div id="nav">
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm"  label-width="100px" class="demo-ruleForm">
         <div>
        <el-form-item label="用户名" prop="username" class="demo-user">
          <el-input type="text" v-model="ruleForm.username" autocomplete="off"></el-input>
        </el-form-item>
        </div>
      <el-form-item label="密码" prop="password" class="demo-pass">
        <el-input type="password" v-model="ruleForm.password"></el-input>
        
      </el-form-item>
      

    <!-- //滑动验证码 -->
        <div>
       <el-form-item >
       <Yanzheng-i v-on:childByConfirmSuccess="childByConfirmSuccess"></Yanzheng-i>
       </el-form-item>
        </div>


      <el-form-item id="demo-login-resing">
        <el-button type="danger" @click="submitForm('ruleForm')" class="demo-login">登陆</el-button>

          <div class="chongzhi">
            <el-button @click="resetForm('ruleForm')" class="demo-resing">重置</el-button>
          </div>
      </el-form-item>
     
      <el-divider></el-divider>

      <el-form-item>
        <el-checkbox v-model="ruleForm.checked">记住密码</el-checkbox>
        <el-link type="danger">忘记密码？</el-link>
       
      
      </el-form-item>
      
    </el-form>
    </div>
   <div id="footer">
     <div class="footer-herder" >
     <ul>
       <li> <a href="#">关于我们</a></li>
        <li> <a href="#">联系我们</a></li>
        <li> <a href="#">诚聘英才</a></li>
          <li> <a href="#">网站合作</a></li>
            <li> <a href="#">知识产权</a></li>
              <li> <a href="#">法律声明</a></li>
              <!-- <li> <a href="#">廉政举报</a></li> -->
              <li> <a href="#">隐私政权政策</a></li>
     </ul>
     </div>
  

                       
   </div>
  </div>
  
</template>






<script>
import axios from "axios";
import Qs from "qs";
import Yanzheng from "@/components/Yanzheng.vue"

export default {
    components: {
      "Yanzheng-i":Yanzheng,
    },
  data() {
    var checkUsername = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("用户名不能为空"));
      } else {
        callback();
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        callback();
      }
    };

    return {
      yanzheng:"",
      
      ruleForm: {
        username: "",
        password: "",

       

      },
      rules: {
        username: [
          { validator: checkUsername, trigger: "blur" },
          {
            pattern: /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/,
            message: "当前用户名不合法，请您重新输入"
          } //用户名正则验证（中英文数字下划线）
        ],
        password: [
          { validator: validatePass, trigger: "blur" },
          {
            min: 6,
            max: 16,
            message: "当前密码不正确，请您重新输入",
            trigger: "blur"
          } //密码正则验证
        ]
      },
      checked: false
    };
  },
  //页面加载调用获取cookie值
  // mounted() {
  //   this.getCookie();
  // },





  methods: {
    // //子传父组件
    childByConfirmSuccess(confirmSuccess){
      this.yanzheng = confirmSuccess;
    },
    //注册页面
    init() {
      this.$router.push({
        name: "resinger",
        params: {}
      });
    },
    //登陆
    login1() {
      var self = this;
      axios({
        method: "POST",
        url: "http://localhost:8080/myproject/api/admin/login",
        data: Qs.stringify({
          username: self.ruleForm.username,
          password: self.ruleForm.password
        })
      })
        .then(response =>{
          let result=response.data;
          console.log(result);
          if(result.status=="200"){
          // window.sessionStorage.setItem("adminuserId", result.data.userId); //获取id
           window.sessionStorage.setItem("adminuserUserName",self.ruleForm.username);
           this.$message.success('登陆成功');
          this.$router.push("/backstage");
          }else{
             this.$message.error("用户名或密码错误");
          }
        })
        .catch(function(error) {
          alert("异常");
        });
    },

    
    submitForm(formName) {
      // if(this.yanzheng == true){
        this.$refs[formName].validate(valid => {
        if (valid) {
          
          if(this.yanzheng == true){
              this.login1();
          }else{
                this.$message.error('请先拖动滑块将悬浮图像正确拼合');

        // alert("请先拖动滑块将悬浮图像正确拼合");
      }
          
        } else {
          console.log("error submit!!");
          return false;
        }
      });     
      // }else{
      //   alert("请拖滑块验证");
      // }
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>

<style scoped>
ul li{
  list-style: none;
}
a{
  color: black;
  font-size: 0.7rem;
}
#login {
  height: 100%;
  width: 100%;
}
#header{
  height: 80px;
  width: 100%;
  /* background-color: aqua; */
  clear: both;
}

#header img{
  height: 50px;
  width: 70px;
  position: relative;
  left: 80px;
}
#header p{
position: relative;
text-align: center;
padding-top: 20px;
font-size: 1.5rem;
color: black;
}
#nav{
  width: 100%;
  height: 600px;
  background-image:url(../assets/333.jpg);
  /* opacity: 0.5; */
  background-size: 100% 100%;
}

#footer{
  width: 100%;
  height: 100px;
  /* background-color: black; */
}
.demo-ruleForm {
  width: 380px;
  height: 450px;
  background-color: #ffffff;
  position: relative;
  margin: auto;
  line-height: 380px;
  top: 80px;
}
.demo-user {
  width: 300px;
  padding-top: 50px;
}
.demo-pass {
  width: 300px;
}
#demo-login-resing {
  text-align: left;

}
#demo-login-resing .demo-login {
  width: 80px;
}
.chongzhi{
  float: right;
  padding-right: 80px
}

#demo-login-resing .demo-resing {
  width: 80px;
 
}
#res{
  float: left;
  margin-top: 0px;
  margin-left: 55px;
}

.footer-herder ul li{
  float: left;
  margin: 10px;
  /* width: 100px;
  background-color:olive */
}

.footer-nav ul li{
  float: left;
  margin: 10px;
}
#footer .footer-header{
  float: left;
  width: 20px;
}
#footer .footer-nav{
  float: left;
  width: 20px;
}
/* .dd{
  float: left;
} */

</style>
// login1() {
//       var self = this;
//       axios({
//         method: "POST",
//         url: "http://www.wxhwg.com:8080/mock/admin/login",
//         data: Qs.stringify({
//           username: self.ruleForm.username,
//           password: self.ruleForm.password
//         })
//       })
//         .then(response =>{
//           let result=response.data;
//           if(result.status=="200"){
//           window.sessionStorage.setItem("adminuserId", result.data.userId); //获取id
//           window.sessionStorage.setItem("adminuserUserName",result.data.userUserName);
//           this.$router.push("/backstage");
//           }else{
//             alert("用户名或密码错误");
//           }
//         })
//         .catch(function(error) {
//           alert(response.data.message);
//         });
//     }
//url(../assets/333.jpg)
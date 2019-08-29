<template>
  <div id="login">
    <div id="header" >
      <div class="herder-left">
      <img src="http://img.zcool.cn/community/01005a57c5309b0000018c1bb46711.jpg"/>
      </div>
      <div class="right">
      <p>欢迎来到塔里木网</p>
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
      <div id="res">
          <el-form-item id="res">
            <el-link type="success" @click="init()" class="demo-resigernow">立即注册</el-link>
          </el-form-item>
      </div>
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
    login() {
      var self = this;
      this.$axios({
        method: "POST",
        url: "http://localhost:8080/myproject/api/user/login",
        data: Qs.stringify({
          username: self.ruleForm.username,
          password: self.ruleForm.password
        })
      })
        .then(response => {
          let result = response.data;
          if(result.status=="200"){   
             this.$router.push({
            name: "home",
            params: {}
          });
        
          
          window.sessionStorage.setItem("userId", response.data.data.userId); //获取id
          window.sessionStorage.setItem(
            "userUserName",
            response.data.data.userUserName
          );//获取姓名
          }
          if(result.status=="404"){
            this.$message.error('错了哦，请你确定你的账户和密码是否正确');
          }
        })
        .catch(function(error) {
            this.$message.error('错了哦，请你确定你的账户和密码是否正确');
        });
    },

    


    // login1() {
    //   const self = this;
    //   //判断复选框是否被勾选 勾选则调用配置cookie方法
    //   if (self.checked == true) {
    //     //传入账号名，密码，和保存天数3个参数
    //     self.setCookie(self.ruleForm.username, self.ruleForm.password, 7);
    //   } else {
    //     console.log("清空Cookie");
    //     //清空Cookie
    //     self.clearCookie();
    //   }
    // },
    // //设置cookie
    // setCookie(c_name, c_pwd, exdays) {
    //   var exdate = new Date(); //获取时间
    //   exdate.setTime(exdate.getTime() + 24 * 60 * 60 * 1000 * exdays); //保存的天数
    //   //字符串拼接cookie
    //   window.document.cookie =
    //     "username" + "=" + c_name + ";path=/;expires=" + exdate.toGMTString();
    //   window.document.cookie =
    //     "password" + "=" + c_pwd + ";path=/;expires=" + exdate.toGMTString();
    // },

    // //读取cookie
    // getCookie: function() {
    //   if (document.cookie.length > 0) {
    //     var arr = document.cookie.split("; "); //这里显示的格式需要切割一下自己可输出看下
    //     for (var i = 0; i < arr.length; i++) {
    //       var arr2 = arr[i].split("="); //再次切割
    //       //判断查找相对应的值
    //       if (arr2[0] == "username") {
    //         //  console.log(arr2[1])
    //         this.ruleForm.username = arr2[1]; //保存到保存数据的地方
    //       } else if (arr2[0] == "password") {
    //         // console.log(arr2[1])
    //         this.ruleForm.password = arr2[1];
    //       }
    //     }
    //     this.checked = true;
    //   }
    // },
    // clearCookie: function() {
    //   this.setCookie("", "", -1); //修改2值都为空，天数为负1天就好了
    // },

    submitForm(formName) {
      // if(this.yanzheng == true){
        this.$refs[formName].validate(valid => {
        if (valid) {
         
          if(this.yanzheng == true){
     this.login();
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
  background-color: #f5d753;
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
left: 80px;
color: #f75a53;
}
#nav{
  width: 100%;
  height: 500px;
  background-image: url(http://ku.90sjimg.com/back_pic/04/52/93/355860d4b9e0087.jpg);
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
  left: 75px;
  top: 0px;
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
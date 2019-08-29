<template>
  <div id="resinger">
    <div id="header">
      <img src="http://img.zcool.cn/community/01005a57c5309b0000018c1bb46711.jpg"/>
    </div>

      <div id="nav">
    <el-form
      :model="ruleForm"
      status-icon
      :rules="rules"
      ref="ruleForm"
      label-width="70px"
      class="demo-ruleForm"
    >

      <el-form-item label="用户名" prop="username" style="width: 400px">
        <el-input type="text" v-model="ruleForm.username"></el-input>
      </el-form-item>

      <el-form-item label="密码" prop="password" style="width: 400px">
        <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="确认密码" prop="checkpassword" style="width: 400px">
        <el-input type="password" v-model="ruleForm.checkpassword" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="电话" prop="telphone" style="width: 400px">
        <el-input v-model="ruleForm.telphone"></el-input>
      </el-form-item>

      <el-form-item label="地址" prop="address" style="width: 400px">
        <el-input v-model="ruleForm.address"></el-input>
      </el-form-item>

      <el-form-item label="商店名称" prop="shopname" style="width: 400px">
        <el-input v-model="ruleForm.shopname"></el-input>
      </el-form-item>

      <el-form-item class="demo-login-res-dia">
        <el-button type="danger" @click="submitForm('ruleForm')" class="demo-login">提交</el-button>
        <el-button @click="resetForm('ruleForm')" class="demo-res">重置</el-button>
        <el-button @click="exit()" class="demo-exit">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
  <div id="footer"></div>
  </div>
  
</template>

<script>
import axios from "axios";
import Qs from "qs";

export default {
  name: "resinger",
  data() {
    var checkUsername = function(rule, value, callback) {
      if (!value) {
        return callback(new Error("用户名不能为空"));
      } else {
        callback();
      }
    };
    var checkTelphone = function(rule, value, callback) {
      if (!value) {
        return callback(new Error("电话不能为空"));
      } else {
        callback();
      }
    };
    var checkShopname = function(rule, value, callback) {
      if (!value) {
        return callback(new Error("商店名称不能为空"));
      } else {
        callback();
      }
    };
    var checkAddress = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("地址不能为空"));
      } else {
        callback();
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm.checkpassword !== "") {
          this.$refs.ruleForm.validateField("checkpassword");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        username: "",
        password: "",
        checkpassword: "",
        address: "",
        telphone: "",
        shopname: ""
      },
      rules: {
        username: [
          { validator: checkUsername, trigger: "blur" },
          { pattern: /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/, message: "请输入正确的用户名" } //用户名正则验证（中英文数字下划线）
        ],
        password: [
          { validator: validatePass, trigger: "blur" },
          {
            min: 6,
            max: 16,
            message: "密码长度在必须 6 到 16 个字符",
            trigger: "blur"
          } //密码正则验证
        ],
        checkpassword: [{ validator: validatePass2, trigger: "blur" }],

        telphone: [
          { validator: checkTelphone, trigger: "blur" },
          {
            pattern: /^1{1}[3-9]{1}[0-9]{9}$|110$|120$|119$/,
            message: "请输入正确的电话号码"
          } //电话正则验证
        ],
        address: [
          { validator: checkAddress, trigger: "blur" },
          {
            pattern: /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/,
            message: "请输入正确的地址"
          } //地址正则验证
        ],
        shopname: [
          { validator: checkShopname, trigger: "blur" },
          {
            pattern: /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/,
            message: "请输入正确的地址"
          } //商店正则验证
        ]
      }
    };
  },
  methods: {
    //返回上一级
    exit() {
      window.history.go(-1);
    },
    //注册方法
    register() {
      axios({
        method: "POST",
        url: "http://localhost:8080/myproject/api/user/add",
        data: Qs.stringify({
          userUserName: this.ruleForm.username,
          userPassword: this.ruleForm.password,
          userTelphone: this.ruleForm.telphone,
          userAddress: this.ruleForm.address,
          userShopName: this.ruleForm.shopname
        })
      })
        .then(function(response) {
          console.log(response);
          // alert(response.data.message);
        })
          this.$message({
          message: '恭喜你，你已经成功注册了账号，赶快去登陆吧',
          type: 'success'
        })
  
        .catch(function(error) {
          // console.log(error);
          // alert(response.data.message);
        });
             this.$message.error('加入失败了,重新进行注册吧');
    },

    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.register(); //调用注册方法
          this.exit(); //调用返回方法
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>
<style scoped>
img{
    display: block;
}
/* #resinger {
 
 
  opacity: 0.5;
} */
#header{

  /* background-color:#fde1cb; */
  height: 80px;
}
#header img{
  position: relative;
  left: 120px;
width: 150px;
height: 80px;
}

#nav{
  background-image: url(http://bpic.588ku.com/back_pic/04/57/51/3858673d9a1cda2.jpg);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  height: 500px;
  width: 100%;
  /* background-color: aqua; */
}
#footer{
  height: 180px;
  width: 100%;
/* background-color: black; */
}
.demo-ruleForm {
  width: 500px;
  height:450px;
  /* background-color:blue; */
  position: relative;
  left: 500px;
  top: 50px;
}
</style>

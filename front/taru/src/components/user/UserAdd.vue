<template>
    <div class="useradd">
      <div id="h-2">
        <p>用户添加</p>
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
        <div class="in-input">
      <el-form-item label="用户名" prop="username" style="width: 400px">
        <el-input type="text" v-model="ruleForm.username"></el-input>
      </el-form-item>
      </div>

        <div class="in-input">
      <el-form-item label="密码" prop="password" style="width: 400px">
        <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
      </el-form-item>
        </div>  


    <div class="in-input">
      <el-form-item label="电话" prop="telphone" style="width: 400px">
        <el-input v-model="ruleForm.telphone"></el-input>
      </el-form-item>
    </div>

    <div class="in-input">
      <el-form-item label="地址" prop="address" style="width: 400px">
        <el-input v-model="ruleForm.address"></el-input>
      </el-form-item>
    </div>

<div class="in-input">
      <el-form-item label="商店名称" prop="shopname" style="width: 400px">
        <el-input v-model="ruleForm.shopname"></el-input>
      </el-form-item>
    </div>

<div class="in-input" id="in-btn">
      <el-form-item class="demo-login-res-dia">
        <el-button type="danger" @click="submitForm('ruleForm')" class="demo-login">提交</el-button>
        <el-button @click="revest()" class="demo-res">重置</el-button>
      </el-form-item>
      </div>
    </el-form>
  </div>
    </div>    
</template>

<script>
export default {
    name:'useradd',
    components:{

    },
    data(){
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
    return {
      ruleForm: {
        username: "",
        password: "",
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
    methods:{
        addUser(){
            this.$axios.post("http://localhost:8080/myproject/api/backstage/user/add",this.$qs.stringify({
                    userUserName:this.ruleForm.username,
                    userPassword:this.ruleForm.password,
                    userTelphone:this.ruleForm.telphone,
                    userAddress:this.ruleForm.address,
                    userShopName:this.ruleForm.shopname
            }))
            .then(res=>{
                let result = res.data;
                if(result.status == 200){
                    this.$message({
                        type: 'success',
                        message: '添加成功!'
                    });
                    this.revest()
                }else{
                    this.$message({
                        type: 'warn',
                        message: result.message
                    });
                }
            })
            .catch(error=>{
                alert("未知错误")
            })
        },
        revest(){
            this.ruleForm.username='',
            this.ruleForm.password='',
            this.ruleForm.telphone='',
            this.ruleForm.address='',
            this.ruleForm.shopname='',
            this.ruleForm.comment=''
        },

        submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.addUser(); //调用注册方法
          this.revest(); //调用返回方法
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },

    }
}
</script>

<style scoped>
*{
    margin: 0;
    padding: 0;
}
.useradd{
    width: 1300px;
    height: 690px;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
    box-shadow:0px 0px 5px lightgray;
    /* background:#e1f1f0; */
    padding-top: 20px;
}
#h-2{
  font-size: 20px;
  margin-left: 40px;
}

#nav{
    width: 450px;
    height: 500px;
    background:#ffffff;
    display: inline-block;
    margin-left:450px;
    margin-top: 80px;
    box-shadow:0px 0px 10px lightgray;
 
} 

.in-input{
   margin-left: 15px;
   margin-top: 35px;
}

#in-btn{
    margin-left: 75px;
}

.demo-login{
    margin-right: 20px;
}
</style>



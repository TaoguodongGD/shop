<template>
  <div id="addshop">
    <div id="header">
      <p>添加商品</p>
    </div>
    <div id="form">
      <div id="row" style>
        <div style="float:left; ">
          <div id="word">
            <p>商品名称：</p>
          </div>
          <input type="text" id="text" v-model="productName" style="width:540px;"  v-on:blur="AproductName()">
          <p style="color:red; font-size:13px;" v-show="isshow">商品名称不能为空</p>
        </div>
      </div>
      <div id="right">
        <!-- <div id="addphoto"> -->
          <el-upload
            class="upload-demo"
            action="http://localhost:8080/api/backstage/upload"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            name='image-file'
            :on-success="addphoto"
            :file-list="fileList"
            list-type="picture"
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
          <!-- <el-upload
                        class="upload-demo"
                        action="http://www.wxhwg.com:8080/mock/common/upload"
                        :show-file-list="false"
                        name='image-file'
                        :on-success="addphoto"
                        :before-upload="beforeAvatarUpload">
                        <img v-if="imageUrl" :src="imageUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>-->
        <!-- </div> -->
      </div>
      <div id="row" style="width:320px; margin-left:1px;">
        <div style="float:left;">
          <div id="word">
            <p>商品序列：</p>
          </div>
          <input type="text" id="text" v-model="productCode" v-on:blur="AproductCode()">
          <p style="color:red; font-size:13px;" v-show="isshow1">{{mes1}}</p>
        </div>
      </div>
      <div id="row" style="width:310px;">
        <div style="float:left;">
          <div id="word">
            <p>品牌：</p>
          </div>
          <input type="text" id="text" v-model="productBrandId" v-on:blur="AproductBrandId()">
          <p style="color:red; font-size:13px;" v-show="isshow2">{{mes2}}</p>
        </div>
      </div>
      <!-- 添加照片 -->

      <div id="row2">
        <div>
          <div id="word">
            <p>单价：</p>
          </div>
          <input type="text" id="text2" v-model="productSmallPrice" v-on:blur="AproductSmallPrice()">
          <div id="word2">￥</div>
          <p style="color:red; font-size:13px;" v-show="isshow3">{{mes3}}</p>
        </div>
      </div>
      <div id="row2">
        <div>
          <div id="word" style="margin-left:0px;">
            <p>总价：</p>
          </div>
          <input type="text" id="text2" v-model="productLargerPrice" v-on:blur="AproductLargerPrice()">
          <div id="word2">￥</div>
          <p style="color:red; font-size:13px;" v-show="isshow4">{{mes4}}</p>
        </div>
      </div>
        <div id="row2">
          <div id="word">
            <p>小规格：</p>
          </div>
          <input type="text" id="text2" style="width：200px;" v-model="productStandard"  v-on:blur="AproductStandard()">
          
          <select id="sel" v-model="productSmallUnit">
            <option value="包">包</option>
            <option value="袋">袋</option>
            <option value="个">个</option>
          </select>
          <p style="color:red; font-size:13px;" v-show="isshow5">{{mes5}}</p>
        </div>
        
        <div id="row2">
          <div id="word" style="width:90px;">
            <p>大规格：</p>
          </div>
          <input type="text" id="text2" v-model="productLargerStandard" v-on:blur="AproductLargerStandard()">
          <select name="city" id="sel" v-model="productLargerUnit">
            <option value="件">件</option>
            <option value="箱">箱</option>
          </select>
          <p style="color:red; font-size:13px;" v-show="isshow6">{{mes6}}</p>
        </div>
        
      
      
      <div id="row2">
        <div>
          <div id="word">
            <p>商品类名：</p>
          </div>
          <!-- <select
            name="city"
            id="sel"
            style="width:215px; margin-left:0px;"
            v-model="productCategoryId"
          >
            <option
              v-for="item in list"
              :key="item.categoryId"
              :label="item.categoryName"
              value="item.categoryId"
            >{{item.categoryName}}</option>
          </select> -->
          <div >
           <el-select v-model="productCategoryId" placeholder="请选择"> 
            <el-option
              v-for="item in list"
              :key="item.categoryId"
              :label="item.categoryName"
              :value="item.categoryId"
            ></el-option>
          </el-select>
          </div>
          
        </div>
      </div>
      <div id="row2">
        <div>
          <div id="word">
            <p>排序：</p>
          </div>
          <input type="text" id="text2" style="margin-right:70px;" v-model="productOrder" v-on:blur="AproductOrder()">
          <p style="color:red; font-size:13px;" v-show="isshow8">{{mes8}}</p>
        </div>
      </div>
      <div id="row2">
        <div>
          <div id="word">
            <p>缺货：</p>
          </div>
          <div id="rad" >
            <el-radio v-model="productIsLack" label="1"  size="mini">是</el-radio>
            <el-radio v-model="productIsLack" label="0"  size="mini">否</el-radio>
          </div>
        </div>
      </div>
      <div id="row2">
        <div id="word">
          <p>在销：</p>
        </div>
        <div id="rad">
          <el-radio v-model="productIsSale" label="1"  size="mini">是</el-radio>
          <el-radio v-model="productIsSale" label="0"  size="mini">否</el-radio>
        </div>
      </div>
      <div id="row">
        <div>
          <input id="ok" type="button" value="立即添加" @click="addshop(),A()">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      imageUrl: "",
        //商品名称
        productName: "",
        //产品序列号
        productCode: "",
        //品牌或者品牌编号
        productBrandId: "",
        //图片名称
        productPhoto: "",
        //小单位价格
        productSmallPrice: "",
        //大单位价格
        productLargerPrice: "",
        //小单位规格
        productStandard: "",
        //小单位
        productSmallUnit: "",
        //大单位规格
        productLargerStandard: "",
        //大单位
        productLargerUnit: "",
        //类别编号
        productCategoryId: "",
        //是否在销
        productIsSale:"",
        //是否缺货
        productIsLack:"",

        //状态
        productStatus: 1,
        //排序
        productOrder: "",
        //建议零售价
        productSuggestPrice: 1,
        list: [],
        isshow:false,
        isshow1:false,
        mes1:"",
        isshow2:false,
        mes2:"",
        isshow3:false,
        mes3:"",
        isshow4:false,
        mes4:"",
        isshow5:false,
        mes5:"",
        isshow6:false,
        mes6:"",
        isshow7:false,
        mes7:"",
        isshow8:false,
        mes8:"",
        isshow9:false,
        mes9:"",
        fileList: [
          {
            name: "food.jpeg",
            url:
              "https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100"
          },
          {
            name: "food2.jpeg",
            url:
              "https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100"
          }
        ]
      }
  },
  created: function() {
    this.init();
  },
  methods: {
    handleRemove(file, fileList) {
      
    },
    handlePreview(file) {
      
    },
    //添加图
    addphoto: function(response) {
      this.productPhoto = response.data;
       this.$message.success("添加图片成功")
    },
    //表单验证
     //商品名称
      AproductName(){
        this.isshow=false;
        if(this.productName=='' ){
              this.isshow=true;
        }
      },
        //商品序列
      AproductCode(){
           this.isshow1=false;
        if(this.productCode==''){
          this.mes1="商品序列不能为空";
              this.isshow1=true;
        }
        if(/\D/.test(this.productCode)){
           this.mes1="请输入数字";
              this.isshow1=true;
        }
      },
        //商品品牌
        AproductBrandId(){
           this.isshow2=false;
        if(this.productBrandId==''){
          this.mes2="商品品牌不能为空";
              this.isshow2=true;
        }
      },
      //商品单价
        AproductSmallPrice(){
           this.isshow3=false;
        if(this.productSmallPrice==''){
          this.mes3="单价不能为空";
              this.isshow3=true;
        }
        if(/[^\d^\.]+/g.test(this.productSmallPrice)){
           this.mes3="格式不正确";
              this.isshow3=true;
        }
      },
      //商品总价
        AproductLargerPrice(){
           this.isshow4=false;
        if(this.productLargerPrice==''){
          this.mes4="总价不能为空";
              this.isshow4=true;
        }
        if(/[^\d^\.]+/g.test(this.productLargerPrice)){
           this.mes4="格式不正确";
              this.isshow4=true;
        }
      },
      //商品小规格
        AproductStandard(){
           this.isshow5=false;
        if(this.productStandard==''){
          this.mes5="商品小规格不能为空";
              this.isshow5=true;
        }
        if(/[^\d^\.]+/g.test(this.productSmallPrice)){
           this.mes5="格式不正确";
              this.isshow5=true;
        }
      },
      //商品大规格
        AproductLargerStandard(){
           this.isshow6=false;
        if(this.productLargerStandard==''){
          this.mes6="商品大规格不能为空";
              this.isshow6=true;
        }
        if(/\d+[*]\d    /g.test(this.productLargerStandard)){
           this.mes6="格式不正确";
              this.isshow6=true;
        }
      },
      
      //商品类名
      //   AproductCategoryId(){
      //      this.isshow7=false;
      //   if(this.productCategoryId==''){
      //     this.mes7="商品类名不能为空";
      //         this.isshow7=true;
      //   }
      // },
      //商品排序
        AproductOrder(){
           this.isshow8=false;
        if(this.productOrder==''){
          this.mes8="商品排序不能为空";
              this.isshow8=true;
        }
        if(/\D/.test(this.productOrder)){
           this.mes8="请输入数字";
              this.isshow8=true;
        }
      },
      A:function(){
          this.AproductName();
          this.AproductCode();
          this.AproductBrandId();
          this.AproductSmallPrice();
          this.AproductLargerPrice();
          this.AproductStandard();
          this.AproductLargerStandard();
          this.AproductOrder();
      },
    //添加商品
    addshop() {
      this.$axios({
        method: "POST",
        url: "http://localhost:8080/myproject/api/backstage/product/add",
        data: this.$qs.stringify({
          //商品名称
          productName: this.productName,
          //产品序列号
          productCode: this.productCode,
          //品牌或者品牌编号
          productBrandId: this.productBrandId,
          //图片名称
          productPhoto: this.productPhoto,
          //小单位价格
          productSmallPrice: this.productSmallPrice,
          //大单位价格
          productLargerPrice: this.productLargerPrice,
          //小单位规格
          productStandard: this.productStandard,
          //小单位
          productSmallUnit: this.productSmallUnit,
          //大单位规格
          productLargerStandard: this.productLargerStandard,
          //大单位
          productLargerUnit: this.productLargerUnit,
          //类别类号
          productCategoryId: this.productCategoryId,
          //是否在销
          productIsSale: this.productIsSale,
          //是否缺货
          productIsLack: this.productIsLack,
          //状态
          productStatus: this.productStatus,
          //排序
          productOrder: this.productOrder,
          //建议零售价
          productSuggestPrice: this.productSuggestPrice,
        })
      })
        .then(response => {
          let result = response.data;
          if (result.status == "200") {
             this.$message.success("添加商品成功")
          } else if (result.status == "500") {
             this.$message.warning("传输数据错误");
          }
        })
        .catch(function(error) {
         this.$message.error("系统错误");
        });
    },
    //查询类别ID
    init() {
      this.$axios({
        method: "GET",

        url: "http://localhost:8080/myproject/api/Category/CategoryList",

        parmars: {}
      })
        .then(response => {
          let result = response.data;
          console.log(result);
          if (result.status == "200") {
            this.list = result.data;
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    }
    //添加
  }
};
</script>

<style scoped>
#header {
  margin-top: 0%;
  height: 50px;
  width: 100%;
  
  margin-bottom: 20px;
}
#header p {
  float: left;
  margin-top: 10px;
  margin-left: 35px;
  font-size: 20px;
}
#addshop {
  /* background-color: #cdecee; */
  width: 1300px;
  height: 690px;
  float: left;
  box-shadow: 0px 0px 10px lightgray;
  
}
#form {
  width: 1100px;
  height: 550px;
  background-color: #ffffff;
  padding-bottom: 20px;
  padding-left: 20px;
  padding-right: 20px;
  margin-left: 100px;
  box-shadow:0px 0px 5px lightgray;
  float: left;
}
#right {
  width: 350px;
  height: 500px;
  margin-top: 30px;
  margin-right: 40px;
  float: right;
}
#row {
  width: 650px;
  height: 40px;
  float: left;
  background-color: #ffffff;
  margin-top: 40px;
}
#row2 {
  width: 320px;
  height: 40px;
  float: left;
  background-color: #ffffff;
  margin-top: 40px;
}
#word {
  float: left;
  text-align: right;
  width: 90px;
  height: 40px;
  line-height: 40px;
  font-size: 14px;
  color: #606266;
  line-height: 40px;
}
#word2 {
  float: left;
  width: 30px;
  height: 40px;
  line-height: 40px;
  font-size: 14px;
  color: #606266;
  line-height: 40px;
  margin-right: 45px;
}
#text {
  height: 40px;
  width: 215px;
  color: #606266;
  line-height: 40px;
  float: left;
  background-color: #fff;
  border-radius: 5px;
  border: 1px solid #dcdfe6;
}
#text2 {
  height: 40px;
  width: 150px;
  color: #606266;
  line-height: 40px;
  float: left;
  background-color: #fff;
  border-radius: 5px;
  border: 1px solid #dcdfe6;
  box-sizing: border-box;
  display: inline-block;
}
#text:focus {
  border: 1px solid #ebb6c6;
}
#text2:focus {
  border: 1px solid #ebb6c6;
}
#avatar-uploader {
  float: right;
  margin-top: 50px;
}
.avatar-uploader-icon[data-v-4fd73326] {
  height: 158px;
  border: 1px solid #e3e5eb;
}
#addphoto {
  margin-top: 25px;
  margin-right: 70px;
}
#sel {
  width: 55px;
  height: 40px;
  float: left;
  line-height: 40px;
  float: left;
  background-color: #fff;
  border-radius: 5px;
  border: 1px solid #dcdfe6;
  margin-right: 15px;
  margin-left: 10px;
}
#sel:focus {
  border: 1px solid #ebb6c6;
}
.el-select {
  width: 215px;
  float: left;
  margin-right: 10px;
}
#distance {
  margin-left: 100px;
}
#rad {
  width: 185px;
  height: 38px;
  line-height: 38px;
  float: left;
}
#ok {
  float: right;
  width: 250px;
  line-height: 1;
  margin-right: 20px;
  background-color: #fef0f0;
  border: 1px solid #dcdfe6;
  color: #f9a7a7;
  text-align: center;
  transition: 0.1s;
  font-weight: 500;
  padding: 12px 20px;
  font-size: 14px;
  border-radius: 4px;
}
#ok:hover {
  background-color: #f56c6c;
}
#no {
  line-height: 1;
  cursor: pointer;
  background: #fff;
  border: 1px solid #dcdfe6;
  color: #606266;
  text-align: center;
  outline: none;
  transition: 0.1s;
  font-weight: 500;
  padding: 12px 20px;
  font-size: 14px;
  border-radius: 4px;
}
.avatar-uploader .el-upload:hover {
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 158px;
  height: 158px;
  line-height: 158px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

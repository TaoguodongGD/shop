<template>
    <div id="fixed-right">
            <div id="f1">
              <ul>
                <li>
                  <a href="javascript:void(0)" id="aa">排序:</a>
                </li>
                <li>
                  <a href="javascript:void(0);">
                    销量
                    <i class="iconfont">&#xe603;</i>
                  </a>
                </li>
                <li>
                  <a href="javascript:void(0);">
                    新品
                    <i class="iconfont">&#xe603;</i>
                  </a>
                </li>
                <li>
                  <a href="javascript:void(0);">
                    价格
                    <i class="iconfont" id="i1">&#xe6b7;</i>
                  </a>
                </li>
                <li>
                  <div id="price-range">
                    价格区间：
                    <input type="text" id="text">-
                    <input type="text" id="text">
                    <input type="button" value="确定" id="btn">
                  </div>
                </li>
              </ul>
              <div id="page">
                <span>
                  共 <em id="em">{{total}}</em> 个商品   <em id="em">{{page}}</em><em>/{{Math.ceil(total/Number(24))}}</em>
                  <a href="javascript:void(0)" @click="befpage(),categoryfind();">上一页</a>
                  <a href="javascript:void(0)" @click="laspage(),categoryfind();">下一页</a>
                </span>
              </div>
            </div>
            <div id="box">
              <ul>
                <li v-for="product in a" :key="product.productId" >
                  <div id="img" @click="productMes(product.productId)">
                    <img :src="'http://localhost:8080/myproject/api/static/'+product.productPhoto" alt>
                  </div>
                  <div id="name">{{product.productName}}</div>
                  <div id="price">{{product.productSmallPrice}}￥</div>
                  <div id="btn">
                    <input type="button" @click="addcar(product.productId,product.productSmallUnit,product.productSmallPrice,)" value="加入购物车">
                  </div>
                </li>
              </ul>
            </div>
          </div>
</template>

<script>
export default {
        data(){
            return{
                page:1,
                a:[{}],
                total:''
            }
        },
        created: function() {
            this.categoryfind();
        },
        
        methods:{
             //上一页
            befpage:function(){
                if(this.page>1){
                    this.page-=Number(1);
                    //Bus.$emit('val', this.page);
                }
            },
            //下一页
            laspage:function(){
                if(this.page<Math.ceil(this.total/Number(24))){
                this.page+=Number(1);
                //Bus.$emit('val', this.page);
                }
            },
            //根据类名id查询商品
            categoryfind:function() {
            //this.changepage(),
                this.$axios({
                    method: "GET",
                    url: "http://localhost:8080/myproject/api/product/searchList",
                    params: {
                            categoryId: "02c6fd20-808b-441e-b02c-edb5b7e95df1",
                            // productIsLack:'',
                            // orderStr:'',
                            // pageSize: 24,
                            // pageNum: this.page
                    }
                    //get
                })
                .then(response => {
                let result = response.data;
                if (result.status == "200") {
                    this.a = result.data.list;
                    this.total=result.data.total
                }
                })  
                .catch(function(error) {});
            },
            //加入购物车
            addcar(productId,productSmallUnit,productSmallPrice){
                this.$axios({
                    method:"POST",
                    url:"http://localhost:8080/myproject/api/car/insert",
                    data: this.$qs.stringify({
                        detailProductId:productId,
                        detailProductCount:"1",
                        detailProductUnit:productSmallUnit,
                        detailProductPrice:productSmallPrice,
                        detailComment:null,
                        //需要对接
                        detailUserId:sessionStorage.getItem("UserID")
                    })
                    }).then(response=>{
                    let result = response.data;
                    if (result.status == "200") {
                        alert("加入购物车成功");
                        }else{
                        alert("加入购物车失败");
                        }
                    }).catch(error=>{

                })
            },
             //商品详情页跳转
            productMes(id){
                sessionStorage.setItem("prodictId",id);
                this.$router.push("/Product");
                alert(id);
            },
        }
}
</script>

<style>
    #fixed-right {
      width: 1010px;
      height: 455px;
      float: left;
    }
    #f1 {
      background-color: #f7f7f7;
      float: left;
      width: 1010px;
      height: 40px;
    }
    #f1 ul li {
      min-width: 80px;
      height: 40px;
      float: left;
      color: black;
      font-size: 13px;
      line-height: 40px;
      text-align: center;
      border-right: 1px solid #eaeaea;
    }
    #f1 #aa {
      font-weight: 600;
    }
    #f1 li i {
      font-size: 13px;
      color: #cccccc;
    }
    #f1 #i1 {
      font-size: 10px;
    }
    #f1 #price-range {
      padding-left: 25px;
      padding-right: 25px;
    }
    #f1 ul li:hover i {
      color: #e61952;
    }
    #f1 ul li:hover a {
      color: #e61952;
    }
    #f1 li a {
      color: black;
    }
    #f1 #text {
      width: 44px;
      height: 20px;
      margin-right: 8px;
      margin-left: 8px;
      border: 1px solid #eaeaea;
    }
    #f1 #btn {
      width: 45px;
      height: 20px;
      margin-left: 5px;
      background-color: #f7f7f7;
      color: #666666;
      border: 1px solid #eaeaea;
      font-size: 12px;
    }
    #f1 #page{
      line-height: 40px;
      float: right;
      margin-right: 20px;
    }
    #page a{
      color: #666666;
      border: 1px solid #e4e4e4;
      margin-left: 8px;
    }
    #page a:focus{
      color: #e61952;
    }
    #page #em{
      color: #e61952;
      font-size: 12px;
      font-style:normal
    }
    #page span{
      font-size: 12px;
    }
    #box {
      background-color: #ffffff;
      float: left;
      width: 1010px;
      min-height: 550px;
      display: flex;
      flex-direction: row;
      flex-wrap: wrap;
    }
    #box li {
      width: 200px;
      height: 250px;
      float: left;
      margin-left: 40px;
      margin-top: 20px;
    }
    #box li:hover {
      box-shadow: 0px 0px 5px #e61952;
      transform: (0, -20px, 0);
      transition-property: all;
      transition-duration: 0.3s;
      transition-timing-function: linear;
      transition-delay: 0s;
    }
    #box li #img {
      width: 180px;
      height: 150px;
      float: left;
      margin: auto;
      margin-left: 10px;
      margin-top: 10px;
    }
    #img img {
      width: 100%;
      height: 100%;
    }
    #box li #name {
      background-color: rgb(251, 244, 232);
      width: 180px;
      height: 25px;
      float: left;
      text-align: center;
      margin-left: 10px;
      font-size: auto;
      line-height: 25px;
    }
    #box li #price {
      background-color: rgb(251, 244, 232);
      color: #e61952;
      width: 180px;
      height: 25px;
      float: left;
      text-align: center;
      margin-left: 10px;
      font-size: 15px;
      font-weight: 500;
      line-height: 25px;
    }
    #box li #btn {
      background-color: rgb(251, 244, 232);
      width: 180px;
      height: 30px;
      float: left;
      text-align: center;
      margin-left: 10px;
    }
    #btn input {
      background-color: #e61952;
      color: #fff;
      width: 80px;
      height: 30px;
      font-size: 12px;
      letter-spacing: 1px;
      border: 3px solid #e61952;
      border-radius: 5px;
    }
</style>

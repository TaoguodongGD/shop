<template>
  <div class="cart">
    <div id="allheader">
      <A-Header></A-Header>
      <s-header></s-header>
    </div>

    <div class="cart_title">
      <div class="fl">
        我的购物车
        <span>因为活动较为频繁，退出登录后，购物中的促销商品将会被移除！</span>
      </div>
    </div>
    <div class="wrap">
      <form action id="formCart">
        <table class="cart_table">
          <tbody>
            <tr>
              <th width="156" style="text-align:left; text-indent:14px;">
                <span class="deck"></span>
                <span style="padding-left:15px">图片</span>
              </th>
              <th width="447">商品信息</th>
              <th width="115">单价（元）</th>
              <th width="125">数量</th>
              <th width="121">小计（元）</th>
              <th width="116">操作</th>
            </tr>
            <tr>
              <td colspan="6">
                <div class="inner_wrap3 bg">
                  <table
                    class="inner_table"
                    width="1156"
                    cellspacing="0"
                    cellpadding="0"
                    border="0"
                  >
                    <tbody>
                      <tr v-for="(item ,index) in list" :key="index">
                        <td width="43"></td>
                        <td width="92">
                          <div class="s-photo">
                            <img
                              :src="'http://localhost:8080/myproject/api/static/'+item.product.productPhoto"
                            />
                          </div>
                        </td>
                        <td width="417">{{item.product.productName}}</td>
                        <td width="115">{{item.product.productSmallPrice}}</td>
                        <td width="125">
                          <span>
                            <button
                              type="button"
                              @click="item.detailProductCount>1?item.detailProductCount--:''"
                            >-</button>
                          </span>
                          {{item.detailProductCount}}
                          <span>
                            <button type="button" @click="item.detailProductCount++">+</button>
                          </span>
                        </td>
                        <td width="121">{{item.product.productSmallPrice*item.detailProductCount}}</td>
                        <td width="95">
                          <a href="javascript:void(0)" @click="deletepro(index,item.detailId);">删除</a>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </td>
            </tr>
            <tr class="s-bot">
              <td colspan="6">
                <div class="acount">
                  <div class="s-t">
                    <div class="fixed">
                      <p class="fl">
                        <input type="checkbox" class="check_all" style="margin-left:15px" />
                        <a href="#" class="color_blue">批量删除</a>
                        <span style="color:#b199b1; padding-left:3px;">|</span>
                        <a
                          href="javascript:void(0)"
                          class="color_blue"
                          @click="removeCart(id)"
                        >清空购物车</a>
                      </p>
                    </div>
                    <div class="fr price" id="total_goods_price2">
                      <p id="total_goods_price21">
                        <span>
                          共
                          <em>{{sum}}</em>件商品
                        </span>
                        <span>
                          共计：
                          <span class="price2">
                            <b>￥</b>
                            {{sumPrice}}
                            <!-- 总金额 -->
                          </span>
                        </span>
                      </p>
                    </div>
                  </div>
                  <div class="info">
                    <div>
                      <a class="a-btn" href="javascript:void(0)">
                        <button class="s-btn" @click="submitOrders()">提交订单</button>
                      </a>
                    </div>
                    <p class="fr total2">
                      <span id="total_goods_price">
                        总计（不含运费）：
                        <span class="price3">
                          <b>￥</b>
                          {{sumPrice}}
                        </span>
                      </span>
                    </p>
                  </div>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
    </div>
  </div>
</template>
<script>
import cnHeader from "@/components/cnHeader.vue";
import BHeader from "@/components/Header-2.vue";
import AHeader from "@/components/Header-1.vue";
export default {
    name:'cart',
    components:{
        's-header':cnHeader, //头组件
          "B-Header": BHeader,
          "A-Header":AHeader
    },
    data(){
        return{
             list:[],
             data:[],
             i:''
        }
    },
    created(){
        this.queryCart()
        
    },
    computed:{
            sum:function(){
                let count=0;
                let list=this.list;
                for(let i = 0 ; i < list.length;i++){
                count += parseInt(list[i].detailProductCount);
            }
                return count;
            },
            sumPrice:function(){
                let price=0;
                let list=this.list;
                for(let i = 0 ; i < list.length;i++){
                price += parseFloat(list[i].product.productSmallPrice*list[i].detailProductCount);
            }
                return price;
            }

    },
    methods:{
       
        deletepro(index,id){//删除单条
            this.list.splice(index,1);
            this.removeCart(id);
        },

        queryCart(){//查询购物车
            this.$axios.get('http://localhost:8080/myproject/api/car/querylist',{
                params:{
                    uid:sessionStorage.getItem("userId")//要数据
                }
            }).then(response=>{
                let result=response.data;
                if(result.status==200){
                    this.list=result.data;
                }
            })          
        },
        
         removeCart(id){//清空购物车
          var self = this;
            this.$axios.get('http://localhost:8080/myproject/api/car/delete',{
                params:{
                    detailId:id,
                    uid:sessionStorage.getItem("userId")

                }
            }).then(response=>{
                let result=response.data;
                if(result.status==200){
                    self.queryCart()
                    this.$message({
                            message: result.message,
                            type: 'success',
                            
                        });
                }
                if(result.status==500){
                    this.$message({
                        message:result.message,
                        type: 'warning'
                    });
                }
            })
        },
        submitOrders(){//提交
        // if(sessionStorage.getItem("userId")!==null){
          // this.$router.push("/shopCart");
          // }else{
          //   this.$message.warning("请登陆用户");
          //   this.$router.push("/Login");
          // }
          
            this.$axios.post('http://localhost:8080/myproject/api/order/commit',this.$qs.stringify({
                orderUserId:sessionStorage.getItem("userId"),//要数据
                orderTotalPrice:this.sumPrice,//要数据
                orderComment:""
                })
            )
            .then(response=>{
                    let result=response.data;
                    if(result.status==200){
                        this.data=result.data;
                        this.$message({
                            message: '订单已提交',
                            type: 'success'
                        });
                    }
                }).catch(error=>{
                    this.$message({
                        message: '未知错误',
                        type: 'warning'
                    });
                })
        }
    }
}
</script>
<style scoped>
.wrap {
  width: 1200px;
  margin: 0 auto;
}
.cart_title {
  margin: 30px 0;
  font-size: 24px;
  height: 24px;
  color: #333;
  font-family: "\5fae\8f6f\96c5\9ed1", "Microsoft YaHei", Arial, Helvetica,
    sans-serif, 宋体;
}
.fl {
  float: left;
  margin-left: 150px;
}
.cart_title span {
  font-size: 14px;
  color: #999;
  margin-left: 10px;
}
.cart_table {
  margin: 10px auto;
  border: 1px solid #e5e5e5;
  font-size: 13px;
  border-collapse: collapse;
  width: 1198px;
}
tbody {
  display: table-row-group;
  vertical-align: middle;
  border-color: inherit;
}
tr {
  display: table-row;
  vertical-align: inherit;
  border-color: inherit;
}
.deck {
  display: inline-block;
  width: 5px;
  height: 20px;
  background: #fd475a;
  position: relative;
  top: 3px;
  margin-right: 15px;
}
input {
  vertical-align: middle;
  position: relative;
  top: -2px;
  outline: none;
}
th {
  border-bottom: 1px solid #e5e5e5;
  color: #444;
  font-weight: 700;
  background: #f4f4f4;
  height: 38px;
  line-height: 38px;
  text-align: center;
}
.cart_table td {
  text-align: center;
  line-height: 20px;
}
.inner_wrap3 {
  padding: 0 20px;
}

.inner_wrap3 a {
  color: #333;
}
.inner_table {
  margin: 10px auto 10px;
  border: 1px solid #e5e5e5;
  border-bottom: none;
  font-size: 13px;
}
.inner_table tr {
  height: 115px;
  background: #fffcf5;
  color: #666;
  border-bottom: 1px solid #e5e5e5;
  text-align: center;
  line-height: 20px;
}
.s-photo img {
  height: 60px;
  width: 80px;
  margin-top: 30px;
  margin-left: 30px;
}
.s-bot td {
  height: 170px;
}
.acount {
  text-align: left;
  background: #f4f4f4;
  padding: 20px 20px 15px 20px;
  border-top: 1px solid #e5e5e5;
  margin-top: 10px;
  height: 160px;
}
.s-t {
  width: 1160px;
  height: 40px;
}
.fixed {
  display: block;
  min-height: 1%;
  height: 23px;
  float: left;
}
.color_blue {
  color: #458df0;
  padding-left: 15px;
}
.fr {
  float: right;
  margin-right: 60px;
}
.acount .price {
  text-align: right;
  color: #444;
}
p {
  display: block;
  -webkit-margin-before: 1em;
  -webkit-margin-after: 1em;
  -webkit-margin-start: 0px;
  -webkit-margin-end: 0px;
}
#total_goods_price2 {
  height: 21px;
  float: right;
}

#total_goods_price2 span {
  margin: 0 10px;
}
#total_goods_price2 em {
  color: #e61940;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 16px;
  padding: 0 4px;
  font-style: normal;
}
#total_goods_price2 .price2 {
  color: #e61940;
  display: inline-block;
  width: 120px;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 16px;
}
#total_goods_price2 .price2 b {
  font-weight: 500;
  font-size: 11px;
  font-family: 微软雅黑, "Microsoft YaHei", Arial, Helvetica, sans-serif, 宋体;
}
.info {
  border-top: 1px dashed #ccc;
  padding: 10px 0 0 14px;
  margin-top: 10px;
  display: block;
  min-height: 1%;
}
.s-btn {
  width: 171px;
  height: 49px;
  position: relative;
  top: 30px;
  color: #f4f4f4;
  background-color: #e61940;
  border-radius: 4px;
  float: right;
  border: none;
  margin-right: 20px;
  margin-bottom: 30px;
}
.cart_table .info .total2 {
  color: #444;
  font-size: 14px;
  float: right;
}
.cart_table .info .total2 em {
  color: #444;
  font-size: 14px;
  color: #e61940;
  font-size: 18px;
  margin-right: 30px;
  font-family: Arial, Helvetica, sans-serif;
  font-style: normal;
}
.cart_table .info .total2 .price3 {
  color: #e61940;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 26px;
  margin-right: 20px;
}
.cart_table .info .total2 .price3 b {
  font-weight: 500;
  font-size: 18px;
  font-family: 微软雅黑, "Microsoft YaHei", Arial, Helvetica, sans-serif, 宋体;
}
.cart_table td {
  text-align: center;
  line-height: 20px;
}
</style>


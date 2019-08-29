<template>
  <div id="product">
    <div id="header-all">
      <A-Header></A-Header>
      <B-Header style=" background-color: #ffffff;"></B-Header>
    </div>

    <div class="shop_list">
      <div>
        <div class="product_img">
          <!-- <img :src="web_static_url+photo"> -->

          <div
            class="preview-box"
            @mousemove="move($event)"
            @mouseleave="out"
            ref="previewBox"
            @mouseenter="enter"
          >
            <img class="ha" :src="previewImg" style="width:450px">
            <div class="mask" ref="hoverBox" v-show="maskShow"></div>
          </div>
          <div class="zoom-box" v-show="zoomVisiable" ref="zoomBox">
            <img class="ha" :src="zoomImg" ref="bigImg">
          </div>

          <div id="buttom" style="width:350px;height:120px; margin:auto;">
            <div id="d1" @mouseout="change2()" @mousemove="change1()">
              <img :src="uu" :style="boo1">
            </div>
            <div id="d2" @mouseout="change12()" @mousemove="change11()">
              <img :src="uu" :style="boo2">
            </div>
            <div id="d3" @mouseout="change22()" @mousemove="change21()">
              <img :src="uu" :style="boo3">
            </div>
          </div>
          <div class="img_foot">
            <i style="margin-left:20px;font-size:0.9rem" class="el-icon-star-off">收藏商品(12565人气)</i>
            <i style="margin-left:30px;margin-top:15px;font-size:0.7rem" class="el-icon-share">分享</i>
          </div>
        </div>
      </div>

      <!-- <div v-for="item in list" :key="item.productId">测试{{item.productName}}</div> -->
      <div class="product_list">
        <div class="product_name">{{list.productName}}</div>
        <div class="product_price">
          <div class="product_price-a">
            <p class="price_change" style="font-size:2rem">￥{{list.productSmallPrice}}</p>
            <div id="p1">
              <p>零食618</p>
            </div>
            <div id="p2">
              <p>为您节省0.5￥</p>
            </div>
          </div>
        </div>

        <div class="product_yun" style="border:1px">
          <div id="yun">
            <p style>
              运费：
              <i class="el-icon-arrow-down el-icon--right"></i>
            </p>
          </div>
        </div>

        <div class="el">
          <el-checkbox-group v-model="checkboxGroup1" style="height:50px;">
            <p style="font-size:15px;">
              口味：
              <i class="el-icon-arrow-down el-icon--right"></i>
            </p>
            <el-checkbox-button
              style="margin:3px"
              v-for="city in cities"
              :label="city"
              :key="city"
            >{{city}}</el-checkbox-button>
          </el-checkbox-group>
        </div>

        <div id="hr">
          <!-- <hr style="border:1px dashed #000;width:80%;"> -->
        </div>
        <div class="product_stand">
          <p style>规格：</p>
          <p class="stand" style="font-size:13px">{{list.productStandard}} {{list.productSmallUnit}}</p>
        </div>

        <div class="product_num">
          <p>数量：</p>
          <el-input-number
            style="float:left"
            size="small"
            v-model="num"
            @change="handleChange()"
            :min="1"
            :max="10"
            label="件"
          ></el-input-number>
        </div>
        <div class="product_stand">
          <p>商品状态：</p>
          <p class="stand" style="font-size:13px">{{shop_status(list.productIsLack)}}</p>
        </div>
        <div class="product_price_total">
          <div style="float:left;margin-left:22px">总价:</div>
          <p
            class="price_change"
            style="font-size:2rem;margin-left:10px"
          >￥{{list.productSmallPrice*this.num}}</p>
        </div>
        <div id="acar">
          <el-button
            @click="addShop()"
            class="el-icon-shopping-cart-2"
            type="danger"
            style=" margin:10px auto;margin-left: 80px;float:left;width:150px; margin-top:15px;"
          >加入购物车</el-button>

          <el-button
            style=" margin:10px  auto;margin-left: 60px;float:left;margin-top:15px; "
            plain
          >立即购买</el-button>
          <el-dropdown size="mini" placement="bottom-end" split-button>
            支付方式
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item class="el-icon-lock">余额宝</el-dropdown-item>
              <el-dropdown-item class="el-icon-present">微信</el-dropdown-item>
              <el-dropdown-item class="el-icon-headset">信用卡</el-dropdown-item>
              <el-dropdown-item class="el-icon-sort">花呗</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>

        <div class="foot">
          <!-- <el-link href="https://element.eleme.io" target="_blank">默认链接</el-link> -->
          <el-link type="info">产品承诺</el-link>
          <el-link type="info">破损包退</el-link>
          <el-link type="info">正品保证</el-link>
          <el-link type="info">极速退款</el-link>
          <el-link type="info">赠送运费险</el-link>
          <el-link type="info">七天无理由退款</el-link>
        </div>
      </div>
    </div>
    <el-divider style=" clear: both;
  margin-top: 420px;"></el-divider>
    <div class="shop_detil">
      <el-tabs type="border-card">
        <el-tab-pane label="用户管理">
          <img
            src="https://img.alicdn.com/imgextra/i4/3108195592/TB2sPSjXtVIxuRjHxmnXXc6dpXa_!!3108195592.jpg"
            style="height: 600px;width: 600px;"
          >
          <img
            src="https://img.alicdn.com/imgextra/i3/3108195592/TB2tVOhXsXIxuRkSRThXXcgupXa_!!3108195592.jpg"
            style="height: 600px;width: 600px;"
          >
          <img
            src="https://img.alicdn.com/imgextra/i1/3108195592/TB2GjClmYorBKNjSZFjXXc_SpXa_!!3108195592.jpg"
            style="height: 600px;width: 600px;"
          >
        </el-tab-pane>
        <el-tab-pane label="累计评论">累计评论</el-tab-pane>
      </el-tabs>
      <!-- <div data-am-widget="gotop" class="am-gotop am-gotop-fixed">
        <a href="#top" title="回到顶部">
          <span class="am-gotop-title">回到顶部</span>
          <i class="am-gotop-icon am-icon-chevron-up"></i>
        </a>
      </div>-->
      <!-- 回到顶部 -->
      <!-- <div
          style="width: 100%; text-align: center; color: rgb(25, 137, 250); line-height: 40px; box-shadow: 0px 0px 6px rgba(0,0,0,0.12); background-color: rgb(242, 245, 246);"
      >UP</div>-->
      <div class="el-backtop" style="right: 40px; bottom: 100px;">
        <a href="#top" title="回到顶部" style="color: rgb(25, 137, 250)">UP</a>
      </div>
    </div>
    <div id="cart" class="el-icon-shopping-cart-2" style="font-size:2rem" @click="tran()">{{count}}</div>
    <div class="ball-container">
      <!--小球 inner inner-hook-->
      <div v-for="ball in balls" :key="ball.index">
        <transition
          name="drop"
          @before-enter="beforeDrop"
          @enter="dropping"
          @after-enter="afterDrop"
        >
          <div class="ball" v-show="ball.show">
            <div class="inner inner-hook"></div>
          </div>
        </transition>
      </div>
    </div>
  </div>
</template>
    <router-view v-if="true"/>
  </div>
  <!-- </div> -->
</template>


<script>
import BHeader from "@/components/Header-2.vue";
import AHeader from "@/components/Header-1.vue";
function offset(el) {
  let top = el.offsetTop;
  let left = el.offsetLeft;
  if (el.offsetParent) {
    el = el.offsetParent;
    top += el.offsetTop;
    left += el.offsetLeft;
  }
  return {
    left: left,
    top: top
  };
}
const cityOptions = ["麻辣味", "五香味", "烧烤味", "变态辣", "蒜香味"];
export default {
  name: "product",
  components: {},
  data() {
    return {
      previewImg: "",
      zoomImg: "",
      zoomVisiable: false,
      maskShow: false,
      balls: [
        //小球 设为3个
        {
          show: false
        },
        {
          show: false
        },
        {
          show: false
        },
        {
          show: false
        },
        {
          show: false
        },
        {
          show: false
        }
      ],
      dropBalls: [],
      count: 0,
      productIds: sessionStorage.getItem("productId"),
      // window.sessionStorage.getItem("productId"),
      list: [],
      photo: "",
      web_static_url: "http://localhost:8080/myproject/api/static/",
      checkboxGroup1: ["麻辣味"],
      checkboxGroup2: ["五香味"],
      checkboxGroup3: ["烧烤味"],
      checkboxGroup4: ["变态辣"],
      checkboxGroup5: ["香辣味"],
      checkboxGroup6: ["蒜香味"],
      cities: cityOptions,
      num: 1,
      boo1: "border:3px solid white",
      boo2: "border:3px solid white",
      boo3: "border:3px solid white",
      uu: "",
      list1: []
    };
  },
  created() {
    this.init1();
  },

  methods: {
    tran() {
      this.$router.push({
        name:'shopCart'
      });
    },
    enter() {
      this.maskShow = true;
    },
    out() {
      this.zoomVisiable = false;
      this.maskShow = false;
    },
    move(ev) {
      this.init();
      // 鼠标距离屏幕距离
      let moveX = ev.clientX;
      let moveY = ev.clientY;
      // 大盒子距离顶部的距离
      let offsetLeft = offset(this.oPreviewBox).left;
      let offsetTop = offset(this.oPreviewBox).top;
      let left = moveX - offsetLeft - this.houverWidth / 2;
      let top;
      if (this.scroll > 0) {
        top = moveY - offsetTop + this.scroll - this.houverHeight / 2;
      } else {
        top = moveY - offsetTop - this.houverHeight / 2;
      }
      let maxWidth = this.pWidth - this.houverWidth;
      let maxHeight = this.pWidth - this.houverHeight;
      left = left < 0 ? 0 : left > maxWidth ? maxWidth : left;
      top = top < 0 ? 0 : top > maxHeight ? maxHeight : top;
      let percentX = left / maxWidth;
      let percentY = top / maxHeight;
      this.oHoverBox.style.left = left + "px";
      this.oHoverBox.style.top = top + "px";
      this.oBigImg.style.left = percentX * (this.bWidth - this.imgWidth) + "px";
      this.oBigImg.style.top =
        percentY * (this.bHeight - this.imgHeight) + "px";
      this.$emit("move", ev);
      this.zoomVisiable = true;
    },
    init() {
      this.oHoverBox = this.$refs.hoverBox;
      this.oPreviewBox = this.$refs.previewBox;
      this.oBigImg = this.$refs.bigImg;
      this.imgBox = this.$refs.zoomBox;
      this.houverWidth = this.oHoverBox.offsetWidth;
      this.houverHeight = this.oHoverBox.offsetHeight;
      this.pWidth = this.oPreviewBox.offsetWidth;
      this.pHeight = this.oPreviewBox.offsetHeight;
      this.imgWidth = this.oBigImg.offsetWidth;
      this.imgHeight = this.oBigImg.offsetHeight;
      this.bWidth = this.imgBox.offsetWidth;
      this.bHeight = this.imgBox.offsetHeight;
      this.scroll =
        document.documentElement.scrollTop || document.body.scrollTop;
    },
    search(id) {
      //alert(id);
      this.$axios
        .get("http://localhost:8080/myproject/api/backstage/category/querybyid", {
          params: {
            categoryId: id
          }
        })
        .then(response => {
          let result = response.data;
          if (result.status == "200") {
            this.list1 = result.data;
            console.log(result.data);
          }
        })
        .catch();
    },
    shop_status(status) {
      if (status == "1") {
        return "不缺货";
      } else {
        return "缺货";
      }
    },
    change1() {
      this.boo1 = "border:1px solid black";
      this.previewImg = this.uu;
    },
    change2() {
      this.boo1 = "border:1px solid white";
    },
    change11() {
      this.boo2 = "border:1px solid black";
      this.previewImg = this.uu;
    },
    change12() {
      this.boo2 = "border:1px solid white";
    },
    change21() {
      this.boo3 = "border:1px solid black";
    //  idu.com/timg?image&quality=80&size=b9999_10000&sec=1560775191114&di=6b918bd0d3e7c380e9cf80fa8ce17ab8&imgtype=0&src=http%3A%2F%2Fk.zol-img.com.cn%2Fsjbbs%2F7692%2Fa7691515_s.jpg";
    },
    change22() {
      this.boo3 = "border:1px solid white";
    },
    handleChange(value) {
      console.log(value);
    },
    clear() {
      this.reload();
    },
    init1() {
      this.$axios({
        method: "GET",
        url: "http://localhost:8080/myproject/api/product/productById",
        params: {
          productId: this.productIds
        }
      })
        .then(Response => {
          let result = Response.data;
          if (result.status == "200") {
            this.list = result.data; //箭头函数this内部指针 指向外部vue对象  var self=this;
            this.photo = result.data.productPhoto;
            this.uu = this.web_static_url + this.photo;
            this.previewImg = this.web_static_url + this.photo;
            this.zoomImg = this.web_static_url + this.photo;
            this.search(this.list.productCategoryId);
          }
        })
        .catch(Response => {});
    },

    
    addShop() {
      if(sessionStorage.getItem("userId")==null){
              this.$message.warning("请登陆用户");
              this.$router.push("/login")
        }else{ 
      if (this.list.productIsLack == "0") {
        this.$message({
          showClose: true,
          message: "商品缺货不能添加",
          type: "error"
        });
      } else {
        this.drop(event.target);
        this.count++;
        this.$axios
          .get("http://localhost:8080/myproject/api/car/insert", {
            params: {
              detailProductId: sessionStorage.getItem("productId"),
              detailProductCount: this.num,
              detailProductUnit: this.list.productSmallUnit,
              detailProductPrice: this.list.productSmallPrice * this.num,
              detailComment: "是否加入购物车",
              detailUserId: sessionStorage.getItem("userId")
            }
          })
          .then(response => {
            let result = response.data;
            if (result.status == 200 || 500) {
              //alert("加入购物车成功");
            } else {
              alert("加入购物车失败");
            }
          })
          .catch({});
      }
        }
    },
    drop(el) {
      //抛物
      for (let i = 0; i < this.balls.length; i++) {
        let ball = this.balls[i];
        if (!ball.show) {
          ball.show = true;
          ball.el = el;
          this.dropBalls.push(ball);
          return;
        }
      }
    },
    beforeDrop(el) {
      /* 购物车小球动画实现 */
      let count = this.balls.length;
      while (count--) {
        let ball = this.balls[count];
        if (ball.show) {
          let rect = ball.el.getBoundingClientRect(); //元素相对于视口的位置
          let x = rect.left;

          let y = -(window.innerHeight - rect.top); //获取y
          el.style.display = "";
          el.style.webkitTransform = "translateY(" + y + "px)"; //translateY
          el.style.transform = "translateY(" + y + "px)";
          let inner = el.getElementsByClassName("inner-hook")[0];
          inner.style.webkitTransform = "translateX(" + x + "px)";
          inner.style.transform = "translateX(" + x + "px)";
        }
      }
    },
    dropping(el, done) {
      /*重置小球数量  样式重置*/
      let rf = el.offsetHeight;
      el.style.webkitTransform = "translate3d(0,0,0)";
      el.style.transform = "translate3d(0,0,0)";
      let inner = el.getElementsByClassName("inner-hook")[0];
      inner.style.webkitTransform = "translate3d(0,0,0)";
      inner.style.transform = "translate3d(0,0,0)";
      el.addEventListener("transitionend", done);
    },
    afterDrop(el) {
      /*初始化小球*/
      let ball = this.dropBalls.shift();
      if (ball) {
        ball.show = false;
        el.style.display = "none";
      }
    }
  },
  components: {
    "B-Header": BHeader,
    "A-Header": AHeader
  }
};
</script>

<style scoped>
#app {
  height: 900px;
  width: 900px;
}

/* 这是底层 */
.preview-box {
  width: 450px;
  height: 450px;
  /* border: 1px solid #dddddd; */
  position: relative;
  background-color: rgba(255, 255, 255, 0);
  cursor: crosshair;
}
/* 这是选择块 */
.mask {
  top: 10px;
  left: 20px;
  width: 150px;
  height: 150px;
  position: absolute;
  background-color: rgba(255, 255, 255, 0.445);
  opacity: 0.6;
  border: 1px solid #ccc;
  cursor: crosshair;
  /* z-index: 101; */
}
/* 这是放大后的图片底层 */
.zoom-box {
  top: 200px;
  left: 560px;
  width: 350px;
  height: 350px;
  /* border: 1px solid #999; */
  position: absolute;
  overflow: hidden;
  z-index: 100;
  /* background: rgb(231, 231, 231); */
}
/* 这是所有图片 */
.ha {
  width: 400px;
  height: 400px;
  position: absolute;
  border: 0px;
  display: block;
  /* left: 100.286px;
  top: 125.357px; */
}

.shop {
  /* position: fixed; */
  margin-left: 600px;
  top: 300px;
  left: 40px;
}
.ball {
  position: fixed;
  left: 32px;
  bottom: 22px;
  /* z-index: 200; */
  transition: all 0.8s cubic-bezier(0.75, 0.16, 0.38, 0.92); /*贝塞尔曲线 0.49, -0.29, 0.75, 0.41*/
}
.inner {
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background-color: #ef8ea8;
  transition: all 0.8s linear;
}
#cart {
  position: fixed;
  bottom: 22px;
  left: 32px;
  width: 30px;
  height: 30px;
  /* background-color: rgb(0, 160, 220); */
  color: #ef8ea8;
}
#product {
  height: 2800px;
  width: 1519.2px;
  background-color: #faf1f4;
}
#product #header-all {
  height: 200px;
  width: 1519.2px;
}

.shop_list {
  margin: auto;
  width: 1200px;
  height: 645px;
  background-color: #ffffff;
}
.product_img {
  height: 500px;
  width: 470px;
  /* border: 1px solid black; */
  float: left;
}
.product_img img {
  height: 400px;
  width: 400px;
  margin-top: 50px;
}
.product_list {
  width: 650px;
  height: 550px;
  padding-top: 50px;
  /* border: 1px solid black; */
  float: left;
}
#buttom img {
  margin-top: 10px;
  margin-right: 10px;
  height: 100px;
  width: 100px;
  float: left;
}
.product_name {
  float: left;
  text-align: center;
  margin-left: 15px;
  margin-bottom: 30px;
  margin-top: 10px;
  font-weight: 500;
  font-size: 1.5rem;
}
.product_price {
  background-color: #f9eaee;
  float: left;
  height: 80px;
  width: 100%;
  text-align: center;
  border-radius: 5px;
}
.product_price_total {
  background-color: #f9eaee4f;
  float: left;
  height: 50px;
  width: 100%;
  text-align: center;
  border-radius: 5px;
  line-height: 50px;
}

p {
  float: left;
  font-size: 13px;
}
.product_price-a {
  width: 650px;
  height: 40px;
  font-size: 20px;
  margin-top: 20px;
  margin-left: 20px;
}
.product_price-a #p1 {
  color: #f9eaee;
  color: #ffffff;
  float: left;
  font-size: 15px;
  background-color: #ef8ea8;
  width: 50px;
  line-height: 20px;
  height: 20px;
  margin-left: 10px;
}
.product_price-a #p2 {
  font-size: 15px;
  width: 100px;
  color: gray;
  margin-left: 10px;
}
.price_change {
  color: #ff0036;
}
.product_yun {
  /* background-color: #e5e4e4; */
  float: left;
  height: 40px;
  width: 100%;
  text-align: center;
}
#yun {
  height: 20px;
  margin-top: 10px;
  margin-left: 25px;
}
.el {
  width: 100%;
  height: 50px;
  float: left;
  line-height: 50px;
  padding-left: 25px;
}
.product_stand {
  height: 40px;
  width: 100%;
  margin-top: 10px;
  line-height: 40px;
  margin-left: 25px;
  float: left;
}
.product_num {
  /* clear: both; */
  float: left;
  line-height: 60px;
  margin-left: 25px;
  height: 60px;
  width: 100%;
  /* border: 1px solid black; */
}
.el-row {
  width: 100%;
  height: 530px;
}
.el-input-number {
  margin-top: 17px;
  margin-left: 20px;
}
.el-link {
  float: left;
  margin-left: 20px;
  font-size: 0.7rem;
}
#acar {
  width: 600px;
  height: 60px;
  float: left;
  line-height: 60px;
}
.foot {
  margin-top: 15px;
  margin-left: 10px;
  float: left;
}
.el-divider {
  clear: both;
  margin-top: 20px;
}
.shop_detil {
  width: 70%;
  margin: 0 auto;
}
</style>

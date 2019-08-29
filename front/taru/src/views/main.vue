<template>
  <div id="main">
    <A-Header></A-Header>
    <B-Header v-on:getlistvalue="getlist"></B-Header>
    <div id="allbody">
      <div id="body">
        <div id="wrap-fixed">
          <i-menu v-on:getcategoryIdvalue="getcategoryId"></i-menu>

          <router-view></router-view>
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
                  <a href="javascript:void(0);" @click="sort()">
                    价格
                    <i class="iconfont" id="i1">&#xe6b7;</i>
                  </a>
                </li>
                <li>
                  <div id="price-range">
                    价格区间：
                    <input type="text" id="text" />-
                    <input type="text" id="text" />
                    <input type="button" value="确定" id="btn" />
                  </div>
                </li>
              </ul>
              <div id="page" v-if="T">
                <span>
                  共
                  <em id="em">{{total}}</em> 个商品
                  <em id="em">{{page}}</em>
                  <em>/{{Math.ceil(total/Number(24))}}</em>
                  <a href="javascript:void(0)" @click="befpage(),manyfind();">上一页</a>
                  <a href="javascript:void(0)" @click="laspage(),manyfind();">下一页</a>
                </span>
              </div>
              <div v-else></div>
            </div>
            <div id="box">
              <ul>
                <li v-for="product in a" :key="product.productId">
                  <div id="img" @click="productMes(product.productId)">
                    <img
                      :src="'http://localhost:8080/myproject/api/static/'+product.productPhoto"
                      alt
                    />
                  </div>
                  <div id="name">{{product.productName}}</div>
                  <div id="price">{{product.productSmallPrice}}￥</div>
                  <div id="btn">
                    <input
                      type="button"
                      @click="addcar(product.productId,product.productSmallUnit,product.productSmallPrice)"
                      value="加入购物车"
                    />
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
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

<script>
// @ is an alias to /src
import BHeader from "@/components/Header-2.vue";
import AHeader from "@/components/Header-1.vue";
import Menu from "@/components/menu.vue";
import Bus from "@/bus.js";
export default {
  data() {
    return {
      list: [],
      id: null,
      T: true,
      page: 1,
      a: [{}],
      total: "",
      str: "",
      id: "",
      b: "",
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
      count: 0
    };
  },
  created: function() {
    this.getsess();
    this.getfuzzvalue1();

    // sessionStorage.removeItem("categoryIdvalue");
    //findfuzz();
    // this.manyfind();
  },
  watch: {
    id(val, oldval) {
      this.page = Number(1);
      this.manyfind();
    }
  },
  methods: {
    getsess() {
      //if( sessionStorage.getItem("categoryId")!==null){
      this.id = sessionStorage.getItem("categoryId");
      //}
    },
    //小球
    tran() {
      this.$router.push({
        name: "shopCart"
      });
    },
    //传类id
    getcategoryId: function(val) {
      this.id = val;
    },
    //传空值
    getcategoryId2: function(val1) {
      if (this.id == null) {
        this.page = Number(1);
        this.findmanyall();
      } else {
        this.id = val1;
        this.page = Number(1);
      }
    },

    //得到模糊查询的值
    getlist: function(childValue) {
      this.a = childValue;
      this.T = false;
      sessionStorage.removeItem("fuzzname");
    },
    getfuzzvalue1: function() {
      if (
        sessionStorage.getItem("fuzzname") !== null &&
        sessionStorage.getItem("fuzzname") !== ""
      ) {
        this.findfuzz();
        sessionStorage.removeItem("fuzzname");
        this.T = false;
      } else if (this.id !== null) {
        this.manyfind();
      } else {
        this.findmanyall();
      }
    },

    //上一页
    befpage: function() {
      if (this.page > 1) {
        this.page -= Number(1);
        //Bus.$emit('val', this.page);
      }
    },
    //下一页
    laspage: function() {
      if (this.page < Math.ceil(this.total / Number(24))) {
        this.page += Number(1);
      }
    },
    //多条件查询
    manyfind: function() {
      this.T = true;
      //this.changepage(),
      this.$axios({
        method: "GET",
        url: "http://localhost:8080/myproject/api/product/searchList",
        params: {
          categoryId: this.id,
          productIsLack: "",
          orderStr: null,
          pageSize: 24,
          pageNum: this.page
        }
        //get
      })
        .then(response => {
          let result = response.data;
          console.log(result);
          if (result.status == "200") {
            console.log(result);
            this.a = result.data.list;
            this.total = result.data.total;
            sessionStorage.removeItem("categoryId");
          }
        })
        .catch(function(error) {});
    },
    //查询全部商品
    findmanyall: function() {
      this.T = true;
      this.$axios({
        method: "GET",
        url: "http://localhost:8080/myproject/api/product/searchList",
        params: {
          categoryId: this.id,
          productIsLack: "",
          orderStr: null,
          pageSize: 24,
          pageNum: this.page
        }
        //get
      })
        .then(response => {
          let result = response.data;
          console.log(result);
          if (result.status == "200") {
            this.a = result.data.list;

            this.total = result.data.total;
          }
        })
        .catch(function(error) {});
    },

    addcar(productId, productSmallUnit, productSmallPrice) {
      if (sessionStorage.getItem("userId") == null) {
        this.$message.warning("请登陆用户");
        this.$router.push("/login");
      } else {
        this.drop(event.target);
        this.count++;
        this.$axios
          .get("http://localhost:8080/myproject/api/car/insert", {
            params: {
              detailProductId: productId,
              detailProductCount: 1,
              detailProductUnit: "",
              detailProductPrice: productSmallPrice,
              detailComment: "",
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
    },
    //模糊查询
    findfuzz: function() {
      this.$axios({
        method: "GET",
        url: "http://localhost:8080/myproject/api/product/productByName",
        params: {
          productName: sessionStorage.getItem("fuzzname")
        }
      })
        .then(response => {
          let result = response.data;
          if (result.status == "200") {
            this.a = result.data.list;
            console.log(list);
            this.$emit("getlistvalue", this.list);
          } else {
            alert("查询异常");
          }
        })
        .catch(error => {});
    },
    //商品详情页跳转
    productMes(id) {
      sessionStorage.setItem("productId", id);
      this.$router.push("/Shopone");
    }
  },
  components: {
    "B-Header": BHeader,
    "i-menu": Menu,
    "A-Header": AHeader
  }
};
</script>
<style scoped>
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
#body {
  width: 1519.2px;
  min-height: 1780px;
  background-color: #faf1f4;
}
#wrap-fixed {
  width: 1200px;
  height: 600px;
  margin: auto;
}
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
#f1 #page {
  line-height: 40px;
  float: right;
  margin-right: 20px;
}
#page a {
  color: #666666;
  border: 1px solid #e4e4e4;
  margin-left: 8px;
}
#page a:focus {
  color: #e61952;
}
#page #em {
  color: #e61952;
  font-size: 12px;
  font-style: normal;
}
#page span {
  font-size: 12px;
}
#box {
  background-color: #ffffff;
  float: left;
  width: 1010px;
  min-height: 1650px;
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
  font-size: 12px;
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
#pagefixed {
  margin-top: 30px;
  margin-left: 600px;
  width: 1100;
  height: 60px;
  line-height: 70px;
}
@font-face {
  font-family: "iconfont";
  src: url("//at.alicdn.com/t/font_1232886_171g5fflc11.eot?t=1560047980668"); /* IE9 */
  src: url("//at.alicdn.com/t/font_1232886_171g5fflc11.eot?t=1560047980668#iefix")
      format("embedded-opentype"),
    /* IE6-IE8 */
      url("data:application/x-font-woff2;charset=utf-8;base64,d09GMgABAAAAAAUoAAsAAAAACjgAAATbAAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHEIGVgCDSAqHIIVhATYCJAMYCw4ABCAFhG0HYRugCCMRdnNwspL9M4E30TpGsYpY8oRhMKph89rS4jiXh8SJhEjwJh6ev6+d+7aZad8oqo/5Tu2WSBwFXmCBhHnC4bYIzNF7+dNLmU0KIyXwd3lSUtmPNqJspnQAVt7K7zf31n3aWJioyZL8dmOJdqXkmh6+XDr6n2OmS2uw/DabS3bQ3gBHCTSw9WVTtFiJHYCeyBnEzu6DtwRdTGDEBkM8Rw8BEfAU2qZAXHN2KryYTikhh76y5RxYIO6BTz+Vcc4AuHN/Pv6BiiBIagZtx8sszwz4nnm2gjH/GQk2Avj9WcAdRkYHFOI213YD9WMdzIjyG8YxMKYvKV/wDHum9cz52cr//8REmiuM6RWEKmv3xyMaENkYsKfxyheYTIIvjJcn14JUWu4MjQT5CgT3SNV6XDEHYgUgrgoN15GV01iYxC5J8PArEoICOG2PaAiTwhgmvaW5GRcubwRgmhAZkRIXxeIHo9jGSei6aIlWLquw0qhRUjgNe8mKkE9vGpGJbmmRjkEVY8xo9cZxdgIRFW1MKz4UbQnJzAjN3nItqvtc+RnFKxFnrqhedYxtakIiFS0y8fuj2WtpHRVyTYxkY7l0M5sQ14RakE4sitNqknGseJwUq8OxqDre0s6QiUF4vBRJU7dqlrTwMhUe4OHO1zSuX49u21mxW+Hq1Ugmyq55sLVNyrYJVci0tEe4NLb5YneWnRWNsmlslWJPbN/S0T68JVM0N0tsDY6x4zjeOAocvLzEEtlUsa6TtttTEfg7JEu8R/zoPfHWZPGiRpolHrMUof8ptnly3rjpt6Y0n3gKf4g6n2jgk0ARPvUQgRTxAJyyOfuSRp++reNrCSGss5RDgjyQHMIuJOrID/IfSPznICnh9G/rjh1b3h/YDaaXCoMcUiRTDbwNQlMlAx3i7DN5wOWQQY7cbVwWvy1kmDMPLpR9ZlbRQeO5zUrl+w33lyudOqyp58KvisXIU3py1I1UuoSgYk0Ji19c+nCesaPlzjT6h4I5YbGZS1+VN/VSlDf7RtOpD5Xq85N6UlKD9uXT26tTuedNuFxHFwU+Ho9SNld+7L3D/bE8+RKW42HNLj0v4GND1zx35Se32oQ8Es2PFr8liDbSznu8VhvS3l1IeeeR/GGn15rVSoIqxrR1WLR5tbVbQD0VniICIi4PMJQSLnKsun6/r0ybFcd8KbMVtLn5iTMOMDy8/tXZpd4tg2mt72w7xgCQHjmLn+Iqr7XUx69zh69bdN+HXtTrHfENOwfqx/sSHP/D49fsKapQ07cidzcbBJqHJ/qJhBEj+NxcM+71pZivXUjomw8h6VmHrG8TWbAdVKN2oOnbhRHbvA8fNYuGiiIEbCmiIEyZgWTCM2RTziIL9i5UC95CMxUMRhz0mesTHtyE97eCveIEwx/wGAMZRt17xa9oVtdLVOhSPlEyB6Ep62TyEQNKH7vkb9OqEpBEDw/U5dC5CEnigqOWs2o6VhXlPVE5Rl/s3RKop9AEDP4ANooCsgerfer7XyFj5fSkpKvC+gmJjGcHjVJdA/koC7W6zmV+9s1oKUX0OiIiDzzAgpwei0DKX7ZAI1WaW0SSo4r0o7qycnyFv8pTAOC0++LPSjPZObk2P/f/PTebhfugcd04DibPfG5a+mAKhHlW6p2oLQoA")
      format("woff2"),
    url("//at.alicdn.com/t/font_1232886_171g5fflc11.woff?t=1560047980668")
      format("woff"),
    url("//at.alicdn.com/t/font_1232886_171g5fflc11.ttf?t=1560047980668")
      format("truetype"),
    /* chrome, firefox, opera, Safari, Android, iOS 4.2+ */
      url("//at.alicdn.com/t/font_1232886_171g5fflc11.svg?t=1560047980668#iconfont")
      format("svg"); /* iOS 4.1- */
}

.iconfont {
  font-family: "iconfont" !important;
  font-size: 16px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.icon-xiazai1:before {
  content: "\e600";
}

.icon-jiantou:before {
  content: "\e63f";
}

.icon-lingshi:before {
  content: "\e625";
}

.icon-shangxiajiantou:before {
  content: "\e6b7";
}

.icon-falling:before {
  content: "\e603";
}
</style>
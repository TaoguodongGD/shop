<template>
  <div class="home">
    <tou1/>
    <tou2 id="first"/>
    <div class="body">
      <meun class="meun"/>
      <div class="S">
        <el-carousel :interval="2500" arrow="always" height="455px">
          <el-carousel-item v-for="item in imagesbox" :key="item.id">
            <img :src="item.idView" class="image" width="1200px" height="500px">
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
    <div class="wei">
      <div class="listA">
        <div class="listC">
          <p id="must">每日必杀</p>
        </div>
        <div class="lblock">
          <img src="..\assets\1496642566539938019.jpg" alt width="305" height="615px">
        </div>
        <div
          class="rblock"
          v-for="item in list1"
          :key="item.productId"
          @click="jump(item.productId)"
        >
          <div class="list">
            <img
              :src="web_server+item.productPhoto"
              height="175px"
              width="155px"
              @click="jump(item.productId)"
            >
          </div>
          <ul class="zi">
            <li>{{item.productName}}</li>
            <li>￥{{item.productSmallPrice}}</li>
          </ul>
        </div>
      </div>
      <div class="listB">
        <img src="../assets/1453972873781796647.jpg" alt>
      </div>

      <div class="listA">
        <div class="listC">
          <p id="hot">热卖推荐</p>
        </div>
        <div
          class="block"
          v-for="item in list2"
          :key="item.productId"
          @click="jump(item.productId)"
        >
          <div class="hot"></div>
          <div class="list1">
            <img :src="web_server+item.productPhoto" height="160px" width="140px">
          </div>
          <ul class="zi">
            <li>{{item.productName}}</li>
            <li>￥{{item.productSmallPrice}}</li>
          </ul>
        </div>
      </div>
      <div class="listB">
        <img src="../assets/1449048971540606201.jpg" alt>
      </div>

      <div class="listA">
        <div class="listC">
          <p id="sale">优惠促销</p>
        </div>
        <div class="lblock">
          <img src="..\assets\1496642513798376285.jpg" alt width="305" height="615px">
        </div>
        <div
          class="rblock"
          v-for="item in list3"
          :key="item.productId"
          @click="jump(item.productId)"
        >
          <div class="list">
            <img
              :src="web_server+item.productPhoto"
              height="175px"
              width="155px"
              @click="jump(item.productId)"
            >
          </div>
          <ul class="zi">
            <li>{{item.productName}}</li>
            <li>￥{{item.productSmallPrice}}</li>
          </ul>
          <ul class="zi1">
            <li>{{item.productName}}</li>
          </ul>
        </div>
      </div>
      <div class="listB">
        <img src="..\assets\1503471340677595949.jpg" alt style="float: left;">
        <img src="..\assets\1503471248065787219.jpg" alt style="float: left;">
        <img src="..\assets\1503471176138399079.jpg" alt style="float: left;">
      </div>
    </div>
    <div class="loaction">
      <a href="#first">
        <div class="list">
          <i class="fa fa-home fa-2x" aria-hidden="true"></i>
          <p>首页</p>
        </div>
      </a>
      <a href="#must">
        <div class="list">
          <i class="fa fa-thumbs-up fa-2x" aria-hidden="true"></i>
          <p>每日必杀</p>
        </div>
      </a>
      <a href="#hot">
        <div class="list">
          <i class="fa fa-fire fa-2x" aria-hidden="true"></i>
          <p>热卖推荐</p>
        </div>
      </a>
      <a href="#sale">
        <div class="list">
          <i class="fa fa-grav fa-2x" aria-hidden="true"></i>
          <p>优惠促销</p>
        </div>
      </a>
    </div>
  </div>
</template>


<script>
import tou1 from "@/components/Header-1.vue";
import tou2 from "@/components/Header-2.vue";
import meun from "@/components/menu.vue";
export default {
  components: {
    tou1,
    tou2,
    meun
  },
  data() {
    return {
      list1: [],
      list2: [],
      list3: [],
      msg: "",
      ID1: "285aa919-5266-4da6-85c3-ebcd9ee85282",
      ID2: "02c6fd20-808b-441e-b02c-edb5b7e95df1",
      ID3: "afd7136a-4bdd-475f-be7d-123280f34532",
      web_server: "http://localhost:8080/myproject/api/static/",
      imagesbox: [
        { id: 0, idView: require("../assets/1557120015516649296.jpg") },
        { id: 1, idView: require("../assets/1537239284765876614.jpg") },
        { id: 2, idView: require("../assets/1536797872191783523.jpg") },
        { id: 3, idView: require("../assets/1517810945694990298.jpg") }
      ]
    };
  },
  created() {
    this.details1();
    this.details2();
    this.details3();
  },

  methods: {
    du(id) {
      this.msg = id;
    },

    details1() {
      var self = this;
      this.$axios({
        method: "GET",
        url: "http://localhost:8080/myproject/api/product/productByCategoryId",
        params: {
          categoryId: self.ID1,
          pageNum: 3,
          pageSize: 8
        }
      })
        .then(function(x) {
          self.list1 = x.data.data.list;
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    details2() {
      var self = this;
      this.$axios({
        method: "GET",
        url: "http://localhost:8080/myproject/api/product/productByCategoryId",
        params: {
          categoryId: self.ID2,
          pageNum: 10,
          pageSize: 10
        }
      })
        .then(function(x) {
          self.list2 = x.data.data.list;
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    details3() {
      var self = this;
      this.$axios({
        method: "GET",
        url: "http://localhost:8080/myproject/api/product/productByCategoryId",
        params: {
          categoryId: self.ID3,
          pageNum: 6,
          pageSize: 8
        }
      })
        .then(function(x) {
          self.list3 = x.data.data.list;
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    jump(id) {
      window.sessionStorage.setItem("productId", id);
      this.$router.push({
        name: "Shopone"
      });
    }
  }
};
</script>

<style scoped>
.home {
  background-color: #f7f7f7;
  height: 3500px;
}
.meun {
  position: absolute;
  float: left;
  z-index: 10;
}
.S {
  float: left;
  width: 1200px;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: azure;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.body {
  width: 1200px;
  height: 455px;
  margin: auto;
  position: relative;
}
.listA {
  width: 1214px;
  height: 650px;
  margin: 70px auto;
}

.list {
  margin: 27px;
  width: 155px;
  height: 175px;
}
.list1 {
  margin-left: 45px;
  margin-top: 50px;
  width: 140px;
  height: 160px;
}

.listB {
  width: 1200px;
  height: 120px;
  margin: 60px auto;
}
.listC {
  width: 1200px;
  height: 40px;
  margin: auto;
  position: relative;
}

.lblock {
  border: 1px solid red;
  width: 303px;
  height: 615px;
  float: left;
  margin: 7px;
}
.rblock {
  width: 209.6px;
  height: 300px;
  float: left;
  margin: 7px;
  background-color: #ffffff;
  position: relative;
  border-radius: 10px;
}
.block {
  width: 228.5px;
  height: 309px;
  float: left;
  margin: 7px;
  background-color: #ffffff;
  position: relative;
  border-radius: 10px;
}
.block:hover {
  box-shadow: 0 0 20px rgb(241, 166, 26);
}
.zi {
  height: 53px;
  margin-top: 30px;
  text-align: center;
}

.zi li {
  list-style: none;
  margin-top: 20px;
}
p {
  float: left;
  font-size: 20px;
}
.zi1 {
  position: absolute;
  bottom: 0px;
  left: 0;
  right: 0;
  width: 100%;
  height: 0;
  background-color: sandybrown;
  color: black;
  transition: all 0.3s;
  overflow: hidden;
  text-align: center;
}
.zi1 li {
  list-style: none;
}
.rblock:hover .zi1 {
  height: 80px;
  border-radius: 10px;
  line-height: 80px;
}
.hot {
  width: 54px;
  height: 54px;
  background: url(../assets/hot.png) -382px -400px no-repeat;
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  text-indent: -9999px;
}
.loaction {
  position: absolute;
  position: fixed;
  right: 0;
  top: 250px;
  width: 110px;
}
.loaction .list {
  height: 80px;
  width: 80px;
  margin-top: -25px;
  position: relative;
  background-color: #ffffff;
}
.loaction .list i {
  height: 40px;
  width: 60px;
  position: relative;
  margin-left: 25px;
  float: left;
  margin-top: 10px;
  color: gray;
  
}
.loaction .list p {
  height: 20px;
  width: 80px;
  font-size: 15px;
  text-align: center;
  color: black;
}
</style>
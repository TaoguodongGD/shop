<template>
  <div id="Header-2">
    <div id="header">
      <div id="logo">
        <a href="/"><img src="../../src/assets/login2.png" alt></a>
      </div>

      <div id="formsearch">
        <div id="searchbg">
          <input type="text" v-model="fuzzname" id="ks" placeholder="搜索">
          <input type="button" value="找零食" id="btnsearch" @click="setfuzzname()">
        </div>
        <div id="hotSearch">
          <ul>
            <li>
              <a href="javascript:void(0);">海苔</a>
            </li>
            <li>
              <a href="javascript:void(0);">果冻</a>
            </li>
            <li>
              <a href="javascript:void(0);">饼干</a>
            </li>
            <li>
              <a href="javascript:void(0);">锅巴</a>
            </li>
            <li>
              <a href="javascript:void(0);">威化饼</a>
            </li>
          </ul>
        </div>
      </div>

      <div id="qrc"></div>
    </div>

    <div id="navbar_wrap">
      <div id="navbar">
        <div class="allclass">
          <div id="a">
            <a>全部商品分类</a>
          </div>
          <div id="img">
            <img src="../assets/菜单.png" alt>
          </div>
        </div>
        <ul>
          <li @click="jump()">
            <a href="javascript:void(0);">首页</a>
          </li>
          <li>
            <a href="javascript:void(0);">秒杀</a>
          </li>
          <li>
            <a href="javascript:void(0);">促销</a>
          </li>
          <li>
            <a href="javascript:void(0);">新品</a>
          </li>
          <li>
            <a href="javascript:void(0);">进口零食</a>
          </li>
          <li>
            <a href="javascript:void(0);">批发</a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "Header-2",
  components: {},
  data() {
    return {
      list: [{}],
      fuzzname: "",
      pageSize: 24
    };
  },
  created: function() {},
  methods: {
    //模糊查询
    setfuzzname: function() {
      sessionStorage.setItem("fuzzname", this.fuzzname);
      this.$router.push("/main");
      alert(this.fuzzname);
      this.$axios({
        method: "GET",
        url: "http://localhost:8080/myproject/api/product/productByName",
        params: {
          productName: this.fuzzname
        }
      })
        .then(response => {
          let result = response.data;
          if (result.status == "200") {
            this.list = result.data.list;
            this.console.log(list);
            this.$emit("getlistvalue", this.list);
          } else {
            alert("查询失败");
          }
        })
        .catch(error => {});
    },
    //跳转页面
    jump() {
      this.$router.push("/");
      this.$router.go(0);
    }
  }
};
</script>
<style scoped>
#header {
  height: 130px;
  width: 1200px;
  margin: auto;
  margin-top: 0%;
}
#logo img {
  /* background-color: black; */
  height: 100px;
  width: 300px;
  float: left;
  margin-top: 20px;
}
#formsearch {
  width: 518px;
  height: 60px;
  float: left;
  margin-left: 100px;
  margin-top: 40px;
}
#searchbg {
  background-color: #777;
  width: 518px;
  height: 38px;
  float: left;
}
#hotSearch {
  margin-top: 6px;
  width: 518px;
  height: 22px;
  float: left;
}

#searchbg #ks {
  width: 413px;
  height: 33px;
  float: left;
  font-size: 15px;
  font-weight: 500;
  border: 3px solid #e61952;
}
#btnsearch:hover {
  color: #ff9;
}
#btnsearch {
  background: #e61952;
  border: none;
  color: #fff;
  cursor: pointer;
  font-family: "\5fae\8f6f\96c5\9ed1", "Microsoft YaHei", Arial;
  font-size: 16px;
  font-weight: 700;
  height: 41px;
  width: 98px;
  letter-spacing: 1px;
  float: left;
  border: 3px solid #e61952;
}
#hotSearch:hover {
  color: #e61952;
}
#hotSearch a {
  float: left;
  margin-right: 5px;
  color: #777;
  font-size: 12px;
}
#hotSearch a:hover {
  color: #e61952;
}
#qrc {
  display: block;
  float: right;
  display: inline;
  background: url(../assets/hot.png) 0 -132px no-repeat;
  width: 195px;
  height: 84px;
  clear: both;
  clear: none;
  margin-top: 20px;
}
#navbar_wrap {
  background-color: #e61952;
  width: 1519.2px;
  height: 40px;
  margin-bottom: 0%;
}
#navbar {
  margin: auto;
  width: 1200px;
  height: 40px;
}
#navbar #img {
  float: left;
  line-height: 40px;
  margin-top: 4px;
  margin-left: 5px;
}
#navbar #a {
  margin-left: 30px;
  float: left;
  line-height: 40px;
}
.allclass {
  background-color: #333;
  float: left;
  width: 190px;
  height: 40px;
  text-align: center;
  line-height: 40px;

  font-size: 15px;
}
.allclass a {
  color: rgb(221, 221, 221);
}
#navbar ul li {
  width: 85px;
  height: 40px;
  text-align: center;
  line-height: 40px;
  float: left;
}
#navbar ul li:hover {
  background: rgba(88, 88, 88, 0.6);
}
#navbar ul li a {
  color: #fff;
  font-weight: 700;
  font-size: 14px;
}
#sp_r {
  background-color: #333;
  text-align: center;
  line-height: 34;
  float: right;
  width: 212px;
  height: 40px;
}
#sp_r a {
  color: #fff;
  margin: left;
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
html,
body,
div,
span,
object,
iframe,
h1,
h2,
h3,
h4,
h5,
h6,
p,
blockquote,
pre,
abbr,
address,
cite,
code,
del,
dfn,
em,
img,
ins,
kbd,
q,
samp,
small,
strong,
sub,
sup,
var,
b,
i,
dl,
dt,
dd,
ol,
ul,
li,
fieldset,
form,
label,
legend,
table,
caption,
tbody,
tfoot,
thead,
tr,
th,
td,
article,
aside,
canvas,
details,
figcaption,
figure,
footer,
header,
hgroup,
menu,
nav,
section,
summary,
time,
mark,
audio,
video {
  margin: 0;
  padding: 0;
  border: 0;
  outline: 0;
  font-size: 100%;
  vertical-align: baseline;
  background: transparent;
}
body {
  line-height: 1;
}
:focus {
  outline: 1;
}
article,
aside,
canvas,
details,
figcaption,
figure,
footer,
header,
hgroup,
menu,
nav,
section,
summary {
  display: block;
}
nav ul {
  list-style: none;
}
blockquote,
q {
  quotes: none;
}
blockquote:before,
blockquote:after,
q:before,
q:after {
  content: "";
  content: none;
}
a {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  vertical-align: baseline;
  background: transparent;
}
ins {
  background-color: #ff9;
  color: #000;
  text-decoration: none;
}
mark {
  background-color: #ff9;
  color: #000;
  font-style: italic;
  font-weight: bold;
}
del {
  text-decoration: line-through;
}
abbr[title],
dfn[title] {
  border-bottom: 1px dotted #000;
  cursor: help;
}
table {
  border-collapse: collapse;
  border-spacing: 0;
}
hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 1px solid #cccccc;
  margin: 1em 0;
  padding: 0;
}
input,
select {
  vertical-align: middle;
}
ul,
ol {
  list-style: none;
}
</style>


<template>
  <div id="menuu">
    <ul>
      <li  @click="setcategoryId(null)">
        <div id="menu-item">
          <a href="javascript:void(0);" id="a111">
            <i class="iconfont">&#xe625;</i>
            全部商品
          </a>
        </div>
        <div id="jiantou">
          <i class="iconfont">&#xe63f;</i>
        </div>
      </li>

      <li
        v-for="item in list1"
        :key="item.categoryId"
        @click="setcategoryId(item.categoryId)"
      >
        <div id="menu-item">
          <a href="javascript:void(0)">
            <i class="iconfont">&#xe625;</i>
            {{item.categoryName}}
          </a>
          <div id="jiantou">
            <i class="iconfont">&#xe63f;</i>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "menuu",
  data() {
    return {
      a: "",
      N:"",
      M:'',
      //list: {},
      list1:[]
    };
  },
  created() {
    this.init();
  },
  methods: {
    init() {
      this.$axios({
        method: "GET",

        url: "http://localhost:8080/myproject/api/Category/CategoryList",

        parmars: {}
      })
        .then(response => {
          let result = response.data;
          if (result.status == "200") {
            this.list1 = result.data;
            // for( let a of this.list){
            //       if(a.categoryName!==null){
            //           this.list1.push(a);
            //       }
            //}
            
          }
        })
        .catch(function(error) {
          console.log(error);
        })
        
      },
      setcategoryId(categoryId){
          this.N=categoryId;
          this.$emit('getcategoryIdvalue', this.N)
          sessionStorage.setItem("categoryId",this.N)
          this.$router.push("/main");
      },
      // getnull(){
      //    this.M="";
      //    this.$emit('getcategorynullvalue', this.M)
      //    this.$router.push("/main");
      // }
  }
};
</script>

<style scoped>
#menuu {
  background-color: #777;
  width: 190px;
  min-height: 455px;
  float: left;
  opacity: 0.9;
}
#menuu ul li {
  color: #fff;
  width: 190px;
  height: 35px;
  text-align: center;
  line-height: 35px;
  margin-top: 1.5px;
  font-size: 14px;
  float: left;
}
#menuu ul li:hover {
  background-color: #fff;
}
#menuu ul li:hover a {
  color: #e61952;
}
#menuu ul li:hover #jiantou {
  color: #e61952;
}
#menuu a {
  color: #fff;
  float: left;
}
#menuu img {
  margin-top: 8px;
  float: left;
}
#jiantou {
  float: right;
  line-height: 35px;
  margin-right: 5px;
}
#menu-item {
  margin-left: 50px;
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
body,
h1,
h2,
h3,
h4,
h5,
h6,
hr,
p,
blockquote,
dl,
dt,
dd,
ul,
ol,
li,
pre,
form,
fieldset,
legend,
button,
input,
textarea,
th,
td {
  margin: 0;
  padding: 0;
}
body,
button,
input,
select,
textarea {
  font: 12px/1.5 tahoma, arial, \5b8b\4f53;
}
h1,
h2,
h3,
h4,
h5,
h6 {
  font-size: 100%;
}
address,
cite,
dfn,
em,
var {
  font-style: normal;
}
code,
kbd,
pre,
samp {
  font-family: couriernew, courier, monospace;
}
small {
  font-size: 12px;
}
ul,
ol {
  list-style: none;
}
a {
  text-decoration: none;
}
sup {
  vertical-align: text-top;
}
sub {
  vertical-align: text-bottom;
}
legend {
  color: #000;
}
fieldset,
img {
  border: 0;
}
button,
input,
select,
textarea {
  font-size: 100%;
}
table {
  border-collapse: collapse;
  border-spacing: 0;
}
</style>

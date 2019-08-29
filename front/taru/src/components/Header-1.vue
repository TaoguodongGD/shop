<template>
    <div id="top">
          <div id="topcontent">
                <ul id="topleft">
                      
                      <li id="loginI">您好，<a href="javascript:void(0); " v-if="K">欢迎您来到中国零食网！</a> <a href="javascript:void(0); " v-else>欢迎{{username}}</a></li>
                      <li id="loginI">
                        <a href="javascript:void(0);" @click="login();" v-if="K" >登陆</a>
                        <div v-else >
                          <a href="javascript:void(0);" @click="login();" style="margin-right:8px; float:left;">用户中心</a>
                               <span class="sep" style="float:left;">|</span>
                           <a href="javascript:void(0);"  style="margin-left:10px; float:left;" @click="open()"> 注销</a>
                        </div> 
                        
                         
                      </li >
                      <li id="loginI">
                        <span class="sep">|</span>
                      </li> 
                      <li id="loginI" @click="regist();">
                        <a href="javascript:void(0);">注册</a> 
                      </li>
                </ul>

              <ul id="topright">
                  <li id="car" @click="car()"><a href="javascript:void(0);"><i class="iconfont">&#xe600;</i>购物车</a> 
                  </li>
                  <li id="loginI">|</li> 
                  <li> <a href="javascript:void(0);">积分兑换</a></li>
                  <li id="loginI">|</li> 
                  <li> <a href="javascript:void(0);">订单速查</a> </li>
                  <li id="loginI">|</li> 
                  <li @click="judge()"> <a href="javascript:void(0);">我的订单</a></li>
                 
                  
              </ul>
          </div>
        </div>
</template>

<script>
export default {
    name:'Header-1',
    data(){
      return{
          K:true,
          username:sessionStorage.getItem("userUserName")
      }
    },
    created:function(){
        this.hi();
    },
    methods:{
      login:function(){
            if(sessionStorage.getItem("userId")==null){
              this.$router.push("/Login");
              this.$router.go(0);
            }else{
                 this.$router.push("/usermessage");
                 this.$router.go(0);
                  
            }
      },
      regist:function(){
            this.$router.push("/Resinger")
            this.$router.go(0);    
      },
      judge:function(){
        if(sessionStorage.getItem("userId")==null){
           this.$message.warning("请登陆用户");
           this.$router.push("/Login");
        }else{
          this.$router.push("/userpush2");
          this.$router.go(0);
          
        }
      },
        hi:function(){
          if(sessionStorage.getItem("userId")!==null){
            this.K=false;
          }
        },
        car:function(){
          if(sessionStorage.getItem("userId")!==null){
          this.$router.push("/shopCart");
          }else{
            this.$message.warning("请登陆用户");
            this.$router.push("/Login");
          }
        },
        //判断
        open() {
        this.$confirm('您确定要退出吗？, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '已退出!'
          });
          this.exit();
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '退出已取消'
          });          
        });
      },
        //注销
        exit:function(){
            sessionStorage.removeItem("userId");
            sessionStorage.removeItem("userUserName");
            this.K=true;
        }
    }
}
</script>

<style scoped>
    #top{
      width: 1519.2px;
      height: 30px;
      background-color:  #f5f5f5;
    }
    #topcontent{
      width: 1200px;
      height: 100%;
      margin: auto;   
      font-size: 12px;

    }
    #topcontent a:hover{
      color: #333;
    }
    #topleft{
      float: left;
      line-height: 30px;
    }
    #topleft a{
      color:  #777;
    } 
    #topleft li{
      float: left;
      color:  #777;
      min-width: 10px;  
      margin-left: 5px;
    }
    #topcontent #topright{
      float: right;
    }
    #topright #car:hover a{
      color: #e61952;
    }
    #topright a{  
      color:  #777;
    }
    #topright li{
      float: right;
      color:  #777;
      line-height: 30px;
      margin-left: 10px;
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
    html, body, div, span, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, abbr, address, cite, code, del, dfn, em, img, ins, kbd, q, samp, small, strong, sub, sup, var, b, i, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas, details, figcaption, figure, footer, header, hgroup, menu, nav, section, summary, time, mark, audio, video { margin:0; padding:0; border:0; outline:0; font-size:100%; vertical-align:baseline; background:transparent; } 
    body { line-height:1; } 
    :focus { outline: 1; } article,aside,canvas,details,figcaption,figure, footer,header,hgroup,menu,nav,section,summary { display:block; } 
    nav ul { list-style:none; } blockquote, 
    q { quotes:none; } 
    blockquote:before, 
    blockquote:after,
    q:before, 
    q:after { content:''; content:none; } 
    a { margin:0; padding:0; border:0; font-size:100%; vertical-align:baseline; background:transparent; } 
    ins { background-color:#ff9; color:#000; text-decoration:none; }
    mark { background-color:#ff9; color:#000; font-style:italic; font-weight:bold; }
    del { text-decoration: line-through; } 
    abbr[title], dfn[title] { border-bottom:1px dotted #000; cursor:help; } 
    table { border-collapse:collapse; border-spacing:0; } 
    hr { display:block; height:1px; border:0; border-top:1px solid #cccccc; margin:1em 0; padding:0; } 
    input, select { vertical-align:middle; }
    ul, ol { list-style:none; }
</style>

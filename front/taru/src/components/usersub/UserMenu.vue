<template>
  <div class="loginsideleft">
    <div>
      <a href class="my-account">我的零食</a>
    </div>
    <div class="usermenu">
      <h5 class="btn-slid">订单中心</h5>
      <ul class="panel" style="display:block">
        <li>
          <a href="#">我的订单</a>
        </li>
        <li>
          <router-link to="/userpush2">历史订单</router-link>
        </li>
        <li>
          <a href="#">我的收藏</a>
        </li>
        <li>
          <a href="#">我的评论</a>
        </li>
      </ul>
      <h5 class="btn-slid">账户中心</h5>
      <ul class="panel" style="display:block">
        <li>
          <router-link to="/usermessage">个人信息</router-link>
        </li>
        <li>
          <a href="#">我的积分</a>
        </li>
        <li>
          <a href="#">我的代金券</a>
        </li>
        <li>
          <a href="#">账户余额</a>
        </li>
        <li>
          <a href="#">礼品卡充值</a>
        </li>
        <li>
          <a href="#">收货信息</a>
        </li>
        <li>
          <a href="#">修改密码</a>
        </li>
      </ul>
      <h5 class="btn-slid">服务中心</h5>
      <ul class="panel" style="display:block">
        <li>
          <a href="#">订单速度</a>
        </li>
        <li>
          <a href="#">会员留言</a>
        </li>
        <li>
          <a href="#">我要投诉</a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "usermenu",
  data() {
    return {};
  },
  methods: {
      
  }
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  list-style-type: none;
  text-decoration: none;
}
.loginsideleft {
  width: 208px;
}
.my-account {
  margin-top: 20px;
  width: 208px;
  height: 38px;
  background: #d4d4d4;
  color: #212121;
  display: block;
  line-height: 38px;
  text-align: center;
}

.usermenu {
  margin-top: 10px;
  width: 206px;
  border: 1px solid #e7e7e7;
  border-top: none;
  text-align: center;
}

.loginsideleft h5 {
  width: 206px;
  border-top: 2px solid #e33a3d;
  color: #333;
  line-height: 28px;
  font-size: 14px;
  cursor: pointer;
  list-style: none outside none;
}

.btn-slid {
  background: #f3f3f3;
}

.loginsideleft ul {
  padding-top: 5px;
  padding-bottom: 5px;
}

.loginsideleft li {
  line-height: 25px;
  font-size: 12px;
}
.usermenu a {
  color: #666;
}
</style>
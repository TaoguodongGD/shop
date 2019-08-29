<template>
    <div>
        <div class="push2-container">
            <div class="push2-header">
                <h5><span>历史订单</span></h5>
            </div>
            <div class="push2-container-out">
                <table>
                    <thead>
                        <tr>
                            <th>订单编号</th>
                            <th>下单时间</th>
                            <th>订单总金额</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="item in history" :key="item.orderId">
                            <th>{{item.orderId}}</th>
                            <th>{{item.orderDateTime}}</th>
                            <th>{{item.orderTotalPrice}}￥</th>
                            <th><a href="#">查看</a></th>
                        </tr>
                    </tbody>
                </table>
                <p>总共{{history.length}}个记录</p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name:'userpush2',
    data(){
        return{
            userid:sessionStorage.getItem("userId"),//用户ID
            history:[],  //返回的历史订单数据
            dialogTableVisible: false,
            list:''
        }
    },
    created(){
        this.findHistory();
    },
    methods:{

        //查询订单详情
        findOneOrder(id){
            alert(id);
            this.$axios.get("http://localhost:8080/myproject/api/detail/queryId",{
                params:{
                    orderid:id
                }
            })
            .then(res=>{
                let result = res.data;
                if(result.status == 200){
                    this.list = result.data;
                }else{
                    this.$message({
                    message: result.message,
                    type: 'warning'
                });
                }
            })
            .catch(error=>{
                this.$message({
                    message: '未知错误',
                    type: 'warning'
                });
            })
        },
        //查询历史订单方法
        findHistory(){
            this.$axios.get("http://localhost:8080/myproject/api/order/selectHistory",{
                params:{
                    userId:this.userid
                }
            })
            .then(res=>{
                let result = res.data;
                console.log(result);
                if(result.status=="200"){
                    this.history=result.data;
                }
            })
        }
    }
}
</script>

<style scoped>

a{
    color: black;
    font-size: 14px;
}

.push2-container{
    width: 975px;
}

.push2-header{
    width: 975px;
}

.push2-header h5{
    background:#ffd6ba;
    font-size: 14px;
    line-height: 30px;
    height: 30px;
    margin-bottom: 20px;
    padding-left: 12px;
    color: #ff6628;
}

.push2-container-out{
    border: 2px solid red;
    padding:20px 0px 0px 0px;
    text-align: center;
}

.push2-container-out table{
    margin: 0 auto;
    border: 1px solid #ccc;
    width: 935px;
}

.push2-container-out th{
    font-size: 14px;
    font-family: Arial, Helvetica, sans-serif;
    padding: 10px;
    text-align: center;
    border-left: 1px solid #ccc;
    border-top: 1px solid #ccc;
}

.push2-container-out p{
    font-size: 12px;
    color: red;
    margin-top: 15px;
    margin-bottom: 10px;
}

.lookBtn{
    color: black;
    font-size: 14px;
}
</style>
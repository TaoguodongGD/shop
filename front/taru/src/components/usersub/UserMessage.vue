<template>
    <div>
        <div class="message-container">
            <div class="message-header">
                <h5><span>个人信息</span></h5>
            </div>
            <div class="message-contaniner-out">
                <table>
                        <tr>
                            <th class="message-table-left">用户名</th>
                            <th class="message-table-in">{{personmessage.userUserName}}</th>
                        </tr>
                        <tr>
                            <th class="message-table-left">手机号</th>
                            <th class="message-table-in">{{personmessage.userTelphone}}</th>
                        </tr>
                        <tr>
                            <th class="message-table-left">地址</th>
                            <th class="message-table-in">{{personmessage.userAddress}}</th>
                        </tr>
                        <tr>
                            <th class="message-table-left">商店名称</th>
                            <th class="message-table-in">{{personmessage.userShopName}}</th>
                        </tr>
                        <tr>
                            <th class="message-table-left" id="message-table-comment">备注</th>
                            <th class="message-table-in">{{personmessage.userComment}}</th>
                        </tr>
                </table>
            </div>
        </div>
    </div>    
</template>

<script>
export default {
    name:'usermessage',
    data(){
        return{
            personmessage:[],
            userid:sessionStorage.getItem("userId")
        }
    },
    created(){
        this.findMessage();
    },
    methods:{
        findMessage(){
            this.$axios.get("http://localhost:8080/myproject/api/user/findById",{
                params:{
                    userId:this.userid
                }
            })
            .then(res=>{
                let result = res.data;
                if(result.status == "200"){
                    this.personmessage = result.data;
                }
            })
            .catch(error=>{

            })
        }
    }
}
</script>

<style scoped>
.message-container{
    width: 975px;
    float: left;
}

.message-header{
    width: 975px;
}

.message-header h5{
    background:#ffd6ba;
    font-size: 14px;
    line-height: 30px;
    height: 30px;
    margin-bottom: 20px;
    padding-left: 12px;
    color: #ff6628;
}

.message-contaniner-out table{
    width: 975px;
    border: 1px solid #ccc;
}

.message-contaniner-out table th{
    font-size: 12px;
    font-family: Arial, Helvetica, sans-serif;
    padding: 10px;
    text-align: center;
    border-left: 1px solid #ccc;
    border-top: 1px solid #ccc;
}

.message-table-left{
    width: 200px;
}

#message-table-comment{
    height: 50px;
    line-height: 50px;
}

</style>



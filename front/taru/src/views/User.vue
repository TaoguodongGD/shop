<template>
    <div>
        <div class="user-header">
            <A-Header></A-Header>
            <B-Header></B-Header>
        </div>
        <div class="user-container">
            <div class="user-container-left">
                <UserMenu></UserMenu>
            </div>
            <div class="user-container-right">
                <router-view></router-view>
            </div>
            <div class="footer"></div>
        </div>
        
    </div>
    
</template>

<script scoped>
import UserMenu from "@/components/usersub/UserMenu.vue"
import BHeader from "@/components/Header-2.vue";
import AHeader from "@/components/Header-1.vue";
export default {
    name:'personuser',
    components:{
        UserMenu,
        "B-Header": BHeader,
        "A-Header":AHeader
    }
}
</script>

<style scoped>
.user-header{
    width: 1519.2px;
    height: 200px;
    margin: 0 auto;
}
.user-container{
    width:1200px;
    margin: 0 auto;
}

.user-container-left{
    width: 208px;
    float: left;
}

.user-container-right{
    float: right;
    margin-top: 20px;
}

.footer{
    margin-bottom: 30px;
    width: 1200px;
    height: 100px;
    margin: 0 auto;
    float: left;
}
</style>



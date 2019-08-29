<template>
<div>
    <div class="head2">
        <div id="heade-cart">
            <div id="logo" @click="jump()">
                <a href="/"><img src="../../src/assets/login2.png" alt></a>
            </div>

        </div>
    </div>
</div>    
</template>
<script>
export default {
    methods:{
        jump(){
            this.$router.push("")
        }
    }
}
</script>
<style scoped>
#heade-cart{
    height: 100px;
    border-bottom: 2px solid #e5e5e5;
    margin-left: 150px;
}

</style>

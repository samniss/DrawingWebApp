<template>
    <div id="dialog-box">
        <div id="dialog-content">
            <textarea id="inputarea"></textarea>
            <button @click="input()">Enter</button>
        </div>
    </div>
</template>
<script>
export default {
    name:'dialog-box',
    methods:{
        input:function(){
            var textarea=document.getElementById('inputarea');
            this.$emit('dialog-input',textarea.value);
            textarea.value=""
        }
    }
}
</script>
<style  scoped>
#dialog-box{
display:none;
position: fixed;
z-index: 1;
border:1px solid black;
width:100px;
}
#dialog-content{
    display:flex;
    flex-direction: column;
    justify-content: space-around;
    background-color:#fefefe;
    border:1px solid black;
    padding:20px;
}
#inputarea{
    margin-bottom: 10px;
}
</style>
<template>
    <div id="w-h-dialog">
        <div id="dialog-content">
            <label>Width</label>
            <textarea id="widtharea"></textarea>
            <label>Height</label>
            <textarea id="heightarea"></textarea>
            <button @click="input()">Enter</button>
        </div>
    </div>
</template>
<script>
export default {
    name:'w-h-dialog',
    methods:{
        input:function(){
            var widtharea=document.getElementById('widtharea');
            var heightarea=document.getElementById('heightarea');
            this.$emit('w-h-input',widtharea.value,heightarea.value);
            widtharea.value=""
            heightarea.value=""
        }
    }
}
</script>
<style  scoped>
#w-h-dialog{
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
#widtharea,#heightarea{
    margin-bottom: 10px;
}
</style>
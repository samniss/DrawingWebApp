<template>
    <div id="ellipse-dialog">
        <div id="dialog-content">
            <label>RadiusX</label>
            <textarea id="xarea"></textarea>
            <label>RadiusY</label>
            <textarea id="yarea"></textarea>
            <label>Angle(rad)</label>
            <textarea id="rarea"></textarea>
            <button @click="input()">Enter</button>
        </div>
    </div>
</template>
<script>
export default {
    name:'ellipse-dialog',
    methods:{
        input:function(){
            var xarea=document.getElementById('xarea');
            var yarea=document.getElementById('yarea');
            var rarea=document.getElementById("rarea");
            this.$emit('ellipse-input',xarea.value,yarea.value,rarea.value);
            xarea.value=""
            yarea.value=""
            rarea.value="";
        }
    }
}
</script>
<style  scoped>
#ellipse-dialog{
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
#xarea,#ytarea,#rarea{
    margin-bottom: 10px;
}
</style>
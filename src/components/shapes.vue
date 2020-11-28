<template>
<div >
    <button id="line" @click="Line()">Line</button>
    <button id="circle" @click="Circle()">Circle</button>
    <button id="rectangle" @click="Rectangle()">Rectangle</button>
    <button id="triangle"  @click="Triangle()">Triangle</button>
    <button id="ellipse" @click="Ellipse()">Ellipse</button>
</div>
</template>
<script>
export default {
    name:'shapes',
    data:function(){
        return {
            shape :null
        }
    },
    methods:{
        Circle: function(){
            this.shape=
            {
                type:"circle",
                x:[],
                y:[],
                radius:0
            }
            this.$emit('draw-shape',this.shape)
        },
        Line:function(){
            this.shape={
                type:"line",
                x:[],
                y:[],
            }
            this.$emit('draw-shape',this.shape);
        },
        Rectangle:function(){
            this.shape={
                type:"rectangle",
                x:[],
                y:[],
                width:0,
                height:0
            }
            this.$emit('draw-shape',this.shape);
        },
        Triangle:function(){
            this.shape={
                type:"triangle",
                x:[],
                y:[],
            }
            this.$emit('draw-shape',this.shape);
        },
        Ellipse:function(){
            this.shape={
                type:"ellipse",
                x:[],
                y:[],
                radiusX:0,
                radiusY:0,
                rotationAngle:0
            }
            this.$emit('draw-shape',this.shape);
        }
    }
}
</script>
<style  scoped>
</style>

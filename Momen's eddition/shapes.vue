<template>
<div >
    <button id="line" @click="Line()">Line</button>
    <button id="circle" @click="Circle()">Circle</button>
    <button id="rectangle" @click="Rectangle()">Rectangle</button>
    <button id="square" @click="Square()">Square</button>
    <button id="triangle"  @click="Triangle()">Triangle</button>
    <button id="ellipse" @click="Ellipse()">Ellipse</button>
    <button id="select" @click="Select()">Select</button>
    <button id="move" @click="Move()">Move</button>
    <button id="copy" @click="Copy()">Copy</button>
    <button id="delete" @click="Delete()">Delete</button>
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
        Select(){
            this.$emit('selected')
        },
        Move(){
            this.$emit('moved')
        },
        Copy(){
            this.$emit('copied')
        },
        Delete(){
            this.$emit('Deleted')
        },
        Circle: function(){
            this.shape=
            {
                type:"circle",
                x:[],
                y:[],
                radius:0,
            }
            this.$emit('draw-shape',this.shape)
        },
        Line:function(){
            this.shape=
            {
                type:"line",
                x:[],
                y:[],
            }
            this.$emit('draw-shape',this.shape);
        },
        Rectangle:function(){
            this.shape=
            {
                type:"rectangle",
                x:[],
                y:[],
                width:0,
                height:0
            }
            this.$emit('draw-shape',this.shape);
        },
        Square:function(){
            this.shape=
            {
                type:"square",
                x:[],
                y:[],
                side:0
            }
            this.$emit('draw-shape',this.shape);
        },
        Triangle:function(){
            this.shape=
            {
                type:"triangle",
                x:[],
                y:[],
            }
            this.$emit('draw-shape',this.shape);
        },
        Ellipse:function(){
            this.shape=
            {
                type:"ellipse",
                x:[],
                y:[],
                radiusX:0,
                radiusY:0,
                rotationAngle:0
            }
            this.$emit('draw-shape',this.shape);
        },
        
    }
}
</script>
<style  scoped>
</style>
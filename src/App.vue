<template>
  <div id="app">
     <shapes @draw-shape="DrawShape" @undo-shape="Undo()" @redo-shape="Redo()" @copy-shape="Copy()" id="shapes"/>
    <canvas id="canvas" height=500px width=1000px @click="Draw"></canvas>
    <DialogBox  @dialog-input="DialogInput" id="dialog-box"/>
    <WHDialog   @w-h-input="WHDialogInput" id="w-h-dialog"/>
    <EllipseDialog @ellipse-input="EllipseDialogInput" id="ellipse-dialog"/>
    
  </div>
</template>

<script>
import shapes from "../src/components/shapes"
import DialogBox from "../src/components/Dialog"
import WHDialog from "../src/components/WHDialog"
import EllipseDialog from "../src//components//EllipseDialog";
const axios = require('axios').default;
export default {
  name: 'App',
  data: function(){
    return {
      shape:null,
      counter:0,
      shapes:[]//array of shapes
    }
  },
  components:{
    shapes,
    DialogBox,
    WHDialog,
    EllipseDialog
  },
  methods:{


    DrawShape: function(shape){//Determine the shape (circle,rectangle etc)
      this.shape=shape;
      },




    GetCoors:function(event){/*getting the x and y coordinates of the mouse when mouse is clicked on the canvas*/
    var canvas=document.getElementById("canvas");
    var rect=event.target.getBoundingClientRect();//Get the surrounding rectangle of the canvas including padding and border
    var borderLeft=getComputedStyle(canvas,null).getPropertyValue('border-left-width')//get the left border of the canvas
    var padLeft=getComputedStyle(canvas,null).getPropertyValue('padding-left');//get the left padding of the canvas
    var borderTop=getComputedStyle(canvas,null).getPropertyValue('border-top-width')//get the top border of the canvas
    var padTop=getComputedStyle(canvas,null).getPropertyValue('padding-top');//get the top padding of the canvas
    this.shape.x.push(event.clientX-parseFloat(borderLeft)-parseFloat(padLeft)-rect.left)//get the x coordinate of the mouse 
    this.shape.y.push(event.clientY-parseFloat(borderTop)-parseFloat(padTop)-rect.top)//get the y coordinate of the mouse
    },




    Draw:function(event){
    if(this.shape!=null){
     if(this.shape.type==="circle"){
      this.GetCoors(event);  
      var dialog=document.getElementById("dialog-box");
      dialog.style.display="block";      //show the dialog box for the user to enter the radius
      dialog.style.left=event.clientX+'px';//make the top left x coordinate of the dialog box appear on the mouse x coordinate
      dialog.style.top=event.clientY+'px';//make the top left y coordinate of the dialog box appear on the mouse y coordinate
    }
    else if(this.shape.type==="line"){
      this.GetCoors(event);
      this.Line();
    }
    else if(this.shape.type==="rectangle"){
      this.GetCoors(event);
      var whdialog=document.getElementById("w-h-dialog");
      whdialog.style.display="block";//Show the dialog box for the user to enter the width and height of the rectangle 
      whdialog.style.left=event.clientX+'px';//make the top left x coordinate of the dialog box appear on the mouse x coordinate
      whdialog.style.top=event.clientY+'px';//make the top left y coordinate of the dialog box appear on the mouse y coordinate
    }
    else if (this.shape.type==="square"){
      this.GetCoors(event);
      var dialog2=document.getElementById("dialog-box");
      dialog2.style.display="block";      //show the dialog box for the user to enter the radius
      dialog2.style.left=event.clientX+'px';//make the top left x coordinate of the dialog box appear on the mouse x coordinate
      dialog2.style.top=event.clientY+'px';//make the top left y coordinate of the dialog box appear on the mouse y coordinate
    }
    else if(this.shape.type==="triangle"){
      this.GetCoors(event);
      this.Triangle();
    }
    else if(this.shape.type==="ellipse"){
      this.GetCoors(event);
      var ellipsedialog=document.getElementById("ellipse-dialog");
      ellipsedialog.style.display="block";
      ellipsedialog.style.left=event.clientX+'px';
      ellipsedialog.style.top=event.clientY+'px';
    }
    }

    },
    Circle:function(){
      //Draw the circle
      this.CircleDraw();
      this.shape.id=this.counter;
      this.counter++;
      this.shapes.push(this.shape);//put the shape in the shapes array
      const t=this;
      
      axios.post("http://localhost:8081/draw",t.shape
      ).then(response=>{
        console.log(response.data)
      }).catch(e=>{
        console.log(e);
      })

      this.shape=null;//reset the shape pointer
    },
    CircleDraw:function(){
       var canvas=document.getElementById("canvas");
      var ctx=canvas.getContext('2d');
      
      ctx.beginPath();
      ctx.arc(this.shape.x[0],this.shape.y[0],this.shape.radius,0,Math.PI*2);
      ctx.stroke();
    },
    Line:function(){
      if(this.shape.x.length===2){//if the user clicked on the canvas 2 times then we have 2 x coordinates and 2 y coordinates and we can then draw the line 
      //draw the line 
      this.LineDraw();
      this.shape.id=this.counter;
      this.counter++;
      this.shapes.push(this.shape);//Put the shape in the shapes array
      const t=this;
      
      axios.post("http://localhost:8081/draw",t.shape
      ).then(response=>{
        console.log(response.data)
      }).catch(e=>{
        console.log(e);
      })
      this.shape=null;//reset the shape pointer
      }
    },
    LineDraw:function(){
      var canvas=document.getElementById("canvas");
      var ctx=canvas.getContext('2d');
      ctx.beginPath();
      ctx.moveTo(this.shape.x[0],this.shape.y[0]);
      ctx.lineTo(this.shape.x[1],this.shape.y[1]);
      ctx.stroke();
    },
    Rectangle:function(){
      //draw the rectangle
      this.RectangleDraw();
      this.shape.id=this.counter;
      this.counter++;
      this.shapes.push(this.shape);//put the shape into the shapes array
       const t=this;
      
      axios.post("http://localhost:8081/draw",t.shape
      ).then(response=>{
        console.log(response.data)
      }).catch(e=>{
        console.log(e);
      })
      this.shape=null;//reset the shape pointer
    },
    RectangleDraw:function(){
      var canvas=document.getElementById("canvas");
      var ctx=canvas.getContext('2d');
      ctx.beginPath();
      ctx.rect(this.shape.x[0],this.shape.y[0],this.shape.width,this.shape.height);
      ctx.stroke(); 
    },
    Square:function(){
      this.SquareDraw();
      this.shape.id=this.counter;
      this.counter++;
      this.shapes.push(this.shape);//put the shape into the shapes array
      const t=this;
      
      axios.post("http://localhost:8081/draw",t.shape
      ).then(response=>{
        console.log(response.data)
      }).catch(e=>{
        console.log(e);
      })
      this.shape=null;//reset the shape pointer

    },
    SquareDraw:function(){
      var canvas=document.getElementById("canvas");
      var ctx=canvas.getContext('2d');
      ctx.beginPath();
      ctx.rect(this.shape.x[0],this.shape.y[0],this.shape.side,this.shape.side);
      ctx.stroke();
    },
    Triangle:function(){
      if(this.shape.x.length===3){//if the user clicked on the canvas three times
        this.TriangleDraw();
        this.shape.id=this.counter;
        this.counter++;
        this.shapes.push(this.shape);
        const t=this;
      
      axios.post("http://localhost:8081/draw",t.shape
      ).then(response=>{
        console.log(response.data)
      }).catch(e=>{
        console.log(e);
      })
        this.shape=null;
      }
      
    },
    TriangleDraw:function(){
      var canvas=document.getElementById('canvas');
        var ctx=canvas.getContext('2d');
        ctx.beginPath();
        ctx.moveTo(this.shape.x[0],this.shape.y[0]);
        ctx.lineTo(this.shape.x[1],this.shape.y[1]);
        ctx.lineTo(this.shape.x[2],this.shape.y[2]);
        ctx.lineTo(this.shape.x[0],this.shape.y[0]);
        ctx.stroke();
    },
    Ellipse:function(){
      this.EllipseDraw();
      this.shape.id=this.counter;
      this.counter++;
      this.shapes.push(this.shape);
      const t=this;
      
      axios.post("http://localhost:8081/draw",t.shape
      ).then(response=>{
        console.log(response.data)
      }).catch(e=>{
        console.log(e);
      })
      this.shape=null;
    },
    EllipseDraw(){
      var canvas=document.getElementById("canvas");
      var ctx=canvas.getContext("2d");
      ctx.beginPath();
      ctx.ellipse(this.shape.x[0],this.shape.y[0],this.shape.radiusX,this.shape.radiusY,this.shape.rotationAngle,0,2*Math.PI);
      ctx.stroke();
    },

    DialogInput:function(input){
      if(this.shape.type==="circle"){
      this.shape.radius=parseFloat(input)*38;
      console.log(parseFloat(input));
      }
      else if (this.shape.type==="square"){
        this.shape.side=parseFloat(input)*38;
      }
      var dialog=document.getElementById("dialog-box");
      dialog.style.display="none";
      if(this.shape.type==="circle"){
      this.Circle();
      }
      else if (this.shape.type==="square"){
        this.Square();
      }
    },
    WHDialogInput:function(width,height){
      this.shape.width=parseFloat(width)*38;
      this.shape.height=parseFloat(height)*38;
      var whdialog=document.getElementById("w-h-dialog");
      whdialog.style.display="none";
      this.Rectangle();
    },
    EllipseDialogInput:function(radiusX,radiusY,rotationAngle){
      this.shape.radiusX=parseFloat(radiusX)*38;
      this.shape.radiusY=parseFloat(radiusY)*38;
      this.shape.rotationAngle=parseFloat(rotationAngle);
      var ellipsedialog=document.getElementById("ellipse-dialog");
      ellipsedialog.style.display="none";
      this.Ellipse();
    },
    Undo:function(){
      const t=this;
      axios.get("http://localhost:8081/undo").then(response=>{
        t.shape=response.data;
         
       if (t.shape!=null&&t.shape!=""){
        if(this.shape.type!="line"&&this.shape.type!="triangle"){
          t.shape.x=[t.shape.x];
          t.shape.y=[t.shape.y];
        }
        t.shapes[t.shape.id]=t.shape;
      }
      t.clearCanvas();
      t.DrawAll();
      }).catch(e=>{
        console.log("This is the undo error");
        console.log(e);
      })
      
    },
    Redo:function(){
      const t=this;
      axios.get("http://localhost:8081/redo").then(response=>{
        t.shape=response.data
        console.log(response.data);
       if (t.shape.type!=null){
         if(this.shape.type!="line"&&this.shape.type!="triangle"){
          t.shape.x=[t.shape.x];
          t.shape.y=[t.shape.y];
        }
        t.shapes[t.shape.id]=t.shape;
      }
      t.clearCanvas();
      t.DrawAll();
      }).catch(e=>{
        console.log("This is the redo error");
        console.log(e);
      })
      
    },
    DrawAll:function(){
      for(var i=0;i<this.shapes.length;i++){
      
        this.shape=this.shapes[i];
        if(this.shape.type!=null){
        if(this.shape.type==="circle"){
          this.CircleDraw();
        }
        else if (this.shape.type==="ellipse"){
          this.EllipseDraw();
        }
        else if (this.shape.type==="square"){
          this.SquareDraw();
        }
        else if (this.shape.type==="rectangle"){
          this.RectangleDraw();
        }
        else if (this.shape.type==="line"){
          this.LineDraw();
        }
        else {
          this.TriangleDraw();
        }
        }
      }
      this.shape=null;
    },
    clearCanvas:function(){
      var canvas=document.getElementById("canvas");
      var ctx=canvas.getContext("2d");
      ctx.clearRect(0,0,canvas.width,canvas.height);
      ctx.beginPath();
    },
    Copy:function(){
      axios.get('http://localhost:8081/copy',{
        params:{
          id:0
        }
      }).then(response=>{
        console.log(response);
      }).catch(e=>{
        console.log(e);
      })
    }
    },
  }
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  display:flex;
  flex-direction:row;
}
#canvas {
 border:1px solid black;
 margin:auto;
}
#shapes{
border:1px solid black;
display:flex;
left:0px;
flex-direction: column;
}

</style>

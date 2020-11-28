<template>
  <div id="app">
     <shapes @draw-shape="DrawShape" id="shapes"/>
    <canvas id="canvas" height=500px width=500px @click="Draw"></canvas>
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
export default {
  name: 'App',
  data: function(){
    return {
      shape:null,
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
      var canvas=document.getElementById("canvas");
      var ctx=canvas.getContext('2d');
      ctx.beginPath();
      ctx.arc(this.shape.x[0],this.shape.y[0],this.shape.radius,0,Math.PI*2);
      ctx.stroke();

      this.shapes.push(this.shape);//put the shape in the shapes array
      this.shape=null;//reset the shape pointer
    },
    Line:function(){
      if(this.shape.x.length===2){//if the user clicked on the canvas 2 times then we have 2 x coordinates and 2 y coordinates and we can then draw the line 
      //draw the line 
      var canvas=document.getElementById("canvas");
      var ctx=canvas.getContext('2d');
      ctx.beginPath();
      ctx.moveTo(this.shape.x[0],this.shape.y[0]);
      ctx.lineTo(this.shape.x[1],this.shape.y[1]);
      ctx.stroke();

      this.shapes.push(this.shape);//Put the shape in the shapes array
      this.shape=null;//reset the shape pointer
      }
    },
    Rectangle:function(){
      //draw the rectangle
      var canvas=document.getElementById("canvas");
      var ctx=canvas.getContext('2d');
      ctx.beginPath();
      ctx.rect(this.shape.x[0],this.shape.y[0],this.shape.width,this.shape.height);
      ctx.stroke();

      this.shapes.push(this.shape);//put the shape into the shapes array
      this.shape=null;//reset the shape pointer
    },
    Square:function(){
      var canvas=document.getElementById("canvas");
      var ctx=canvas.getContext('2d');
      ctx.beginPath();
      ctx.rect(this.shape.x[0],this.shape.y[0],this.shape.side,this.shape.side);
      ctx.stroke();
    },
    Triangle:function(){
      if(this.shape.x.length===3){//if the user clicked on the canvas three times
        var canvas=document.getElementById('canvas');
        var ctx=canvas.getContext('2d');
        ctx.beginPath();
        ctx.moveTo(this.shape.x[0],this.shape.y[0]);
        ctx.lineTo(this.shape.x[1],this.shape.y[1]);
        ctx.lineTo(this.shape.x[2],this.shape.y[2]);
        ctx.lineTo(this.shape.x[0],this.shape.y[0]);
        ctx.stroke();
        this.shapes.push(this.shape);
        this.shape=null;
      }
      
    },
    Ellipse:function(){
      var canvas=document.getElementById("canvas");
      var ctx=canvas.getContext("2d");
      ctx.beginPath();
      ctx.ellipse(this.shape.x[0],this.shape.y[0],this.shape.radiusX,this.shape.radiusY,this.shape.rotationAngle,0,2*Math.PI);
      ctx.stroke();
      this.shapes.push(this.shape);
      this.shape=null;
    },

    DialogInput:function(input){
      if(this.shape.type==="circle"){
      this.shape.radius=parseFloat(input);
      }
      else if (this.shape.type==="square"){
        this.shape.side=parseFloat(input);
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
      this.shape.width=parseFloat(width);
      this.shape.height=parseFloat(height);
      var whdialog=document.getElementById("w-h-dialog");
      whdialog.style.display="none";
      this.Rectangle();
    },
    EllipseDialogInput:function(radiusX,radiusY,rotationAngle){
      this.shape.radiusX=radiusX;
      this.shape.radiusY=radiusY;
      this.shape.rotationAngle=rotationAngle;
      var ellipsedialog=document.getElementById("ellipse-dialog");
      ellipsedialog.style.display="none";
      this.Ellipse();
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

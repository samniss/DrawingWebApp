<template>
  <div id="app">
    <shapes
      @draw-shape="DrawShape"
      @selected="chooseSelect"
      @moved="chooseMove"
      @copied="chooseCopy"
      @Deleted="deleting"
      @undo-shape="Undo"
      @redo-shape="Redo"
      @Colored="Color"
      id="shapes"
    />
    <canvas
      id="canvas"
      height="500px"
      width="1000px"
      @click="excuteChosen"
    ></canvas>
    <DialogBox @dialog-input="DialogInput" id="dialog-box" />
    <WHDialog @w-h-input="WHDialogInput" id="w-h-dialog" />
    <EllipseDialog @ellipse-input="EllipseDialogInput" id="ellipse-dialog" />
  </div>
</template>

<script>
import shapes from "../src/components/shapes";
import DialogBox from "../src/components/Dialog";
import WHDialog from "../src/components/WHDialog";
import EllipseDialog from "../src//components//EllipseDialog";
const axios = require("axios").default;
export default {
  name: "App",
  data: function () {
    return {
      shape: null,
      wotkingShape: null,
      shapes: [], //array of shapes
      counter: 0,
      draw: true,
      select: false,
      move: false,
      copy: false,
      delet: false,
      dotted: false,
      redraw: false,
      color: "",
      m: 0, //the index of the workingShape in the array of shapes
    };
  },
  components: {
    shapes,
    DialogBox,
    WHDialog,
    EllipseDialog,
  },
  methods: {
    Color: function (c) {
      this.color = c;
      this.coloring();
      this.color = "";
    },
    excuteChosen: function (event) {
      if (this.draw === true) {
        this.Draw(event);
      } else if (this.select === true) {
        this.selecting(event);
      } else if (this.move === true) {
        this.moving(event);
      } else if (this.copy === true) {
        this.copying(event);
      }
    },
    DrawShape: function (shape) {
      //Determine the shape (circle,rectangle etc)
      this.draw = true;
      this.select = false;
      this.move = false;
      this.copy = false;
      this.delet = false;
      this.dotted = false;
      this.redrawCanvas();
      this.shape = shape;
    },
    GetCoors: function (event) {
      /*getting the x and y coordinates of the mouse when mouse is clicked on the canvas*/
      var canvas = document.getElementById("canvas");
      var rect = event.target.getBoundingClientRect(); //Get the surrounding rectangle of the canvas including padding and border
      var borderLeft = getComputedStyle(canvas, null).getPropertyValue(
        "border-left-width"
      ); //get the left border of the canvas
      var padLeft = getComputedStyle(canvas, null).getPropertyValue(
        "padding-left"
      ); //get the left padding of the canvas
      var borderTop = getComputedStyle(canvas, null).getPropertyValue(
        "border-top-width"
      ); //get the top border of the canvas
      var padTop = getComputedStyle(canvas, null).getPropertyValue(
        "padding-top"
      ); //get the top padding of the canvas*/
      if (this.draw === true) {
        this.shape.x.push(
          event.clientX -
            parseFloat(borderLeft) -
            parseFloat(padLeft) -
            rect.left
        ); //get the x coordinate of the mouse
        this.shape.y.push(
          event.clientY - parseFloat(borderTop) - parseFloat(padTop) - rect.top
        ); //get the y coordinate of the mouse
      } else if (this.move === true) {
        this.wotkingShape.x[0] =
          event.clientX -
          parseFloat(borderLeft) -
          parseFloat(padLeft) -
          rect.left; //get the x coordinate of the mouse
        this.wotkingShape.y[0] =
          event.clientY - parseFloat(borderTop) - parseFloat(padTop) - rect.top; //get the y coordinate of the mouse
      } else if (this.copy === true) {
        this.shape.x[0] =
          event.clientX -
          parseFloat(borderLeft) -
          parseFloat(padLeft) -
          rect.left; //get the x coordinate of the mouse
        this.shape.y[0] =
          event.clientY - parseFloat(borderTop) - parseFloat(padTop) - rect.top; //get the y coordinate of the mouse
      }
    },
    Draw: function (event) {
      if (this.shape != null) {
        if (this.shape.type === "circle") {
          this.GetCoors(event);
          var dialog = document.getElementById("dialog-box");
          dialog.style.display = "block"; //show the dialog box for the user to enter the radius
          dialog.style.left = event.clientX + "px"; //make the top left x coordinate of the dialog box appear on the mouse x coordinate
          dialog.style.top = event.clientY + "px"; //make the top left y coordinate of the dialog box appear on the mouse y coordinate
        } else if (this.shape.type === "line") {
          this.GetCoors(event);
          this.Line();
        } else if (this.shape.type === "rectangle") {
          this.GetCoors(event);
          var whdialog = document.getElementById("w-h-dialog");
          whdialog.style.display = "block"; //Show the dialog box for the user to enter the width and height of the rectangle
          whdialog.style.left = event.clientX + "px"; //make the top left x coordinate of the dialog box appear on the mouse x coordinate
          whdialog.style.top = event.clientY + "px"; //make the top left y coordinate of the dialog box appear on the mouse y coordinate
        } else if (this.shape.type === "square") {
          this.GetCoors(event);
          var dialog2 = document.getElementById("dialog-box");
          dialog2.style.display = "block"; //show the dialog box for the user to enter the radius
          dialog2.style.left = event.clientX + "px"; //make the top left x coordinate of the dialog box appear on the mouse x coordinate
          dialog2.style.top = event.clientY + "px"; //make the top left y coordinate of the dialog box appear on the mouse y coordinate
        } else if (this.shape.type === "triangle") {
          this.GetCoors(event);
          this.Triangle();
        } else if (this.shape.type === "ellipse") {
          this.GetCoors(event);
          var ellipsedialog = document.getElementById("ellipse-dialog");
          ellipsedialog.style.display = "block";
          ellipsedialog.style.left = event.clientX + "px";
          ellipsedialog.style.top = event.clientY + "px";
        }
      }
    },
    Circle: function () {
      //Draw the circle
      var canvas = document.getElementById("canvas");
      var ctx = canvas.getContext("2d");
      ctx.beginPath();
      ctx.arc(
        this.shape.x[0],
        this.shape.y[0],
        this.shape.radius,
        0,
        Math.PI * 2
      );
      if (this.dotted == true) {
        ctx.setLineDash([5, 5]);
        this.dotted = false;
      }

      ctx.stroke();
      ctx.fillStyle = this.shape.color;
      ctx.fill();
      ctx.setLineDash([]);
      if (this.redraw != true && (this.draw === true || this.copy == true)) {
        this.shape.id = this.counter;
        this.counter++;
        this.shapes.push(this.shape); //put the shape in the shapes array
        this.redraw = false;
        const t = this;
        axios
          .post("http://localhost:8081/draw", t.shape)
          .then((response) => {
            console.log(response.data);
          })
          .catch((e) => {
            console.log(e);
          });
      }
      this.shape = null; //reset the shape pointer
    },
    Line: function () {
      if (this.shape.x.length === 2) {
        //if the user clicked on the canvas 2 times then we have 2 x coordinates and 2 y coordinates and we can then draw the line
        //draw the line
        var canvas = document.getElementById("canvas");
        var ctx = canvas.getContext("2d");
        ctx.beginPath();
        ctx.moveTo(this.shape.x[0], this.shape.y[0]);
        ctx.lineTo(this.shape.x[1], this.shape.y[1]);
        if (this.dotted == true) {
          ctx.setLineDash([5, 5]);
          this.dotted = false;
        }
        ctx.stroke();
        ctx.setLineDash([]);
        if (this.redraw != true && (this.draw === true || this.copy == true)) {
          this.shape.id = this.counter;
          this.counter++;
          this.shapes.push(this.shape); //Put the shape in the shapes array
          this.redraw = false;
          const t = this;
          axios
            .post("http://localhost:8081/draw", t.shape)
            .then((response) => {
              console.log(response.data);
            })
            .catch((e) => {
              console.log(e);
            });
        }
        this.shape = null; //reset the shape pointer
      }
    },
    Rectangle: function () {
      //draw the rectangle
      var canvas = document.getElementById("canvas");
      var ctx = canvas.getContext("2d");
      ctx.beginPath();
      ctx.rect(
        this.shape.x[0],
        this.shape.y[0],
        this.shape.width,
        this.shape.height
      );
      if (this.dotted == true) {
        ctx.setLineDash([5, 5]);
        this.dotted = false;
      }
      ctx.stroke();
      ctx.fillStyle = this.shape.color;
      ctx.fill();
      ctx.setLineDash([]);
      if (this.redraw != true && (this.draw === true || this.copy == true)) {
        this.shape.id = this.counter;
        this.counter++;
        this.shapes.push(this.shape); //put the shape into the shapes array
        this.redraw = false;
        const t = this;
        axios
          .post("http://localhost:8081/draw", t.shape)
          .then((response) => {
            console.log(response.data);
          })
          .catch((e) => {
            console.log(e);
          });
      }
      this.shape = null; //reset the shape pointer
    },
    Square: function () {
      //Draw the square
      var canvas = document.getElementById("canvas");
      var ctx = canvas.getContext("2d");
      ctx.beginPath();
      ctx.rect(
        this.shape.x[0],
        this.shape.y[0],
        this.shape.side,
        this.shape.side
      );
      if (this.dotted == true) {
        ctx.setLineDash([5, 5]);
        this.dotted = false;
      }
      ctx.stroke();
      ctx.fillStyle = this.shape.color;
      ctx.fill();
      ctx.setLineDash([]);
      if (this.redraw != true && (this.draw === true || this.copy == true)) {
        this.shape.id = this.counter;
        this.counter++;
        this.shapes.push(this.shape); //put the shape into the shapes array
        this.redraw = false;
        const t = this;
        axios
          .post("http://localhost:8081/draw", t.shape)
          .then((response) => {
            console.log(response.data);
          })
          .catch((e) => {
            console.log(e);
          });
      }
      this.shape = null; //reset the shape pointer
    },
    Triangle: function () {
      //Draw the triangle
      if (this.shape.x.length === 3) {
        //if the user clicked on the canvas three times
        var canvas = document.getElementById("canvas");
        var ctx = canvas.getContext("2d");
        ctx.beginPath();
        ctx.moveTo(this.shape.x[0], this.shape.y[0]);
        ctx.lineTo(this.shape.x[1], this.shape.y[1]);
        ctx.lineTo(this.shape.x[2], this.shape.y[2]);
        ctx.lineTo(this.shape.x[0], this.shape.y[0]);
        if (this.dotted == true) {
          ctx.setLineDash([5, 5]);
          this.dotted = false;
        }
        ctx.stroke();
        ctx.fillStyle = this.shape.color;
        ctx.fill();
        ctx.setLineDash([]);
        if (this.redraw != true && (this.draw === true || this.copy == true)) {
          this.shape.id = this.counter;
          this.counter++;
          this.shapes.push(this.shape); //put the shape in the shapes array
          this.redraw = false;
          const t = this;
          axios
            .post("http://localhost:8081/draw", t.shape)
            .then((response) => {
              console.log(response.data);
            })
            .catch((e) => {
              console.log(e);
            });
        }
        this.shape = null; //reset the shape pointer
      }
    },
    Ellipse: function () {
      var canvas = document.getElementById("canvas");
      var ctx = canvas.getContext("2d");
      ctx.beginPath();
      ctx.ellipse(
        this.shape.x[0],
        this.shape.y[0],
        this.shape.radiusX,
        this.shape.radiusY,
        this.shape.rotationAngle,
        0,
        2 * Math.PI
      );
      if (this.dotted == true) {
        ctx.setLineDash([5, 5]);
        this.dotted = false;
      }
      ctx.stroke();
      ctx.fillStyle = this.shape.color;
      ctx.fill();
      ctx.setLineDash([]);
      if (this.redraw != true && (this.draw === true || this.copy == true)) {
        this.shape.id = this.counter;
        this.counter++;
        this.shapes.push(this.shape); //put the shape in the shapes array
        this.redraw = false;
        const t = this;
        axios
          .post("http://localhost:8081/draw", t.shape)
          .then((response) => {
            console.log(response.data);
          })
          .catch((e) => {
            console.log(e);
          });
      }
      this.shape = null; //reset the shape pointer
    },
    DialogInput: function (input) {
      if (this.shape.type === "circle") {
        this.shape.radius = parseFloat(input) * 38;
      } else if (this.shape.type === "square") {
        this.shape.side = parseFloat(input) * 38;
      }
      var dialog = document.getElementById("dialog-box");
      dialog.style.display = "none";
      if (this.shape.type === "circle") {
        this.Circle();
      } else if (this.shape.type === "square") {
        this.Square();
      }
    },
    WHDialogInput: function (width, height) {
      this.shape.width = parseFloat(width) * 38;
      this.shape.height = parseFloat(height) * 38;
      var whdialog = document.getElementById("w-h-dialog");
      whdialog.style.display = "none";
      this.Rectangle();
    },
    EllipseDialogInput: function (radiusX, radiusY, rotationAngle) {
      this.shape.radiusX = radiusX * 38;
      this.shape.radiusY = radiusY * 38;
      this.shape.rotationAngle = (rotationAngle * Math.PI) / 180;
      var ellipsedialog = document.getElementById("ellipse-dialog");
      ellipsedialog.style.display = "none";
      this.Ellipse();
    },
    chooseDraw: function () {
      this.draw = true;
      this.select = false;
      this.move = false;
      this.copy = false;
      this.delet = false;
      this.dotted = false;
    },
    chooseSelect: function () {
      this.draw = false;
      this.select = true;
      this.move = false;
      this.copy = false;
      this.delet = false;
      this.dotted = false;
    },
    chooseMove: function () {
      this.draw = false;
      this.select = false;
      this.move = true;
      this.copy = false;
      this.delet = false;
      this.dotted = false;
    },
    chooseCopy: function () {
      this.draw = false;
      this.select = false;
      this.move = false;
      this.copy = true;
      this.delet = false;
      this.dotted = false;
    },
    sign: function (p1x, p1y, p2x, p2y, p3x, p3y) {
      return (p1x - p3x) * (p2y - p3y) - (p2x - p3x) * (p1y - p3y);
    },
    PointInTriangle: function (ptx, pty, v1x, v1y, v2x, v2y, v3x, v3y) {
      var d1, d2, d3;
      var has_neg, has_pos;
      d1 = this.sign(ptx, pty, v1x, v1y, v2x, v2y);
      d2 = this.sign(ptx, pty, v2x, v2y, v3x, v3y);
      d3 = this.sign(ptx, pty, v3x, v3y, v1x, v1y);
      has_neg = Boolean(d1 < 0 || d2 < 0 || d3 < 0);
      has_pos = Boolean(d1 > 0 || d2 > 0 || d3 > 0);
      return Boolean(!(has_neg && has_pos));
    },
    checkpoint: function (h, k, x, y, a, b, alpha) {
      // checking the equation of ellipse with the given point
      var p =
        parseInt(
          Math.pow((x - h) * Math.cos(alpha) + (y - k) * Math.sin(alpha), 2)
        ) /
          parseInt(Math.pow(a, 2)) +
        parseInt(
          Math.pow((x - h) * Math.sin(alpha) - (y - k) * Math.cos(alpha), 2)
        ) /
          parseInt(Math.pow(b, 2));
      return p;
    },
    checkPointOnLine: function (ptx, pty, v1x, v1y, v2x, v2y) {
      var m = (v2y - v1y) / (v2x - v1x);
      var angle = Math.atan(m);
      var a = Math.sqrt(Math.pow(v1x - v2x, 2) + Math.pow(v1y - v2y, 2)) / 2;
      var b = 20;
      var h = (v1x + v2x) / 2;
      var k = (v1y + v2y) / 2;
      return this.checkpoint(h, k, ptx, pty, a, b, angle);
    },
    selecting: function () {
      var canvas = document.getElementById("canvas");
      var rect = event.target.getBoundingClientRect(); //Get the surrounding rectangle of the canvas including padding and border
      var borderLeft = getComputedStyle(canvas, null).getPropertyValue(
        "border-left-width"
      ); //get the left border of the canvas
      var padLeft = getComputedStyle(canvas, null).getPropertyValue(
        "padding-left"
      ); //get the left padding of the canvas
      var borderTop = getComputedStyle(canvas, null).getPropertyValue(
        "border-top-width"
      ); //get the top border of the canvas
      var padTop = getComputedStyle(canvas, null).getPropertyValue(
        "padding-top"
      ); //get the top padding of the canvas
      var x =
        event.clientX -
        parseFloat(borderLeft) -
        parseFloat(padLeft) -
        rect.left; //get the x coordinate of the mouse
      var y =
        event.clientY - parseFloat(borderTop) - parseFloat(padTop) - rect.top; //get the y coordinate of the mouse
      var i;
      for (i = this.shapes.length - 1; i >= 0; i--) {
        if (this.shapes[i] != null && this.shapes[i] != undefined) {
          this.chooseSelect();
          if (this.shapes[i].type === "circle") {
            if (
              Math.sqrt(
                Math.pow(x - this.shapes[i].x[0], 2) +
                  Math.pow(y - this.shapes[i].y[0], 2)
              ) <=
              this.shapes[i].radius / 2
            ) {
              this.clearCanvas();
              this.m = i;
              this.redrawCanvas(this.m);
              this.shape = this.shapes[i];
              this.wotkingShape = this.shapes[i];
              this.dotted = true;
              this.Circle();
              break;
            }
          } else if (this.shapes[i].type === "line") {
            if (
              this.checkPointOnLine(
                x,
                y,
                this.shapes[i].x[0],
                this.shapes[i].y[0],
                this.shapes[i].x[1],
                this.shapes[i].y[1]
              ) <= 1
            ) {
              this.clearCanvas();
              this.m = i;
              this.redrawCanvas(this.m);
              this.shape = this.shapes[i];
              this.wotkingShape = this.shapes[i];
              this.dotted = true;
              this.Line();
              break;
            }
          } else if (this.shapes[i].type === "square") {
            if (
              x >= this.shapes[i].x[0] &&
              x <= this.shapes[i].x[0] + this.shapes[i].side &&
              y >= this.shapes[i].y[0] &&
              y <= this.shapes[i].y[0] + this.shapes[i].side
            ) {
              this.clearCanvas();
              this.m = i;
              this.redrawCanvas(this.m);
              this.shape = this.shapes[i];
              this.wotkingShape = this.shapes[i];
              this.dotted = true;
              this.Square();
              break;
            }
          } else if (this.shapes[i].type === "rectangle") {
            if (
              x >= this.shapes[i].x[0] &&
              x <= this.shapes[i].x[0] + this.shapes[i].width &&
              y >= this.shapes[i].y[0] &&
              y <= this.shapes[i].y[0] + this.shapes[i].height
            ) {
              this.clearCanvas();
              this.m = i;
              this.redrawCanvas(this.m);
              this.shape = this.shapes[i];
              this.wotkingShape = this.shapes[i];
              this.dotted = true;
              this.Rectangle();
              break;
            }
          } else if (this.shapes[i].type === "triangle") {
            if (
              this.PointInTriangle(
                x,
                y,
                this.shapes[i].x[0],
                this.shapes[i].y[0],
                this.shapes[i].x[1],
                this.shapes[i].y[1],
                this.shapes[i].x[2],
                this.shapes[i].y[2]
              )
            ) {
              this.clearCanvas();
              this.m = i;
              this.redrawCanvas(this.m);
              this.shape = this.shapes[i];
              this.wotkingShape = this.shapes[i];
              this.dotted = true;
              this.Triangle();
              break;
            }
          } else if (this.shapes[i].type === "ellipse") {
            if (
              this.checkpoint(
                this.shapes[i].x[0],
                this.shapes[i].y[0],
                x,
                y,
                this.shapes[i].radiusX / 2,
                this.shapes[i].radiusY / 2,
                this.shapes[i].rotationAngle
              ) <= 1
            ) {
              this.clearCanvas();
              this.m = i;
              this.redrawCanvas(this.m);
              this.shape = this.shapes[i];
              this.wotkingShape = this.shapes[i];
              this.dotted = true;
              this.Ellipse();
              break;
            }
          }
          this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
        }
      }
    },
    coloring: function () {
      if (this.wotkingShape != null && this.wotkingShape != undefined) {
        this.chooseMove();
        if (this.wotkingShape.type === "circle") {
          this.wotkingShape.color = this.color;
          this.clearCanvas();
          this.redrawCanvas(this.m);
          this.shape = this.wotkingShape;
          axios
            .post("http://localhost:8081/update", this.shape)
            .then((response) => {
              console.log(response.data);
            })
            .catch((e) => {
              console.log(e);
            });
          this.Circle();
          this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
          this.wotkingShape = null;
        } else if (this.wotkingShape.type === "square") {
          this.wotkingShape.color = this.color;
          this.clearCanvas();
          this.redrawCanvas(this.m);
          this.shape = this.wotkingShape;
          axios
            .post("http://localhost:8081/update", this.shape)
            .then((response) => {
              console.log(response.data);
            })
            .catch((e) => {
              console.log(e);
            });
          this.Square();
          this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
          this.wotkingShape = null;
        } else if (this.wotkingShape.type === "rectangle") {
          this.wotkingShape.color = this.color;
          this.clearCanvas();
          this.redrawCanvas(this.m);
          this.shape = this.wotkingShape;
          axios
            .post("http://localhost:8081/update", this.shape)
            .then((response) => {
              console.log(response.data);
            })
            .catch((e) => {
              console.log(e);
            });
          this.Rectangle();
          this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
          this.wotkingShape = null;
        } else if (this.wotkingShape.type === "triangle") {
          this.wotkingShape.color = this.color;
          this.clearCanvas();
          this.redrawCanvas(this.m);
          this.shape = this.wotkingShape;
          axios
            .post("http://localhost:8081/update", this.shape)
            .then((response) => {
              console.log(response.data);
            })
            .catch((e) => {
              console.log(e);
            });
          this.Triangle();
          this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
          this.wotkingShape = null;
        } else if (this.wotkingShape.type === "ellipse") {
          this.wotkingShape.color = this.color;
          this.clearCanvas();
          this.redrawCanvas(this.m);
          this.shape = this.wotkingShape;
          axios
            .post("http://localhost:8081/update", this.shape)
            .then((response) => {
              console.log(response.data);
            })
            .catch((e) => {
              console.log(e);
            });
          this.Ellipse();
          this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
          this.wotkingShape = null;
        }
      }
    },
    moving: function () {
      var x;
      var y;
      var xDiff;
      var yDiff;
      var i;
      if (this.wotkingShape != null && this.wotkingShape != undefined) {
        this.chooseMove();
        if (this.wotkingShape.type === "circle") {
          this.GetCoors(event);
          this.clearCanvas();
          this.redrawCanvas(this.m);
          this.shape = this.wotkingShape;
          axios
            .post("http://localhost:8081/update", this.shape)
            .then((response) => {
              console.log(response.data);
            })
            .catch((e) => {
              console.log(e);
            });
          this.Circle();
          this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
          this.wotkingShape = null;
        } else if (this.wotkingShape.type === "line") {
          x = parseFloat(this.wotkingShape.x[0]);
          y = parseFloat(this.wotkingShape.y[0]);
          this.GetCoors(event);
          xDiff = this.wotkingShape.x[0] - x;
          yDiff = this.wotkingShape.y[0] - y;
          for (i = 1; i < this.wotkingShape.x.length; i++) {
            this.wotkingShape.x[i] = this.wotkingShape.x[i] + xDiff;
          }
          for (i = 1; i < this.wotkingShape.y.length; i++) {
            this.wotkingShape.y[i] = this.wotkingShape.y[i] + yDiff;
          }
          this.clearCanvas();
          this.redrawCanvas(this.m);
          this.shape = this.wotkingShape;
          axios
            .post("http://localhost:8081/update", this.shape)
            .then((response) => {
              console.log(response.data);
            })
            .catch((e) => {
              console.log(e);
            });
          this.Line();
          this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
          this.wotkingShape = null;
        } else if (this.wotkingShape.type === "square") {
          this.GetCoors(event);
          this.clearCanvas();
          this.redrawCanvas(this.m);
          this.shape = this.wotkingShape;
          axios
            .post("http://localhost:8081/update", this.shape)
            .then((response) => {
              console.log(response.data);
            })
            .catch((e) => {
              console.log(e);
            });
          this.Square();
          this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
          this.wotkingShape = null;
        } else if (this.wotkingShape.type === "rectangle") {
          this.GetCoors(event);
          this.clearCanvas();
          this.redrawCanvas(this.m);
          this.shape = this.wotkingShape;
          axios
            .post("http://localhost:8081/update", this.shape)
            .then((response) => {
              console.log(response.data);
            })
            .catch((e) => {
              console.log(e);
            });
          this.Rectangle();
          this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
          this.wotkingShape = null;
        } else if (this.wotkingShape.type === "triangle") {
          x = parseFloat(this.wotkingShape.x[0]);
          y = parseFloat(this.wotkingShape.y[0]);
          this.GetCoors(event);
          xDiff = this.wotkingShape.x[0] - x;
          yDiff = this.wotkingShape.y[0] - y;
          for (i = 1; i < this.wotkingShape.x.length; i++) {
            this.wotkingShape.x[i] = this.wotkingShape.x[i] + xDiff;
          }
          for (i = 1; i < this.wotkingShape.y.length; i++) {
            this.wotkingShape.y[i] = this.wotkingShape.y[i] + yDiff;
          }
          this.clearCanvas();
          this.redrawCanvas(this.m);
          this.shape = this.wotkingShape;
          axios
            .post("http://localhost:8081/update", this.shape)
            .then((response) => {
              console.log(response.data);
            })
            .catch((e) => {
              console.log(e);
            });
          this.Triangle();
          this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
          this.wotkingShape = null;
        } else if (this.wotkingShape.type === "ellipse") {
          this.GetCoors(event);
          this.clearCanvas();
          this.redrawCanvas(this.m);
          this.shape = this.wotkingShape;
          axios
            .post("http://localhost:8081/update", this.shape)
            .then((response) => {
              console.log(response.data);
            })
            .catch((e) => {
              console.log(e);
            });
          this.Ellipse();
          this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
          this.wotkingShape = null;
        }
      }
    },
    copying: function (event) {
      if (this.wotkingShape != null && this.wotkingShape != undefined) {
        this.chooseCopy();
        var i;
        const t = this;
        if (this.wotkingShape.type === "circle") {
          axios
            .get("http://localhost:8081/copy", {
              params: {
                id: this.m,
              },
            })
            .then((response) => {
              console.log(response);
              this.shape = response.data;
              this.shape.x = [t.shape.x];
              this.shape.y = [t.shape.y];
              this.GetCoors(event);
              this.Circle();
              this.clearCanvas();
              this.redrawCanvas();
              this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
              this.wotkingShape = null;
            })
            .catch((e) => {
              console.log(e);
            });
        } else if (this.wotkingShape.type === "line") {
          axios
            .get("http://localhost:8081/copy", {
              params: {
                id: this.m,
              },
            })
            .then((response) => {
              console.log(response);
              this.shape = response.data;
              var x = parseFloat(this.shape.x[0]);
              var y = parseFloat(this.shape.y[0]);
              this.GetCoors(event);
              var xDiff = this.shape.x[0] - x;
              var yDiff = this.shape.y[0] - y;
              for (i = 1; i < this.shape.x.length; i++) {
                this.shape.x[i] = this.shape.x[i] + xDiff;
              }
              for (i = 1; i < this.shape.y.length; i++) {
                this.shape.y[i] = this.shape.y[i] + yDiff;
              }
              this.Line();
              this.clearCanvas();
              this.redrawCanvas();
              this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
              this.wotkingShape = null;
            })
            .catch((e) => {
              console.log(e);
            });
        } else if (this.wotkingShape.type === "square") {
          axios
            .get("http://localhost:8081/copy", {
              params: {
                id: this.m,
              },
            })
            .then((response) => {
              console.log(response);
              this.shape = response.data;
              t.shape.x = [t.shape.x];
              t.shape.y = [t.shape.y];
              this.GetCoors(event);
              this.Square();
              this.clearCanvas();
              this.redrawCanvas();
              this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
              this.wotkingShape = null;
            })
            .catch((e) => {
              console.log(e);
            });
        } else if (this.wotkingShape.type === "rectangle") {
          axios
            .get("http://localhost:8081/copy", {
              params: {
                id: this.m,
              },
            })
            .then((response) => {
              console.log(response);
              this.shape = response.data;
              t.shape.x = [t.shape.x];
              t.shape.y = [t.shape.y];
              this.GetCoors(event);
              this.Rectangle();
              this.clearCanvas();
              this.redrawCanvas();
              this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
              this.wotkingShape = null;
            })
            .catch((e) => {
              console.log(e);
            });
        } else if (this.wotkingShape.type === "triangle") {
          axios
            .get("http://localhost:8081/copy", {
              params: {
                id: this.m,
              },
            })
            .then((response) => {
              console.log(response);
              this.shape = response.data;
              var x = parseFloat(this.shape.x[0]);
              var y = parseFloat(this.shape.y[0]);
              this.GetCoors(event);
              var xDiff = this.shape.x[0] - x;
              var yDiff = this.shape.y[0] - y;
              for (i = 1; i < this.shape.x.length; i++) {
                this.shape.x[i] = this.shape.x[i] + xDiff;
              }
              for (i = 1; i < this.shape.y.length; i++) {
                this.shape.y[i] = this.shape.y[i] + yDiff;
              }
              this.Triangle();
              this.clearCanvas();
              this.redrawCanvas();
              this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
              this.wotkingShape = null;
            })
            .catch((e) => {
              console.log(e);
            });
        } else if (this.wotkingShape.type === "ellipse") {
          axios
            .get("http://localhost:8081/copy", {
              params: {
                id: this.m,
              },
            })
            .then((response) => {
              console.log(response);
              this.shape = response.data;
              t.shape.x = [t.shape.x];
              t.shape.y = [t.shape.y];
              this.GetCoors(event);
              this.Ellipse();
              this.clearCanvas();
              this.redrawCanvas();
              this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
              this.wotkingShape = null;
            })
            .catch((e) => {
              console.log(e);
            });
        }
      }
    },
    deleting: function () {
      axios
        .get("http://localhost:8081/remove", {
          params: {
            id: this.m,
          },
        })
        .then((response) => {
          console.log(response);
          this.shapes[this.m] = response.data;
          this.clearCanvas();
          this.redrawCanvas();
          this.chooseDraw(); //to return to the default position which is drawind (may be modified then)
          this.wotkingShape = null;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    Undo: function () {
      const t = this;
      axios
        .get("http://localhost:8081/undo")
        .then((response) => {
          t.shape = response.data;
          if (t.shape != null && t.shape != "") {
            if (this.shape.type != "line" && this.shape.type != "triangle") {
              t.shape.x = [t.shape.x];
              t.shape.y = [t.shape.y];
            }
            t.shapes[t.shape.id] = t.shape;
          }
          t.clearCanvas();
          t.redrawCanvas();
        })
        .catch((e) => {
          console.log("This is the undo error");
          console.log(e);
        });
    },
    Redo: function () {
      const t = this;
      axios
        .get("http://localhost:8081/redo")
        .then((response) => {
          t.shape = response.data;
          console.log(response.data);
          if (t.shape.type != null) {
            if (this.shape.type != "line" && this.shape.type != "triangle") {
              t.shape.x = [t.shape.x];
              t.shape.y = [t.shape.y];
            }
            t.shapes[t.shape.id] = t.shape;
          }
          t.clearCanvas();
          t.redrawCanvas();
        })
        .catch((e) => {
          console.log("This is the redo error");
          console.log(e);
        });
    },
    clearCanvas: function () {
      var canvas = document.getElementById("canvas");
      var ctx = canvas.getContext("2d");
      ctx.clearRect(0, 0, canvas.width, canvas.height);
    },
    redrawCanvas: function (j) {
      this.clearCanvas();
      var i;
      for (i = 0; i < this.shapes.length; i++) {
        if (i != j) {
          if (this.shapes[i] != null && this.shapes[i] != undefined) {
            if (this.shapes[i].type != "null") {
              if (this.shapes[i].type === "circle") {
                this.shape = this.shapes[i];
                this.redraw = true;
                this.Circle();
                this.redraw = false;
              } else if (this.shapes[i].type === "line") {
                this.shape = this.shapes[i];
                this.redraw = true;
                this.Line();
                this.redraw = false;
              } else if (this.shapes[i].type === "rectangle") {
                this.shape = this.shapes[i];
                this.redraw = true;
                this.Rectangle();
                this.redraw = false;
              } else if (this.shapes[i].type === "square") {
                this.shape = this.shapes[i];
                this.redraw = true;
                this.Square();
                this.redraw = false;
              } else if (this.shapes[i].type === "triangle") {
                this.shape = this.shapes[i];
                this.redraw = true;
                this.Triangle();
                this.redraw = false;
              } else if (this.shapes[i].type === "ellipse") {
                this.shape = this.shapes[i];
                this.redraw = true;
                this.Ellipse();
                this.redraw = false;
              }
            }
          }
        }
      }
    },
  },
};
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  display: flex;
  flex-direction: row;
}
#canvas {
  border: 1px solid black;
  margin: auto;
}
#shapes {
  border: 1px solid black;
  display: flex;
  left: 0px;
  flex-direction: column;
}
.display {
  color: #a3a3a3;
  border-bottom: 1px solid #e1e1e1;
  margin-bottom: 15px;
  overflow: hidden;
  text-overflow: clip;
}
</style>

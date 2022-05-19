<template>
  <div class="ButtonsGroup">
    <button class="b" id="line" @click="Line()">____</button>
    <button class="b" id="circle" @click="Circle()">&#9711;</button>
    <button class="b" id="rectangle" @click="Rectangle()">&#9645;</button>
    <button class="b" id="square" @click="Square()">&#8414;</button>
    <button class="b" id="triangle" @click="Triangle()">&#8420;</button>
    <button class="b" id="ellipse" @click="Ellipse()">&#11055;</button>
    <button class="b" id="select" @click="Select()">Select</button>
    <button class="b" id="move" @click="Move()">Move</button>
    <button class="b" id="copy" @click="Copy()">Copy</button>
    <button class="b" id="resize" @click="Resize()">Resize</button>
    <button class="b" id="delete" @click="Delete()">Delete</button>
    <button class="b" id="undo" @click="Undo()">Undo</button>
    <button class="b" id="redo" @click="Redo()">Redo</button>
    <span class="palette">
      <button
        class="p"
        id="blue"
        style="background-color: blue"
        @click="SelectedColor('blue')"
      ></button>
      <button class="p" id="red" style="background-color: red" @click="SelectedColor('red')"></button>
      <button
        class="p"
        id="yellow"
        style="background-color: yellow"
        @click="SelectedColor('yellow')"
      ></button>
      <button
        class="p"
        id="green"
        style="background-color: green"
        @click="SelectedColor('green')"
      ></button>
      <button
        class="p"
        id="black"
        style="background-color: black"
        @click="SelectedColor('black')"
      ></button>
      <button
        class="p"
        id="cyan"
        style="background-color: cyan"
        @click="SelectedColor('cyan')"
      ></button>
      <button
        class="p"
        id="magenta"
        style="background-color: magenta"
        @click="SelectedColor('magenta')"
      ></button>
      <button
        class="p"
        id="brown"
        style="background-color: brown"
        @click="SelectedColor('brown')"
      ></button>
      <button
        class="p"
        id="white"
        style="background-color: white"
        @click="SelectedColor('white')"
      ></button>
    </span>
  </div>
</template>
<script>
export default {
  name: "shapes",
  data() {
    return {
      shape: null,
    };
  },
  methods: {
    Select() {
      this.$emit("selected");
    },
    Move() {
      this.$emit("moved");
    },
    Copy() {
      this.$emit("copied");
    },
    Resize() {
      this.$emit("resized");
    },
    Delete() {
      this.$emit("Deleted");
    },
    Circle() {
      this.shape = {
        type: "circle",
        x: [],
        y: [],
        radius: 0,
        id: 0,
        color: "white",
      };
      this.$emit("draw-shape", this.shape);
    },
    Line() {
      this.shape = {
        type: "line",
        x: [],
        y: [],
        id: 0,
        color: "white",
      };
      this.$emit("draw-shape", this.shape);
    },
    Rectangle() {
      this.shape = {
        type: "rectangle",
        x: [],
        y: [],
        width: 0,
        height: 0,
        id: 0,
        color: "white",
      };
      this.$emit("draw-shape", this.shape);
    },
    Square() {
      this.shape = {
        type: "square",
        x: [],
        y: [],
        side: 0,
        id: 0,
        color: "white",
      };
      this.$emit("draw-shape", this.shape);
    },
    Triangle() {
      this.shape = {
        type: "triangle",
        x: [],
        y: [],
        id: 0,
        color: "white",
      };
      this.$emit("draw-shape", this.shape);
    },
    Ellipse() {
      this.shape = {
        type: "ellipse",
        x: [],
        y: [],
        radiusX: 0,
        radiusY: 0,
        rotationAngle: 0,
        id: 0,
        color: "white",
      };
      this.$emit("draw-shape", this.shape);
    },
    Undo() {
      this.$emit("undo-shape");
    },
    Redo() {
      this.$emit("redo-shape");
    },
    SelectedColor(TheColor){
      this.$emit("Colored", TheColor);
    },
  },
};
</script>
<style  scoped>
.ButtonsGroup {
  background-image: linear-gradient(-45deg, cyan, magenta);
  border-radius: 20px;
  margin-top: 1px;
  width: 150px;
}

.b {
  margin: auto;
  margin-top: 10px;
  margin-bottom: 5px;
  width: 50px;
  height: 30px;
  background-color: linear-gradient(
    -100deg,
    rgb(108, 35, 168),
    rgb(213, 233, 31)
  );
  background: cyan;
  border-radius: 20px;
}
.p {
  margin-bottom: 2px;
  width: 30px;
  height: 30px;
  background-color: linear-gradient(
    -100deg,
    rgb(108, 35, 168),
    rgb(213, 233, 31)
  );
  background: cyan;
  border-radius: 10px;
}
.palette {
  width: 100%;
  height: 10%;
  border-radius: 15px;
  margin-bottom: 60px;
}

#square {
  font-display: center;
  font-size: 20px;
}
#triangle {
  font-display: center;
  font-size: 20px;
}
#rectangle {
  font-display: center;
  font-size: 20px;
}
#circle {
  font-display: center;
  font-size: 20px;
}
#elipse {
  font-display: center;
  font-size: 20px;
}
</style>

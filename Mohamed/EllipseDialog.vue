<template>
  <div id="ellipse-dialog">
    <div id="dialog-content">
      <label>RadiusX</label>
      <textarea id="xarea"></textarea>
      <label>RadiusY</label>
      <textarea id="yarea"></textarea>
      <label>Angle(deg)</label>
      <textarea id="rarea"></textarea>
      <button @click="input()">Enter</button>
    </div>
  </div>
</template>
<script>
export default {
  name: "ellipse-dialog",
  methods: {
    input: function () {
      var xarea = document.getElementById("xarea");
      var yarea = document.getElementById("yarea");
      var rarea = document.getElementById("rarea");
      // handling errors
      if (isNaN(xarea.value) || isNaN(yarea.value) || isNaN(rarea.value)) {
        alert("Please, Enter only numbers");
      } else if (xarea.value < 0 || yarea.value < 0) {
        alert(
          "Please, Enter only positive numbers for all the radiuses values"
        );
      } else {
        this.$emit("ellipse-input", xarea.value, yarea.value, rarea.value);
        xarea.value = "";
        yarea.value = "";
        rarea.value = "";
      }
    },
  },
};
</script>
<style  scoped>
#ellipse-dialog {
  display: none;
  position: fixed;
  z-index: 1;
  border: 1px solid black;
  width: 100px;
}
#dialog-content {
  flex-direction: column;
  justify-content: space-around;
  background-color: #fefefe;
  border: 1px solid black;
  padding: 20px;
  display: inline-block;
}

#rarea {
  margin-bottom: 10px;
  max-height: 30px;
  min-height: 30px;
  min-width: 150px;
  max-width: 150px;
  display: inline-block;
}
#xarea {
  margin-bottom: 10px;
  max-height: 30px;
  min-height: 30px;
  min-width: 150px;
  max-width: 150px;
  display: inline-block;
}
#yarea {
  margin-bottom: 10px;
  max-height: 30px;
  min-height: 30px;
  min-width: 150px;
  max-width: 150px;
  display: inline-block;
}
</style>
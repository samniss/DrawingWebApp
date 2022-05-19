<template>
  <div id="ellipse-dialog">
    <div id="dialog-content">
      <input
          type="text"
          value=""
          placeholder="RadiusX"
          id = "xarea"
          v-model="text"
          class="text-box"
        />  
      <input
          type="text"
          value=""
          placeholder="RadiusY"
          id = "yarea"
          v-model="text"
          class="text-box"
        /> 
      <input
          type="text"
          value=""
          placeholder="Angle(deg)"
          id = "rarea"
          v-model="text"
          class="text-box"
        /> 
      <button @click="input()">Enter</button>
    </div>
  </div>
</template>
<script>
export default {
  name: "ellipse-dialog",
  methods: {
    input() {
      let xarea = document.getElementById("xarea");
      let yarea = document.getElementById("yarea");
      let rarea = document.getElementById("rarea");
      // handling errors
      if (isNaN(xarea.value) || isNaN(yarea.value) || isNaN(rarea.value)) {
        alert("Please, Enter Only Numbers");
      } else if (xarea.value <= 0 || yarea.value <= 0) {
        alert(
          "Please, Enter Only Positive Numbers For All The Radiuses Values"
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
  background-color: #e1fc6b;
  border-radius: 10px;
  width: 100px;
}
#dialog-content {
  flex-direction: column;
  justify-content: space-around;
  background-color: #e1fc6b;
  border: 1px solid black;
  padding: 20px;
  border-radius: 10px;
  display: inline-block;
}

.text-box {
  margin-bottom: 10px;
  height: 30px;
  width: 80px;
  padding: 5px;
  font-size: 15px;
  border-radius: 20px;
  background-color: azure;
  display: inline-block;
}

button{
  background-color: rgb(95, 106, 255);
  border-radius: 20px;  
}
</style>
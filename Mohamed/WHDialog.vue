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
  name: "w-h-dialog",
  methods: {
    input: function () {
      var widtharea = document.getElementById("widtharea");
      var heightarea = document.getElementById("heightarea");
      // handling errors
      if (isNaN(widtharea.value) || isNaN(heightarea.value)) {
        alert("Please, Enter only numbers");
      } else if (widtharea.value < 0 || heightarea.value < 0) {
        alert("Please, Enter only positive numbers");
      } else {
        this.$emit("w-h-input", widtharea.value, heightarea.value);
        widtharea.value = "";
        heightarea.value = "";
      }
    },
  },
};
</script>
<style  scoped>
#w-h-dialog {
  display: none;
  position: fixed;
  z-index: 1;
  border: 1px solid black;
  width: 100px;
}
#dialog-content {
  flex-direction: column;
  justify-content: space-around;
  background-color: #ffffff;
  border: 1px solid black;
  padding: 20px;
  display: inline-block;
}
#widtharea,
#heightarea {
  margin-bottom: 10px;
  max-height: 30px;
  min-height: 30px;
  min-width: 50px;
  max-width: 50px;
  display: inline-block;
}
</style>
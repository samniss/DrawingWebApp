<template>
  <div id="save-dialog-box">
    <div id="dialog-content">
        <label>Enter File name:</label>
        <textarea id="inputFileName"></textarea>
        <select v-model="selected">
                <option disabled value="">Please select one</option>
                <option>json</option>
                <option>XML</option>
        </select>
        <span>Selected: {{ selected }}</span>
        <button @click="input()">Enter</button>
    </div>
  </div>
</template>
<script>
export default {
  name: "save-dialog-box",
  selected: '',
  data: function () {
    return {
      selected: ''
    }
  },
  methods: {
    input: function () {
        var userInputFile = document.getElementById("inputFileName");
        console.log(userInputFile.value)
        console.log(this.selected)
        this.$emit("save-dialog-input", userInputFile.value,this.selected);
        //userInputFile.value = "";
        
    },
  },
};
</script>
<style  scoped>
#save-dialog-box {
  display: none;
  position: fixed;
  z-index: 1;
  border: 1px solid black;
  width: 200px;
}
#dialog-content {
  flex-direction: column;
  justify-content: space-around;
  background-color: #fefefe;
  border: 1px solid black;
  padding: 20px;
  display: inline-block;
}
#inputarea {
  margin-bottom: 10px;
  max-height: 20px;
  min-height: 10px;
  min-width: 100px;
  max-width: 200px;
  display: inline-block;
}
</style>
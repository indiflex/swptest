<template>
  <div class="md-form w-75">
    <!-- <input type="text" id="adminKey" v-model="myModel" class="form-control"> -->
    <input type="text" v-model="content" @input="handleInput" class="form-control">
    <label for="adminKey">레이블</label>
  </div>
</template>
<script>
  export default {
    props:['value'],

    created() {
      console.log("iiiiiiiiii>>", $('input'))
      
      // this.myModel = this.model;
    },

    mounted() {
      // $('input').trigger("change");
    },

    // watch: {
    //   myModel() {
    //     this.$emit('input', this.myModel)
    //   }
    // },

    data() {
      return {
        content: this.value
      }
    },

    methods: {
      handleInput (e) {
        console.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>", this.content)
        this.$emit('input', this.content)
      }
    }
  }
</script>
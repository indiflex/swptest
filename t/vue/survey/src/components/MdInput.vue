<template>
  <div class="md-form">
    <!-- <i v-if="icon" class="fa prefix" :class="icon ? icon : ''"></i> -->
    <!-- <input type="text" id="title" :value="value" @input="updateValue($event.target.value)" class="form-control"> -->
    <input type="text" id="title" :value="content" @input="handleInput" class="form-control"/>
    <label for="title">label</label>
  </div>
</template>

<script>
  export default {
    // props: ['value', 'label', 'icon'],
    props: ['value'],

    created() {
      console.log("created!!!!!!!!", this.value, this.label)
    },

    mounted() {
      console.log("mounted!!!!!!!!", this.model, this.value)
      $('input').trigger('change')
    },

    data() {
      return {
        content: this.value
      }
    },

    methods: {
      handleInput(e) {
        console.log("updateValue>>>>>>>", e)
        this.$emit('input', this.content)
      }
    }

  }
</script>
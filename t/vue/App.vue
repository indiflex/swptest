<template>
  <div>
    <h1>Hello!</h1>
    <div>ggg - {{name}} :: {{greetWithName}}</div>
    <input type="text" v-model="name" @keyup="visit++">
    <hr>
    <div class="red m-3">{{greeting}}, You visit {{visit}} Times!</div>
    <input type="text" v-model="greeting">
    <button @click="visit++">TEST</button> <button v-on:click="visit+=3">TEST222</button>
  </div>
</template>

<script>
  export default {
    data() {
      return {greeting: 'Hello', visit: 1, name: '세종대왕'}
    },

    computed: {
      greetWithName: function() {
        return this.greeting + ' ' + this.name;
      }
    }
  }
</script>

<style>
  .red { color: red; font-weight: 700;}
  .m-3 { margin: 30px; }
</style>
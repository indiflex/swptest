<template>
  <div>
    <h1>This is Sister : {{name}}</h1>
    <button @click.prevent="sendToHome()">Send To Home from Sister</button>
  </div>
</template>

<script>
  import { EventBus } from '@/main.js'
  import { AAA } from '@/main.js'

  export default {
    name: 'Sister',
    props: ['name'],

    created() {
      this.rname = this.name;
    },

    methods: {
      sendToHome() {
        this.rname = "누나";
        console.log("Sister:sendToHome>>", this.name)
        this.$emit('fromSister', this.rname)
        // EventBus.$emit('fromSisterToBrother', this.rname)
        EventBus.fromSisterToBrother(this.rname);
        AAA.aaa();
      }
    }
  }
</script>
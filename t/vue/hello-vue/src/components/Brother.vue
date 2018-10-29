<template>
  <div>
    <h1>This is Brother : <span class="red">{{msg}}</span> : {{jsonData.name}}</h1>
    <button @click.prevent="sendToHome()">Send To Home from Brother</button>
    <h3 class="red">fromSister rName: {{fromSisterMessage(fromSister)}}</h3>
  </div>
</template>

<script>
  import { EventBus } from '@/main'

  export default {
    name: 'Brother',
    props: {
      msg: {
        type: String
      },
      jsonData: {
        type: Object
      }
    },

    data() {
      return {
        fromSister: null
      }
    },

    // mixins: [utils],

    created() {
      console.log("msg>>", this.msg, this.jsonData)
      EventBus.$on('fromSisterToBrother', rname => {
      // EventBus.$on('fromSisterToBrother', function(rname) { //this 접근 안됨!!
        console.log("fromSisterToBrother>>>", rname, this)
        this.fromSister = rname;
      });
    },

    methods: {
      sendToHome() {
        this.jsonData.name = "홍길동";
        console.log("Brother:sendToHome!!", this.msg, this.jsonData)
        this.$emit('fromBrother', this.jsonData)
      }
    }
  }
</script>
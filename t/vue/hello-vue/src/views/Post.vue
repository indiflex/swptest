<template>
  <div class="posts">
    <h1>This is an Posts/Post</h1>
    
    <router-view/>
  </div>

</template>

<script>

  export default {
    // components: { 부모 }
    data() {
      return {
      }
    },

    created() {
      console.log("Posts/Post created!!", this.$route.fullPath, this.$route.params)
    },

    methods: {
    }
  }
</script>

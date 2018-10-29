<template>
  <div class="posts">
    <h1>This is an Posts : {{msg}}</h1>
    <div id="nav">
      <span v-for="(post, idx) in posts">
        <router-link to="/posts/post/">Post {{post.id}}</router-link>
        <span v-if="!idx"> | </span>
      </span>
    </div>

    <router-view/>
  </div>

</template>

<script>

  export default {
    props: ['msg'],

    data() {
      return {
        posts: [
          {id:1, text: "This is post1"},
          {id:2, text: "This is post2"},
        ]
      }
    },

    created() {
      console.log("Posts created!!", this.$route.fullPath, this.$route.params, this.$route.query)
    },

    methods: {
    }
  }
</script>

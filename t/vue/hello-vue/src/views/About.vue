<template>
  <div class="about">
    <h1>This is an about page</h1>
    <strong class="red">{{post.replytext}}</strong>
    <!-- <부모></부모> -->
    <HelloWorld msg="AAAAAAAAAAAAAAAAAAAA"/>
    <HelloWorld msg="BBBBBBBBBBBBBBBBBBBB"/>
  </div>

</template>

<script>
  // import 부모 from '@/components/부모.vue';

  export default {
    // components: { 부모 }
    data() {
      return {
        loading: false,
        post: {}
      }
    },

    created() {
      console.log("about created!!", this.$route.fullPath, this.$route.params)
      this.getPost(this.$route.params.id);
    },

    methods: {
      getPost(id) {
        console.log("getPost", id, this.$URL + '/replies/265/390')
        this.$http.get(this.$URL + '/replies/265/' + id).then( ret => {
          console.log("rrrrrrrrrrr>>", ret.data)
          this.post = ret.data;
        });
      }
    }
  }
</script>

<template>
  <section class="container wow fadeIn">
    <div class="row">
      <div class="col-5">
            <div class="list-group">
              <router-link to="/surveylist/surveyedit/1" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                survey.id
              </router-link>
            </div>
      </div>

      <div class="col-7">
        <router-view/>
      </div>

    </div>
  </section>
</template>

<script>
  export default {
    created() {
      console.log("Adm>>", this.EventBus.admin)
      if (!this.EventBus.admin)
        this.$router.replace('/Adm')
    },

    data() {
      return {
        adminKey: null
      }
    },

    methods: {

    }

  }
</script>
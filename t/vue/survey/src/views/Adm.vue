<template>
  <section class="pt-5">
    <div v-if="!EventBus.admin" class="d-flex justify-content-center">
      <div class="md-form w-75">
        <i class="fa fa-key prefix"></i>
        <input type="password" name="key" id="key" v-model="adminKey" class="form-control">
        <label for="key">Admin Key</label>
      </div>
    </div>
  </section>
</template>

<script>
  export default {
    beforeCreate() {
      console.log("Adm.beforeCreate>>", this.EventBus.admin)
      if (this.EventBus.admin)
        this.$router.replace('surveylist')
    },
    created() {
      console.log("Adm.create>>", this.EventBus.admin)
      this.$watch('adminKey', this._.debounce(this.checkKey, 500))
    },

    data() {
      return {
        adminKey: null
      }
    },

    methods: {
      checkKey() {
        console.log("tttttttt>>", this.adminKey)
        if (this.adminKey === '1212') {
          this.EventBus.admin = true
          this.$router.push({name: 'surveylist', params: {}})
        }
      }
    }

  }
</script>
<template>
  <section class="container wow fadeIn">
    <div class="row">
      <div class="col-5">
            <div class="list-group">
              <a href="#!" class="list-group-item list-group-item-action">
                Cras justo odio
              </a>
              <a href="#!" class="list-group-item list-group-item-action active">Dapibus ac facilisis in</a>
              <a href="#!" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">Morbi leo risus <span class="badge badge-primary badge-pill">14</span></a>
              <a href="#!" class="list-group-item list-group-item-action">Porta ac consectetur ac</a>
              <a href="#!" class="list-group-item list-group-item-action disabled">Vestibulum at eros</a>
            </div>
      </div>

      <div class="col-7">
        <div class="card">

          <h5 class="card-header info-color white-text text-center py-4">
            <strong>survey.title</strong>
          </h5>

          <!--Card content-->
          <div class="card-body px-lg-5 pt-2">

            <!-- Form -->
            <div class="text-center" style="color: #757575;">

              <!-- <div class="md-form">
                <input type="password" id="key" class="form-control">
                <label for="key">Admin Key</label>
              </div> -->

              <div class="md-form">
                <input type="text" id="materialLoginFormEmail" v-model="adminKey" class="form-control">
                
                <label for="materialLoginFormEmail">E-mail</label>
              </div>

              <div class="md-form">
                <input type="text" id="materialLoginFormPassword" class="form-control">
                <label for="materialLoginFormPassword">Password</label>
              </div>

              <div class="d-flex justify-content-around">
                <div>
                  <!-- Remember me -->
                  <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="materialLoginFormRemember">
                    <label class="form-check-label" for="materialLoginFormRemember">Remember me</label>
                  </div>
                </div>
                <div>
                  <!-- Forgot password -->
                  <a href="">Forgot password?</a>
                </div>
              </div>

              <!-- Sign in button -->
              <button class="btn btn-outline-info btn-roundedx btn-blockx my-4 waves-effect" type="submit">Sign in</button>

              <!-- Register -->
              <p>Not a member?
                <a href="">Register</a>
              </p>

            </div>
            <!-- Form -->

          </div>

        </div>
      </div>

    </div>
  </section>
</template>

<script>
  export default {
    created() {
      console.log("Adm>>", this.EventBus.admin)
      if (!this.EventBus.admin)
        this.$router.replace('Adm')
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
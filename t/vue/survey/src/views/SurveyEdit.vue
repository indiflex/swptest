<template>
  <div class="card wow fadeIn">

    <h5 class="card-header info-color white-text text-center py-4">
      <strong>survey.title</strong>
      <a href="#" @click.prevent="toggle(survey.isEditing)" class="text-dark pull-right m-0"><i class="fa fa-edit"></i></a>
    </h5>

    <!--Card content-->
    <div v-if="survey.isEditing" class="card-body px-lg-5 pt-2">

      <!-- Form -->
      <div class="text-center" style="color: #757575;">

        <div class="md-form">
          <input type="text" id="materialLoginFormEmail" class="form-control">
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
</template>

<script>
  export default {
    created() {

    },

    data() {
      return {
        survey: {isEditing: false}
      }
    },

    methods: {
      toggle(a) {
        console.log("toggleeeeeeee>>", a)
        this.survey.isEditing = !a;
      }
    }
  }
</script>
<template>
  <div class="container">

    <!--Section: Jumbotron-->
    <section class="card blue-gradient wow fadeIn" id="intro">

      <!-- Content -->
      <div class="card-body text-white text-center py-5 px-5 my-5">

        <h1 class="mb-4">
          <strong>TJE Survey System</strong>
        </h1>
        <p>
          <strong>Node.js & Vue.js Survey System</strong>
        </p>
        <p class="mb-4">
          <strong>설문에 참여해주셔서 감사합니다. 여러분의 의견을 최대한 반영하여 최상의 서비스를 제공해 드리겠습니다. 고맙습니다.</strong>
        </p>

        <div v-if="surveys.length">
          <div v-for="survey in surveys">
            <router-link to="/survey" class="btn btn-outline-white btn-lg">
              Start Survey
              <i class="fa fa-send ml-2"></i>
            </router-link>
          </div>
        </div>
        <div v-else class="mt-5 text-warning">진행중인 설문이 없습니다.</div>

      </div>
      <!-- Content -->
    </section>
    <!--Section: Jumbotron-->
  </div>
</template>

<script>
  import { EventBus } from '@/main'

  export default {
    created() {
      console.log("created")
      let url = this.ApiURL + 'surveys'
      console.log("uuuuuuuuuuuu>>", url)
      this.$http.get(url).then(ret => {
        if (ret.status !== 200) {
          alert("Error on get Surveys!!")
          return [];
        }

        EventBus.admin = 111;

        this.surveys = ret.data;
      })
    },

    data() {
      return {
        surveys: []
      }
    }
  }
</script>

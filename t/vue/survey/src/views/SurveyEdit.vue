<template>
  <section class="wow fadeIn">
    <div class="card">

      <h5 class="card-header info-color white-text text-center py-4">
        <strong>{{survey.title}}</strong>
        <a @click.prevent="toggleSurveyEditing()" href="#" class="pull-right"><i class="fa fa-edit"></i></a>
      </h5>


      <!--Card content-->
      <div v-if="isEditing" class="card-body px-lg-5 pt-0">

        <!-- Form -->
        <form class="text-center" style="color: #757575;">
          <div class="md-form w-75">
            <input type="text" id="title" :value="survey.title" @input="survey.title=$event.target.value" class="form-control">
            <!-- <input type="text" id="title" v-model="survey.title" class="form-control">
            <input type="text" id="title" :value="survey.title" @input="survey.title=$event.target.value" class="form-control"> -->
            <label for="title">설문 제목</label>
          </div>
          <Mi label="설문제목" v-model="survey.title" />
          <MdInput v-model="survey.title" label="설문 제목"/>
          <!-- <MdInput v-model="ttt"/> -->

          <div class="d-flex justify-content-around">
            <button @click.prevent="toggleSurveyEditing()" class="btn btn-outline-default btn-block my-4 waves-effect z-depth-0" type="submit">취소</button>
            <button class="ml-2 btn btn-outline-danger btn-rounded btn-block my-4 waves-effect z-depth-0" type="submit">삭제</button>
            <button class="ml-2 btn btn-outline-primary btn-rounded btn-block my-4 waves-effect z-depth-0" type="submit">저장</button>
          </div>

        </form>
        <!-- Form -->

      </div>

    </div>
  </section> 
</template>

<script>
  export default {
    created() {
      let id = this.$route.params.id,
          url = this.ApiURL + 'surveys/' + id;

      console.log("url>>", url)    
      this.$http.get(url).then(ret => {
        this.survey = ret.data
      });
    },

    data() {
      return {
        survey: {},
        ttt: '123',
        isEditing: false
      }
    },

    methods: {
      toggleSurveyEditing() {
        this.isEditing = !this.isEditing;
      }
    }
  }
</script>
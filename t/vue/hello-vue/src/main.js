import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Global from './components/_global.js'
import HelloWorld from './components/HelloWorld'
import axios from 'axios'
import lodash from 'lodash'
import vueLodash from 'vue-lodash'

import { utils } from '@/utils'


console.log("This is main.js!!")

Vue.config.productionTip = false

Vue.prototype.$http = axios
Vue.prototype.$URL = "http://localhost:7000/dbtest";
Vue.prototype._ = lodash

Vue.mixin(utils)

export const EventBus = new Vue({
  methods: {
    fromSisterToBrother(rname) {
      this.$emit('fromSisterToBrother', rname)
    }
  }
});

export const AAA = new Vue({
  methods: {
    aaa() { console.log("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")}
  }
})

Vue.component('HelloWorld', HelloWorld)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

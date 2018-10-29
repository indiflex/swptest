import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Post from './views/Post.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  // mode: 'hash',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    
    {
      path: '/posts', name: 'posts',
      component: () => import('./views/Posts'),
      children: [
        {path: 'post', name: 'post', component: Post}
      ]
    },

    {
      path: '/about/:id',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    }
  ]
})



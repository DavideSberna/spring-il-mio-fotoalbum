import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Photos from '../views/Photos.vue'
import Categories from '../views/Categories.vue'
import ShowPhoto from '../views/Show-photo.vue'
import ShowCategory from '../views/Show-category.vue'
import Form from '../views/Contact-us.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/photos',
      name: 'photos',
      component: Photos
    },
    {
      path: '/categories',
      name: 'categories',
      component: Categories
    },
    {
      path: '/contact',
      name: 'contact',
      component: Form
    },
    {
      path: '/photo/:id',
      name: 'show-photo',
      component: ShowPhoto,
      props: true
    },
    {
      path: '/category/:id',
      name: 'show-category',
      component: ShowCategory,
      props: true
    }
  ]
})

export default router

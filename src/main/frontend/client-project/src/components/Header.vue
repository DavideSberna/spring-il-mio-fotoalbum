<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Media Photo</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item" v-for="(link, index) in menu" :key="index">
            <router-link :to="{ name: link.routeName }" class="nav-link" active-class="active">
              <p class="p-0 m-0">{{ link.label }}</p>
            </router-link>
          </li>
          
        </ul>
      </div>
      <div>
		  <a href="http://localhost:8080/dashboard">Dashboard</a>
	  </div>
    </div>
  </nav>
</template>

<script>
export default {
    name: 'Header',
    data(){
        return{
          menu: [
                {
                    label: 'Home',
                    routeName: 'home'
                },
                {
                    label: 'Photos',
                    routeName: 'photos'
                },
                {
                    label: 'Categories',
                    routeName: 'categories'
                },
                {
                    label: 'Contact',
                    routeName: 'contact'
                },
            ]

        }
    }

}
</script>

<style lang="scss" scoped></style>
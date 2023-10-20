
<template>
  <header>
    <Header/>
  </header>
  
  <main>
  	<RouterView />
  </main>
  
  <footer>
	  
  </footer>

</template>

<script>
import Header from "./components/Header.vue";
 

export default {
  name: 'App',

  components:{
    Header,
  },
  data() {
    return {
      
    };
  },
};
</script>

<style lang="scss" scoped></style>

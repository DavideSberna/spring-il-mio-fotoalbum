<template>
  
  <div class="container pt-5">
	 <h2 class="mb-3">Photos</h2>
	 <div class="col-4 mb-4">
    	<input type="text" class="form-control" v-model="searchPhoto" placeholder="Cerca per nome...">
  	 </div>
    <div class="row">
       <Card :searchPhoto="searchPhoto" />
    </div>
  </div>
  
   
</template>



<script>
import Card from "../components/Card.vue";
export default{
 name: 'Photos',
  components: {
    Card,
    
  },
 
 data(){
	  return{
		  searchPhoto: "",
	  }
  },
 methods:{
     
 },
 
 mounted(){
       
 }
 
  

}


</script>

<style lang="scss" scoped></style>
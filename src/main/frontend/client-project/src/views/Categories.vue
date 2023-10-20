<template>
  
 <div class="container pt-5">
	 <h2 class="mb-3 mt-5">Categories</h2>
    <div class="col-4 mb-4">
    	<input type="text" class="form-control" v-model="searchCategory" placeholder="Cerca per nome...">
  	 </div>
    <div>		
    	<Table :searchCategory="searchCategory"/>
	</div>
 </div>
   
</template>



<script>
import Table from "../components/Table.vue";
export default{
 name: 'Categories',
  components: {
    Table,
  },
 
 data(){
     return{
         searchCategory : "",
       
     }
 },
 methods:{
     
 },
 
 mounted(){
       
 }
 
  

}


</script>

<style lang="scss" scoped></style>
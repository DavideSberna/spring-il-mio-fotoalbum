<template>
  
   <table class="table w-50" v-if="categoriesData.length != 0">
    <thead>
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Azioni</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="item in categoriesData" :key="item.id">
        <td>{{ item.id }}</td>
        <td>{{ item.name }}</td>
        <td>
			<router-link :to="{ name: 'show-category', params: { id: item.id } }">
		    	<a href="#" class="btn btn-primary">Guarda</a>
	        </router-link>
		</td>
      </tr>
    </tbody>
  </table>
  <div v-else>
	<div class="alert alert-warning" role="alert">
	  Nessun risultato corrisponde alla ricerca
	</div>
  </div>
  <Loader :loading="isLoading" />
</template>

<script>
import Loader from "../components/Loader.vue";
import axios from "axios";
import { store } from '../data/store';
export default {
	name: "Table",
	components: {
    Loader,
  },
  props: {
    searchCategory: String,
  },
  data() {
    return {
      categoriesData: [],
      isLoading: false,
    };
  },
  methods: {
    getCategories() {
		
		if(this.searchCategory != ""){	
			store.endpoint.searchCategoryQuery = "?query=" + this.searchCategory
		} else{
			store.endpoint.searchCategoryQuery = ""
		}
		
		
	  this.isLoading = true;
      const url = store.baseUrl + store.endpoint.categories  + store.endpoint.searchCategoryQuery;
      axios.get(url).then((res) => {
		  console.log(res.data)
        this.categoriesData = res.data;
        this.isLoading = false;
      });
    },
  },
  watch: {
    searchCategory: "getCategories",
  },
  mounted() {
    this.getCategories();
  },
};
</script>

<style scoped></style>
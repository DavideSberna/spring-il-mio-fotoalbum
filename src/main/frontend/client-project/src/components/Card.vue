<template>
	 
	<div class="col-4 pb-4" v-for="(items, index) in photosData" v-if="photosData.length != 0">
		<div class="card">
		  <img class="card-img-top" :src="items.url" :alt="items.title">
		  <div class="card-body">
		    <h5 class="card-title">{{items.title}}</h5>
		    <p class="card-text">{{items.description}}</p>
		     <router-link :to="{ name: 'show-photo', params: { id: items.id } }">
		    	<a href="#" class="btn btn-primary">Guarda</a>
	        </router-link>
		  </div>
		</div>
	</div>
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
   name: "Card",
   
   components: {
    Loader,
  },
   props: {
    searchPhoto: String,
  },
  data() {
    return {
      photosData: [],
      isLoading: false,
    };
  },
  methods: {
    getPhotos() {
		
		if(this.searchPhoto != ""){	
			store.endpoint.searchPhotoQuery = "?query=" + this.searchPhoto
		} else{
			store.endpoint.searchPhotoQuery = ""
		}
		
	  this.isLoading = true;
      const url = store.baseUrl + store.endpoint.photos + store.endpoint.searchPhotoQuery;
      axios.get(url).then((res) => {
		  console.log(res.data)
          this.photosData = res.data;
          this.isLoading = false;
      });
       
    },
  },
  watch: {
    searchPhoto: "getPhotos",
  },
  mounted() {
    this.getPhotos();
  },
};
</script>

<style scoped></style>
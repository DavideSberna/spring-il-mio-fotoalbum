import { reactive } from 'vue';

export const store = reactive({
    baseUrl: 'http://localhost:8080/api/v1.0/',
    endpoint: {
		sendMessage: "message/send",
		photos : "photos",
		categories : "categories",
		searchPhotoQuery: "",
		searchCategoryQuery: "",
		},

     
})
<template>
    <Loader :loading="isLoading" />
    
    <div v-if="successMessage" class="alert alert-success">{{ successMessage }}</div>
    <div v-if="errorMessage" class="alert alert-danger">{{ errorMessage }}</div>
    
    <div>
        <form @submit="sendForm">
            <div class="mb-3">
                <label for="name" class="form-label">Nome</label>
                <input v-model="formData.name" type="text" class="form-control" id="name" name="name">
                <div v-if="nameError" class="text-danger">{{ nameError }}</div>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input v-model="formData.email" type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp">
                <div v-if="emailError" class="text-danger">{{ emailError }}</div>
            </div>
            <div class="mb-3">
                <label for="message" class="form-label">Messaggio</label>
                <input v-model="formData.message" type="text" class="form-control" name="message" id="message">
                <div v-if="messageError" class="text-danger">{{ messageError }}</div>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</template>

<script>
import Loader from "../components/Loader.vue";
import axios from "axios";
import { store } from '../data/store';

export default {
  name: "Form",
  components: {
    Loader,
  },
  data() {
    return {
      isLoading: false,
      formData: {
        name: "",
        email: "",
        message: "",
      },
      successMessage: "",
      errorMessage: "",
      nameError: "",
      emailError: "",
      messageError: "",
    };
  },
  methods: {
    sendForm(event) {
      event.preventDefault();
      
     
      this.nameError = this.validateName(this.formData.name);
      this.emailError = this.validateEmail(this.formData.email);
      this.messageError = this.validateMessage(this.formData.message);

      if (this.nameError || this.emailError || this.messageError) {
        return;
      }
      
      this.isLoading = true;
      const url = store.baseUrl + store.endpoint.sendMessage;

      axios.post(url, this.formData)
        .then((response) => {
          this.successMessage = "Messaggio inviato con successo.";
          this.errorMessage = ""; 
          console.log("Risposta dal server:", response.data);
        })
        .catch((error) => {
          this.errorMessage = "Errore nell'invio del modulo. Riprova.";
          this.successMessage = "";
          console.error("Errore nell'invio del modulo:", error);
        })
        .finally(() => {
          this.isLoading = false;
        });
    },
    validateName(name) {
      if (!name.trim()) {
        return "Il campo Nome è obbligatorio.";
      }
      return "";
    },
    validateEmail(email) {
      if (!email.trim() || !email.includes('@')) {
        return "L'Email non è valida.";
      }
      return "";
    },
    validateMessage(message) {
      if (!message.trim()) {
        return "Il campo Messaggio è obbligatorio.";
      }
      return "";
    },
  },
};
</script>
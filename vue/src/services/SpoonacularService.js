import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
});

export default {

    getWineRecommendation(foodInput) {
      return http.get(`/getPairing?query=${foodInput}`);
    },
  
  }
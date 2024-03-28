import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  saveWine(wine) {
    return axios.post('/wines/addWine', wine)
  },

  getSavedWines() {
    return axios.get('/wines/saved')
  }

}

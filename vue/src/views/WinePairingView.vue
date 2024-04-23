<template>
    <div id="wine-pairing-box">
        <div class="content-box">
            <h1>Wine Pairing App</h1>
            <label for="foodInput">Enter a food item or cuisine:</label>
            <br>
            <input v-model="foodInput" id="foodInput" type="text">
            <br>
            <button v-on:click="getWineRecommendation">Get Wine Pairings</button>

            <div class="loading" v-if="isLoading">
                <img src="../assets/wine gif.gif" alt="animated wine pour gif">
            </div>

            <WineRecommendation :wineRec="wineRec" :searchPerformed="searchPerformed" :foodInput="foodInput"
                :invalidSearch="invalidSearch" />


        </div>
    </div>
</template>
  
<script>
import axios from 'axios';
import WineRecommendation from '../components/WineRecommendation.vue';
import SpoonacularService from '../services/SpoonacularService';

export default {
    name: 'App',
    components: {
        WineRecommendation,
    },
    data() {
        return {
            foodInput: 'Italian', // Default value
            wineRec: null,
            searchPerformed: false,
            invalidSearch: '',
            isLoading: false,
        };
    },
    methods: {
        // async fetchWineRecommendation() {
        //     this.searchPerformed = true;
        //     try {
        //         const response = await axios.get(
        //             `http://localhost:9000/getPairing?query=${this.foodInput}`
        //         );
        //         this.wineRec = response.data;
        //         console.log(response.data);
        //     } catch (error) {
        //         this.wineRec = null;
        //         this.invalidSearch = this.foodInput;
        //         if (error.response.status === 500) {
        //             console.error('Invalid search criteria.');
        //         } else {
        //             console.error('Error fetching wine recommendation:', error);
        //         }
        //     }
        // },

        async getWineRecommendation() {
            this.searchPerformed = true;
            this.wineRec = null;
            this.isLoading = true;
            SpoonacularService.getWineRecommendation(this.foodInput)
                .then(response => {
                    this.wineRec = response.data;
                    console.log(response.data);
                    this.isLoading = false;
                })
                .catch(error => {
                    this.isLoading = false;
                    if (error.status === 500) {
                        this.wineRec = null;
                        this.invalidSearch = this.foodInput;
                        console.error('Invalid search criteria.');
                    } else {
                        this.wineRec = null;
                        this.invalidSearch = this.foodInput;
                        console.error('Error fetching wine recommendation:', error);
                    }
                })
        }
    },
};
</script>
  
<style>
#wine-pairing-box {
    display: flex;
    align-items: center;
    justify-content: center;
    min-height: 100vh;
}

.content-box {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
    max-width: 600px;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    background-color: rgb(255, 180, 155);
}

h1 {
    font-size: 24px;
    margin-bottom: 20px;
}
</style>
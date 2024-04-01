<template>
    <div>
        <h1>My Saved Wines</h1>
        <ul>
            <li v-for="wine in savedArray" :key="wine.title">
                <div id="wine-title">{{ wine.title }}</div>
                <img :src="wine.imageUrl" alt="Wine Image" @error="handleImageError">
            </li>
        </ul>
    </div>
</template>

<script>
import AuthService from '../services/AuthService';
import fallbackImageUrl from '@/assets/generic_wine_image.png';

export default {
    data() {
        return {
            savedArray: [],
            fallbackImageUrl: fallbackImageUrl,
        }
    },
    created() {
        AuthService.getSavedWines()
            .then((response) => {
                console.log(response.data);
                this.savedArray = response.data;
            })
            .catch((error) => {
                console.error('Error fetching saved wines:', error);
            });
    },
    methods: {
        handleImageError(event) {
            event.target.src = this.fallbackImageUrl;
        }
    }
}


</script>

<style>
h1 {
    text-align: center;
    padding-top: 10px;
}

ul {
    list-style: none;
    padding: 0;
    display: flex;
    flex-wrap: wrap;
    padding-left: 80px;
}

li {
    width: 200px;
    /* Adjust the width based on your design */
    margin: 10px;
    padding: 15px 15px;
    border: 1px solid #ccc;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-decoration: none;
    color: inherit;
    cursor: pointer;
    font-family: Avenir, Helvetica, Arial, sans-serif;
}

li:hover {
    background-color: rgb(255, 180, 155);
}

img {
    max-width: 100%;
}

#wine-title {
    text-align: center;
    padding: 5px;
    margin-bottom: 10px;
}
</style>
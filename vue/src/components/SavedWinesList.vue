<template>
    <div>
        <h1>My Saved Wines</h1>
        <ul>
            <li v-for="wine in savedArray" :key="wine.title">
            <div>{{ wine.title }}</div>
            <img :src="wine.imageUrl" alt="Wine Image">
            </li>
        </ul>
    </div>
</template>

<script>
import AuthService from '../services/AuthService';

export default {
    data() {
        return {
            savedArray: [],
        }
    },
    created() {
        AuthService.getSavedWines()
        .then((response) => {
            console.log(response.data);
            this.savedArray = response.data;
        })
    }
}


</script>

<style>

h1{
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
    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}

li:hover{
    background-color: rgb(255, 180, 155);
}

</style>
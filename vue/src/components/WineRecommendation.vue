<template>
    <div>
        <div v-if="wineRec && wineRec.wines && wineRec.wines.length > 0">
            <div v-if="wineRec">
                <h2 class="section-title">Wine Recommendation</h2>
                <div class="pairings">
                    <h3>Recommended Pairings</h3>
                    <ul class="pairings-list">
                        <li v-for="wine in wineRec.wines" :key="wine">{{ capitalize(wine) }}</li>
                    </ul>
                </div>
            </div>

            <div v-if="wineRec && wineRec.recBlurb">
                <div class="opinion">
                    <h3>Our Opinion</h3>
                    <p>{{ wineRec.recBlurb }}</p>
                </div>
            </div>

            <div v-if="wineRec.specificRec">
                <div class="recommendation">
                    <h3>Our Recommendation</h3>
                    <h4>{{ wineRec.specificRec.title }}</h4>
                    <img :src="wineRec.specificRec.imageUrl" alt="Wine Image" @error="handleImageError" />
                    <div>
                        <span>
                            <button v-on:click="saveWine" v-if="$store.state.token != ''">Save Wine to
                                Favorites</button>
                            <button v-on:click="goToSignIn" v-if="$store.state.token === ''">Sign in to Save
                                Wine</button>
                            &nbsp;
                            <button>Find Online</button>
                        </span>
                    </div>

                    <p>{{ wineRec.specificRec.description }}</p>
                    <div id="price-div">
                        <p id="price">${{ formatPrice(wineRec.specificRec.price) }}</p>
                    </div>
                </div>
            </div>
        </div>


        <div v-if="searchPerformed && invalidSearch && (wineRec === null || wineRec.wines.length === 0)">
            <p>Sorry, we don't have any recommendations for "{{ invalidSearch }}".</p>
        </div>



    </div>
</template>


<script>
import { useLink } from 'vue-router';
import AuthService from '../services/AuthService';
import fallbackImageUrl from '@/assets/generic_wine_image.png';

export default {
    props: {
        wineRec: {
            type: Object,
            default: () => ({}), // Default to an empty object
        },
        searchPerformed: {
            type: Boolean,
            default: false,
        },
        foodInput: {
            type: String,
            default: '',
        },
        invalidSearch: {
            type: String,
            default: '',
        }
    },
    data() {
        return {
            fallbackImageUrl: fallbackImageUrl,
        }
    },
    methods: {
        capitalize: function (value) {
            if (!value) return '';
            value = value.toString();
            return value.charAt(0).toUpperCase() + value.slice(1);
        },
        formatPrice: function (price) {
            // Remove the dollar sign and any other non-numeric characters
            const numericPrice = parseFloat(price.replace(/[^0-9.]/g, ''));

            return isNaN(numericPrice) ? 'N/A' : numericPrice.toFixed(2);
        },
        saveWine() {
            let newWine = {
                title: this.wineRec.specificRec.title,
                averageRating: this.wineRec.specificRec.averageRating,
                description: this.wineRec.specificRec.description,
                imageUrl: this.wineRec.specificRec.imageUrl,
                link: this.wineRec.specificRec.link,
                price: this.wineRec.specificRec.price,
                ratingCount: this.wineRec.specificRec.ratingCount,
                score: this.wineRec.specificRec.score
            }
            AuthService.saveWine(newWine)
                .then((response) => {
                    if (response.status === 200) {
                        alert("Wine saved successfully.");
                    }
                }).catch((error) => {
                    console.log(error);
                })
        },
        goToSignIn() {
            this.$router.push('/login');
        },
        handleImageError(event) {
            event.target.src = this.fallbackImageUrl;
        },
    },
};
</script>

<style scoped>
.section-title {
    font-size: 27px;
    margin-bottom: 10px;
    padding: 10px;
    /* background-color: #1A615F;
    color: #f4eaeb; */
    /* border-bottom: #0d3231 3px solid; */

}

.pairings-list {
    list-style-type: none;
    padding: 0;
    margin: 0;
}

.pairings-list li {
    background-color: #ebd8db;
    padding: 8px;
    margin-bottom: 5px;
    border-radius: 5px;
}

.pairings,
.opinion,
.recommendation {
    margin-bottom: 20px;
}

.recommendation img {
    max-width: 100%;
    height: auto;
    max-height: 350px;
}

#price {
    background-color: #e2ced0;
    margin-left: 240px;
    margin-right: 240px;
    padding-top: 5px;
    padding-bottom: 5px;
    border-radius: 10px;
}

#price-div{
    /* width: 100px;
    height: 30px;
    background-color: #BBABAD; */
    /* display: flex;
    flex-direction: column; */
}

</style>
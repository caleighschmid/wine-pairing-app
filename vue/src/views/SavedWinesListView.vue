<template>
    <div>
        <div class="main">
            <saved-wines-list />
        </div>
    </div>
</template>

<script>
import SavedWinesList from '../components/SavedWinesList.vue';

export default {
    components: {
        SavedWinesList,
    }
}
</script>

<style>
.main {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgb(40, 43, 47);
}
</style>
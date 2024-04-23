<template>
  <body>
    <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'wine-recommendation' }" class="router-link">Home</router-link>
      <span class="router-dash">
        &nbsp;|&nbsp;
      </span>
      <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token === ''" class="router-link">Log In</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="router-link">Logout</router-link>
      <span v-if="$store.state.token != ''" class="router-dash">
        &nbsp;|&nbsp;
      </span>
     <router-link v-bind:to="{ name: 'saved-wines-list' }" v-if="$store.state.token != ''" class="router-link">View Saved Wines</router-link>    </div>
    <router-view />
  </div>
  </body>

</template>

<style>

/* colors: 
  Raisin black: #2F2A2D
  Caribbean Current: #1A615F
  Rose quartz: #BBABAD
  Dutch White: #F6E1C0
  Mint green: #D0F0ED
*/

.router-link{
  color: rgb(255, 180, 155);
  text-decoration: none;
}

.router-link:hover{
  text-decoration: underline;
}

.router-dash{
  color: rgb(255, 180, 155);
}

body {
  background-color: #2F2A2D;
}

#capstone-app{
  font-family: Avenir, Helvetica, Arial, sans-serif;
}

</style>
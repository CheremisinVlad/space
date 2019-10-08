<template>
  <div id="app">
    <router-view/>
  </div>
</template>
<script>
export default{
  name:'App'
}
</script>
<style>
  .public.container{
    max-width: 100%;
    margin: fill;
    background-color: rgba(9,4,13,0.93);
  }
  .register-form {
    margin-top: 50px;
    max-width: 320px;
  }

  .form-group label {
    font-weight: bold;
    color: #555;
  }
  .accept-terms {
    margin: 20px 0 40px 0;
  }
  a {
    color: #666;
  }


</style>




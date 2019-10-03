<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <div class="logo-wrapper">
          <img class="logo" src="/images/logo.png">
          <div class="tagline">Organize your space like your want</div>
        </div>
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">{{errorMessage}}</div>
          <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" v-model="form.username">
          </div>
          <div class="form-group">
            <label for="email">Email address</label>
            <input type="email" class="form-control" id="email" v-model="form.email">
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" v-model="form.password">
          </div>
          <button type="submit" class="btn btn-primary btn-block">Create account</button>
          <p class="accept-terms text-muted">By clicking “Create account”, you agree to our <a href="#">terms of service</a> and <a href="#">privacy policy</a>.</p>
          <p class="text-center text-muted">Already have an account? <a href="/login">Sign in</a></p>
        </form>
      </div>
    </div>
    <footer class="footer">
      <span class="copyright">  2019 space.com</span>
      <ul class="footer-links list-inline float-right">
        <li class="list-inline-item"><a href="#">About</a></li>
        <li class="list-inline-item"><a href="#">Terms of Service</a></li>
        <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
        <li class="list-inline-item"><a href="https://github.com/CheremisinVlad/space" target="_blank">GitHub</a></li>
      </ul>
    </footer>
  </div>
</template>

<script>
  import registrationService from '@/services/registration'
  export default {
    name: "RegisterPage",
    data: function () {
      return {
        form: {
          username: '',
          email: '',
          password: ''
        },
        errorMessage: ''
      }
    },
    methods:{
      submitForm(){
        //TODO: Validate data
        registrationService.register(this.form).then(()=>{
          this.$router.push({name:'LoginPage'})
        }).catch((error)=>{
          this.errorMessage = 'Failed to register user: ' + (error.message ? error.message : 'Unknown') + "."
        })

      }
    }
  }
</script>

<style lang="scss" scoped>

  .container {
    max-width: 100%;
    margin: fill;
    background-color: rgba(9,4,13,0.93);
  }
  .register-form {
    margin-top: 50px;
    max-width: 320px;
  }
  .logo-wrapper {
    text-align: center;
    margin-bottom: 40px;
  }
  .tagline {
    line-height: 180%;
    color: #666;
  }
  .logo {
    max-width: 150px;
    margin: 0 auto;
  }
  .form-group label {
    font-weight: bold;
    color: #555;
  }
  .accept-terms {
    margin: 20px 0 40px 0;
  }
  .footer {
    display: block;
    margin: fill;
    padding: 0 15px 10px;
    font-size: 13px;
    color: #1a3330;
    line-height: 40px;
    background-color: rgba(9,4,13,0.93);
  }
  .list-inline-item {
    margin-right: 10px;
  }
  a {
    color: #666;
  }
</style>


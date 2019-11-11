<template>
  <div class="container public">
    <div class="row justify-content-center">
      <div class="register-form">
        <Logo/>
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">{{errorMessage}}</div>
          <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" v-model="form.username">
            <div class="field-error" v-if="$v.form.username.$dirty">
              <div class="error" v-if="!$v.form.username.required">Username is required</div>
              <div class="error" v-if="!$v.form.username.alphaNum">Username can only contain letters and numbers</div>
              <div class="error" v-if="!$v.form.username.minLength">Username must have at least {{$v.form.username.$params.minLength.min}} letters.</div>
              <div class="error" v-if="!$v.form.username.maxLength">Username is too long. It can contains maximium {{$v.form.username.$params.maxLength.max}} letters.</div>
            </div>
          </div>
          <div class="form-group">
            <label for="email">Email address</label>
            <input type="email" class="form-control" id="email" v-model="form.email">
            <div class="field-error" v-if="$v.form.email.$dirty">
              <div class="error" v-if="!$v.form.email.required">Email address is required</div>
              <div class="error" v-if="!$v.form.email.email">This is not a valid email address</div>
              <div class="error" v-if="!$v.form.email.maxLength">Email address is too long. It can contains maximium {{$v.form.email.$params.maxLength.max}} letters.</div>
            </div>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" v-model="form.password">
            <div class="field-error" v-if="$v.form.password.$dirty">
              <div class="error" v-if="!$v.form.password.required">Password is required</div>
              <div class="error" v-if="!$v.form.password.minLength">Password is too short. It can contains at least {{$v.form.password.$params.minLength.min}} letters.</div>
              <div class="error" v-if="!$v.form.password.maxLength">Password is too long. It can contains maximium {{$v.form.password.$params.maxLength.max}} letters.</div>
            </div>
          </div>
          <button type="submit" class="btn btn-primary btn-block">Create account</button>
          <p class="accept-terms text-muted">By clicking “Create account”, you agree to our <a href="#">terms of service</a> and <a href="#">privacy policy</a>.</p>
          <p class="text-center text-muted">Already have an account? <a href="/login">Sign in</a></p>
        </form>
      </div>
    </div>
    <Footer/>
  </div>
</template>

<script>
  import registrationService from '@/services/registration'
  import Logo from '@/components/Logo.vue'
  import Footer from '@/components/Footer.vue'
  import {required, email, minLength, maxLength, alphaNum} from 'vuelidate/lib/validators'
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
    components:{
      Logo,Footer
    },
    methods:{
      submitForm(){
        this.$v.$touch()
        if(this.$v.$invalid){
          return
        }
        registrationService.register(this.form).then(()=>{
          console.log('registered')
          this.$router.push({name:'LoginPage'})
        }).catch((error)=>{
          this.errorMessage = 'Failed to register user: ' + (error.template ? error.template : 'Unknown') + "."
        })

      }
    },
    validations: {
      form: {
        username: {
          required,
          minLength: minLength(2),
          maxLength: maxLength(50),
          alphaNum
        },
        email: {
          required,
          email,
          maxLength: maxLength(100)
        },
        password: {
          required,
          minLength: minLength(6),
          maxLength: maxLength(30)
        }
      }
    }
  }
</script>

<style lang="scss" scoped>

  .field-error .error{
    color: rgba(146, 18, 21, 0.93);
  }
</style>

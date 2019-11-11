<template>
  <div class = "container public">
    <div class="justify-content-center">
      <div class="form">
        <Logo/>
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
          <div class="form-group">
            <label for="username">Username or emailMessage</label>
            <input type="text" class="form-control" id="username" v-model="form.username">
            <div class="field-error" v-if="$v.form.username.$dirty">
              <div class="error" v-if="!$v.form.username.required">input username</div>
            </div>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" v-model="form.password">
            <div class="field-error" v-if="$v.form.password.$dirty">
            <div class="error" v-if="!$v.form.password.required">input password</div>
          </div>
          </div>
          <button type="submit" class="btn btn-primary btn-block">Sign in</button>
          <div class="links">
            <p class="sign-up text-muted">Don't have an account yet? <a href="/register" class="link-sign-up">Sign up here</a></p>
            <p><a href="#">Forgot your password?</a></p>
          </div>
        </form>
      </div>
    </div>
    <Footer/>
  </div>
</template>

<script>
  import {required} from 'vuelidate/lib/validators'
  import Logo from '@/components/Logo.vue'
  import Footer from '@/components/Footer.vue'

  export default {
    name : 'LoginPage',
    data: ()=>{
      return {
        form: {
          username: '',
          password: ''
        },
        errorMessage: ''
      }
    },
    components: {
      Logo,Footer
    },
    validations:{
      form: {
        username: {
          required
        },
        password: {
          required
        }
      }
    },
    methods: {
      submitForm() {
        this.$v.$touch()
        if (this.$v.$error) {
          return
        }
        this.$store.dispatch('authenticate', this.form)
          .then(() => {
            this.$router.push({name: 'HomePage'})
          }).catch((error) => {
          this.errorMessage = error.message
        })

      }
    }
  }
</script>
<style lang="scss" scoped>
  .links {
    margin: 30px 0 50px 0;
    text-align: center;
  }
</style>

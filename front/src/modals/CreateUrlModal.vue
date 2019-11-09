<template>
  <form @submit.prevent="saveUrl">
    <div class="modal fade" tabindex="-1" role="dialog" backdrop="static" id="createUrlModal">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Create Url</h5>
            <button type="button" class="close" @click="close" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
            <div class="form-group">
              <input type="text" class="form-control" id="urlInput" v-model="url.url" placeholder="Url name">
              <div class="field-error" v-if="$v.url.url.$dirty">
                <div class="error" v-if="!$v.url.url.required">Url is required</div>
              </div>
            </div>
            <div class="form-group">
              <input type="text" class="form-control" id="urlDescriptionInput" v-model="url.description" placeholder="Url description" maxlength="50">
              <div class="field-error" v-if="$v.url.description.$dirty">
                <div class="error" v-if="!$v.url.description.required">Description is required</div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="submit" class="btn btn-primary">Create</button>
            <button type="button" class="btn btn-default btn-cancel" @click="close">Cancel</button>
          </div>
        </div>
      </div>
    </div>
  </form>
</template>

<script>
  import 'bootstrap'
  import $ from 'jquery'
  import { required } from 'vuelidate/lib/validators'
  import urlService from '@/services/urls'

  export default {
    name: "CreateUrlModal",
    props: [],
    data () {
      return {
        url: {
          url: '',
          description: ''
        },
        errorMessage: ''
      }
    },
    validations: {
      url: {
        url: {
          required
        },
        description: {
          required
        }
      }
    },
    mounted() {
      console.log('mounted url')
      this.$nextTick( () => {
        $('#createUrlModal').on('shown.bs.modal', () => {
          $('#urlInput').trigger('focus')
        })
      })
    },
    methods: {
      saveUrl () {
        this.$v.$touch()
        if (this.$v.$invalid) {
          return
        }
        const url = {
          userId: this.userId,
          parentId: this.parentId,
          url: this.url.url,
          description: this.url.description
        }
        urlService.create(url).then((createdUrl) => {
          this.$store.dispatch('addUrl', createdUrl)
          this.$emit('created', createdUrl.id)
          this.close()
        }).catch(error => {
          this.errorMessage = error.message
        })
      },
      close () {
        this.$v.$reset()
        this.url.url = ''
        this.url.description= ''
        this.errorMessage = ''
        $('#createUrlModal').modal('hide')
      }
    }
  }
</script>

<style scoped>

</style>

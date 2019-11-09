<template>
  <form @submit.prevent="saveRecord">
    <div class="modal fade" tabindex="-1" role="dialog" backdrop="static" id="createRecordModal">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Create Record</h5>
            <button type="button" class="close" @click="close" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
            <div class="form-group">
              <input type="text" class="form-control" id="recordNameInput" v-model="record.name" placeholder="Record name" maxlength="50">
              <div class="field-error" v-if="$v.record.name.$dirty">
                <div class="error" v-if="!$v.record.name.required">Name is required</div>
              </div>
            </div>
            <div class="form-group">
              <input type="text" class="form-control" id="recordDescriptionInput" v-model="record.description" placeholder="Record description" maxlength="200">
              <div class="field-error" v-if="$v.record.description.$dirty">
                <div class="error" v-if="!$v.record.description.required">Description is required</div>
              </div>
            </div>
            <div class="form-group">
              <textarea class="form-control" id="recordContentInput" v-model="record.content" placeholder="Record content" minlength="2" rows="10"/>
              <div class="field-error" v-if="$v.record.content.$dirty">
                <div class="error" v-if="!$v.record.content.required">Content is required</div>
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
  import recordService from '@/services/records'

  export default {
    name: "CreateRecordModal",
    props: [],
    data () {
      return {
        record: {
          name: '',
          description: '',
          content: ''
        },
        errorMessage: ''
      }
    },
    validations: {
      record: {
        name: {
          required
        },
        description: {
          required
        },
        content: {
          required
        }
      }
    },
    mounted() {
      this.$nextTick( () => {
        $('#createRecordModal').on('shown.bs.modal', () => {
          $('#recordNameInput').trigger('focus')
        })
      })
    },
    methods: {
      saveRecord () {
        this.$v.$touch()
        if (this.$v.$invalid) {
          return
        }
        const record = {
          userId: this.userId,
          parentId: this.parentId,
          name: this.record.name,
          description: this.record.description,
          content: this.record.content
        }
        recordService.create(record).then((createdRecord) => {
          this.$store.dispatch('addRecord', createdRecord)
          this.$emit('created', createdRecord.id)
          this.close()
        }).catch(error => {
          this.errorMessage = error.message
        })
      },
      close () {
        this.$v.$reset()
        this.record.name = ''
        this.record.description= ''
        this.record.content = ''
        this.errorMessage = ''
        $('#createRecordModal').modal('hide')
      }
    }
  }
</script>


<style scoped>

</style>

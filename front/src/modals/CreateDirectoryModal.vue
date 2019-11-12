<template>

  <form @submit.prevent="saveDirectory">
    <div class="modal fade" tabindex="-1" role="dialog" id="createDirectoryModal">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Create Directory</h5>
            <button type="button" class="close" @click="close" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
            <div class="form-group">
              <input type="text" class="form-control" id="directoryNameInput" v-model="directory.name" placeholder="Directory name" maxlength="128">
              <div class="field-error" v-if="$v.directory.name.$dirty">
                <div class="error" v-if="!$v.directory.name.required">Name is required</div>
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
  import directoryService from '@/services/directories'

    export default {
      name: "CreateDirectoryModal",
      props: [],
      data () {
        return {
          directory: {
            name: '',
            parentId:0
          },
          errorMessage: ''
        }
      },
      validations: {
        directory: {
          name: {
            required
          }
        }
      },
      created(){
        console.log('i am created')
      },
      mounted() {
        console.log('mounted');
        this.$nextTick( () => {
          $('#createDirectoryModal').on('shown.bs.modal', () => {
            $('#directoryNameInput').trigger('focus')
          })
        })

      },
      methods: {
        saveDirectory () {
          this.$v.$touch()
          if (this.$v.$invalid) {
            return
          }
          const directory = {
            userId: this.$store.state.user.userId,
            parentId: this.directory.parentId,
            name: this.directory.name
          }
          directoryService.create(directory).then((createdDirectory) => {
            this.$store.dispatch('addDirectory', createdDirectory)
            this.$emit('created', createdDirectory.id)
            this.close()
          }).catch(error => {
            this.errorMessage = error.message
          })
        },
        close () {
          this.$v.$reset()
          this.directory.name = ''
          this.errorMessage = ''
          $('#createDirectoryModal').modal('hide')
        }
      }
    }
</script>

<style scoped>

  .modal{
    position: center;
  }


</style>

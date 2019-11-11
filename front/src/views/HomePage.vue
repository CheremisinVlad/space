<template>
  <div>
    <Header/>
    <CreateDirectoryModal/>
    <CreateUrlModal/>
    <CreateRecordModal/>

      <div class="space container">
        <div class="row">
          <div class="col-3">
            <div class="spaces ">
              <div class="directory add list-group-item" @click="createDirectory()">
                <button class="btn btn-link">+ Create New Directory</button>
              </div>
              <div class="directory list-group-item" v-for="directory in mainDirectories"
                   :key="directory.id" @click="openDirectory(directory)">
                <h2 class="section-title">{{ directory.name }}</h2>
              </div>
            </div>
          </div>
          <div class="col-3 ">
            <div class="spaces ">
              <div class="record add list-group-item" @click="createRecord()">
                <button class="btn btn-link">+ Create New Record</button>
              </div>
              <div class="record list-group" v-for="record in mainRecords"
                   :key="record.id" @mousemove="showRecord(record)">
                <h2 class="section-title">{{ record.name }}</h2>
              </div>
            </div>
          </div>
          <div class="col-6 ">

            <div class="spaces ">
              <div class="url add list-group-item" @click="createUrl()">
                <button class="btn btn-link">+ Create New Url</button>
              </div>
              <div class="url list-group" v-for="url in mainUrls"
                   :key="url.id" @click="openUrl(url)">
                <h2 class="section-title">{{ url.url }}</h2>
              </div>
            </div>

          </div>
        </div>

      </div>
  </div>
</template>

<script>

  import $ from 'jquery'

  import Header from '@/components/Header.vue'
  import CreateDirectoryModal from '@/modals/CreateDirectoryModal.vue'
  import CreateUrlModal from '@/modals/CreateUrlModal.vue'
  import CreateRecordModal from '@/modals/CreateRecordModal.vue'
  import ShowRecordModal from '@/modals/ShowRecordModal.vue'
  import { mapGetters } from 'vuex'

    export default {
      name: 'HomePage',
      data () {
        return {
          selectedDirectoryId: 0
        }
      },
      computed: {
        ...mapGetters([
          'mainDirectories',
          'mainUrls',
          'mainRecords'
        ])
      },
      components: {
        Header,
        CreateDirectoryModal,
        CreateRecordModal,
        CreateUrlModal
      },
      methods: {
        openDirectory (directory) {
          this.$router.push({name: '/', params: {directoryId: directory.id}})
        },
        createDirectory () {
         // this.selectedDirectoryId = directory ? directory.id : 0
            $('#createDirectoryModal').modal('show');
        },
        createUrl () {
          $('#createUrlModal').modal('show')
        },
        openUrl(url){
          this.$router.push({name: url.url})
        },
        createRecord () {
          $('#createRecordModal').modal('show')
        },
        showRecord(record){
          $('#showRecordModal').val(record).modal('show')
        },
        onDirectoryCreated (directoryId) {
          this.$router.push({name: '/', params: {directoryId: directoryId}})
        }
      }
    }
</script>

<style scoped>

  .space {

  }

</style>

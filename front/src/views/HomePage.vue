<template>
  <div>
    <Header/>
      <div class="space container">
        <div class="row">
          <div class="col">
            <div class="spaces d-flex align-content-start flex-wrap">
              <div class="directory list-group" v-for="directory in mainDirectories"
                   :key="directory.id" @click="openDirectory(directory)">
                <h2 class="section-title">{{ directory.name }}</h2>
                <div class="directory add list-group-item" @click="createDirectory()">
                  <button class="btn btn-link">+ Create New Directory</button>
                </div>
              </div>
            </div>
          </div>
          <div class="col-4">
            <div class="spaces d-flex align-content-start flex-wrap">
              <div class="record list-group" v-for="record in mainRecords"
                   :key="record.id" @mousemove="showRecord(record)">
                <h2 class="section-title">{{ record.name }}</h2>
                <div class="record add list-group-item" @click="createRecord()">
                  <button class="btn btn-link">+ Create New Record</button>
                </div>
              </div>
            </div>
          </div>
          <div class="col-6">
            <div class="spaces d-flex align-content-start flex-wrap">
              <div class="url list-group" v-for="url in mainUrls"
                   :key="url.id" @click="openUrl(url)">
                <h2 class="section-title">{{ url.name }}</h2>
                <div class="record add list-group-item" @click="createUrl()">
                  <button class="btn btn-link">+ Create New Url</button>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>
  </div>
</template>

<script>
  import Header from '@/components/Header.vue'
  import $ from 'jquery'
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
        CreateUrlModal,
        CreateRecordModal,
        ShowRecordModal
      },
      methods: {
        openDirectory (directory) {
          this.$router.push({name: '/', params: {directoryId: directory.id}})
        },
        createDirectory (directory) {
          this.selectedDirectoryId = directory ? directory.id : 0
          $('#CreateDirectoryModal').modal('show')
        },
        createUrl () {
          $('#CreateUrlModal').modal('show')
        },
        openUrl(url){
          this.$router.push({name: url.url})
        },
        createRecord () {
          $('#CreateRecordModal').modal('show')
        },
        showRecord(record){
          $('#ShowRecordModal').val(record).modal('show')
        },
        onDirectoryCreated (directoryId) {
          this.$router.push({name: '/', params: {directoryId: directoryId}})
        }
      }
    }
</script>

<style scoped>
  .space-container {
    padding: 0 35px;
  }
  .space-wrapper {
    position: center;
  }
  .space {
    width: 270px;
    height: 110px;
    border-radius: 5px;
    background-color: #377EF6;
    color: #fff;
    padding: 15px;
    margin-right: 100px;
    cursor: pointer;
  }

</style>

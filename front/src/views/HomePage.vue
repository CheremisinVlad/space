<template>
  <div>
    <Header/>
      <div class="space-container">
        <div class="spaces d-flex align-content-start flex-wrap">
          <div class="space list-inline-item">
            <div class="space-wrapper">Patterns</div>
          </div>
          <div class="space list-inline-item">
            <div class="space-wrapper">Urls</div>
          </div>
          <div class="create-space-wrapper">
            <button class="btn btn-link">+ Create New Space</button>
          </div>
        </div>
      </div>

  </div>
</template>

<script>
  import Header from '@/components/Header.vue'
    export default {
        name: "HomePage",
        components: {
          Header
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

<template>
  <div class="page-header d-flex bd-highlight">
    <div class="logo">
      <img class="logo" src="/images/logo.png">
    </div>
    <div class="search-box flex-fill" >
      <div class="search-wrapper">
        <input type="text" placeholder="Search" class="form-control form-control-sm" />

      </div>
    </div>
    <div class="profile-toggle">
      <div class="dropdown">
        <button class="btn dropdown-toggle" type="button" id="profileMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Vlad
        </button>
        <div class="dropdown-menu" aria-labelledby="profileMenu">
          <button class="dropdown-item" type="button">Profile</button>
          <button class="dropdown-item" type="button">Sign Out</button>
        </div>
      </div>
    </div>
  </div>

</template>
<script>
  import { mapGetters } from 'vuex'
    export default {
        name: 'Header',
        computed: {
          ...mapGetters([
            'user',
            'hasSpace',
            'mainDirectories',
            'mainRecords',
            'mainUrls'
          ])
        }

    }
</script>

<style lang="scss" scoped>
  .page-header {
    padding: 12px 12px 12px;
    border-bottom: 1px solid #f4e9dc;
  }
  .logo {
    color: #444;
    height: 60px;
    width: 120px;
    margin-top: 2px;
  }
  img {
    margin-left: 5px;
    width: 80px;
    height: 20px;
    vertical-align: bottom;
  }

  .profile-toggle {
    width: 120px;
    text-align: right;
  }
  .dropdown-toggle {
    padding: 2px 5px !important;
  }
  .search-wrapper {
    width: 150px;
    margin: 0 auto;
    position: relative;
  }
  input {
    padding-left: 30px;
    height: calc(1.8125rem + 5px);
    font-size: 1rem;
    border: 1px solid #eee;
  }
  input:focus {
    border: 1px solid #377EF6;
  }


</style>

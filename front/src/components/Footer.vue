<template>
  <footer class="footer">
    <span class="copyright">&copy; 2018 space.com</span>
    <ul class="footer-links list-inline float-right">
      <li class="list-inline-item"><a href="#">About</a></li>
      <li class="list-inline-item"><a href="#">Terms of Service</a></li>
      <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
      <li class="list-inline-item"><a href="https://github.com/CheremisinVlad/space" target="_blank">GitHub</a></li>
    </ul>
  </footer>
</template>

<script>
  export default {
    name: 'PageFooter'
  }
</script>

<style lang="scss" scoped>
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

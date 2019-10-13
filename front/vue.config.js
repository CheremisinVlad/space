module.exports = {
  devServer:{
    port:8080,
    proxy:{
      '/api/*':{
        target:'http://localhost:8080'
      }
    }
  },
  configureWebpack: {
    entry:{
      app: './src/main.js',
      style: [
        'bootstrap/dist/css/bootstrap.min.css'
      ]
    }
  }
}

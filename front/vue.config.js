const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
  devServer:{
    port:3000,
    proxy:{
      '':{
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
    },plugins: [
      new CleanWebpackPlugin()
    ]
  }
}

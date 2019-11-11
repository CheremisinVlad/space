import errorParser from '../util/error-parser'
import axios from 'axios'

export default {
  create(url){
    return new Promise((resolve,reject) => {
      axios.post('/urls',url)
        .then(({data}) => {
          resolve(data)
        })
        .catch((error) => {
          reject(errorParser.parse(error))
        })
    })
  },
  delete(url){
    return new Promise((resolve,reject) => {
      axios.delete('/urls',url)
        .then(({data}) => {
          resolve(data)
        })
        .catch((error) => {
          reject(errorParser.parse(error))
        })
    })
  }
}

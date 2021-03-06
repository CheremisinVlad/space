import axios from 'axios'
import errorParser from '@/util/error-parser'

export default {
  create(directory){
    return new Promise((resolve, reject) => {
      axios.post('/directories/create', directory)
        .then(({data}) => {
          resolve(data)
        })
        .catch((error) => {
          reject(errorParser.parse(error))
        })
    })
  },
  delete(directory){
    return new Promise((resolve, reject) => {
      axios.delete('/directories/delete', directory)
        .then(({data}) => {
          resolve(data)
        })
        .catch((error) => {
          reject(errorParser.parse(error))
        })
    })
  }
}

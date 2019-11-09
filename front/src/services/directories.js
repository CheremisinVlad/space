import axios from 'axios'
import errorParser from '@/util/error-parser'

export default {
  create(directory){
    return new Promise((resolve, reject) => {
      axios.post('/directories', directory)
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
      axios.delete('/directories', directory)
        .then(({data}) => {
          resolve(data)
        })
        .catch((error) => {
          reject(errorParser.parse(error))
        })
    })
  }
}

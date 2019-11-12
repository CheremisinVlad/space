import axios from 'axios'
import errorParser from '@/util/error-parser'

export default {
  create(record){
    return new Promise((resolve, reject) => {
      axios.post('/records/create', record)
        .then(({data}) => {
          resolve(data)
        })
        .catch((error) => {
          reject(errorParser.parse(error))
        })
    })
  },
  delete(record){
    return new Promise((resolve, reject) => {
      axios.delete('/records/delete', record)
        .then(({data}) => {
          resolve(data)
        })
        .catch((error) => {
          reject(errorParser.parse(error))
        })
    })
  }
}

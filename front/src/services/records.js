import axios from 'axios'
import errorParser from '@/util/error-parser'

export default {
  create(record){
    return new Promise((resolve, reject) => {
      axios.post('/record', record)
        .then(({data}) => {
          resolve(data)
        })
        .catch((error) => {
          reject(errorParser.parse(error))
        })
    })
  }
}

import errorParser from '../util/error-parser'
import axios from 'axios'

export default {
  getData(){
    return new Promise((resolve,reject) => {
      axios.get('/space')
        .then(({data}) => {
          resolve(data)
        })
        .catch((error) => {
          reject(errorParser.parse(error))
        })
    })
  }
}

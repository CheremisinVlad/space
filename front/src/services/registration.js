import axios from 'axios'
import errorParser from '@/util/error-parser'

export default {
  register(detail){
    return new Promise((resolve, reject) => {
      axios.post('/registration',detail).then(({data}) => {
        resolve(data)
      }).catch((error) =>{
        reject(errorParser.parse(error))
      })
    })
  }
}

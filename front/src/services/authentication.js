import axios from 'axios'
import errorParser from '@/util/error-parser'

export default {
  authenticate(user){
    return new Promise((resolve, reject) => {
      axios.post("/authentication",user).then(({data})=>{
        resolve(data)
      }).catch((error)=>{
        reject(errorParser.parse(error))
      })
    })
  }
}

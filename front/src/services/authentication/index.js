import axios from 'axios'

export default {
  authenticate(user){
    return new Promise((resolve, reject) => {
      axios.post("/authentication",user).then(({data})=>{
        resolve(data)
      }).catch((error)=>{
        reject(error)
      })
    })
  }
}

export default {
  register(detail){
    return new Promise((resolve, reject) => {
      detail.email === "vlad@mail.com"
      ?resolve({result: 'success'})
       :reject(new Error('user already exist'))
    })
  }
}

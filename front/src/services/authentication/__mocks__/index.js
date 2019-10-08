export default {
  authenticate(user){
    return new Promise((resolve, reject) => {
      console.log(user.username)
      console.log(user.password)
      user.username === 'vlad' && user.password === 'password'
        ? resolve({result: 'success'})
        : reject(new Error('incorrect login and password'))
    })
  }
}

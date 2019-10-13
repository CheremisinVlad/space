export default {
  authenticate(user){
    return new Promise((resolve, reject) => {
      console.log(user.password + ' ' + user.username)
      user.username === 'vlad' && user.password === 'password'
        ? resolve({result: 'success'})
        : reject(new Error('incorrect login and password'))
    })
  }
}

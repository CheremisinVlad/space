module.exports = {
  url: function () {
    return this.api.launch_url + 'register'
  },
  elements: {
    app: {
      selector: '#app'
    },
    logoImage: {
      selector: 'img.logo'
    },
    usernameInput: {
      selector: '#username'
    },
    emailInput: {
      selector: '#email'
    },
    passwordInput: {
      selector: '#password'
    },
    submitButton: {
      selector: 'button[type=submit]'
    },
    formError: {
      selector: '.failed'
    }
  },
  commands: [{
    register: function (username, email, password) {
      this
        .setValue('@usernameInput', username)
        .setValue('@emailInput', email)
        .setValue('@passwordInput', password)
        .click('@submitButton')
    }
  }]
}

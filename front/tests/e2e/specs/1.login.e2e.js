const user = require('../data/user')

module.exports = {
  'login page renders elements': function (browser) {
    const loginPage = browser.page.LoginPage()
    loginPage.navigate()
      .waitForElementVisible('@app', 30000)
      .assert.hidden('@formError')
      .assert.visible('@usernameInput')
      .assert.visible('@passwordInput')
      .assert.visible('@submitButton')


    browser.end()
  },
  'login with incorrect credentials': function (browser) {
    const loginPage = browser.page.LoginPage()
    loginPage
      .navigate()
      .login('invalid', 'incorrect')

    browser.pause(2000)

    loginPage
      .assert.visible('@formError')
      .assert.containsText('@formError', 'Request failed with status code 400')

    browser
      .assert.urlEquals(browser.launchUrl + 'login')
      .end()
  },
  'login with username': function (browser) {
    const loginPage = browser.page.LoginPage()
    const homePage = browser.page.HomePage()
    loginPage
      .navigate()
      .login(user.username, user.password)

    browser.pause(2000)

    homePage
      .navigate()
      .expect.element('@pageTitle').text.to.contain('Home')

    browser.end()
  },
  'login with email': function (browser) {
    const loginPage = browser.page.LoginPage()
    const homePage = browser.page.HomePage()
    loginPage
      .navigate()
      .login(user.email, user.password)

    browser.pause(2000)

    homePage
      .navigate()
      .expect.element('@pageTitle').text.to.contain('Home')

    browser.end()
  }
}

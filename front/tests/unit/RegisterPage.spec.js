import { mount, createLocalVue } from '@vue/test-utils'
import VueRouter from 'vue-router'
import RegisterPage from '@/views/RegisterPage'

const localVue = createLocalVue()
localVue.use(VueRouter)
const router = new VueRouter()

jest.mock('@/services/registration')

describe("RegisterPage.vue",()=>{
    let wrapper
    let userNameField
    let emailField
    let passwordField
    let buttonSubmit
    beforeEach(()=>{
      wrapper = mount(RegisterPage,{
        localVue,router
      })
      userNameField = wrapper.find('#username')
      emailField = wrapper.find('#email')
      passwordField = wrapper.find('#password')
      buttonSubmit = wrapper.find('form button[type="submit"]')
    })
  afterAll(() => {
    jest.restoreAllMocks()
  })

  it('should render registration form',()=>{
    expect(wrapper.find('.logo').attributes().src).toEqual('/images/logo.png')
    expect(wrapper.find('.tagline').text()).toEqual('Organize your space like your want')
    expect(userNameField.element.value).toEqual('')
    expect(emailField.element.value).toEqual('')
    expect(passwordField.element.value).toEqual('')
    expect(buttonSubmit.text()).toEqual('Create account')
  })

  it('should contain data with empty initial values',()=>{
    expect(wrapper.vm.form.username).toEqual('')
    expect(wrapper.vm.form.email).toEqual('')
    expect(wrapper.vm.form.password).toEqual('')
  })

  it('should have form inputs bound with data',()=>{
    const username = 'vlad'
    const email = 'vlad@mail.com'
    const password = 'vlad'

    wrapper.vm.form.username = username
    wrapper.vm.form.email = email
    wrapper.vm.form.password = password

    expect(userNameField.element.value).toEqual(username)
    expect(emailField.element.value).toEqual(email)
    expect(passwordField.element.value).toEqual(password)
  })

  it('should have form submit handler `submitForm`',()=>{
    const stub = jest.fn()
    wrapper.setMethods({submitForm: stub})
    buttonSubmit.trigger('submit')
    expect(stub).toBeCalled()
  })

  it('should register when new user come',()=>{
    const stub = jest.fn()
    wrapper.vm.$router.push = stub
    wrapper.vm.form.username = 'vlad'
    wrapper.vm.form.email = 'vlad@mail.com'
    wrapper.vm.form.password = 'vlad'
    wrapper.vm.submitForm()
    wrapper.vm.$nextTick(()=>{
      expect(stub).toHaveBeenCalledWith({name: 'LoginPage'})
    })
  })

  it('should fail with user is not new ',()=>{
    
    wrapper.vm.form.email = 'vasya@mail.com'
    expect(wrapper.find('.failed').isVisible()).toBe(false)
    wrapper.vm.submitForm()
    wrapper.vm.$nextTick(()=>{
      expect(wrapper.find('.failed').isVisible()).toBe(true)
    })
  })






})

import { mount, createLocalVue } from '@vue/test-utils'
import Vuelidate from 'vuelidate'
import VueRouter from 'vue-router'
import LoginPage from '@/views/LoginPage'
import authenticationService from '@/services/authentication'

const localVue = createLocalVue()

localVue.use(VueRouter)
localVue.use(Vuelidate)

const router = new VueRouter()
jest.mock('@/services/authentication')

describe('LoginPage.vue', () => {
  let wrapper
  let usernameField
  let passwordField
  let submitButton
  let authSpy

  beforeEach(()=>{
    wrapper = mount(LoginPage, {
      localVue,
      router
    })
    usernameField = wrapper.find('#username')
    passwordField = wrapper.find('#password')
    submitButton = wrapper.find('form button[type="submit"]')
    authSpy = jest.spyOn(authenticationService,'authenticate')
  })

  afterEach(() => {
    authSpy.mockReset()
    authSpy.mockRestore()
  })

  afterAll(() => {
    jest.restoreAllMocks()
  })

  it('should render login form ',()=>{
    expect(usernameField.element.value).toEqual('')
    expect(passwordField.element.value).toEqual('')
    expect(submitButton.text()).toEqual('Sign in')
    expect(wrapper.find('.logo').attributes().src).toEqual('/images/logo.png')
    expect(wrapper.find('.tagline').text()).toEqual('Organize your space like your want')
    expect(wrapper.find('.link-sign-up').attributes().href).toEqual('/register')
    expect(wrapper.find('.link-forgot-password')).toBeTruthy()
  })

  it('form should contain data with initial values',()=>{
    expect(wrapper.vm.form.username).toEqual('')
    expect(wrapper.vm.form.password).toEqual('')
  })

  it('should correct bound values to form fields',()=>{
    const username = 'vlad'
    const password = 'password'

    wrapper.vm.form.username = username
    wrapper.vm.form.password = password

    expect(usernameField.element.value).toEqual(username)
    expect(passwordField.element.value).toEqual(password)
  })

  it('form should have submit handler',()=>{
    const stub = jest.fn();
    wrapper.setMethods({submitForm: stub})
    submitButton.trigger('submit')
    expect(stub).toBeCalled()
  })

  it('should succeed with valid credentials',async ()=>{
    expect.assertions(2)
    const stub = jest.fn()
    wrapper.vm.$router.push= stub
    wrapper.vm.form.username = 'vlad'
    wrapper.vm.form.password = 'password'
    wrapper.vm.submitForm()
    expect(authSpy).toBeCalled()
    await wrapper.vm.$nextTick()
    expect(stub).toHaveBeenCalledWith({name: 'HomePage'})
  })

  it('should fail with invalid credentials',async ()=>{
    expect.assertions(3)

    wrapper.vm.form.username = 'vlad'
    wrapper.vm.form.password = 'wrong'

    expect(wrapper.find('.failed').isVisible()).toBe(false)

    wrapper.vm.submitForm()

    expect(authSpy).toBeCalled()
    await wrapper.vm.$nextTick()
    console.log(wrapper.find('.failed').isVisible())
    expect(wrapper.find('.failed').isVisible()).toBe(true)
  })

  it('should validation work', () => {
    wrapper.vm.submitForm()
    expect(authSpy).not.toHaveBeenCalled()

    wrapper.vm.form.username = 'vlad'
    wrapper.vm.submitForm()
    expect(authSpy).not.toHaveBeenCalled()

    wrapper.vm.form.username = 'vlad@mail.com'
    wrapper.vm.submitForm()
    expect(authSpy).not.toHaveBeenCalled()

    wrapper.vm.form.username = ''
    wrapper.vm.form.password = 'password'
    wrapper.vm.submitForm()
    expect(authSpy).not.toHaveBeenCalled()
  })


})

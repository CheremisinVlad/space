import Vue from 'vue'
import LoginPage from '@/views/LoginPage'

describe('LoginPage.vue', () => {
  it('render test content', () => {
    const Construcor = Vue.extend(LoginPage)
    const vm = new Construcor().$mount()
    expect(vm.$el.querySelector('h1').textContent).toEqual('Organize your space like your want')
  })
})

import Vue from 'vue'
import RegisterPage from '@/views/RegisterPage'

describe("RegisterPage.vue",()=>{
    it('should render correct contents',()=>{
      const Constructor = Vue.extend(RegisterPage)
      const vm = new Constructor().$mount()
      expect(vm.$el.querySelector('.logo').getAttribute('src'))
        .toEqual('/images/logo.png')
      expect(vm.$el.querySelector('.tagline').textContent)
        .toEqual('Organize your space like your want')
      expect(vm.$el.querySelector('#username').value).toEqual('')
      expect(vm.$el.querySelector('#email').value).toEqual('')
      expect(vm.$el.querySelector('#password').value).toEqual('')
      expect(vm.$el.querySelector('form button[type="submit"]').textContent).toEqual('Create account')
    })
})

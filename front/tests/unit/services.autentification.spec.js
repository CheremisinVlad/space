import moxios from "moxios"
import authenticationService from '@/services/authentication'

describe('service/authentications', function () {
  beforeEach(()=>{
    moxios.install()
  })

  afterEach(()=>{
    moxios.uninstall()
  })

  it('should perform call to /authentications',()=>{
    expect.assertions(1)
    moxios.wait(()=>{
      let request = moxios.requests.mostRecent()
      expect(request.url).toEqual('/authentications')
      request.respondWith({
        status: 200,
        response: {result: 'success'}
      })
    })
    return authenticationService.authenticate()
  })

  it('should pass the response to caller when request succeeded', () => {
    expect.assertions(2)
    moxios.wait(() => {
      let request = moxios.requests.mostRecent()
      expect(request).toBeTruthy()
      request.respondWith({
        status: 200,
        response: {result: 'success'}
      })
    })
    return authenticationService.authenticate().then(data => {
      expect(data.result).toEqual('success')
    })
  })

  it('should send error to caller when request fails',()=>{
    expect.assertions(2)
    moxios.wait(()=>{
      let request = moxios.requests.mostRecent()
      expect(request).toBeTruthy()
      request.reject({
        status: 400,
        response: {result: 'fail'}
      })

    })
    return authenticationService.authenticate().catch(error=>{
      expect(error.response.result).toEqual('fail')
    })
  })


});

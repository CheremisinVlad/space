import spaceService from '../services/space'
import authService from '../services/authentication'

export const getData = ({commit},userId) => {
  spaceService.getData(userId).then(data => {
    commit('updateData',data)
  })
}
export const authenticate = ({commit},user) => {
  authService.authenticate(user).then(data => {
    commit('updateCurrentUser',data)
  })
}

export const addDirectory = ({commit},directory) =>{
  commit('addDirectory',directory)
}
export const deleteDirectory = ({commit},directory) =>{
  commit('deleteDirectory',directory)
}
export const deleteUrl= ({commit},url) =>{
  commit('deleteUrl',url)
}
export const addUrl= ({commit},url) =>{
  commit('addUrl',url)
}
export const addRecord = ({commit},record) =>{
  commit('addRecord',record)
}

export const deleteRecord = ({commit},record) =>{
  commit('deleteRecord',record)
}

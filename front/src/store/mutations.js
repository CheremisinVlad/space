export default {
  updateData(state,data){
    state.directories = data.directories
    state.urls = data.urls
  },
  updateCurrentUser(state,user){
    state.user.authenticated= user.authenticated
    state.user.userId= user.userId
    state.user.name = user.name
  },
  addDirectory(state,directory){
    state.directories.push(directory)
  },
  addUrl(state,url){
    state.urls.push(url)
  },
  addRecord(state,record){
    state.records.push(record)
  },
  deleteDirectory(state,directory){
    let dirs = state.directories
    if(dirs.includes(directory)){
      let index = dirs.indexOf(directory)
      dirs.splice(index,1)
    }
  },
  deleteUrl(state,url){
    let urls = state.urls
    if(urls.includes(url)){
      let index = urls.indexOf(url)
      urls.splice(index,1)
    }
  },
  deleteRecord(state,record){
    let records = state.records
    if(records.includes(record)){
      let index = records.indexOf(record)
      records.splice(index,1)
    }
  }

}

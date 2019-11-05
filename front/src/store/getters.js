export const user = state => state.user

export const hasSpace = state => {
  return state.directories.length > 0 || state.urls.length >0 || state.records.length > 0
}

export const mainDirectories = state => {
  state.directories.filter(dir => dir.parentId === 0)
}

export const mainUrls = state => {
  state.urls.filter(url => url.dir.parentId === 0)
}

export const mainRecords = state => {
  state.records.filter(record => record.dir.parentId === 0)
}


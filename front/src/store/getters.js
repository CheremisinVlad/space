export const user = state => state.user

export const urls = state => state.urls

export const hasSpace = state => {
  return state.directories.length > 0 || state.urls.length >0 || state.records.length > 0
}

export const mainDirectories = state => {
  return state.directories.filter(dir => dir.parentId === 0)
}

export const mainUrls = state => {
  return state.urls.filter(url => url.parentId === 0)
}

export const mainRecords = state => {
  return state.records.filter(record => record.parentId === 0)
}


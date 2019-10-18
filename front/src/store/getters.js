export const user = state => state.user

export const hasSpace = state => {
  return state.directories.length > 0 || state.urls.length >0 || state.records.length > 0
}

export const mainDirectories = state => {
  state.directories.filter(dir => dir.isMain === true)
}

export const mainUrls = state => {
  state.urls.filter(url => url.isMain === true)
}

export const mainRecords = state => {
  state.records.filter(record => record.isMain === true)
}


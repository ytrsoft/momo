var profileData

function setup(remoteId) {
  const url = 'https://api.immomo.com/v3/user/profile/info'
  const requestParams = createRequestParams(remoteId)
  const httpClient = createHttpClient()
  const response = httpClient.doPost(url, requestParams)
  profileData = JSON.parse(response).data
}

function createRequestParams(remoteId) {
  const LinkedHashMap = Java.use("java.util.LinkedHashMap")
  const params = LinkedHashMap.$new()
  params.put('remoteid', remoteId)
  return params
}

function createHttpClient() {
  const HttpClientClass = Java.use('com.immomo.momo.protocol.http.a.a')
  return HttpClientClass.$new()
}

rpc.exports = {
  profile: (remoteId) => {
    Java.perform(() => {
      setup(remoteId)
    })
    return profileData
  }
}

var timelineData

function setup(remoteId) {
  const url = 'https://api.immomo.com/v1/feed/user/timeline'
  const requestParams = createRequestParams(remoteId)
  const httpClient = createHttpClient()
  const response = httpClient.doPost(url, requestParams)
  timelineData = JSON.parse(response).data
}

function createRequestParams(remoteId) {
  const LinkedHashMap = Java.use('java.util.LinkedHashMap')
  const params = LinkedHashMap.$new()
  params.put('remoteid', remoteId)
  return params
}

function createHttpClient() {
  const HttpClientClass = Java.use('com.immomo.momo.protocol.http.a.a')
  return HttpClientClass.$new()
}

rpc.exports = {
  timeline: (remoteId) => {
    Java.perform(() => {
        setup(remoteId)
    })
    return timelineData
  }
}

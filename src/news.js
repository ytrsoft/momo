var newsData

function setup() {
  const url = 'https://api.immomo.com/v2/feed/nearbyv2/lists'
  const httpClient = createHttpClient()
  const requestParams = createRequestParams()
  const response = httpClient.doPost(url, requestParams)
  newsData = JSON.parse(response).data
}

function createHttpClient() {
  const targetClass = Java.use('com.immomo.momo.protocol.http.a.a')
  return targetClass.$new()
}

function createRequestParams() {
  const LinkedHashMap = Java.use('java.util.LinkedHashMap')
  const params = LinkedHashMap.$new()
  params.put('lat', '28.196451')
  params.put('lng', '112.977301')
  params.put('count', '20')
  return params
}


rpc.exports = {
  news: () => {
    Java.perform(setup)
    return newsData
  }
}

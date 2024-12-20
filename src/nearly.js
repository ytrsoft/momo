var nearbyData

function setup() {
  const url = 'https://api.immomo.com/v2/nearby/people/lists'
  const targetClass = Java.use('com.immomo.momo.protocol.http.a.a')
  const http = targetClass.$new()
  const requestParams = createRequestParams()
  const response = http.doPost(url, requestParams)
  nearbyData = JSON.parse(response).data
}

function createRequestParams() {
  const LinkedHashMap = Java.use('java.util.LinkedHashMap')
  const params = LinkedHashMap.$new()
  // params.put('online_time', '1')
  params.put('lat', '28.196451')
  params.put('lng', '112.977301')
  params.put('age_min', '18')
  params.put('age_max', '100')
  // params.put('sex', 'F')
  return params
}

rpc.exports = {
  nearly: () => {
    Java.perform(setup)
    return nearbyData
  }
}

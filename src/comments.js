  var commentsData

  function setup(feedId) {
    const url = 'https://api.immomo.com/v2/feed/comment/comments'
    const http = createHttpClient()
    const requestParams = createRequestParams(feedId)
    const response = http.doPost(url, requestParams)
    commentsData = JSON.parse(response).data
  }

  function createRequestParams(feedId) {
    const LinkedHashMap = Java.use('java.util.LinkedHashMap')
    const params = LinkedHashMap.$new()
    params.put('feedid', feedId)
    params.put('sort_type', 'early')
    params.put('count', '50')
    params.put('index', '0')
    return params
  }

  function createHttpClient() {
    const HttpClientClass = Java.use('com.immomo.momo.protocol.http.a.a')
    return HttpClientClass.$new()
  }

  rpc.exports = {
    comments: (feedId) => {
      Java.perform(() => {
        setup(feedId)
      })
      return commentsData
    }
  }

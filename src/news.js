var v_news

function setup() {
  const url = 'https://api.immomo.com/v2/feed/nearbyv2/lists'
  const targetClass = Java.use('com.immomo.momo.protocol.http.a.a')
  const http = targetClass.$new()
  const args = generateParams()
  const response = http.doPost(url, args)
  v_news = v_news_deep(JSON.parse(response))
}

function generateParams() {
  const LinkedHashMap = Java.use('java.util.LinkedHashMap')
  const root = LinkedHashMap.$new()
  root.put('ish265', '1')
  root.put('h265_level', '1')
  root.put('lat', '28.196451')
  root.put('lng', '112.977301')
  root.put('firstRefresh', '0')
  root.put('count', '20')
  root.put('index', '20')
  return root
}

function v_news_deep(obj) {
  if (Array.isArray(obj)) {
      return obj.map(v_news_deep)
  } else if (typeof obj === 'object' && obj !== null) {
      Object.keys(obj).forEach(function (key) {
          var value = obj[key]
          if (typeof value === 'string') {
              try {
                  obj[key] = v_news_deep(JSON.parse(value))
              } catch (e) { }
          } else {
              obj[key] = v_news_deep(value)
          }
      })
  }
  return obj
}

rpc.exports = {
  news: function() {
    Java.perform(function() {
      setup()
    })
    return v_news
  }
}

var v_nearly

function setup() {
  const url = 'https://api.immomo.com/v2/nearby/people/lists'
  const targetClass = Java.use('com.immomo.momo.protocol.http.a.a')
  const http = targetClass.$new()
  const args = generateParams()
  const response = http.doPost(url, args)
  v_nearly = v_nearly_deep(JSON.parse(response))
}

function generateParams() {
  const LinkedHashMap = Java.use('java.util.LinkedHashMap')
  const root = LinkedHashMap.$new()
  root.put('online_time', '1')
  root.put('lat', '28.196451')
  root.put('age_max', '100')
  root.put('refreshmode', 'user')
  root.put('lng', '112.977301')
  root.put('count', '20')
  root.put('age_min', '18')
  root.put('index', '0')
  root.put('sex', 'F')
  root.put('firstRefresh', '0')
  return root
}


function v_nearly_deep(obj) {
  if (Array.isArray(obj)) {
      return obj.map(v_nearly_deep)
  } else if (typeof obj === 'object' && obj !== null) {
      Object.keys(obj).forEach(function (key) {
          var value = obj[key]
          if (typeof value === 'string') {
              try {
                  obj[key] = v_nearly_deep(JSON.parse(value))
              } catch (e) { }
          } else {
              obj[key] = v_nearly_deep(value)
          }
      })
  }
  return obj
}

rpc.exports = {
  nearly: function() {
    Java.perform(function() {
      setup()
    })
    return v_nearly
  }
}
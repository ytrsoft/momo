  var v_timeline

  function setup(remoteid) {
    const url = 'https://api.immomo.com/v1/feed/user/timeline'
    const targetClass = Java.use('com.immomo.momo.protocol.http.a.a')
    const http = targetClass.$new()
    const args = generateParams(remoteid)
    const response = http.doPost(url, args)
    v_timeline = JSON.parse(response).data
  }

  function generateParams(remoteid) {
    const LinkedHashMap = Java.use('java.util.LinkedHashMap')
    const root = LinkedHashMap.$new()
    root.put('ish265', '1')
    root.put('h265_level', '1')
    root.put('remoteid', remoteid)
    root.put('index', '0')
    root.put('count', '10')
    root.put('source', 'profile')
    return root
}

rpc.exports = {
  timeline: function(remoteid) {
    Java.perform(function() {
      setup(remoteid)
    })
    return v_timeline
  }
}

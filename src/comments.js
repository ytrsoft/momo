  var v_comments

  function setup(feedId) {
    const url = 'https://api.immomo.com/v2/feed/comment/comments'
    const targetClass = Java.use('com.immomo.momo.protocol.http.a.a')
    const http = targetClass.$new()
    const args = generateParams(feedId)
    const response = http.doPost(url, args)
    v_comments = JSON.parse(response).data
  }

  function generateParams(feedId) {
    const LinkedHashMap = Java.use('java.util.LinkedHashMap')
    const root = LinkedHashMap.$new()
    root.put('feedid', feedId)
    root.put('sort_type', 'early')
    root.put('count', '20')
    root.put('index', '0')
    return root
  }


  rpc.exports = {
    comments: function(feedId) {
      Java.perform(function() {
        setup(feedId)
      })
      return v_comments
    }
  }

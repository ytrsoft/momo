var v_history

function setup() {
  const msgs = []
  const MessageApi = Java.use('com.immomo.momo.protocol.http.MessageApi')
  const result = MessageApi.a().b()
  for (let i = 0; i < result.size(); i++) {
    let match, jsonObject = {}
    const message = result.get(i).toString()
    const regex = /([a-zA-Z0-9]+)=([^,]+)/g
    while ((match = regex.exec(message)) !== null) {
      const key = match[1]
      const value = match[2]
      jsonObject[key] = value
    }
    msgs.push(jsonObject)
  }
  v_history = msgs
}

rpc.exports = {
  history: function() {
    Java.perform(function() {
      setup()
    })
    return v_history
  }
}

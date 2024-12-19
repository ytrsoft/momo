var v_post

function setup(msg) {
  const { momoid, remoteId, content } = msg
  const MessageClass = Java.use('com.immomo.momo.service.bean.Message')
  const MessageHelper = Java.use('com.immomo.momo.message.helper.p')
  const owner = getUser(momoid)
  const instance = MessageHelper.a()
  const remote = getUser(remoteId)
  const message = instance.a(content, remote, null, 1)
  const field = MessageClass.class.getDeclaredField('owner')
  field.setAccessible(true)
  field.set(message, owner)
  sendMsg(message)
  msg.msgId = message.msgId.value
  msg.messageTime = message.messageTime.value
  v_post = msg
}

function sendMsg(message) {
  const MessageSender = Java.use('com.immomo.momo.mvp.message.task.c')
  const sender = MessageSender.$new()
  const ms = MessageSender.class.getDeclaredMethods()
  ms[0].setAccessible(true)
  ms[0].invoke(sender, [message])
}

function getUser(id) {
  const UserService = Java.use('com.immomo.momo.service.user.UserService')
  return UserService.getInstance().get(id)
}

rpc.exports = {
  post: (msg) => {
    Java.perform(() => {
      setup(msg)
    })
    return v_post
  }
}


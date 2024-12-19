var v_post

function setup(msg) {
  const { momoid, remoteId, content } = msg
  const message = createMessage(momoid, remoteId, content)
  const MessageSender = Java.use('com.immomo.momo.mvp.message.task.c')
  const sender = MessageSender.$new()
  sender.start()
  sender.a(message)
  v_post = message
}

function newString(value) {
  const StringClass = Java.use("java.lang.String")
  return StringClass.valueOf(JSON.stringify(value))
}

function newLong(value) {
  const LongClass = Java.use("java.lang.Long")
  return LongClass.valueOf(Number(value))
}

function newFloat(value) {
  const FloatClass = Java.use("java.lang.Float")
  return FloatClass.valueOf(Number(value))
}

function createMessage(momoid, remoteId, content) {
  const UserService = Java.use('com.immomo.momo.service.user.UserService')
  const owner = UserService.getInstance().get(momoid)
  const Utils = Java.use('com.immomo.momo.util.g')
  const messageTime = Utils.c()
  const MessageClass = Java.use('com.immomo.momo.service.bean.Message')
  const msgId = Utils.a(
    newString(momoid),
    newString(content),
    newString(remoteId),
    messageTime
  )

  const message = MessageClass.$new(msgId)

  message.setContent(content)

  const fRid = MessageClass.class.getDeclaredField('remoteId')
  fRid.setAccessible(true)
  fRid.set(message, newString(remoteId))

  const fUser = MessageClass.class.getDeclaredField('owner')
  fUser.setAccessible(true)
  fUser.set(message, owner)

  const fMt = MessageClass.class.getDeclaredField('messageTime')
  fMt.setAccessible(true)
  fMt.set(message, newLong(messageTime))


  const fDistance = MessageClass.class.getDeclaredField('distance')
  fDistance.setAccessible(true)
  fDistance.set(message, newFloat(2604.0))

  return message
}

function createUser(momoid) {
  const UserClass = Java.use('com.immomo.momo.service.bean.User')
  const user = UserClass.$new(momoid)
  return user
}

Java.perform(function() {
  setup({
    'momoid': '994491371',
    'remoteId': '976807129',
    'content': '[TERMINAL] - HOLA'
  })
})


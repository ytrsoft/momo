Java.perform(() => {
  const MessageSender = Java.use('com.immomo.momo.mvp.message.task.c')
  const Message = MessageSender.a.overload('com.immomo.momo.service.bean.Message')
  Message.implementation = function(message) {
    console.log('[MSG]', message.toString())
    this.a(message)
  }
})

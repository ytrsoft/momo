Java.perform(function () {
  const MessageSender = Java.use('com.immomo.momo.mvp.message.task.c')
MessageSender.a.overload('com.immomo.momo.service.bean.Message').implementation = function (message)  {
    console.log('[MSG] - ', message.toString())
    this.a(message)
  }
})

const basics = [
  'Byte',
  'Short',
  'Integer',
  'Long',
  'Float',
  'Double',
  'Character',
  'Boolean',
  'String'
]

const formatter = (date) => {
  const regex = /^(\w+)\s(\w+)\s(\d{1,2})\s(\d{2}:\d{2}:\d{2})\sGMT([+\-]\d{2}):(\d{2})\s(\d{4})$/
  const match = date.toString().match(regex)
  if (!match) return undefined
  const map = {
    'Jan': '01', 'Feb': '02', 'Mar': '03', 'Apr': '04', 'May': '05', 'Jun': '06',
    'Jul': '07', 'Aug': '08', 'Sep': '09', 'Oct': '10', 'Nov': '11', 'Dec': '12'
  }
  const n = map[match[2]]
  return `${match[7]}-${n}-${match[3].padStart(2, '0')} ${match[4]}`
}

const getValue = (field, instance) => {
  const ref = {}
  try {
    ref.value = field.get(instance)
  } catch (e) {
    ref.value = undefined
  }
  return ref.value
}

const getType = (value) => {
  const clz = value.getClass()
  const className = clz.getSimpleName()
  if (className === 'Date') {
    return formatter(value)
  }
  if (className === 'User') {
    return toJSON(value)
  }
  if (basics.includes(className)) {
    return value.toString()
  }
  return undefined
}

const toJSON = (instance) => {
  const obj = {}
  const Modifier = Java.use('java.lang.reflect.Modifier')
  const clazz = instance.getClass()
  const fields = clazz.getDeclaredFields()
  fields.forEach((field) => {
    const name = field.getName()
    const modifiers = field.getModifiers()
    if (!Modifier.isStatic(modifiers)) {
      field.setAccessible(true)
      const value = getValue(field, instance)
      if (value !== null) {
        const fieldValue = getType(value)
        if (fieldValue !== undefined) {
          obj[name] = fieldValue
        }
      }
    }
  })
  return obj
}

const API = {
  NEWS: 'https://api.immomo.com/v2/feed/nearbyv2/lists',
  COMMENTS: 'https://api.immomo.com/v2/feed/comment/comments',
  TIMELINE: 'https://api.immomo.com/v1/feed/user/timeline',
  PROFILE: 'https://api.immomo.com/v3/user/profile/info',
  NEARLY: 'https://api.immomo.com/v2/nearby/people/lists'
}

const settings = {
  lat: '28.196451',
  lng: '112.977301'
}

const setup = (handler) => {
  const ref = { value: {} }
  Java.perform(() => {
    handler && handler(ref)
  })
  return ref.value
}

const createMap = (params) => {
  const LinkedHashMap = Java.use('java.util.LinkedHashMap')
  const map = LinkedHashMap.$new()
  Object.keys(params).forEach((key) => {
    map.put(key, params[key])
  })
  return map
}

const doPost = (url, body) => {
  const params = createMap(body)
  const HttpClient = Java.use('com.immomo.momo.protocol.http.a.a')
  const instance = HttpClient.$new()
  const response = instance.doPost(url, params)
  return JSON.parse(response).data
}

const getMessage = (remote, content) => {
  const MessageHelper = Java.use('com.immomo.momo.message.helper.p')
  const instance = MessageHelper.a()
  return instance.a(content, remote, null, 1)
}

const sendMsg = (message) => {
  const MessageSender = Java.use('com.immomo.momo.mvp.message.task.c')
  const sender = MessageSender.$new()
  const ms = MessageSender.class.getDeclaredMethods()
  ms[0].setAccessible(true)
  ms[0].invoke(sender, [message])
}

const updateMsgBean = (instance, key, value) => {
  const Message = Java.use('com.immomo.momo.service.bean.Message')
  const field = Message.class.getDeclaredField(key)
  field.setAccessible(true)
  field.set(instance, value)
}

const getUser = (id) => {
  const UserService = Java.use('com.immomo.momo.service.user.UserService')
  return UserService.getInstance().get(id)
}

const postMsg = (msg) => {
  setup(() => {
    const { momoid, remoteId, content } = msg
    const owner = getUser(momoid)
    const remote = getUser(remoteId)
    const message = getMessage(remote, content)
    updateMsgBean(message, 'owner', owner)
    sendMsg(message)
    return toJSON(message)
  })
}

const receiveMsg = (handler) => {
  setup(() => {
    const MessageServiceHelper = Java.use('com.immomo.momo.service.sessions.f')
    const Message = MessageServiceHelper.e.overload('com.immomo.momo.service.bean.Message')
    Message.implementation = function (message) {
      const msg = toJSON(message)
      handler && handler(msg)
      this.a(message)
    }
  })
}

const fetchNearby = () => {
  return doPost(API.NEARLY, {
    lat: settings.lat,
    lng: settings.lng,
    age_min: '18',
    age_max: '100',
    online_time: '1',
    sex: 'F'
  })
}

const fetchProfile = (id) => {
  return doPost(API.PROFILE, {
    remoteid: id
  })
}

const fetchNews = () => {
  return doPost(API.TIMELINE, {
    lat: settings.lat,
    lng: settings.lng,
    count: '20'
  })
}

const fetchTimeline = (id) => {
  return doPost(API.TIMELINE, {
    remoteid: id
  })
}

const fetchComments = (id) => {
  return doPost(API.COMMENTS, {
    feedid: id,
    sort_type: 'early',
    index: '0',
    count: '50'
  })
}

const fetchImage = (id) => {
  setup((ref) => {
    const MoliveKit = Java.use('com.immomo.molive.foundation.util.ay')
    ref.value = MoliveKit.e(id)
  })
}

rpc.exports = {
  fetchImage,
  fetchProfile,
  fetchNearby,
  fetchNews,
  fetchTimeline,
  fetchComments,
  postMsg,
  receiveMsg
}

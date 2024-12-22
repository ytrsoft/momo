const WRAPS = [
  'Byte', 'Short', 'Integer', 'Long', 'Float', 'Double', 'Character', 'Boolean', 'String'
]

const PKGS = {
  MODIFIER: 'java.lang.reflect.Modifier',
  LINKED_HASH_MAP: 'java.util.LinkedHashMap',
  HTTP_CLIENT: 'com.immomo.momo.protocol.http.a.a',
  MSG_HELPER: 'com.immomo.momo.message.helper.p',
  MSG_SENDER: 'com.immomo.momo.mvp.message.task.c',
  MSG_BEAN: 'com.immomo.momo.service.bean.Message',
  USER_SERVICE: 'com.immomo.momo.service.user.UserService',
  MSG_CACHE: 'com.immomo.momo.messages.b.b',
  IMAGE_UTIL: 'com.immomo.molive.foundation.util.ay'
}

const BASE_URL = 'https://api.immomo.com'

const API = {
  NEWS: `${BASE_URL}/v2/feed/nearbyv2/lists`,
  COMMENTS: `${BASE_URL}/v2/feed/comment/comments`,
  TIMELINE: `${BASE_URL}/v1/feed/user/timeline`,
  PROFILE: `${BASE_URL}/v3/user/profile/info`,
  NEARLY: `${BASE_URL}/v2/nearby/people/lists`
}

const isWrapType = (instance) => {
  const name = getInstName(instance)
  return WRAPS.includes(name)
}

const isStatic = (field) => {
  const Modifier = Java.use(PKGS.MODIFIER)
  const modifiers = field.getModifiers()
  return Modifier.isStatic(modifiers)
}

const getInstName = (instance) => {
  const classes = instance.getClass()
  return classes.getSimpleName()
}

const getFields = (instance) => {
  const classes = instance.getClass()
  return classes.getDeclaredFields()
}

const getMethods = (instance) => {
  const classes = instance.getClass()
  return classes.getDeclaredMethods()
}

const getValue = (instance, field) => {
  const ref = {}
  try {
    field.setAccessible(true)
    ref.value = field.get(instance)
  } catch (e) {
    ref.value = undefined
  }
  return ref.value
}

const setValue = (instance, key, value) => {
  const classes = instance.getClass()
  const field = classes.getDeclaredField(key)
  field.setAccessible(true)
  field.set(instance, value)
}

const invoke = (instance, index, ...value) => {
  const classes = instance.getClass()
  const ms = classes.getDeclaredMethods()
  ms[index].setAccessible(true)
  ms[index].invoke(instance, value)
}

const getTime = (date) => {
  const regex = /^(\w+)\s(\w+)\s(\d{1,2})\s(\d{2}:\d{2}:\d{2})\sGMT([+\-]\d{2}):(\d{2})\s(\d{4})$/
  const months = {
    'Jan': 0, 'Feb': 1, 'Mar': 2, 'Apr': 3, 'May': 4, 'Jun': 5,
    'Jul': 6, 'Aug': 7, 'Sep': 8, 'Oct': 9, 'Nov': 10, 'Dec': 11
  }
  const match = date.toString().match(regex)
  if (!match) return undefined
  const month = months[match[2]]
  const day = parseInt(match[3], 10)
  const year = parseInt(match[7], 10)
  const [hours, minutes, seconds] = match[4].split(':').map(num => parseInt(num, 10))
  const offsetHour = parseInt(match[5], 10)
  const offsetMinute = parseInt(match[6], 10)
  const totalOffsetMinutes = offsetHour * 60 + offsetMinute
  const dateUTC = Date.UTC(year, month, day, hours, minutes, seconds)
  const timeStamp = dateUTC - totalOffsetMinutes * 60 * 1000
  return timeStamp
}

const dispatched = (value) => {
  if (value == null) {
    return undefined
  }
  if (isWrapType(value)) {
    return value.toString()
  } else {
    const name = getInstName(value)
    if (name === 'Date') {
      const st = value.toString()
      return getTime(st)
    } else if (name === 'User') {
      return serialize(value)
    } else {
      return undefined
    }
  }
}

const serialize = (instance) => {
  const result = {}
  const fields = getFields(instance)
  fields.forEach((field) => {
    if (!isStatic(field)) {
      const name = field.getName()
      const value = getValue(instance, field)
      if (value !== null) {
        result[name] = dispatched(value)
      }
    }
  })
  return result
}

const newLinkedHashMap = (args) => {
  const LinkedHashMap = Java.use(PKGS.LINKED_HASH_MAP)
  const instance = LinkedHashMap.$new()
  for (const key in args) {
    instance.put(key, args[key])
  }
  return instance
}

const makePostRequest = (url, params) => {
  console.log(url, params)
  const body = newLinkedHashMap(params)
  const HttpClient = Java.use(PKGS.HTTP_CLIENT)
  const instance = HttpClient.$new()
  const response = instance.doPost(url, body)
  return JSON.parse(response).data
}

const setup = (handle) => {
  let value
  Java.perform(() => {
    value = handle()
  })
  return value
}

const request = (api, body) => {
  return setup(() => {
    return makePostRequest(api, body)
  })
}

const nearly = () => {
  return request(API.NEARLY, {
    online_time: '1',
    lat: '28.196451',
    lng: '112.977301',
    age_min: '18',
    age_max: '100',
    sex: 'F'
  })
}

const news = () => {
  return request(API.NEWS, {
    lat: '28.196451',
    lng: '112.977301',
    count: '20'
  })
}

const timeline = (id) => {
  return request(API.TIMELINE, {
    remoteid: id
  })
}

const profile = (id) => {
  return request(API.PROFILE, {
    remoteid: id
  })
}

const comments = (id) => {
  return request(API.COMMENTS, {
    feedid: id,
    sort_type: 'early',
    index: '0',
    count: '50'
  })
}

const image = (id) => {
  return setup(() => {
    const MoliveKit = Java.use(PKGS.IMAGE_UTIL)
    return MoliveKit.e(id)
  })
}

const getUser = (id) => {
  const UserService = Java.use(PKGS.USER_SERVICE)
  return UserService.getInstance().get(id)
}

const post = (msg) => {
  return setup(() => {
    const { momoid, remoteId } = msg
    const n =  Math.floor(Math.random() * 90000) + 10000
    const content = n + '-该消息由测试组发送'
    console.log(content)
    const MessageSender = Java.use(PKGS.MSG_SENDER)
    const MessageHelper = Java.use(PKGS.MSG_HELPER)
    const owner = getUser(momoid)
    const remote = getUser(remoteId)
    const helper = MessageHelper.a()
    const message = helper.a(content, remote, null, 1)
    setValue(message, 'owner', owner)
    const sender = MessageSender.$new()
    invoke(sender, 0, message)
    return serialize(message)
  })
}

const receive = () => {
  return setup(() => {
    const ChatMsgMemCache = Java.use(PKGS.MSG_CACHE)
    const Message = ChatMsgMemCache.a.overload(PKGS.MSG_BEAN)
    Message.implementation = function(message) {
      send(serialize(message))
      return this.a(message)
    }
  })
}

rpc.exports = {
  post,
  receive,
  image,
  nearly,
  news,
  timeline,
  profile,
  comments
}

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
  MSG_SERVICE: 'com.immomo.momo.service.sessions.f',
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

const dispatched = (value) => {
  if (isWrapType(value)) {
    return value.toString()
  } else {
    const name = getInstName(value)
    if (name === 'Date') {
      console.log(value)
      return value
    } else {
      return serialize(value)
    }
  }
}

const serialize = (instance) => {
  const result = {}
  const name = getInstName(instance)
  const fields = getFields(instance)
  fields.forEach((field) => {
    if (!isStatic(field)) {
      const value = getValue(instance, field)
      if (value !== null) {
        const dv = dispatched(value)
        if (dv !== undefined) {
          result[name] = dv
        }
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

const comments = (params) => {
  return request(API.COMMENTS, {
    remoteid: id,
    sort_type: 'early',
    index: params?.index,
    count: params?.count
  })
}

const image = (id) => {
  return setup(() => {
    const MoliveKit = Java.use(PKGS.IMAGE_UTIL)
    return MoliveKit.e(id)
  })
}

rpc.exports = {
  image,
  nearly,
  news,
  timeline,
  profile,
  comments
}

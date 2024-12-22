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
  TIMELINE: `${BASE_URL}v1/feed/user/timeline`,
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

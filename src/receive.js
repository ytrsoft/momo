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

function fmt(date) {
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

function getFieldValue(field, instance) {
  const ref = {}
  try {
    ref.value = field.get(instance)
  } catch (e) {
    ref.value = undefined
  }
  return ref.value
}

function handleFieldType(value) {
  const clz = value.getClass()
  const className = clz.getSimpleName()

  if (className === 'Date') {
    return fmt(value)
  }

  if (className === 'User') {
    return toJSON(value)
  }

  if (basics.includes(className)) {
    return value.toString()
  }

  return undefined
}

function toJSON(instance) {
  const obj = {}
  const Modifier = Java.use('java.lang.reflect.Modifier')
  const clazz = instance.getClass()
  const fields = clazz.getDeclaredFields()

  fields.forEach((field) => {
    const name = field.getName()
    const modifiers = field.getModifiers()

    if (!Modifier.isStatic(modifiers)) {
      field.setAccessible(true)
      const value = getFieldValue(field, instance)

      if (value !== null) {
        const fieldValue = handleFieldType(value)
        if (fieldValue !== undefined) {
          obj[name] = fieldValue
        }
      }
    }
  })

  return obj
}

Java.perform(() => {
  const MessageServiceHelper = Java.use('com.immomo.momo.service.sessions.f')
  const Message = MessageServiceHelper.e.overload('com.immomo.momo.service.bean.Message')

  Message.implementation = function(message) {
    console.log('[MSG]', JSON.stringify(toJSON(message)))
    this.a(message)
  }
})

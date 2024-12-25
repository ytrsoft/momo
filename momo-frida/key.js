const getFields = (instance) => {
  const classes = instance.getClass()
  return classes.getDeclaredFields()
}

const getValue = (instance, name) => {
  const ref = {}
  const field = getFields(instance).find((f) => {
    return f.getName() === name
  })
  try {
    field.setAccessible(true)
    ref.value = field.get(instance)
  } catch (e) {
    ref.value = undefined
  }
  return ref.value
}

const invoke = (instance, index, ...value) => {
  const classes = instance.getClass()
  const ms = classes.getDeclaredMethods()
  ms[index].setAccessible(true)
  return ms[index].invoke(instance, value)
}

Java.perform(() => {
  const APIKeyholderClass = Java.use('com.immomo.momoenc.d')
  const APIKeyholder = APIKeyholderClass.a()
  APIKeyholder.b.overload().implementation = function() {
    const i = getValue(this, 'i')
    const innerKeyMap = getValue(this, 'a')
    const APIKeyholderInner = invoke(innerKeyMap, 11, i)
    const key = getValue(APIKeyholderInner, 'b')
    //  
    console.log('key', key)
    return this.b()
  }
})

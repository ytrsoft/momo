
const equalLine = (s, char = '/') => {
  return Array(s).fill(char).join('')
}

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

Java.perform(() => {
  const ApiSecurity = Java.use('com.immomo.momoenc.e')
  ApiSecurity.f.implementation = function() {
    this.f()
    console.log(equalLine(100))
    const d = getValue(this, 'd')
    console.log('encKey', d)
    console.log(equalLine(100))
  }
  ApiSecurity.c.implementation = function() {
    console.log(equalLine(100))
    const i = getValue(this, 'i')
    const k = getValue(this, 'k')
    const m = getValue(this, 'm')
    const j = getValue(this, 'j')
    console.log('url', m)
    console.log(equalLine(100, '='))
    console.log('header', k)
    console.log(equalLine(100, '='))
    console.log('args', j)
    console.log(equalLine(100))
    console.log('i', i)
    console.log(equalLine(100))
    this.c()
  }
})

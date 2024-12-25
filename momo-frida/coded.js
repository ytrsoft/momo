const setup = () => {
  const Coded = Java.use('com.immomo.momo.util.jni.Coded')
  const sign = Coded.aesEncode.overload('[B', '[B')
  const computeOutputLength = Coded.computeOutputLength.overload('int', 'int')
  const aesEncode = Coded.aesEncode.overload('[B', 'int', '[B', 'int', '[B')
  computeOutputLength.implementation = function(...args) {
    console.log('computeOutputLength', args)
    return this.call(...args)
  }
  aesEncode.implementation = function(...args) {
    console.log('aesEncode', args)
    return this.call(...args)
  }
  sign.implementation = function(...args) {
    console.log('sign', args)
    return this.call(...args)
  }
}

Java.perform(setup)

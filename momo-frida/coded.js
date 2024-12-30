const equalLine = (s) => {
  return Array(s).fill('=').join('')
}

const bytes_text = (bytes) => {
  return JSON.stringify(bytes)
}

function printMap(map) {
  const JSONObject = Java.use('org.json.JSONObject')
  const json = JSONObject.$new(map)
  console.log(json)
}

const setup = () => {
  const Coded = Java.use('com.immomo.momo.util.jni.Coded')
  const sign = Coded.sign.overload('[B', '[B')
  const sdbyecbu37x = Coded.sdbyecbu37x.overloads[0]
  const computeOutputLength = Coded.computeOutputLength.overload('int', 'int')
  const aesEncode = Coded.aesEncode.overload('[B', 'int', '[B', 'int', '[B')
  // computeOutputLength.implementation = function(i, i2) {
  //   const result = this.computeOutputLength(i, i2)
  //   console.log('computeOutputLength: ', equalLine(100))
  //   console.log('第1个参数：', i)
  //   console.log('第2个参数：', i2)
  //   console.log('返回结果：', result)
  //   console.log(equalLine(100))
  //   return result
  // }
  // aesEncode.implementation = function(bArr, i, bArr2, i2, bArr3) {
  //   const result = this.aesEncode(bArr, i, bArr2, i2, bArr3)
  //   console.log('[aesEncode方法被调用]')
  //   console.log('第1个参数：', bytes_text(bArr))
  //   console.log('第2个参数：', i)
  //   console.log('第3个参数：', bytes_text(bArr2))
  //   console.log('第4个参数：', i2)
  //   console.log('第5个参数：', bytes_text(bArr3))
  //   console.log('返回结果：', result)
  //   console.log(equalLine(100))
  //   console.log(    )
  //   return result
  // }
  /*aesEncode.implementation = function(bArr, i, bArr2, i2, bArr3) {
    const result = this.aesEncode(bArr, i, bArr2, i2, bArr3)
    console.log('aesEncode:', equalLine(200))
    console.log('(1)', bytes_text(bArr))
    console.log('(2)', bytes_text(bArr3))
    console.log(equalLine(200))
    return result
  }*/
  sign.implementation = function(bArr, bArr2) {
     const result = this.sign(bArr, bArr2)
     const String = Java.use('java.lang.String')
     console.log('sign:', equalLine(200))
     console.log('(1)', bytes_text(bArr))
     console.log('(2)', String.$new(bArr2))
     console.log(equalLine(200))
     return result
   }
  // sdbyecbu37x.implementation = function(...args) {
  //   const result = this.sdbyecbu37x(...args)
  //   console.log('[sign方法被调用]')
  //   console.log('第1个参数：', bytes_text(args[0]))
  //   console.log('第2个参数：', bytes_text(args[1]))
  //   console.log('第3个参数：', bytes_text(args[2]))
  //   console.log('第4个参数：', bytes_text(args[3]))
  //   console.log('返回结果：', result)
  //   console.log(equalLine(100))
  //   return result
  // }
  /* const ApiSecurity = Java.use('com.immomo.momoenc.e')
  ApiSecurity.a.overload('[B', 'java.util.Map', 'java.lang.String').implementation=function(...args) {
    console.log(bytes_text(args[0]))
    printMap(args[1])
    return this.a(...args)
  }*/
}

Java.perform(setup)

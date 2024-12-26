const equalLine = (s) => {
  return Array(s).fill('=').join('')
}

const bytes_text = (bytes) => {
  return JSON.stringify(bytes)
}

const setup = () => {
  const Coded = Java.use('com.immomo.momo.util.jni.Coded')
  const sign = Coded.sign.overload('[B', '[B')
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
  //   console.log('aesEncode: ', equalLine(100))
  //   console.log('第1个参数：', bytes_text(bArr))
  //   console.log('第2个参数：', i)
  //   console.log('第3个参数：', bytes_text(bArr2))
  //   console.log('第4个参数：', i2)
  //   console.log('第5个参数：', bytes_text(bArr3))
  //   console.log('返回结果：', result)
  //   console.log(equalLine(100))
  //   return result
  // }
  sign.implementation = function(bArr, bArr2) {
    const result = this.sign(bArr, bArr2)
    console.log('sign: ', equalLine(100))
    console.log('第1个参数：', bytes_text(bArr))
    console.log('第2个参数：', bytes_text(bArr2))
    console.log('返回结果：', result)
    console.log(equalLine(100))
    return result
  }
}

Java.perform(setup)

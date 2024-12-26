const equalLine = (s) => {
  return Array(s).fill('=').join('')
}

const bytes_text = (bytes) => {
  return JSON.stringify(bytes)
}

Java.perform(() => {
  const Base64 = Java.use('com.immomo.mmutil.a')
  Base64.a.implementation = function(bytes) {
    console.log(equalLine(100))
    console.log('加密前:', bytes_text(bytes))
    const cipher = this.a(bytes)
    console.log('加密后:', cipher)
    console.log(equalLine(100))
    return this.a(bytes)
  }
  Base64.b.implementation = function(bytes) {
    console.log(equalLine(100))
    console.log('解密前:', bytes_text(bytes))
    const plain = this.b(bytes)
    console.log('解密后:', bytes_text(plain))
    console.log(equalLine(100))
    return this.a(bytes)  
  }
})

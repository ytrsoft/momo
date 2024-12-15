Java.perform(function () {
  Java.use('okhttp3.Request')
  Java.use('okhttp3.Response')
  Java.use('okhttp3.Interceptor$Chain')
  const Buffer = Java.use('okio.Buffer')
  const intercept = Java.use('com.immomo.d.a').intercept
  intercept.implementation = function (chain) {
      const response = chain.proceed(chain.request())
      const request = response.request()
      var url = request.url().toString()
      var method = request.method().toUpperCase()
      const body = request.body()
      const buffer = Buffer.$new()
      body.writeTo(buffer)
      const bs = buffer.readUtf8()
      console.log(`[${method}] - ${url}`)
      console.log(bs)

      return response
  }
})

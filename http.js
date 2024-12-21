
// function logPostRequest(request) {
//   const Buffer = Java.use('okio.Buffer')
//   const buffer = Buffer.$new()
//   request.body().writeTo(buffer)
//   const body = buffer.readUtf8()
//   console.log(fill(100))
//   console.log(body)
//   console.log(fill(100))
// }

// function logPostResponse(response) {
//   const Buffer = Java.use('okio.Buffer')
//   const buffer = Buffer.$new()
//   const body = response.body()
//   if (body != null) {
//     body.source().readAll(buffer)
//     const responseBody = buffer.readUtf8()
//     console.log(fill(100))
//     console.log(responseBody)
//     console.log(fill(100))
//   }
// }

// function logTrigger(response) {
//   const request = response.request()
//   const url = request.url().toString()
//   const method = request.method().toUpperCase()
//   console.log(`[${method}] - ${url}`)
//   if (method === 'POST') {
//     logPostRequest(request)
//     logPostResponse(response)
//   }
// }

const equalLine = (s) => {
  return Array(s).fill('=').join('')
}

Java.perform(() => {
  // Java.use('okhttp3.Request')
  // Java.use('okhttp3.Response')
  // Java.use('okhttp3.Interceptor$Chain')
  // const intercept = Java.use('com.immomo.d.a').intercept
  // intercept.implementation = function (chain) {
  //   const request = chain.request()
  //   const response = chain.proceed(request)
  //   logTrigger(response)
  //   return response
  // }
})

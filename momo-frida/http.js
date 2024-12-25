const equalLine = (s) => {
  return Array(s).fill('=').join('')
}

const parseJSON = (input) => {
  if (typeof input === 'string') {
    try {
      input = JSON.parse(input)
    } catch (e) {
        return input
    }
  }
  if (typeof input === 'object' && input !== null) {
    for (let key in input) {
      if (input.hasOwnProperty(key)) {
        input[key] = parseJSON(input[key])
      }
    }
  }
  return input
}

const logData = (data) => {
  console.log(equalLine(100))
  console.log(JSON.stringify(data, null, 2))
  console.log(equalLine(100))
}

const parseRequestBody = (body) => {
  const result = {}
  body = decodeURIComponent(body)
  const pairs = body.split('&')
  pairs.forEach((pair) => {
    const [key, value] = pair.split('=')
    result[key] = value
  })
  return result
}

const logRequest = (request) => {
  const Buffer = Java.use('okio.Buffer')
  const buffer = Buffer.$new()
  request.body().writeTo(buffer)
  const body = buffer.readUtf8()
  const params = parseRequestBody(body)
  logData(params)
}

const logResponse = (response) => {
  const Buffer = Java.use('okio.Buffer')
  const buffer = Buffer.$new()
  const body = response.body()
  if (body != null) {
    body.source().readAll(buffer)
    const responseBody = buffer.readUtf8()
    const data = parseJSON(responseBody)
    logData(data)
  }
}

const logHeader = (request) => {
  const entrys = {}
  const headers = request.headers()
  const names = headers.names().toArray()
  for (let i = 0; i < names.length; i++) {
    const key = names[i]
    const value = headers.get(key)
    entrys[key] = value
  }
  logData(entrys)
}

Java.perform(() => {
  const EncDecInterceptor = Java.use('com.immomo.d.a')
  EncDecInterceptor.intercept.implementation = function(chain) {
    const request = chain.request()
    const url = request.url().toString()
    const method = request.method().toUpperCase()
    console.log(`[${method}] - ${url}`)
    // logHeader(request)
    logRequest(request)
    const response = chain.proceed(request)
    // logResponse(response)
    return response
  }
})

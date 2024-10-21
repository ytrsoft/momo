Java.perform(function () {
    var InterceptorClass = Java.use("com.immomo.d.a")
    var RequestBodyClass = Java.use('okhttp3.RequestBody')
    var BufferClass = Java.use('okio.Buffer')
    var ExceptionClass = Java.use("java.lang.Exception")  

    var intercept = InterceptorClass.intercept.overload('okhttp3.Interceptor$Chain')

    intercept.implementation = function (chain) {
        var request = chain.request()

        var url = request.url().toString()
        var method = request.method().toUpperCase()

        console.log(`[${method}] - ${url}`)

        // 获取请求头
        var headers = {}
        var hs = request.headers()
        for (var i = 0; i < hs.size(); i++) {
            headers[hs.name(i)] = hs.value(i)
        }
        console.log('请求头:')
        console.log(JSON.stringify(headers, null, 2))

        // 获取请求体
        var requestBody = request.body()
        if (requestBody) {
            var buffer = BufferClass.$new()
            requestBody.writeTo(buffer)
            var requestBodyString = buffer.readUtf8()
            console.log('请求体:')
            console.log(requestBodyString)
        } else {
            console.log('请求体为空')
        }

        // 继续处理请求并获取响应
        var response = chain.proceed(request)

        // 打印响应体
        var responseBody = response.body()
        var contentType = responseBody.contentType()

        if (contentType !== null && contentType.toString().includes('application/json')) {
            var responseJSON = deepParse(JSON.parse(responseBody.string()))
            console.log('响应数据:')
            console.log(JSON.stringify(responseJSON, null, 2))
        }

        // 获取并输出当前的调用栈
        var exception = ExceptionClass.$new()  // 构建一个新的 Exception 对象
        var stackTrace = exception.getStackTrace()  // 获取调用栈
        console.log('调用栈:')
        for (var i = 0; i < stackTrace.length; i++) {
            console.log(stackTrace[i].toString())
        }

        return response
    }

    function deepParse(obj) {
        if (Array.isArray(obj)) {
            return obj.map(deepParse)
        } else if (typeof obj === 'object' && obj !== null) {
            Object.keys(obj).forEach(function (key) {
                var value = obj[key]
                if (typeof value === 'string') {
                    try {
                        obj[key] = deepParse(JSON.parse(value))
                    } catch (e) { }
                } else {
                    obj[key] = deepParse(value)
                }
            })
        }
        return obj
    }
})

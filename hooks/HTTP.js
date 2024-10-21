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



Java.perform(function () {
    Java.use('okhttp3.Request')
    Java.use('okhttp3.Response')
    Java.use('okhttp3.Interceptor$Chain')

    var intercept = Java.use('com.immomo.d.a').intercept

    intercept.implementation = function (chain) {
        var request = chain.request()

        var url = request.url().toString()
        var method = request.method().toUpperCase()
        console.log(`[${method}] - ${url}`)

        var headers = {}
        var hs = request.headers()
        for (var i = 0; i < hs.size(); i++) {
            headers[hs.name(i)] = hs.value(i)
        }
        console.log('请求头')
        console.log(JSON.stringify(headers, null, 2))

        var response = chain.proceed(request)
        var responseBody = response.body()
        var contentType = responseBody.contentType()

        if (contentType !== null && contentType.toString().includes('application/json')) {
            var responseJSON = deepParse(JSON.parse(responseBody.string()))
            console.log('请求数据')
            console.log(JSON.stringify(responseJSON, null, 2))
        }

        return response
    }
})

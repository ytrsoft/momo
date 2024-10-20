Java.perform(function () {
  // Hook 拦截器类 com.immomo.d.a
  var InterceptorClass = Java.use('com.immomo.d.a');
  var ResponseBody = Java.use('okhttp3.ResponseBody'); // 引入 ResponseBody 类
  var StringClass = Java.use("java.lang.String"); // 引入 String 类

  // Hook intercept 方法
  InterceptorClass.intercept.implementation = function (chain) {
      var request = chain.request();
      var url = request.url().toString();
      console.log(url);

      var method = request.method();
    //  console.log("[*] 请求方法: " + method);

      if (method === "POST" && request.body() !== null) {
          var Buffer = Java.use("okio.Buffer");
          var buffer = Buffer.$new();
          request.body().writeTo(buffer);
          var bodyString = buffer.readUtf8();

          var params = {};
          bodyString.split('&').forEach(function (param) {
              var keyValue = param.split('=');
              var key = decodeURIComponent(keyValue[0]);
              var value = decodeURIComponent(keyValue[1]);
              params[key] = value;
          });

         // console.log("[*] 请求体参数: " + JSON.stringify(params, null, 2));
      }

      var response = this.intercept(chain);
      var responseBody = response.body();

      if (responseBody !== null) {
          var contentType = responseBody.contentType();
          var responseBytes = responseBody.bytes(); // 使用 bytes() 读取响应体

          var responseString = StringClass.$new(responseBytes); // 将字节数组转换为字符串
          console.log(JSON.stringify(JSON.parse(responseString), null, 2));

          var newResponseBody = ResponseBody.create(contentType, responseBytes); // 创建新的 ResponseBody
          return response.newBuilder().body(newResponseBody).build(); // 使用新的 ResponseBody 替换旧的
      }

      return response;
  };
});

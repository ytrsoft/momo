Java.perform(function () {
    // 获取目标类 com.immomo.momoenc.e
    var EClass = Java.use("com.immomo.momoenc.e");

    // Hook c() 方法
    EClass.c.implementation = function () {
        // 打印调用栈，显示从哪里调用
        console.log("[*] Hooked c() 方法，调用栈如下:");
        console.log(Java.use("android.util.Log").getStackTraceString(Java.use("java.lang.Exception").$new()));

        try {
            // Hook JSONObject.toString() 方法，捕获 jSONObject2
            var JSONObject = Java.use('org.json.JSONObject');
            JSONObject.toString.overload().implementation = function () {
                var result = this.toString();
                console.log("[*] 捕获到的 jSONObject2: " + result);
                return result;
            };

            // 调用原始的 c() 方法
            this.c();

            // 打印调用栈，显示调用后执行到哪里
            console.log("[*] 执行完 c() 方法，调用栈如下:");
            console.log(Java.use("android.util.Log").getStackTraceString(Java.use("java.lang.Exception").$new()));

        } catch (err) {
            console.log("[*] 执行 c() 方法时出错: " + err);
        }
    };
});

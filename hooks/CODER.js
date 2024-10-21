Java.perform(function () {
  // Hook Coded class
  var Coded = Java.use('com.immomo.momo.util.jni.Coded');
  var StringClass = Java.use('java.lang.String'); // 用于将 byte[] 转换为字符串

  // Hook aesEncode method
  Coded.aesEncode.overload('[B', 'int', '[B', 'int', '[B').implementation = function (input, inputLength, key, keyLength, output) {
      console.log('aesEncode called');
      console.log('Input (as string): ' + byteArrayToString(input));
      console.log('Input Length: ' + inputLength);
      console.log('Key (as string): ' + byteArrayToString(key));
      console.log('Key Length: ' + keyLength);

      var result = this.aesEncode(input, inputLength, key, keyLength, output);

      console.log('Output (as string): ' + byteArrayToString(output));
      console.log('Result Length: ' + result);

      return result;
  };

  // Hook aesDecode method
  Coded.aesDecode.overload('[B', 'int', '[B', 'int', '[B').implementation = function (input, inputLength, key, keyLength, output) {
      console.log('aesDecode called');
      console.log('Input (as string): ' + byteArrayToString(input));
      console.log('Input Length: ' + inputLength);
      console.log('Key (as string): ' + byteArrayToString(key));
      console.log('Key Length: ' + keyLength);

      var result = this.aesDecode(input, inputLength, key, keyLength, output);

      console.log('Output (as string): ' + byteArrayToString(output));
      console.log('Result Length: ' + result);

      return result;
  };

  // Helper function to convert byte array to string
  function byteArrayToString(byteArray) {
      try {
          return StringClass.$new(byteArray); // 使用 Java String 将 byte[] 转换为字符串
      } catch (e) {
          console.log('Error converting byteArray to string: ' + e);
          return '';
      }
  }
});

MOMO
=================

#### Frida安装

```bash
 pip install frida-tools
```

#### Frida命令

```bash
  # 查看版本
  frida --version
  # 查看进程包名
  frida-ps -Ua
  # 启动脚本 进程ID 脚本文件
  frida -U -p 6033 -l http.js
```

#### Shell命令

```bash
  # 查看系统架构
  getprop ro.product.cpu.abi
```

#### [Frida Server](https://github.com/frida/frida/releases)

> frida-server-16.5.9-android-x86_64.xz
  根据架构下载对应版本 解压后重命名sudo

#### 连接模拟器(mumu)
```bash
  adb connect 127.0.0.1:7555
```

#### 注入sudo

```bash
  # 获取超级用户
  su -
  # 最高权限
  chmod 777 sudo
  # 启动
  ./sudo
```
#### 其他命令

```bash
  # 执行脚本
  python apply.py http
```

#### 日志命令

```bash
  # 执行脚本
  adb logcat --pid=5554
```

#### 发送参数

```json
{
   "momoid": "976807129",
   "remoteId": "994491371",
   "content": "该消息由测试组发送"
}
```

> **注意**: apk放在test/resources下

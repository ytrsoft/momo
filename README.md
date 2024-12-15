# momo
> 1、下载安装python环境

> 2、安装Frida
#### 安装
```bash
 pip install frida-tools
```
#### 查看版本
```bash
frida --version
```
> 2、查看处理器架构信息
```bash
getprop ro.product.cpu.abi
```

> 3、安装 [Frida Server](https://github.com/frida/frida/releases)

    1）解压 frida-server-16.5.6-android-x86_64.xz
    2）adb push frida-server /data/local/tmp/
    3) 设置权限chmod 777 frida-server-16.5.6-android-x86_64 最高权限
    4) 开启Root安装MT管理器允许Root权限
    5) ./frida-server-16.5.6-android-x86_64 启动监听

> 查找进程ID
```bash
frida-ps -U
```

> 注入脚本
```bash
frida -U -p 4495 -l http.js # 4495 进程ID 脚本文件
```
> 连接MuMu模拟器

```bash
adb shell
```

> 连接设备

```bash
  adb -s 127.0.0.1:7555 shell
```

> 进入Root权限

``bash
su - # whoami
``
> 连接mumu模拟器
```bash
adb connect 127.0.0.1:7555
```


import frida
from apply import get_pid

def readScript(name):
    with open(f"{name}.js", 'r', encoding='utf8') as f:
        return f.read()

def makeRPC():
    pid = int(get_pid())
    device = frida.get_usb_device()
    session = device.attach(pid)
    code = readScript('rpc')
    script = session.create_script(code)
    script.load()
    return script

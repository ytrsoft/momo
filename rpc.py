import frida
from apply import get_pid

def read_script(name):
    with open(f"{name}.js", 'r', encoding='utf8') as f:
        return f.read()

def rpc():
    pid = int(get_pid())
    device = frida.get_usb_device()
    session = device.attach(pid)
    code = read_script('rpc')
    script = session.create_script(code)
    script.load()
    return script

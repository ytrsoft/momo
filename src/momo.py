import frida
from apply import get_pid

class Momo:

    def __init__(self):
        pid = int(get_pid())
        self.device = frida.get_usb_device()
        self.session = self.device.attach(pid)
        self.scripts = {}

    def load(self, file_name):
        if file_name not in self.scripts:
            with open(f"{file_name}.js", 'r', encoding='utf8') as f:
                self.scripts[file_name] = f.read()
        script = self.session.create_script(self.scripts[file_name])
        script.load()
        return script

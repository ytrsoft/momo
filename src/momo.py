import frida
import sys

class Momo:
    def __init__(self, process_id):
        self.device = frida.get_usb_device()
        self.session = self.device.attach(process_id)
        self.scripts = {}

    def load_js_code(self, script_name):
        try:
            with open(f"{script_name}.js", "r") as file:
                return file.read()
        except FileNotFoundError:
            raise ValueError(f"Script file '{script_name}.js' not found.")
        except Exception as e:
            raise RuntimeError(f"Error loading script '{script_name}.js': {str(e)}")

    def load_script(self, script_name, on_message_callback):
        if script_name in self.scripts:
            print(f"Script '{script_name}' already loaded.")
            script = self.scripts[script_name]
        else:
            js_code = self.load_js_code(script_name)
            script = self.session.create_script(js_code)
            script.on('message', on_message_callback)
            script.load()
            self.scripts[script_name] = script

        class ScriptWrapper:
            def __init__(self, script):
                self.script = script

            def invoke(self, method_name, *args):
                if hasattr(self.script.exports_sync, method_name):
                    method = getattr(self.script.exports_sync, method_name)
                    try:
                        method(*args)
                    except Exception as e:
                        print(f"Error invoking method '{method_name}': {e}")
                else:
                    print(f"Method '{method_name}' not found in script.")

        return ScriptWrapper(script)

    def detach(self):
        self.session.detach()

    def list_scripts(self):
        return list(self.scripts.keys())

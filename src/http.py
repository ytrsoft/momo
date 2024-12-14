from momo import Momo

def on_message(message, data):
    print("[MESSAGE]", message)

if __name__ == '__main__':
    momo = Momo(14924)
    profile = momo.load_script('profile', on_message)
    profile.invoke('setup', '506867391')

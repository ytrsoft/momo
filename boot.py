from flask import Flask
from flask_cors import CORS
from flask_socketio import SocketIO
from rpc import makeRPC

app = Flask(__name__)
CORS(app)

socket = SocketIO(app)

momo = makeRPC()

@socket.on('connect')
def ws_connect():
    print('Soccket开启连接')

@socket.on('disconnect')
def ws_disconnect():
    print('Soccket断开连接')

if __name__ == '__main__':
    app.run(
        host='localhost',
        port=8080,
        threaded=True,
        debug=True
    )

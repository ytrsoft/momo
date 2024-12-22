from flask import Flask, request, jsonify, Response
from flask_cors import CORS
from flask_socketio import SocketIO
from rpc import makeRPC

import requests

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

@app.route('/image/<id>', methods=['GET'])
def image(id):
    result = momo.exports.image(id)
    image = requests.get(result, stream=True)
    return Response(
        image.content,
        content_type=image.headers['Content-Type']
    )

@app.route('/nearly', methods=['GET'])
def nearly():
    body = request.get_json()
    result = momo.exports.nearby(body)
    return jsonify(result)

if __name__ == '__main__':
    app.run(
        host='localhost',
        port=8080,
        threaded=True,
        debug=True
    )

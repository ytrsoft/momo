from flask import Flask, jsonify, Response
from flask_cors import CORS
from flask_socketio import SocketIO
from rpc import createRpc

import json
import requests
import socketio

momo = createRpc()

app = Flask(__name__)
CORS(app)

socket = SocketIO(app)

@app.route('/profile/<id>', methods=['GET'])
def profile(id):
    result = momo.exports.profile(id)
    return jsonify(result)

@app.route('/timeline/<id>', methods=['GET'])
def timeline(id):
    result = momo.exports.timeline(id)
    return jsonify(result)

@app.route('/comments/<id>', methods=['GET'])
def comments(id):
    result = momo.exports.comments(id)
    return jsonify(result)

@app.route('/news', methods=['GET'])
def news():
    result = momo.exports.news()
    return jsonify(result)

@app.route('/nearly', methods=['GET'])
def nearly():
    result = momo.exports.nearly()
    return jsonify(result)

@app.route('/image/<id>', methods=['GET'])
def image(id):
    result = momo.exports.image(id)
    image = requests.get(result, stream=True)
    return Response(image.content, content_type=image.headers['Content-Type'])

@socketio.on('message')
def ws_message(msg):
  data = json.loads(msg)
  momoid = data.get('momoid')
  remoteId = data.get('remoteId')
  content = data.get('content')
  result = momo.exports.post({
      'momoid': momoid,
      'remoteId': remoteId,
      'content': content
  })
  socketio.send(result)

@socketio.on('connect')
def ws_connect():
    print('Soccket开启连接')

@socketio.on('disconnect')
def ws_disconnect():
    print('Soccket断开连接')

if __name__ == '__main__':
    app.run(host='localhost', port=8080, threaded=True, debug=True)

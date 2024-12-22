from flask import Flask, request, jsonify, Response
from flask_cors import CORS
from flask_socketio import SocketIO
from rpc import makeRPC

import requests

app = Flask(__name__)
CORS(app)

momo = makeRPC()

@app.route('/profile/<id>', methods=['GET'])
def profile(id):
    result = momo.exports.profile(id)
    return jsonify(result)

@app.route('/timeline/<id>', methods=['GET'])
def timeline(id):
    result = momo.exports.timeline(id)
    return jsonify(result)

@app.route('/nearly', methods=['GET'])
def nearly():
    result = momo.exports.nearly()
    return jsonify(result)

@app.route('/news', methods=['GET'])
def news():
    result = momo.exports.news()
    return jsonify(result)

@app.route('/image/<id>', methods=['GET'])
def image(id):
    result = momo.exports.image(id)
    image = requests.get(result, stream=True)
    return Response(image.content, content_type=image.headers['Content-Type'])

if __name__ == '__main__':
    app.run(host='localhost', port=8080, threaded=True)

from flask import Flask, jsonify, request, Response, render_template
from flask_cors import CORS
from momo import Momo
import requests

momo = Momo(7513)

app = Flask(__name__)
CORS(app)

@app.route('/', methods=['GET'])
def nearly_view():
    script = momo.load('nearly')
    result = script.exports.nearly()
    return render_template('index.html', data=result)

@app.route('/profile/<id>', methods=['GET'])
def profile(id):
    script = momo.load('profile')
    result = script.exports.profile(id)
    return jsonify(result)

@app.route('/nearly', methods=['GET'])
def nearly():
    script = momo.load('nearly')
    result = script.exports.nearly()
    return jsonify(result)

@app.route('/news', methods=['GET'])
def news():
    script = momo.load('news')
    result = script.exports.news()
    return jsonify(result)

@app.route('/timeline/<id>', methods=['GET'])
def timline(id):
    script = momo.load('timeline')
    result = script.exports.timeline(id)
    return jsonify(result)

@app.route('/comments/<id>', methods=['GET'])
def comments(id):
    script = momo.load('comments')
    result = script.exports.comments(id)
    return jsonify(result)

@app.route('/post', methods=['POST'])
def post():
    data = request.get_json()
    if not all(key in data for key in ['momoid', 'remoteId', 'content']):
        return jsonify({'error': '参数错误'}), 400
    msg = {
        'momoid': data['momoid'],
        'remoteId': data['remoteId'],
        'content': data['content']
    }
    script = momo.load('post')
    result = script.exports.post(msg)
    return jsonify(result)

@app.route('/image/<id>', methods=['GET'])
def image(id):
    script = momo.load('image')
    result = script.exports.image(id)
    image = requests.get(result, stream=True)
    return Response(image.content, content_type=image.headers['Content-Type'])

if __name__ == '__main__':
    app.run(host='localhost', port=8080, threaded=True, debug=True)

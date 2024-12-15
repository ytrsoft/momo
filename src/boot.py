from flask import Flask, jsonify, request
from momo import Momo

momo = Momo(9388)

app = Flask(__name__)

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

if __name__ == '__main__':
    app.run(host='localhost', port=8080, threaded=True)

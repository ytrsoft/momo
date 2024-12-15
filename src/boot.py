from flask import Flask, jsonify, request
from momo import Momo

momo = Momo(14924)

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

if __name__ == '__main__':
    app.run(host='localhost', port=8080, threaded=True)

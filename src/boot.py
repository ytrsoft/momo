from flask import Flask, jsonify, request
from momo import Momo

momo = Momo(14924)

app = Flask(__name__)

@app.route('/profile/<id>', methods=['GET'])
def send_message(id):
    script = momo.load('profile')
    result = script.exports.setup(id)
    return jsonify(result)

if __name__ == '__main__':
    app.run(host='localhost', port=8080, threaded=True)

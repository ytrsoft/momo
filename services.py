import requests

def message_to(message):
    url = 'http://localhost:8081/post'
    body = {
        'momo_id': message.momo_id,
        'remote_id': message.remote_id,
        'content': message.content
    }

    response = requests.post(url, json=body)
    if response.status_code == 200:
        print(' = >', response.json())
    else:
        print(f'Request failed: {response.status_code}')

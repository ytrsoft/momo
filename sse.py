import time
import threading
from rpc import rpc
from gpt import Message, MomoGPT
from mq import MQueueManager
from fastapi import Request
from fastapi.responses import HTMLResponse
from fastapi_sse import sse_handler
from fastapi.templating import Jinja2Templates
from utils import SSEMessage, fast_api, load_image, load_template, message_to

templates = Jinja2Templates(directory='templates')

mq = MQueueManager(1024)

app = fast_api()

momo_rpc = rpc()

momo_gpt = MomoGPT()

def consume_gpt_mq():
    while True:
        if not mq.gpt_empty() and mq.gpt_running == False:
            mq.gpt_running = True
            message = mq.gpt_take()
            momo_gpt.post_message(message)
        else:
            time.sleep(2)

def on_gpt_message(message):
    message_to(message)
    mq.gpt_running = False

def on_rpc_message(body, _):
    payload = body['payload']
    if 'momoid' in payload:
        rpc_msg = str(payload)
        mq.rpc_put(rpc_msg)
        from_id = rpc_msg['momoid']
        to_id = rpc_msg['remoteUser']['momoid']
        gpt_wait_msg = Message(
            momo_id=to_id,
            remote_id=from_id,
            content=body['content']
        )
        mq.gpt_put(gpt_wait_msg)

momo_gpt.on('message', on_gpt_message)

momo_rpc.on('message', on_rpc_message)

momo_rpc.exports_sync.receive()

gpt_thread = threading.Thread(target=consume_gpt_mq)

@app.get('/sse')
@sse_handler()
async def sse():
    if mq.rpc_empty():
        yield SSEMessage(payload=[])
    else:
        takes = []
        while not mq.rpc_empty():
            message = mq.rpc_take()
            takes.append(message)
        yield SSEMessage(payload=takes)

@app.get('/', response_class=HTMLResponse)
async def index(request: Request):
    return load_template('index.html', templates, request)

@app.get('/image/{id}')
async def image(id):
    result = momo_rpc.exports_sync.image(id)
    return load_image(result)

if __name__ == '__main__':
    gpt_thread.start()
    import uvicorn
    uvicorn.run(app, host='localhost', port=8082)

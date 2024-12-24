from rpc import rpc
from fastapi import Request
from fastapi.responses import HTMLResponse
from fastapi_sse import sse_handler
from utils import SSEMessage, fast_app, load_image, load_template
from mq import MQueueManager

mq = MQueueManager(1024)

app = fast_app()

momo_rpc = rpc()

def on_message(body, _):
    payload = str(body['payload'])
    mq.rpc_put(payload)

momo_rpc.on('message', on_message)

momo_rpc.exports_sync.receive()

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
    return load_template(request, 'index.html')

@app.get('/image/{id}')
async def image(id):
    result = momo_rpc.exports_sync.image(id)
    return load_image(result)

if __name__ == '__main__':
    import uvicorn
    uvicorn.run(app, host='localhost', port=8082)

import asyncio
from typing import List
from fastapi import FastAPI, Request
from fastapi.responses import HTMLResponse, StreamingResponse
from fastapi.middleware.cors import CORSMiddleware
from fastapi.templating import Jinja2Templates
from fastapi_sse import sse_handler
from pydantic import BaseModel
import requests
from rpc import rpc
from gpt import Message, MomoGPT, str_json
from services import message_to

login_momo_id = '976807129'

mq = asyncio.Queue(maxsize=1024)

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=['*'],
    allow_credentials=True,
    allow_methods=['*'],
    allow_headers=['*'],
)

templates = Jinja2Templates(directory='templates')

momo = rpc()

momo_gpt = MomoGPT()

def on_message(message):
    message_to(message)

momo_gpt.on('message', on_message)

class SSEMessage(BaseModel):
    payload: List[str]

def on_message(body, _):
    body = body['payload']
    payload = str(body)
    mq.put_nowait(payload)
    from_id = body['momoid']
    to_id = body['remoteUser']['momoid']
    if from_id != login_momo_id:
        message = Message(
            momo_id=to_id,
            remote_id= from_id,
            content=body['content']
        )
        momo_gpt.post_message(message)

momo.on('message', on_message)

momo.exports_sync.receive()

@app.get('/sse')
@sse_handler()
async def sse():
    if mq.empty():
        yield SSEMessage(payload=[])
    else:
        takes = []
        while not mq.empty():
            message = mq.get_nowait()
            takes.append(message)
        yield SSEMessage(payload=takes)

@app.get('/', response_class=HTMLResponse)
async def index(request: Request):
    return templates.TemplateResponse('index.html', {'request': request})

@app.get('/image/{id}')
async def image(id):
    result = momo.exports_sync.image(id)
    image = requests.get(result, stream=True)
    return StreamingResponse(image.iter_content(chunk_size=1024), media_type=image.headers['Content-Type'])

if __name__ == '__main__':
    import uvicorn
    uvicorn.run(app, host='localhost', port=8082)

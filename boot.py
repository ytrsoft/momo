import asyncio
import random
from typing import Optional
from fastapi import FastAPI, Request
from fastapi.responses import JSONResponse, StreamingResponse, HTMLResponse
from fastapi.middleware.cors import CORSMiddleware
from fastapi.templating import Jinja2Templates
from fastapi_sse import sse_handler
from pydantic import BaseModel
import requests
from rpc import rpc

mq = asyncio.Queue(maxsize=10)

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

class SSEMessage(BaseModel):
    payload: str

def on_message(body, _):
    payload = str(body['payload'])
    message = SSEMessage(payload=payload)
    mq.put_nowait(message)

momo.on('message', on_message)

momo.exports_sync.receive()

@app.get('/sse')
@sse_handler()
async def sse():
    while True:
        message = await mq.get()
        yield message

@app.get('/', response_class=HTMLResponse)
async def index(request: Request):
    return templates.TemplateResponse('index.html', {'request': request})

@app.get('/profile/{id}')
async def profile(id):
    result = momo.exports.profile(id)
    return JSONResponse(content=result)

@app.get('/timeline/{id}')
async def timeline(id):
    result = momo.exports.timeline(id)
    return JSONResponse(content=result)

@app.get('/comments/{id}')
async def comments(id):
    result = momo.exports.comments(id)
    return JSONResponse(content=result)

@app.get('/nearly')
async def nearly():
    result = momo.exports.nearly()
    return JSONResponse(content=result)

@app.get('/news')
async def news():
    result = momo.exports.news()
    return JSONResponse(content=result)

@app.post('/post')
async def post(request: Request):
    body = await request.json()
    result = momo.exports_sync.post(body)
    return JSONResponse(content=result)

@app.get('/image/{id}')
async def image(id):
    result = momo.exports.image(id)
    image = requests.get(result, stream=True)
    return StreamingResponse(image.iter_content(chunk_size=1024), media_type=image.headers['Content-Type'])

if __name__ == '__main__':
    import uvicorn
    uvicorn.run(app, host='localhost', port=8080)

import requests
from typing import List
from pydantic import BaseModel
from fastapi import FastAPI, Request
from fastapi.responses import StreamingResponse
from fastapi.middleware.cors import CORSMiddleware

class SSEMessage(BaseModel):
    payload: List[str]

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

def load_image(url):
    image = requests.get(url, stream=True)
    return StreamingResponse(image.iter_content(chunk_size=1024), media_type=image.headers['Content-Type'])

def load_template(file_name, templates, request: Request):
    templates.TemplateResponse(file_name, {'request': request})

def fast_api() -> FastAPI:
    app = FastAPI()
    app.add_middleware(
        CORSMiddleware,
        allow_origins=['*'],
        allow_credentials=True,
        allow_methods=['*'],
        allow_headers=['*'],
    )
    return app

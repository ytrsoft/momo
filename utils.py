import requests
from typing import List
from pydantic import BaseModel
from fastapi import FastAPI, Request
from fastapi.responses import StreamingResponse
from fastapi.middleware.cors import CORSMiddleware
from fastapi.templating import Jinja2Templates

templates = Jinja2Templates(directory='templates')

class SSEMessage(BaseModel):
    payload: List[str]

def message_to(message):
    url = 'http://localhost:8081/post'
    requests.post(url, json=message)

def load_template(request: Request, file_name):
      return templates.TemplateResponse(file_name, {'request': request})

def load_image(url):
    image = requests.get(url, stream=True)
    return StreamingResponse(image.iter_content(chunk_size=1024), media_type=image.headers['Content-Type'])

def CORS(app: FastAPI):
    app.add_middleware(
        CORSMiddleware,
        allow_origins=['*'],
        allow_credentials=True,
        allow_methods=['*'],
        allow_headers=['*'],
    )
    return app

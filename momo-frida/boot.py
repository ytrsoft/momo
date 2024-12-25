from rpc import rpc
from fastapi import FastAPI, Request
from fastapi.responses import JSONResponse
from utils import CORS, load_image

app = FastAPI()
CORS(app)

momo = rpc()

@app.get('/profile/{id}')
async def profile(id):
    result = momo.exports_sync.profile(id)
    return JSONResponse(content=result)

@app.get('/timeline/{id}')
async def timeline(id):
    result = momo.exports_sync.timeline(id)
    return JSONResponse(content=result)

@app.get('/comments/{id}')
async def comments(id):
    result = momo.exports_sync.comments(id)
    return JSONResponse(content=result)

@app.get('/nearly')
async def nearly():
    result = momo.exports_sync.nearly()
    return JSONResponse(content=result)

@app.get('/news')
async def news():
    result = momo.exports_sync.news()
    return JSONResponse(content=result)

@app.get('/publish')
async def news():
    result = momo.exports_sync.publish()
    return JSONResponse(content=result)

@app.post('/post')
async def post(request: Request):
    body = await request.json()
    result = momo.exports_sync.post(body)
    return JSONResponse(content=result)

@app.get('/image/{id}')
async def image(id):
    result = momo.exports_sync.image(id)
    return load_image(result)

if __name__ == '__main__':
    import uvicorn
    uvicorn.run(app, host='localhost', port=8081)

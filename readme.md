  
'''bash
docker run --name TatilKiralamaDB -d -e"MONGO_INITDB_ROOT_USERNAME=admin" -e"MONGO_INITDB_ROOT_PASSWORD=root" -p 27021:27017 mongo:jammy
'''

docker build --platform linux/amd64 -t kirazyagmur/tatil-service:v.0.7 .

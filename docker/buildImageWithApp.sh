SOURCE=https://github.com/rovsheneko/springTemplate.git
docker build -t art-app-image . # uses Dockerfile settings
docker run -it art-app-image:latest
s2i build $SOURCE art-app-image art-app-image-v1

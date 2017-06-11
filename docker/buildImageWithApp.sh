SOURCE=https://github.com/rovsheneko/springTemplate
IMAGE_NAME=gradle-java8-image
docker build -t $IMAGE_NAME . # uses Dockerfile settings
#docker run -it art-app-image:latest /bin/bash # last arg is the entry point into the docker
RUN_IMAGE_NAME=art-app-image-v1
s2i build $SOURCE $IMAGE_NAME $RUN_IMAGE_NAME
#--destination=/usr/local/app
#s2i build --ref=my-branch https://github.com/openshift/ruby-hello-world openshift/ruby-23-centos7 ruby-app
docker run -it $RUN_IMAGE_NAME

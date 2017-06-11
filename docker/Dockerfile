
# art-app-image
#FROM gradle:alpine
FROM anapsix/alpine-java:8_jdk

# TODO: Put the maintainer name in the image metadata
MAINTAINER Rov, https://github.com/rovsheneko

# SET ENV VARS
# TODO: Rename the builder environment variable to inform users about application you provide them
ENV BUILDER_VERSION 0.01
# home folder for ant
ENV IVY_HOME /cache
ENV GRADLE_VERSION 2.12
ENV GRADLE_HOME /usr/local/gradle
ENV PATH ${PATH}:${GRADLE_HOME}/bin
ENV GRADLE_USER_HOME /gradle
#END

# TODO: Set labels used in OpenShift to describe the builder image
LABEL io.k8s.description="Platform for building spring-boot apps with gradle" \
      io.k8s.display-name="builder spring-boot with gradle" \
      io.openshift.expose-services="80:http" \
      io.openshift.tags="builder,spring-boot,gradle." 

# TODO: Install required packages here:
# RUN yum install -y ... && yum clean all -y
WORKDIR /usr/local
RUN apk update && apk add ca-certificates && update-ca-certificates && apk add openssl && \
    wget  https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip && \
    unzip gradle-$GRADLE_VERSION-bin.zip && \
    rm -f gradle-$GRADLE_VERSION-bin.zip && \
    ln -s gradle-$GRADLE_VERSION gradle && \
    echo -ne "- with Gradle $GRADLE_VERSION\n" >> /root/.built && \
    apk update && apk add libstdc++ && rm -rf /var/cache/apk/* && \
    apk add --update curl && rm -rf /var/cache/apk/* && \
    mkdir -p /gradle && mkdir -p /app

# Define volume: your local app code directory can be mounted here
# Mount with: -v /your/local/directory:/docker/dir/path
WORKDIR /app
#ENTRYPOINT gradle

# TODO Allow the host to use gradle cache, otherwise gradle will always download plugins & artifacts on every build
#VOLUME ["/root/.gradle/caches/"]

# TODO (optional): Copy the builder files into /opt/app-root
# COPY ./<builder_folder>/ /opt/app-root/

# Defines the location of the S2I
LABEL io.openshift.s2i.scripts-url=image:///usr/libexec/s2i 
#      io.openshift.s2i.destination="/usr/local/app/" # TODO figure out how to use this label

# TODO: Copy the S2I scripts to /usr/libexec/s2i, since openshift/base-centos7 image
# sets io.openshift.s2i.scripts-url label that way, or update that label
COPY ./s2i/bin/ /usr/libexec/s2i

# TODO: Drop the root user and make the content of /opt/app-root owned by user 1001
# RUN chown -R 1001:1001 /opt/app-root

# This default user is created in the openshift/base-centos7 image
USER root
WORKDIR /user/local/app

# TODO: Set the default port for applications built using this image
EXPOSE 80

# TODO: Set the default CMD for the image
CMD ["/usr/libexec/s2i/usage"]

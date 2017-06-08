
# art-app-image
FROM ubuntu:16.04

# TODO: Put the maintainer name in the image metadata
MAINTAINER Rov, https://github.com/rovsheneko

# TODO: Rename the builder environment variable to inform users about application you provide them
ENV BUILDER_VERSION 0.01

# TODO: Set labels used in OpenShift to describe the builder image
LABEL io.k8s.description="Platform for building spring-boot apps with gradle" \
      io.k8s.display-name="builder spring-boot with gradle" \
      io.openshift.expose-services="8080:http" \
      io.openshift.tags="builder,spring-boot,gradle."

# TODO: Install required packages here:
# RUN yum install -y ... && yum clean all -y
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y  software-properties-common && \
    add-apt-repository ppa:webupd8team/java -y && \
    apt-get update && \
    echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y oracle-java8-installer && \
    apt-get clean && \
    useradd -ms /bin/bash rov -G sudo && \ 
    mkdir -p /data/app

#    useradd rov -s /bin/bash -m -G sudo && \
#    chpasswd << 'END' && \
#    rov:password && \
#    END && \

WORKDIR /data/app

# Define volume: your local app code directory can be mounted here
# Mount with: -v /your/local/directory:/data/app
VOLUME ["/data/app"]
# Allow the host to use gradle cache, otherwise gradle will always download plugins & artifacts on every build
VOLUME ["/root/.gradle/caches/"]

# Install Java (Open JDK)
RUN \
    apt-get update && \
    apt-get -y install unzip openjdk-8-jdk && \
    cd /usr/local && \
    apt-get install curl && \
    curl -L https://services.gradle.org/distributions/gradle-2.5-bin.zip -o gradle-2.5-bin.zip && \
    unzip gradle-2.5-bin.zip && \
    rm gradle-2.5-bin.zip

# Export some environment variables
ENV GRADLE_HOME=/usr/local/gradle-2.5
ENV PATH=$PATH:$GRADLE_HOME/bin JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

# TODO (optional): Copy the builder files into /opt/app-root
# COPY ./<builder_folder>/ /opt/app-root/

# TODO: Copy the S2I scripts to /usr/libexec/s2i, since openshift/base-centos7 image
# sets io.openshift.s2i.scripts-url label that way, or update that label
COPY ./s2i/bin/ /usr/libexec/s2i

# TODO: Drop the root user and make the content of /opt/app-root owned by user 1001
# RUN chown -R 1001:1001 /opt/app-root

# This default user is created in the openshift/base-centos7 image
USER rov
WORKDIR /home/rov

# TODO: Set the default port for applications built using this image
EXPOSE 8080

# TODO: Set the default CMD for the image
CMD ["/usr/libexec/s2i/usage"]

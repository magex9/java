FROM magex9/base
MAINTAINER Scott Finlay <scott@magex.ca>

# Get the repositories
RUN apt-get update && apt-get -y upgrade
RUN apt-get -y install software-properties-common
RUN add-apt-repository ppa:webupd8team/java
RUN apt-get -y update

# Accept the license
RUN echo "oracle-java7-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections

# Install Java
RUN apt-get -y install oracle-java7-installer

# Clenaup
RUN rm -rf /var/lib/apt/lists/*
RUN rm -rf /var/cache/oracle-jdk7-installer

# Define working directory.
ENV WORKDIR /work
WORKDIR ${WORKDIR}

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-7-oracle

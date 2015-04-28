FROM ubuntu:14.04
MAINTAINER Scott Finlay <scott@magex.ca>

RUN wget https://raw.githubusercontent.com/magex9/workspace/gh-pages/scripts/profile -O ~/.bashrc

RUN apt-get update && \
    apt-get install -yq --no-install-recommends wget vim && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

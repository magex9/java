FROM ubuntu:14.04
MAINTAINER Scott Finlay <scott@magex.ca>

RUN apt-get update && \
    apt-get install -yq --no-install-recommends wget vim && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

RUN wget -O ~/.bashrc --no-check-certificate \
	https://raw.githubusercontent.com/magex9/workspace/gh-pages/scripts/profile

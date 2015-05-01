FROM centos:7
MAINTAINER Scott Finlay <scott@magex.ca>

# Required for Ubuntu, not CenoOS.
#RUN apt-get update# && \
#    apt-get install -yq --no-install-recommends wget vim unzip curl && \
#    apt-get clean && \
#    rm -rf /var/lib/apt/lists/*

RUN /usr/bin/wget -O ~/.bashrc --no-check-certificate \
	https://raw.githubusercontent.com/magex9/workspace/gh-pages/scripts/profile

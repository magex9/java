#!/bin/bash

# Build a new versio of the image
docker build -t magex9/nexus .

# Star the server on the same port
docker run -d -p 8081:8081 magex9/nexus

# Open the browser to the start page
open http://$(boot2docker ip 2>/dev/null):8081/ 

################################################################
# Using an external volume so the information will be around on the next upgrade.
# Get the current directory
# DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
# Run it with the -v volume command.
# docker run -d -p 8081:28081 -v ${DIR}/nexus-work:/opt/sonatype-work magex9/nexus

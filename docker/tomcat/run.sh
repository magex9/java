#!/bin/bash

# Build a new versio of the image
docker build -t magex9/tomcat .

# Star the server on the same port
docker run -d -p 8080:8080 magex9/tomcat

# Open the browser to the start page
open http://$(boot2docker ip 2>/dev/null):8080/ 

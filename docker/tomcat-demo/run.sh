#!/bin/bash

# Build a new versio of the image
docker build -t magex9/tomcat-demo .

# Star the server on the same port
docker run -d -p 9001:8080 magex9/tomcat-demo

# Open the browser to the start page
open http://$(boot2docker ip 2>/dev/null):9001/tomcat/

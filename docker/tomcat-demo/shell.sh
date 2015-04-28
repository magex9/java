#!/bin/bash

# Build a new versio of the image
docker build -t magex9/tomcat-demo .

# Star the server on the same port
docker run -i -t -p 9001:8080 magex9/tomcat-demo /bin/bash

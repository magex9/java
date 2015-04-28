#!/bin/bash

# Build a new versio of the image
docker build -t magex9/jquery-demo .

# Star the server on the same port
docker run -d -p 9002:8080 magex9/jquery-demo

# Open the browser to the start page
open http://$(boot2docker ip 2>/dev/null):9002/jquery/

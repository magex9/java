#!/bin/bash

# Build a new versio of the image
docker build -t magex9/jboss .

# Star the server on the same port
docker run -i -t -p 8080:8080 magex9/jboss /bin/bash

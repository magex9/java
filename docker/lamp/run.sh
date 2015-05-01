#!/bin/bash

# Stop and remove old containers
docker stop lamp; docker rm lamp;

# Build a new versio of the image
docker build -t magex9/lamp .

# Star the server on the same port
docker run --name lamp -d -p 80:80 -p 3306:3306 magex9/lamp

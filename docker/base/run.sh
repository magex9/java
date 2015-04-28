#!/bin/bash

# Build a new versio of the image
docker build -t magex9/basic .

# Star the server on the same port
docker run -i -t magex9/basic /bin/bash


#!/bin/bash

# Build a new versio of the image
docker build -t magex9/nginx .

# Star the server on the same port
docker run -i -t -p 80:80 magex9/nginx /bin/bash


#!/bin/bash

# Build a new versio of the image
docker build -t magex9/jdk7-oracle .

# Star the server on the same port
docker run -i -t magex9/jdk7-oracle /bin/bash

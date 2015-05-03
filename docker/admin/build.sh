#!/bin/sh

# Setup the environment variables
. admin/setup.sh

# Build a new versio of the image
echo "Building the container from the docker file"
echo "> docker build -t magex9/$CONTAINER $CONTAINER"
docker build -t magex9/$CONTAINER $CONTAINER


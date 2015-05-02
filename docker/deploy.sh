#!/bin/sh

# Setup the environment variables
. admin/setup.sh

# Stop and remove old 
echo "Stopping and removing old containers";
docker stop $CONTAINER; docker rm $CONTAINER;

# Build a new versio of the image
echo "Building the container from the docker file"
docker build -t magex9/$CONTAINER $CONTAINER;

# Star the server on the same port
echo "Starting the container in -d mode"
docker run --name $CONTAINER -d $PORTS magex9/$CONTAINER;

# Open the server up in a new browser window
echo "Opening the browser on port $BROWSER"
open $BROWSER

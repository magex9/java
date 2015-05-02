#!/bin/sh

# Setup the environment variables
. admin/setup.sh

# Star the server on the same port
echo "Starting the container in -i mode"
docker run -i -t $PORTS magex9/$CONTAINER /bin/bash

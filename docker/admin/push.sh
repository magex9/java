#!/bin/sh

# Setup the environment variables
. admin/setup.sh

# Deploying the container to my local registry
echo "Deployig the container to $IP:5000"
echo docker push $IP:5000/magex9/$CONTAINER
#docker push $IP:5000/magex9/$CONTAINER

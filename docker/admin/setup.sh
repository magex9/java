#!/bin/sh

# Make sure a container name was passed in
CONTAINER=`echo $1 | sed -e 's#/$##'`
if [ -z $CONTAINER ]; then
	echo "Usage: $0 [container]";
	exit 0;
fi

# IF the container doesnt have a dockerfile then theres nothing 
# to build and run
if [ ! -f $CONTAINER/Dockerfile ]; then
	echo "Container has no Dockerfile to build";
	exit 0;
fi

# Get all the parameters setup to be used throughout the script
PORTS=`grep "^$CONTAINER=" admin/ports.properties | sed -n 's/.*=\([^|]*\)|\([^|]*\)/\1/p'`
URL=`grep "^$CONTAINER=" admin/ports.properties | sed -n 's/.*=\([^|]*\)|\([^|]*\)/\2/p'`
IP=`boot2docker ip`;
BROWSER=`echo $URL | sed -n 's/docker/'$IP'/p'`

# Echo all the variables out to the user so they can verify
echo "Building docker container"
echo "  name: $CONTAINER"
echo "  ports: $PORTS"
echo "  url: $URL"
echo "  browser: $BROWSER"
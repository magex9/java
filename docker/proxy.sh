#!/bin/bash

# Stop and remove old containers
docker stop proxy-nginx; docker rm proxy-nginx;
docker stop proxy-nexus; docker rm proxy-nexus;
docker stop proxy-jquery; docker rm proxy-jquery;
docker stop proxy-jboss; docker rm proxy-jboss;

# Build a new versio of the image
docker build -t magex9/nginx .

docker run --name proxy-jboss -d -p 20001:8080 magex9/jboss
docker run --name proxy-jquery -d -p 20002:8080 magex9/tomcat-demo
#docker run --name proxy-nexus -d -p 20004:8081 magex9/nexus

# Star the server on the same port
docker run --name proxy-nginx -d -p 80:80 magex9/nginx


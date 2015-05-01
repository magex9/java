#!/bin/bash

# Stop and remove old containers
docker stop nginx; docker rm nginx;
docker stop nx-nexus; docker rm nx-nexus;
docker stop nx-jquery; docker rm nx-jquery;
docker stop nx-jboss; docker rm nx-jboss;

# Build a new versio of the image
docker build -t magex9/nginx .

docker run --name nx-jboss -d -p 20001:8080 magex9/jboss
docker run --name nx-jquery -d -p 20003:8080 magex9/tomcat-demo
docker run --name nx-nexus -d -p 20004:8081 magex9/nexus

# Star the server on the same port
docker run --name nginx -d -p 80:80 magex9/nginx


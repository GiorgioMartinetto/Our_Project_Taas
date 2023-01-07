#!/bin/bash
docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker rmi user-service profile-service netflix-service prime-service service-discovery-server api-gateway google-service
docker image prune -f
docker volume prune -f
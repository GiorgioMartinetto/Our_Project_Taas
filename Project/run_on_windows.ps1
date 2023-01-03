#!/bin/bash
cd user-service
mvn clean 
mvn install -DskipTests
docker build -t user-service . 
cd .. 

cd service-discovery-server 
mvn clean 
mvn install -DskipTests
docker build -t service-discovery-server . 
cd .. 

cd api-gateway 
mvn clean 
mvn install -DskipTests
docker build -t api-gateway .
cd .. 

cd profile-service
mvn clean 
mvn install -DskipTests
docker build -t profile-service .
cd ..

cd google-service
mvn clean
mvn install -DskipTests
docker build -t google-service .
cd ..

docker compose up
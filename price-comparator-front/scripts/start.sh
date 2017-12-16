#!/bin/bash

# wait for 15 seconds until mysql is up
./wait-for-it.sh -t 120 service-config:8010

./wait-for-it.sh -t 120 price-agregator:8003

./wait-for-it.sh -t 120 product-catalogue:8002

if [ $? -eq 0 ]
then
  # To reduce Tomcat startup time we added a system property pointing to "/dev/urandom" as a source of entropy.
  java -Djava.security.egd=file:/dev/./urandom -jar app.jar
fi
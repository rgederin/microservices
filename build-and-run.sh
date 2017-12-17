#!/usr/bin/env bash

./mvn-build.sh

docker-compose build

docker-compose up
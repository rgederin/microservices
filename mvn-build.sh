#!/usr/bin/env bash
cd service-config

mvn clean install

cd ..

cd service-discovery

mvn clean install

cd ..

cd price-agregator

mvn clean install

cd ..

cd product-catalogue

mvn clean install

cd ..

cd price-comparator-front

mvn clean install

cd ..
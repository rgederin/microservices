#!/usr/bin/env bash
cd price-agregator

mvn clean install

cd ..

cd product-catalogue

mvn clean install

cd ..

cd price-comparator-front

mvn clean install

cd ..
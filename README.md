# Microservices-based application using Spring, Docker and Docker Compose

Distributed application which demonstrates microservices architectural approach and usage of docker and docker-compose for building, deploying and managing containers with microservices.

## How to run

```
Be sure that you have docker or docker tool installed on your machine

cd microservices/
./build-and-run.sh
```

## Architecture and technologies

![Architecture and technologies](https://github.com/rgederin/microservices/blob/master/img/microservice.jpg)


## Microservices description

*  **product-catalogue**: provides REST endpoint for getting products list
*  **price-agregator**: provides REST endpoint for getting min, avg, max prices for products
*  **product-agregator-front**: receives streams of products and prices from endpoints, merges it and display
*  **api-gateway**: routes requests from frontend service to needful endpoints
*  **service-config**: keeps all config files for all services
*  **service-discovery**: acts as passive service discovery server

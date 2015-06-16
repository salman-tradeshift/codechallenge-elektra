#!/bin/bash

# Start postgres
docker run --name elektra-postgres -e POSTGRES_PASSWORD=docker -d elektra-postgres

# Start the web service and link it to postgres
docker run --name elektra-webservice --link elektra-postgres:postgres --rm -t -p 8080:8080 messages
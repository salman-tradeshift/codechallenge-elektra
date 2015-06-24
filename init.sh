#!/bin/bash

# Start postgres
docker run --name chat-database -e POSTGRES_PASSWORD=docker -d chat-database

# Start the web service and link it to postgres
docker run --name chat-backend --link chat-database:POSTGRES -t -p 8080:8080 chat-backend

# Start the front end and which serves the html and communicates with
docker run --name chat-frontend --rm -t -p 3000:3000 chat-frontend
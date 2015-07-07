#!/bin/bash

# Build and move frontend
grunt --gruntfile=../frontend/Gruntfile.js
cp ../frontend/server.js frontend/server.js
cp -R ../frontend/public frontend/public

# Build docker images
docker build --no-cache -t chat-database database
docker build --no-cache -t chat-frontend frontend
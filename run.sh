#! /usr/bin/env bash
boot2docker shellinit >/tmp/init.sh
. /tmp/init.sh
docker build -t ksp/code-challenge-web -f Dockerfile-web .
docker build -t ksp/code-challenge-db -f Dockerfile-db .

docker run -d --name db ksp/code-challenge-db
docker run -d -p 8080:8080 --name web --link db:db ksp/code-challenge-web
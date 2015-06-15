#! /usr/bin/env bash
boot2docker shellinit >/tmp/init.sh
. /tmp/init.sh
docker build -t ksp/code-challenge -f Dockerfile .
docker run -d -p 8080:8080 ksp/code-challenge
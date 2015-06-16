#!/bin/bash

gosu postgres postgres --single -jE <<- EOSQL
   CREATE DATABASE docker OWNER postgres;
EOSQL

gosu postgres postgres --single -jE docker <<- EOSQL
       CREATE TABLE messages ( \
           id              serial                      PRIMARY KEY, \
           message         varchar(256)                NOT NULL, \
           created         TIMESTAMP WITH TIME ZONE    NOT NULL DEFAULT NOW() \
       );
EOSQL

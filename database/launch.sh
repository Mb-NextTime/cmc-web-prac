#!/bin/bash

SCRIPT_DIR="$(readlink -f $( dirname "$0" ) )"

podman run --rm -d \
    -v $SCRIPT_DIR:/database \
    --network host \
    --name postgres \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
    docker.io/library/postgres:14

#! /usr/bin/env bash

SCRIPT_DIR="$(readlink -f $( dirname "$0" ) )"

podman run -it --rm \
    --name postgres \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
    docker.io/library/postgres:14

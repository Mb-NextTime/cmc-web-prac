#!/bin/bash
echo $1

podman exec --user postgres postgres psql -f $1

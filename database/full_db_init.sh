#!/bin/bash

CUR_CIR="$(pwd)"
SCRIPT_DIR="$(readlink -f $( dirname "$0" ) )"
echo $SCRIPT_DIR
cd $SCRIPT_DIR

./launch.sh
sleep 4
./send_request.sh ./database/create.sql
./send_request.sh ./database/init.sql

cd $CUR_CIR

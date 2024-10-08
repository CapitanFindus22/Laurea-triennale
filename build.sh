#!/bin/bash

cd db/Script/
./create.sh;
cd ../../receiver/src;
make;
cd ../../sender/src;
make;

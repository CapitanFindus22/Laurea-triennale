#!/bin/bash

cd db/Script/
./create.sh;
cd ../../mean/src;
make;
cd ../../test_gen/src;
make;

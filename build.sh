#!/bin/bash

cd DB/Script/
./create.sh

cd ../Code/src
make
cd ../../../Redis/src
make
cd ../../Media/src
make
cd ../../test_gen/src
make
cd ../../Covarianza/src
make
cd ../../Monitor/src
make

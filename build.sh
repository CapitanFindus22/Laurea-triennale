#!/bin/bash

cd DB/Script/
./create.sh;
cd ../../Media/src;
make;
cd ../../Sender/src;
make;
cd ../../Covarianza/src;
make;
cd ../../Monitor/src;
make;

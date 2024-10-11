#!/bin/bash

cd ./test_gen/bin;
gnome-terminal --title='Test_generator' -- bash -c "./main; read" &

cd ../../mean/bin;
gnome-terminal --title='Media' -- bash -c "./main; read" &

cd ../../covariance/bin;
gnome-terminal --title='Covarianza' -- bash -c "./main; read" &




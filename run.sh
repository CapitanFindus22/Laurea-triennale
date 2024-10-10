#!/bin/bash

cd ./test_gen/bin;
gnome-terminal -- bash -c "./main; read" &

cd ../../covariance/bin;
gnome-terminal -- bash -c "./main; read" &

cd ../../mean/bin;
gnome-terminal -- bash -c "./main; read" &



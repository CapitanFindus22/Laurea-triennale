#!/bin/bash

cd ./mean/bin;
gnome-terminal -- bash -c "./main; read" &

cd ../../test_gen/bin;
gnome-terminal -- bash -c "./main; read" &

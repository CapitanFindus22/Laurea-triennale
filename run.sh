#!/bin/bash

cd ./receiver/bin;
gnome-terminal -- bash -c "./main; read" &

cd ../../sender/bin;
gnome-terminal -- bash -c "./main; read" &

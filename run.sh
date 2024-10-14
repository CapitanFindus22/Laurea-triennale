#!/bin/bash

cd ./test_gen/bin;
gnome-terminal --title='Test_generator' -- bash -c "./main; read" &

cd ../../Media/bin;
gnome-terminal --title='Media' -- bash -c "./main; read" &

cd ../../Covarianza/bin;
gnome-terminal --title='Covarianza' -- bash -c "./main; read" &

cd ../../Monitor/bin;
gnome-terminal --title='Monitor' -- bash -c "./main; read" &


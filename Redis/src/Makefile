OBJ_DIR = ../obj
SRC = Redis_functions.cpp
OBJ = $(OBJ_DIR)/Redis_functions.o

CC = g++
CFLAGS = -std=c++20 -Wextra -Wall -L/usr/local/lib -I. 
LFLAGS = -lm -lhiredis

all: $(OBJ)

$(OBJ): $(SRC)
	$(CC) -c $< -o $@ $(CPPFLAGS) $(LFLAGS)

.PHONY: clean

clean:
	rm -rf $(OBJ_DIR)/*.o

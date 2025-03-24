OBJ_DIR = ../obj
SRC = Redis_functions.cpp
OBJ = $(OBJ_DIR)/Redis_functions.o

CC = g++
CPPFLAGS = -std=c++20 -Wextra -Wall -I. 
LFLAGS = -lm -lhiredis -L/usr/local/lib

all: $(OBJ_DIR) $(OBJ)

$(OBJ_DIR):
	mkdir -p $(OBJ_DIR)

$(OBJ): $(SRC) | $(OBJ_DIR)
	$(CC) -c $< -o $@ $(CPPFLAGS) $(LFLAGS)

.PHONY: clean

clean:
	rm -rf $(OBJ_DIR)/*.o

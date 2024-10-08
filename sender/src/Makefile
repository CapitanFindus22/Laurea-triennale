OBJ_DIR = ../obj
REDIS_DIR = ../../Redis
BDIR=../bin

DEPS = $(wildcard *.hpp)
SRC = $(wildcard *.cpp) 
OBJ = $(patsubst %.cpp,$(OBJ_DIR)/%.o,$(SRC)) 
REDIS_OBJ = $(REDIS_DIR)/obj/Redis_functions.o 

CC = g++
CFLAGS = -std=c++20 -Wextra -Wall
LFLAGS = -lm -lhiredis -L/usr/local/lib

.PHONY: all clean

all: $(BDIR)/main

$(BDIR)/main: $(REDIS_OBJ) $(OBJ) $(DEPS)
	$(CC) -o $@ $(REDIS_OBJ) $(OBJ) $(CPPFLAGS) $(LFLAGS)

$(REDIS_OBJ): 
	$(MAKE) -C $(REDIS_DIR)/src 

$(OBJ_DIR)/%.o: %.cpp $(DEPS)
	$(CC) -c -o $@ $< $(CPPFLAGS)

clean:
	rm -rf $(OBJ_DIR)/*.o
	rm -rf ../bin/main
	$(MAKE) -C $(REDIS_DIR)/src clean
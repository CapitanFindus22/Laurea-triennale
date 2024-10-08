REDIS_DIR = ../../Redis
DB_DIR = ../../db/Code
OBJ_DIR = ../obj
BDIR = ../bin

CC = g++
CFLAGS = -std=c++20 -Wextra -Wall -I/usr/include/postgresql
LFLAGS = -lm -lhiredis -lpq -L/usr/local/lib

DEPS = $(wildcard *.hpp)
SRC = $(wildcard *.cpp)
OBJ = $(patsubst %.cpp, $(OBJ_DIR)/%.o, $(SRC))
REDIS_OBJ = $(REDIS_DIR)/obj/Redis_functions.o 
DB_OBJ = $(DB_DIR)/obj/Con2DB.o 

.PHONY: all clean

all: $(BDIR)/main

$(BDIR)/main: $(REDIS_OBJ) $(DB_OBJ) $(OBJ) $(DEPS)
	$(CC) -o $@ $(REDIS_OBJ) $(DB_OBJ) $(OBJ) $(CPPFLAGS) $(LFLAGS)

$(REDIS_OBJ):
	$(MAKE) -C $(REDIS_DIR)/src

$(DB_OBJ):
	$(MAKE) -C $(DB_DIR)/src

$(OBJ_DIR)/%.o: %.cpp $(DEPS)
	$(CC) -c -o $@ $< $(CPPFLAGS)

clean:
	rm -rf $(OBJ_DIR)/*.o
	rm -rf ../bin/main
	$(MAKE) -C $(REDIS_DIR)/src clean
	$(MAKE) -C $(DB_DIR)/src clean
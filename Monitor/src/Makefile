REDIS_DIR = ../../Redis
DB_DIR = ../../DB/Code
OBJ_DIR = ../obj
BDIR = ../bin

CC = g++
CPPFLAGS = -std=c++20 -Wextra -Wall -I/usr/include/postgresql
LFLAGS = -lm -lhiredis -lpq -L/usr/local/lib

DEPS = $(wildcard *.hpp)
SRC = $(wildcard *.cpp)
OBJ = $(patsubst %.cpp, $(OBJ_DIR)/%.o, $(SRC))
REDIS_OBJ = $(REDIS_DIR)/obj/Redis_functions.o 
DB_OBJ = $(DB_DIR)/obj/Con2DB.o 

.PHONY: all clean

all: $(OBJ_DIR) $(BDIR) $(BDIR)/main

$(OBJ_DIR):
	mkdir -p $(OBJ_DIR)

$(BDIR):
	mkdir -p $(BDIR)

$(BDIR)/main: $(REDIS_OBJ) $(DB_OBJ) $(OBJ) $(DEPS) | $(OBJ_DIR) $(BDIR)
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
OBJ_DIR = ../obj
SRC = Con2DB.cpp
OBJ = $(OBJ_DIR)/Con2DB.o

CC=g++
CPPFLAGS = -std=c++20 -Wall -lm -I. -I/usr/include/postgresql 
LFLAGS = -L/usr/local/lib -lpq

all: $(OBJ_DIR) $(OBJ)

$(OBJ_DIR):
	mkdir -p $(OBJ_DIR)

$(OBJ): $(SRC) | $(OBJ_DIR)
	$(CC) -c $< -o $@ $(CPPFLAGS) $(LFLAGS)

.PHONY: clean

clean:
	rm -rf $(OBJ_DIR)/*.o
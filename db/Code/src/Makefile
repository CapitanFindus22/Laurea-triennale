OBJ_DIR = ../obj
SRC = Con2DB.cpp
OBJ = $(OBJ_DIR)/Con2DB.o

CC=g++
CPPFLAGS = -std=c++20 -Wall -lm -I. -I/usr/include/postgresql 
LFLAGS = -L/usr/local/lib -lpq

all: $(OBJ)

$(OBJ): $(SRC)
	$(CC) -c $< -o $@ $(CPPFLAGS) $(LFLAGS)

.PHONY: clean

clean:
	rm -rf $(OBJ_DIR)/*.o
IDIR=../../Redis/src
IDIR2=../db/src
IDIR3=./include
ODIR=../obj
BDIR=../bin

CC = g++
CFLAGS = -std=c++20 -Wextra -Wall -L/usr/local/lib -I. -I$(IDIR) -I$(IDIR2) -I$(IDIR3) -I/usr/include/postgresql
LFLAGS = -lm -lhiredis -lpq


DEPS = $(wildcard $(IDIR)/*.hpp)
SRC = $(wildcard *.cpp)
OBJ = $(patsubst %.cpp, $(ODIR)/%.o, $(SRC))

Redis_OBJ = $(wildcard ../../Redis/obj/*.o)
db_OBJ = $(wildcard ../db/obj/*.o)

OBJ_FILES = $(OBJ) $(Redis_OBJ) $(db_OBJ)

.PHONY: all clean Redis db

all: $(ODIR) Redis db $(BDIR)  $(BDIR)/main

Redis:
	$(MAKE) -C $(IDIR)

db:
	$(MAKE) -C $(IDIR2)

$(ODIR)/%.o: %.cpp $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

$(BDIR)/main: $(DEPS) $(OBJ_FILES)
	$(CC) -o $@ $(OBJ_FILES) $(CFLAGS) $(LFLAGS)


clean:
	rm -f $(ODIR)/*.o $(BDIR)/main
	$(MAKE) -C $(IDIR) clean
	$(MAKE) -C $(IDIR2) clean
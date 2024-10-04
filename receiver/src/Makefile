IDIR=../../Redis/src
IDIR2=../../db/src
IDIR3=./include
ODIR=../obj
BDIR=../bin

CC=g++
CFLAGS=-std=c++20 -g -Wextra -Wall -L/usr/local/lib -I. -I$(IDIR) -I$(IDIR2) -I$(IDIR3) -I/usr/include/postgresql  -lm -lhiredis

DEPS = $(wildcard $(IDIR)/*.hpp)
SRC = $(wildcard *.cpp)
OBJ = $(patsubst %.cpp, $(ODIR)/%.o, $(SRC))

Redis_OBJ=$(wildcard ../../Redis/obj/*.o)
db_OBJ=$(wildcard ../../con2db/*.o)

all:	Redis $(BDIR)/main	db $(BDIR)/main

Redis:
	$(MAKE) -C ../../Redis/src

db:
	$(MAKE) -C ../../db/src

$(ODIR)/%.o: %.cpp $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

$(BDIR)/main: $(OBJ) $(DEPS)
	$(CC) -o $@ $(OBJ) $(Redis_OBJ) $(db_OBJ) $(CFLAGS)


.PHONY: clean

clean:
	rm -f $(ODIR)/*.o *~ $(IDIR)/*~ core 
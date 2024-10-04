IDIR=../../Redis/src
IDIR2=./include
ODIR=../obj
BDIR=../bin

CC = g++
CFLAGS = -std=c++20 -Wextra -Wall -L/usr/local/lib -I. -I$(IDIR) -I$(IDIR2)
LFLAGS = -lm -lhiredis

DEPS = $(wildcard $(IDIR)/*.hpp)
SRC = $(wildcard *.cpp)
OBJ = $(patsubst %.cpp, $(ODIR)/%.o, $(SRC))

Redis_OBJ = $(wildcard ../../Redis/obj/*.o)

all: $(ODIR) Redis $(BDIR)/main

Redis:
	$(MAKE) -C $(IDIR)


$(ODIR)/%.o: %.cpp $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

$(BDIR)/main: $(OBJ) $(Redis_OBJ)
	$(CC) -o $@ $^ $(CFLAGS) $(LFLAGS)


.PHONY: clean

clean:
	rm -f $(ODIR)/*.o *~ $(IDIR)/*~ core 
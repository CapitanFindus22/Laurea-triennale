IDIR=.
ODIR=../obj

CC=g++
CPPFLAGS=-std=c++20 -Wall -lm -I. -I/usr/include/postgresql -I$(IDIR) -L/usr/local/lib -lpq -lm

DEPS = $(wildcard $(IDIR)/*.hpp)
SRC = $(wildcard *.cpp)
OBJ = $(patsubst %.cpp, $(ODIR)/%.o, $(SRC))

all:	$(ODIR)/$(OBJ)

$(ODIR)/%.o: 	%.cpp $(DEPS) 
	$(CC) -c -o $@ $< $(CPPFLAGS) 

.PHONY: clean

clean:
	rm -f $(ODIR)/*.o *~ $(IDIR)/*~ core 
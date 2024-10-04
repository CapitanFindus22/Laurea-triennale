#ifndef main2_hpp
#define main2_hpp

#include <iostream>
#include <fstream>
#include <string>
#include <cassert>
#include <unistd.h>

#include "Redis_functions.hpp"
#include "pgsql.hpp"

#define DEBUG 1000

#define IP "localhost"
#define PORT 6379

void log2db(Con2DB,float);

#endif
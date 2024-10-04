#ifndef main_hpp
#define main_hpp

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

std::string GenerateStreamName(std::string, size_t);

#endif
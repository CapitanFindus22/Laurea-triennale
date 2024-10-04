#ifndef main_hpp
#define main_hpp

#include <iostream>
#include <fstream>
#include <string>
#include <cassert>
#include <unistd.h>

#include <../../../Redis/src/Redis_functions.hpp>

#define DEBUG 1000

#define IP "localhost"
#define PORT 6379

#define CSVName "tripadvisor_review.csv"

void String2FloatArray(std::string, char, float[], size_t);
std::string GenerateStreamName(std::string, size_t);

#endif
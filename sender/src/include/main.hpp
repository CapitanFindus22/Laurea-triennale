#ifndef main_hpp
#define main_hpp

#include <iostream>
#include <fstream>
#include <string>
#include <cassert>
#include <unistd.h>

#include "Redis_functions.hpp"

#define DEBUG 1000

#define IP "localhost"
#define PORT 6379
 
void String2FloatArray(std::string, char, float[], size_t);
std::string GenerateStreamName(std::string, size_t);

void SendMessage(redisContext*,size_t,std::string);
void SendMessage(redisContext*,float,std::string);

#endif
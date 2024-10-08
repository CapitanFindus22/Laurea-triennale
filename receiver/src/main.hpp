#ifndef main2_hpp
#define main2_hpp

#include <iostream>
#include <fstream>
#include <string>
#include <cassert>
#include <thread>
#include <mutex>
#include <vector>
#include <deque>
#include <atomic>
#include <unistd.h>

#include "../../Redis/src/Redis_functions.hpp"
#include "../db/src/Con2DB.hpp"

#define DEBUG 1000

#define IP "localhost"
#define PORT 6379

#define BLOCK 10000000000


void log2db(Con2DB,float,std::string);
std::string GenerateStreamName(std::string,size_t);

void ReadMessage(redisContext*,std::string,Con2DB);

float Mean(std::deque<float>);

#endif
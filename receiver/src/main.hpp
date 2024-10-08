#ifndef main_hpp
#define main_hpp

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
#include "../../db/Code/src/Con2DB.hpp"

#define DEBUG 1000

#define IP "localhost"
#define PORT 6379

#define DB_NAME "log_anomalies"
#define PORT_DB "5432"
#define USERNAME "monitor"
#define PASSWORD "65568162"

#define BLOCK 10000000000


void log2db(Con2DB,float,std::string,int);
std::string GenerateStreamName(std::string,size_t);

void ReadMessage(redisContext*,std::string,Con2DB,int);

float Mean(std::deque<float>);

#endif
#ifndef main_hpp
#define main_hpp

#include <iostream>
#include <fstream>
#include <string>
#include <cassert>
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

void String2FloatArray(std::string, char, float[], size_t);
std::string GenerateStreamName(std::string, size_t);

void SendMessage(redisContext*,size_t,std::string);
void SendMessage(redisContext*,float,std::string);

void log2db(Con2DB,size_t,std::string);
int logfromdb(Con2DB,std::string);

#endif
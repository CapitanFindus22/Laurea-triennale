#ifndef main_hpp
#define main_hpp

#include <iostream>
#include <string>
#include <thread>
#include <mutex>
#include <deque>
#include <atomic>
#include <vector>
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

void log2db(Con2DB, double, std::string,std::string, int);
float logfromdb(Con2DB,std::string,std::string);
void logAlert(Con2DB,float,std::string,std::string,int);

void ReadMessage(redisContext *, std::string, Con2DB, int, std::string&);

void Covariance(Con2DB, std::vector<std::vector<float>>&,size_t,int);

void Alert(Con2DB,std::string,std::string,float,int);

void String2Float(std::string,std::vector<float>&);

#endif
#ifndef main_hpp
#define main_hpp

#include <iostream>
#include <string>
#include <thread>
#include <mutex>
#include <deque>
#include <atomic>
#include <unistd.h>

#include "../../Redis/src/Redis_functions.hpp"
#include "../../DB/Code/src/Con2DB.hpp"

#define DEBUG 1000

#define IP "localhost"
#define PORT 6379

#define DB_NAME "log_anomalies"
#define PORT_DB "5432"
#define USERNAME "monitor"
#define PASSWORD "65568162"

#define BLOCK 10000000000

void log2db(Con2DB, float, std::string, int);
float logfromdb(Con2DB,std::string);
void logAlert(Con2DB,float,std::string,int);

void SendMessage(redisContext *,const char *,std::string);
void ReadMessage(std::string, Con2DB, int, std::deque<float>&, std::string&);

float Mean(std::deque<float>);

void Alert(Con2DB,std::string,float,int);

#endif
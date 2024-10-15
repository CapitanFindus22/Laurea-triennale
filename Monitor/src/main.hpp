#ifndef main_hpp
#define main_hpp

#include <iostream>
#include <string>
#include <thread>
#include <utility>

#include "../../Redis/src/Redis_functions.hpp"
#include "../../DB/Code/src/Con2DB.hpp"

#define DEBUG 1000

#define IP "localhost"
#define PORT 6379

#define DB_NAME "log_anomalies"
#define PORT_DB "5432"
#define USERNAME "monitor"
#define PASSWORD "65568162"

#define BLOCK 1000000000

void MM();
void MMT();
void MC();
void MCT();

std::string ReadMessage(redisContext *,std::string);
std::string ReadInfo(redisContext *,std::string);

std::pair<float,std::string> Split(std::string);

float logfromdb(Con2DB&,std::string,std::string,bool);
void log2db(Con2DB&,bool,bool,std::string,std::string,int);

#endif
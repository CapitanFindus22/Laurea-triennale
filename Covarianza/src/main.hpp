#ifndef main_hpp
#define main_hpp

#include <iostream>
#include <string>
#include <deque>
#include <vector>
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

void log2db(Con2DB&, double, std::string,std::string, int);
double logfromdb(Con2DB&,std::string,std::string);
void logAlert(Con2DB&,double,std::string,std::string,int);

std::string ReadMessage(redisContext *,std::string);
std::string ReadInfo(redisContext *,std::string);

void Covariance(redisContext*c,Con2DB&, std::vector<std::vector<double>>&,int);

void Alert(redisContext*,Con2DB&,std::string,std::string,double,int);

void String2Float(std::string,std::vector<double>&);

void SendMessage(redisContext *c, std::string arr, std::string StreamName);

#endif
#ifndef main_hpp
#define main_hpp

#include <iostream>
#include <string>
#include <deque>
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

#define GROUPNAME "MEAN"
#define NAME "user"

void log2db(Con2DB &db1, std::string value, std::string StreamName, int id);
void logAlert(Con2DB &db1, std::string value, std::string StreamName, int id);
double logfromdb(Con2DB &db1, std::string StreamName, int id);

double Mean(std::deque<double> dq);

void Alert(redisContext *c, Con2DB &db, std::string StreamName, double mean, int id);

std::string d2s(std::deque<double> dq);

size_t ChooseSize(size_t maxSize);

double SetDiff();

#endif
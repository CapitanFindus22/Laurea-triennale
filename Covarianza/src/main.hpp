#ifndef main_hpp
#define main_hpp

#include <iostream>
#include <string>
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

#define GROUPNAME "Covariance"
#define NAME "user"

void log2db(Con2DB &db1, double value, std::string StreamName1, std::string StreamName2, int id);
void logAlert(Con2DB &db1, double value, std::string StreamName1, std::string StreamName2, int id);
double logfromdb(Con2DB &db1, std::string StreamName1, std::string StreamName2, int id);

void Covariance(redisContext *c, Con2DB &, std::vector<std::vector<double>> &, int);

void Alert(redisContext *c, Con2DB &db, std::string StreamName1, std::string StreamName2, double covariance, int id);

void String2Double(std::string str, std::vector<double> &val);

double SetDiff();

#endif
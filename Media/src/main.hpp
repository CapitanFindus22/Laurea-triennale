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

#define BLOCK 10000000000

void log2db(Con2DB &db1, std::string value, std::string streamName, int id);
float logfromdb(Con2DB &db1, std::string streamName);
void logAlert(Con2DB &db1, std::string value, std::string streamName, int id);

void SendMessage(redisContext *c, std::string arr, std::string StreamName);
std::string ReadMessage(redisContext *c, std::string StreamName);

double Mean(std::deque<double> dq);

void Alert(redisContext *c, Con2DB &db, std::string streamName, double mean, int id);

size_t ChooseSize(size_t maxSize);

std::string d2s(std::deque<double> dq);

double SetDiff();

#endif
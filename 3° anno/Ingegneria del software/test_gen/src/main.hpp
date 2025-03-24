#ifndef main_hpp
#define main_hpp

#include <iostream>
#include <fstream>
#include <string>
#include <filesystem>
#include <unordered_map>
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

#define CSV_PATH "../csv"

void String2DoubleArray(std::string str, char delimiter, double val[]);

std::string ChooseFile();

size_t SetNum();

void log2db(Con2DB &db1, size_t numStream, std::string fileName);
int logfromdb(Con2DB &db1, std::string fileName);

#endif
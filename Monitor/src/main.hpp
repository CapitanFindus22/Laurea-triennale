#ifndef main_hpp
#define main_hpp

#include <iostream>
#include <string>
#include <thread>

#include "../../Redis/src/Redis_functions.hpp"
#include "../../DB/Code/src/Con2DB.hpp"

#define DEBUG 1000

#define IP "localhost"
#define PORT 6379

#define DB_NAME "log_anomalies"
#define PORT_DB "5432"
#define USERNAME "monitor"
#define PASSWORD "65568162"

#define MONITOR_M_STREAM "MMonitor"
#define MONITOR_M_GROUP "mmonitor"

#define MONITOR_MT_STREAM "TMonitor"
#define MONITOR_MT_GROUP "tmonitor"

#define MONITOR_MA_STREAM "AMonitor"
#define MONITOR_MA_GROUP "amonitor"

#define MONITOR_C_STREAM "CMonitor"
#define MONITOR_C_GROUP "cmonitor"

#define MONITOR_CT_STREAM "CTMonitor"
#define MONITOR_CT_GROUP "ctmonitor"

#define NAME "user"

void MM();
void MMT();
void MMA();

void MC();
void MCT();

double logfromdb(Con2DB &db1, std::string StreamName1, std::string StreamName2, bool fromMean, int id);
double logfromdb_time(Con2DB &db1, std::string StreamName1, std::string StreamName2, bool fromMean, int);
double logfromdb_alert(Con2DB &db1, std::string StreamName, int id);

void log2db(Con2DB &db1, bool outcome, bool isMean, std::string StreamName1, std::string StreamName2, int id);
void log2db_time(Con2DB &db1, bool isMean, std::string StreamName1, std::string StreamName2, int id, double time);
void log2db_alert(Con2DB &db1, bool outcome, std::string StreamName, int id);

#endif
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

#define BLOCK 1000000000

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


void MM();
void MMT();
void MMA();
void MC();
void MCT();

void SendMessage(redisContext *,std::string,std::string);
std::string ReadMessage(redisContext *,std::string);
std::string ReadInfo(redisContext *,std::string);

double logfromdb(Con2DB&,std::string,std::string,bool,int);
double logfromdb_time(Con2DB& db1, std::string streamName1, std::string streamName2, bool fromMean,int);
void log2db(Con2DB&,bool,bool,std::string,std::string,int);
void log2db_time(Con2DB& db1, bool isMean, std::string streamName1, std::string streamName2, int id , double time);
double logfromdb_alert(Con2DB& db1, std::string streamName, int id);
void log2db_alert(Con2DB& db1, bool outcome, std::string streamName, int id);

#endif
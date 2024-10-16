#include "main.hpp"

void MMA()
{
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);
    redisContext *c = redisConnect(IP, PORT);
    std::string streamName;
    double diff;
    int id;
    
    redisCommand(c,"XTRIM %s MAXLEN 0",MONITOR_MA_STREAM);
    redisCommand(c,"XTRIM M1 MAXLEN 0");

    initStreams(c,"INFOSTREAM",MONITOR_MA_GROUP);

    ReadInfo(c,MONITOR_MA_GROUP);

    id = std::stoi(ReadInfo(c,MONITOR_MA_GROUP));

    initStreams(c,MONITOR_MA_STREAM,"monitor");

    while (1)
    {
        streamName = ReadMessage(c,MONITOR_MA_STREAM);
        diff = std::stod(ReadMessage(c,MONITOR_MA_STREAM));
    
        log2db_alert(std::ref(db),(logfromdb_alert(std::ref(db),streamName,id) == diff)?true:false,streamName,id);

        SendMessage(c,"1","M5");

    }

    return;
}
#include "main.hpp"

void MMT()
{
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);
    redisContext *c = redisConnect(IP, PORT);
    std::string streamName;
    int id;
    
    redisCommand(c,"XTRIM %s MAXLEN 0",MONITOR_MT_STREAM);
    redisCommand(c,"XTRIM M2 MAXLEN 0");

    initStreams(c,"INFOSTREAM",MONITOR_MT_GROUP);

    ReadInfo(c,MONITOR_MT_GROUP);

    id = std::stoi(ReadInfo(c,MONITOR_MT_GROUP));

    initStreams(c,MONITOR_MT_STREAM,"monitor");

    while (1)
    {

        streamName = ReadMessage(c,MONITOR_MT_STREAM);

        std::cout << streamName;

        log2db_time(std::ref(db),true,streamName,"",id,logfromdb_time(std::ref(db),streamName,"",true));

        SendMessage(c,"1","M2");

    }

    return;
}
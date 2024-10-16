#include "main.hpp"

void MCT()
{
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);
    redisContext *c = redisConnect(IP, PORT);
    std::string streamName1,streamName2;
    int id;
    
    redisCommand(c,"XTRIM %s MAXLEN 0",MONITOR_CT_STREAM);
    redisCommand(c,"XTRIM M4 MAXLEN 0");

    initStreams(c,"INFOSTREAM",MONITOR_CT_GROUP);

    ReadInfo(c,MONITOR_CT_GROUP);

    id = std::stoi(ReadInfo(c,MONITOR_CT_GROUP));

    initStreams(c,MONITOR_CT_STREAM,"monitor");

    while (1)
    {

        streamName1 = ReadMessage(c,MONITOR_CT_STREAM);
        streamName2 = ReadMessage(c,MONITOR_CT_STREAM);

        log2db_time(std::ref(db),false,streamName1,streamName2,id,logfromdb_time(std::ref(db),streamName1,streamName2,false,id));

        SendMessage(c,"1","M4");

    }

    return;
}
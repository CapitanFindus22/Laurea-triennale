#include "main.hpp"

void MM()
{  
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);
    redisContext *c = redisConnect(IP, PORT);
    std::string streamName;
    double mean;
    int id;
    
    redisCommand(c,"XTRIM %s MAXLEN 0",MONITOR_M_STREAM);
    redisCommand(c,"XTRIM M1 MAXLEN 0");

    initStreams(c,"INFOSTREAM",MONITOR_M_GROUP);

    ReadInfo(c,MONITOR_M_GROUP);

    id = std::stoi(ReadInfo(c,MONITOR_M_GROUP));

    initStreams(c,MONITOR_M_STREAM,"monitor");

    while (1)
    {
        mean = std::stod(ReadMessage(c,MONITOR_M_STREAM));

        streamName = ReadMessage(c,MONITOR_M_STREAM);

    
        log2db(std::ref(db),(logfromdb(std::ref(db),streamName,"",true) == mean)?true:false,true,streamName,"",id);


        SendMessage(c,"1","M1");

    }

    return;
}
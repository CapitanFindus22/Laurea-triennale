#include "main.hpp"

void MM()
{  
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);
    redisContext *c = redisConnect(IP, PORT);
    std::string streamName;
    int id;
    
    initStreams(c,"INFOSTREAM","mmonitor");

    ReadInfo(c,"mmonitor");

    id = std::stoi(ReadInfo(c,"mmonitor"));

    initStreams(c,"MMonitor","monitor");

    while (1)
    {
        auto [mean,streamName] = Split(ReadMessage(c,"MMonitor"));

        if(logfromdb(std::ref(db),streamName,"",true) == mean)
        {
            log2db(std::ref(db),true,true,streamName,"",id);
        }

        else

        {
            log2db(std::ref(db),false,true,streamName,"",id);
        }

    }

    return;
}
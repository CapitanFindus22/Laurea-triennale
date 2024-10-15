#include "main.hpp"

void MC()
{
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);
    redisContext *c = redisConnect(IP, PORT);
    std::string streamName1,streamName2;
    double covariance;
    int id;
    
    redisCommand(c,"XTRIM %s MAXLEN 0",MONITOR_C_STREAM);
    redisCommand(c,"XTRIM M3 MAXLEN 0");

    initStreams(c,"INFOSTREAM",MONITOR_C_GROUP);

    ReadInfo(c,MONITOR_C_GROUP);

    id = std::stoi(ReadInfo(c,MONITOR_C_GROUP));

    initStreams(c,MONITOR_C_STREAM,"monitor");

    while (1)
    {

        covariance = std::stod(ReadMessage(c,MONITOR_C_STREAM));
        streamName1 = ReadMessage(c,MONITOR_C_STREAM);
        streamName2 = ReadMessage(c,MONITOR_C_STREAM);

        std::cout << covariance << " - " << streamName1 << " - " << streamName2 << std::endl;

        log2db(std::ref(db),(logfromdb(std::ref(db),streamName1,streamName2,false) == covariance)?true:false,false,streamName1,streamName2,id);

        SendMessage(c,"1","M3");

    }

    return;
}
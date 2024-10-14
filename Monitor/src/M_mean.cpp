#include "main.hpp"

void MM()
{  
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);
    redisContext *c = redisConnect(IP, PORT);
    redisReply *r;
    std::string str;
    
    while (1)
    {
        str = ReadMessage(c,"MMonitor");


    }
    

    return;
}